# java-bracket-syntax 📝

Welcome to the **java-bracket-syntax** extension! This tool enhances your Java coding experience by allowing you to use the `[]` operator on strings. Upon saving your file, the extension automatically replaces the syntax with `.charAt(index)` for efficient character access. 

To use it, just save your file twice, CTRL+S -> CTRL+S then it'll be fixed!

![2024-11-05 12-45-17](https://github.com/user-attachments/assets/e5d0dc38-b6b1-4810-8773-e2c25109f927)


## Features ✨

- **Auto Replacement**: Automatically converts string bracket notation to `.charAt(index)` on save.
- **Enhanced Readability**: Simplifies accessing characters in strings, making your code cleaner and more intuitive.
- **Support for Multi-Dimensional Arrays**: Works seamlessly with multi-dimensional arrays, enhancing flexibility in your coding.

## Requirements 🔧

- Visual Studio Code version 1.60 or higher.
- Java development environment set up (JDK 8 or later recommended).

## Extension Settings ⚙️

This extension contributes the following settings:

- `javaBracketSyntax.enable`: Enable or disable the auto-replacement feature.

## Known Issues ⚠️

- Ensure your strings are well-formed; the extension may not work with malformed syntax.
- Currently, does not support non-standard string access methods.
- It'll be marked with a red underline as bad syntax
- doesn't work with spaced out syntax (ex. str       [index])
- Have to save twice

## Release Notes 🗒️

### 1.0.0

- Initial release of the java-bracket-syntax extension.

### 1.0.1

- Fixed issue with handling multiple brackets.

### 1.1.0

- Improved performance for large files.

---

## Following Extension Guidelines 📜

Make sure to follow best practices when creating extensions. 

* [Extension Guidelines](https://code.visualstudio.com/api/references/extension-guidelines)

## For More Information 🌐

* [Visual Studio Code's Markdown Support](http://code.visualstudio.com/docs/languages/markdown)
* [Markdown Syntax Reference](https://help.github.com/articles/markdown-basics/)

**Enjoy coding with java-bracket-syntax!**
