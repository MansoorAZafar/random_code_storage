"""
Docstring for img-search.ai.ai_setup
- To create/update a pdf do
    1. documents = load_documents()
    2. chunks = split_documents(documents)
    3. add_to_chroma(chunks)

- To query, follow these steps
    1. embedding_function = get_embedding_function()
    2. db = get_db_instance()
    3. results = db.similarity_search_with_score(query, k=3)
        - k = how many similar documents to return
    4. context_text = "\n\n---\n\n".join([doc.page_content for doc, _score in results])
    5. prompt_template = ChatPromptTemplate.from_template(PROMPT_TEMPLATE)
    6. prompt = prompt_template.format(context=context_text, question=query_text)

    7. model= 
"""
### Langchain imports ###
from langchain_community.document_loaders.pdf import PyPDFDirectoryLoader
from langchain_text_splitters import RecursiveCharacterTextSplitter
from langchain_community.embeddings.ollama import OllamaEmbeddings
from langchain_classic.schema.document import Document
from langchain_classic.prompts import ChatPromptTemplate
from langchain_community.llms.ollama import Ollama

### Chroma imports ###
from langchain_classic.vectorstores.chroma import Chroma

### Constants ###
DATA_PATH="data";
CHROMA_PATH='./chroma_db'
OLLAMA_EMBEDDED_MODEL='nomic-embed-text'
OLLAMA_MODEL='phi3:mini'
PROMPT_TEMPLATE = """
Answer the question based only on the following context:

{context}

---

Answer the question based on the above context: {question}
"""

### functions ###
def load_documents():
    document_loader = PyPDFDirectoryLoader(DATA_PATH);
    return document_loader.load();


def split_documents(documents: list[Document]):
    text_splitter = RecursiveCharacterTextSplitter(
        chunk_size=800,
        chunk_overlap=80,
        length_function=len,
        is_separator_regex=False
    ); 

    return text_splitter.split_documents(documents)


def get_embedding_function():
    embedding = OllamaEmbeddings(model=OLLAMA_EMBEDDED_MODEL);
    return embedding


def setup_db() -> Chroma:
    embedding_function = get_embedding_function()
    db = Chroma(persist_directory=CHROMA_PATH, embedding_function=embedding_function)

    return db;


def prompt(query: str) -> str:
    db = setup_db()
    results = db.similarity_search_with_score(query=query, k=3)

    context_text = "\n\n---\n\n".join([doc.page_content for doc, _score in results])
    prompt_template = ChatPromptTemplate.from_template(PROMPT_TEMPLATE)
    prompt = prompt_template.format(context=context_text, question=query)

    model: Ollama = Ollama(model=OLLAMA_MODEL)
    response_text = model.invoke(prompt)

    sources = [doc.metadata.get('id', None) for doc, _score in results]
    formatted_response = f"Response: {response_text}\nSources: {sources}"

    return formatted_response

def prompt_db(db: Chroma, query: str) -> tuple:
    results = db.similarity_search_with_score(query=query, k=3)

    context_text = "\n\n---\n\n".join([doc.page_content for doc, _score in results])
    prompt_template = ChatPromptTemplate.from_template(PROMPT_TEMPLATE)
    prompt = prompt_template.format(context=context_text, question=query)

    model: Ollama = Ollama(model=OLLAMA_MODEL)
    response_text = model.invoke(prompt)

    sources = [doc.metadata.get('id', None) for doc, _score in results]
    formatted_response = f"Response:\n\n {response_text}\n\n\nSources: {sources}"

    return (response_text, sources)


def add_to_chroma(chunks: list[Document]):
    db = Chroma(
        persist_directory=CHROMA_PATH,
        embedding_function=get_embedding_function()
    )

    chunks_with_id = calculate_chunk_ids(chunks)
    items = db.get(include=[])
    existing_ids = set(items["ids"])

    new_chunks = []
    for chunk in chunks_with_id:
        if chunk.metadata["id"] not in existing_ids:
            new_chunks.append(chunk)

    if len(new_chunks):
        new_chunks_ids = [chunk.metadata["id"] for chunk in new_chunks]        
        db.add_documents(new_chunks, ids=new_chunks_ids)
        db.persist()


def calculate_chunk_ids(chunks: list[Document]):
    last_page_id = None
    current_page_index = 0

    for chunk in chunks:
        source = chunk.metadata.get('source')
        page = chunk.metadata.get('page')
        current_page_id = f"{source}:{page}"

        if current_page_id == last_page_id:
            current_page_index += 1
        else:
            current_page_index = 0
        
        chunk_id = f"{current_page_id}:{current_page_index}"
        last_page_id = current_page_id

        chunk.metadata['id'] = chunk_id
    
    return chunks


class Database:
    def __init__(self, initFnaf=True):
        self.database = setup_db()
        
        if initFnaf:
            documents = load_documents()
            chunks = split_documents(documents);
            
            add_to_chroma(chunks)
    
    async def prompt(self, query: str) -> tuple:
        return prompt_db(self.database, query)