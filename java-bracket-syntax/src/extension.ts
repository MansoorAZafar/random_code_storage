import * as vscode from 'vscode';
import { GetNumberOfBrackets, GetVariableNameFromEndOfText, ReplaceWithGetIfApplicable, GetVaribaleNameFromStartOfText } from '../src/transformers/bracket_to_get';

export function activate(context: vscode.ExtensionContext) {
    // Add event listener for document saves
    context.subscriptions.push(vscode.workspace.onDidSaveTextDocument((document) => {
        if (document.languageId === 'java') {
            transformJavaDocument(document);
        }
    }));
}

function transformJavaDocument(document: vscode.TextDocument) {
    try {
        const text = document.getText();

        // Regex for `String` declarations with optional array brackets and variable names
        const stringDeclarationRegex = /String(\s*\[\s*\])*\s+[A-Za-z1-9]+/g;
        
        // Regex for array accesses like x[123] or x[0]
        const arrayAccessRegex = /\b[A-Za-z_][A-Za-z0-9_]*\[\s*\d+\s*\](\[\s*\d+\s*\])*/g;

        let matches = [];
        let stringDecArray: any[] = [];
        stringDecArray.push(...text.matchAll(stringDeclarationRegex));

        let arrayAccessArray: any[] = [];
        arrayAccessArray.push(...text.matchAll(arrayAccessRegex));

		console.log("arrayAccess: ", arrayAccessArray)

        matches.push(...stringDecArray);
        matches.push(...arrayAccessArray);

        const edits: { range: vscode.Range; newText: string }[] = [];
        let dict: any = {};

        // Collect variable names and number of brackets from string declarations
        for (const match of stringDecArray) {
            const n_brackets = GetNumberOfBrackets(match[0]);
            const var_name = GetVariableNameFromEndOfText(match[0]);
            dict[var_name] = n_brackets;
        }

        // Process array access matches
        for (const match of arrayAccessArray) {
            const var_name = GetVaribaleNameFromStartOfText(match[0]);
            const replaced_text: string = ReplaceWithGetIfApplicable(match[0], dict[var_name]) ?? "";

            const startPos = document.positionAt(match.index);
            const endPos = document.positionAt(match.index + match[0].length);
            const range = new vscode.Range(startPos, endPos);
            edits.push({ range, newText: replaced_text });
        }

        // Apply the edits to the document
        const editBuilder = new vscode.WorkspaceEdit();
        for (const edit of edits) {
            editBuilder.replace(document.uri, edit.range, edit.newText);
        }
        vscode.workspace.applyEdit(editBuilder);
    } catch (err) {
        console.log('ERROR: ', err);
    }
}

export function deactivate() {}
