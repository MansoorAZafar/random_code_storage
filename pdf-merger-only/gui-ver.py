import tkinter as tk
from tkinter import filedialog, messagebox
from pypdf import PdfWriter


def select_pdfs():
    files = filedialog.askopenfilenames(
        title="Select PDF Files",
        filetypes=[("PDF Files", "*.pdf")]
    )

    listbox.delete(0, tk.END)

    for file in files:
        listbox.insert(tk.END, file)


def merge_pdfs():
    pdfs = listbox.get(0, tk.END)

    if not pdfs:
        messagebox.showerror("Error", "No PDF files selected.")
        return

    output_file = filedialog.asksaveasfilename(
        title="Save Merged PDF",
        defaultextension=".pdf",
        filetypes=[("PDF Files", "*.pdf")]
    )

    if not output_file:
        return

    try:
        merger = PdfWriter()

        for pdf in pdfs:
            merger.append(pdf)

        merger.write(output_file)
        merger.close()

        messagebox.showinfo(
            "Success",
            f"Merged {len(pdfs)} PDFs successfully!"
        )

    except Exception as e:
        messagebox.showerror("Error", str(e))


root = tk.Tk()
root.title("PDF Merger")
root.geometry("600x400")

frame = tk.Frame(root)
frame.pack(fill="both", expand=True, padx=10, pady=10)

listbox = tk.Listbox(frame)
listbox.pack(fill="both", expand=True)

btn_frame = tk.Frame(root)
btn_frame.pack(pady=10)

select_btn = tk.Button(
    btn_frame,
    text="Select PDFs",
    command=select_pdfs
)
select_btn.pack(side="left", padx=5)

merge_btn = tk.Button(
    btn_frame,
    text="Merge PDFs",
    command=merge_pdfs
)
merge_btn.pack(side="left", padx=5)

root.mainloop()