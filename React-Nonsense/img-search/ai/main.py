from ai_setup import Database

### FastAPI imports ###
from fastapi import FastAPI;
from fastapi.middleware.cors import CORSMiddleware

app = FastAPI();
db = Database();

origins = [
    "http://localhost:5173",
    "http://localhost:11434"
]

app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,       
    allow_credentials=True,       
    allow_methods=["*"],           
    allow_headers=["*"],            
)

@app.get('/')
async def root():
    response = await db.prompt("Tell me about Foxy The Pirate Fox")

    print('response is generated');
    return {"data": response}

@app.get('/ask/{question}')
async def ask_question(question: str):
    response = await db.prompt(question)
    return {"response": response[0], "sources": response[1]};