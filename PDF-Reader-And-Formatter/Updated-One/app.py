import customtkinter as ct
import camelot
# import tkinter as tk
import re
import subprocess

def show_popup(frame, message, color):
    """Show a popup bar with a message and specified color."""
    popup = ct.CTkLabel(
        master=frame,
        text=message,
        fg_color=color,
        text_color="black",  # Text color, assuming the background is always green or red
        font=("Arial", 16, "bold"),  # Make the font larger and bold
        corner_radius=10,  # Rounded corners for a nicer look
        padx=20,  # Padding to give more space around the text
        pady=10
    )
    popup.pack(pady=12, padx=10)
    
    # Hide the popup after 5 seconds
    frame.after(5000, popup.destroy)

def extract_pdf(start, end, pdf_path, password="", frame=None):
    print('Extracting PDF using Camelot')
    
    try:
        # Use Camelot to extract tables from the PDF
        tables = camelot.read_pdf(pdf_path,
                                   pages=f"{start}-{end}", 
                                  flavor='stream', 
                                  password=password)

        if tables:
            with open("input.csv", "w", encoding="utf-8") as file:
                for table in tables:
                    # Convert the table to a CSV format and write it to a file
                    file.write(table.df.to_csv(index=False, sep='|'))
                    file.write("\n")
            print("Successfully wrote tables to input.csv")
        else:
            print("No tables found in the specified pages.")
            show_popup(frame, "No tables found", "red")
        
        completed = compile_and_execute('HouseOrderer')
        if completed:
            show_popup(frame, "Completed", "green")
        else:
            show_popup(frame, "Error", "red")
    except Exception as e:
        print(f"Error extracting PDF: {e}")
        show_popup(frame, str(e), "red")


def compile_and_execute(program_file: str) -> bool:
    try:
        # Execute the compiled program
        execute_command = ["./HouseOrderer"]  # Adjust this based on your OS
        result = subprocess.run(execute_command, check=True)
        print(f"HouseOrderer program executed successfully with return code: {result.returncode}")
        return True
    except subprocess.CalledProcessError as e:
        print(f"Error compiling or executing {program_file}: {e}")
        return False


def get_PDF(start = 30, end = 39, password="", frame=None):
    file_path = ct.filedialog.askopenfilename(
        filetypes=[("PDF Files", "*.pdf")], title="Select a PDF"
    )
    
    if not file_path:
        return
    
    print(f"PDF file selected: {file_path}")
    extract_pdf(int(start), int(end), file_path, password, frame)


def build_gui(frame, func):
    label = ct.CTkLabel(master=frame, text="Upload PDF")
    label.pack(pady=12, padx=10)

    # Frame for "Starting page" row
    start_frame = ct.CTkFrame(master=frame)
    start_frame.pack(pady=10)  # Add some space before the next line

    # "Starting page" label and entry (same row)
    start_label = ct.CTkLabel(master=start_frame, text="Starting page:")
    start_label.pack(side="left", padx=5)
    start_page_entry = ct.CTkEntry(master=start_frame, width=45)
    start_page_entry.insert(0, "31")
    start_page_entry.pack(side="left", padx=5)

    # Frame for "Ending page" row
    end_frame = ct.CTkFrame(master=frame)
    end_frame.pack(pady=10)  # Add some space before the button

    # "Ending page" label and entry (same row)
    end_label = ct.CTkLabel(master=end_frame, text="Ending page:")
    end_label.pack(side="left", padx=5)
    end_page_entry = ct.CTkEntry(master=end_frame, width=45)
    end_page_entry.insert(0, "38")
    end_page_entry.pack(side="left", padx=5)

    # Decryption Password Input
    password_frame = ct.CTkFrame(master=frame)
    password_frame.pack(pady=10)

    password_label = ct.CTkLabel(master=password_frame, text="If there's a Password\nEnter here:")
    password_label.pack(side="left", padx=5)
    
    password_entry = ct.CTkEntry(master=password_frame)
    password_entry.insert(0, "")
    password_entry.pack(side="left", padx=5)

    button = ct.CTkButton(master=frame, 
                          text="Upload PDF", 
                          command=lambda: func(start=start_page_entry.get(), 
                                                end=end_page_entry.get(),
                                                password=password_entry.get(),
                                                frame=frame
                                            )
                        )
    
    button.pack(pady=12, padx=10)


def center_window(window, width, height):
    screen_width = window.winfo_screenwidth()
    screen_height = window.winfo_screenheight()

    x = int((screen_width / 2) - (width / 2))
    y = int((screen_height / 2) - (height / 2))

    window.geometry(f"{width}x{height}+{x+100}+{y}")


if __name__ == "__main__":

    ct.set_appearance_mode("dark")
    ct.set_default_color_theme("dark-blue")

    width = 500
    height = 350

    root = ct.CTk()
    root.title("PDF to Text")
    root.resizable(False,False)
    
    frame = ct.CTkFrame(master=root)
    frame.pack(pady=20, padx=60, fill="both", expand=True)


    center_window(root, width, height)
    build_gui(frame, get_PDF)

    root.mainloop()
    