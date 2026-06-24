pdfs = [] 
with open("pdfs.txt", "r") as file: 
    pdfs = [pdf for pdf in file] 


from pypdf import PdfWriter

writer = PdfWriter()

for pdf in pdfs:
    writer.append(pdf.strip())

writer.write("merged.pdf")
writer.close()