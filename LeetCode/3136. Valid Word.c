int isVowel(const char ch) {
    switch(tolower(ch)) {
        case 'a':
        case 'e':
        case 'i':
        case 'o':
        case 'u':
            return 1;
        default:
            return 0;
    }
}

bool isValid(char* word) {
    int idx = 0;
    bool hasVowel = false;
    bool hasConsonant = false;

    while(*(word + idx) != '\0') {
        const char ch = *(word + idx);
        if(!isalnum(ch)) {
            return false;
        }

        int vowel = isVowel(ch);
        if(vowel) {
            hasVowel = true;
        } else if (isalpha(ch)) {
            hasConsonant = true;
        }

        ++idx;
    }
    return idx > 2 && hasVowel && hasConsonant;
}
