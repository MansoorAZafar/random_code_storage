export function test()
{

}


export function stringbracket_to_get(text: string)
{

}

export function GetNumberOfBrackets(text: string)
{
    const matches = text.match(/\[.*?\]/g)
    return matches ? matches.length : 0
}

export function GetVariableNameFromEndOfText(text: string) {
    const match = text.match(/(?:\[\s*\])*\s+([A-Za-z_]\w*)\s*$/);
    return match ? match[1].trim() : 'NULL';
}

export function GetVaribaleNameFromStartOfText(text: string)
{
    const match = text.match(/^\s*([^\s\[]+)/g)
    return match ? match[0].trim() : 'NULL'
}

function GetBracketIndex(text: string) 
{
    const match = text.match(/\[(\d+)\]/);
    return match ? match[1] : 0;
}

export function ReplaceWithGetIfApplicable(text: string, string_num_of_brackets: number) {
    const var_name = GetVaribaleNameFromStartOfText(text);
    const indexes = [...text.matchAll(/\[(\d+)\]/g)].map(match => match[1]); // Collect all indexes as array

    const actual_brack_intext = indexes.length;
    
    console.log(`var_name is: ${var_name}\nindexes are: ${indexes.join(', ')}\ntext is: ${text}\nstring_num_of_brackets is: ${string_num_of_brackets}\nactual_brack_intext is: ${actual_brack_intext}\n\n`);

    // Check if the number of brackets matches expectations (it should equal `string_num_of_brackets + 1`)
    if (actual_brack_intext !== string_num_of_brackets + 1) return text;

    // Generate the transformed expression
    let result = `${var_name}`;
    for (let i = 0; i < string_num_of_brackets; i++) {
        result += `[${indexes[i]}]`;
    }
    // Use `.charAt(index)` for the last bracket access
    result += `.charAt(${indexes[string_num_of_brackets]})`;

    console.log('Result is: ', result);
    return result;
}