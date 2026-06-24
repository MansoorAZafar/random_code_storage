// if you only assume 128 ASCII, then just use an array as the map, 
struct HashSet;
struct SetNode;

#define DEFAULT_HASHSET_SIZE 128

struct HashSet {
    struct SetNode** buckets;
    int capacity;
};

struct SetNode {
    char key;
    struct SetNode* next;
};

int hash(char key, int capacity) {
    int h = key % capacity;
    return h < 0 ? h + capacity : h;
}

struct HashSet* createHashSet(int capacity) {
    struct HashSet* set = (struct HashSet*) malloc (sizeof(struct HashSet));
    set->capacity = capacity;
    set->buckets = (struct SetNode**) malloc(sizeof(struct SetNode*) * capacity);
    
    for(int i = 0; i < capacity; ++i) {
        set->buckets[i] = NULL;
    }
    return set;
} 

void freeHashSet(struct HashSet* set) {
    for(int i = 0; i < set->capacity; ++i) {
        struct SetNode* curr = set->buckets[i];
        while(curr != NULL) {
            struct SetNode* next = curr->next;
            free(curr);
            curr = next;
        }
    }
    free(set->buckets);
    free(set);
}

void insert(struct HashSet* set, char key) {
    int index = hash(key, set->capacity);
    struct SetNode* next = (struct SetNode*) malloc (sizeof(struct SetNode));
    next->key = key;
    next->next = set->buckets[index];

    set->buckets[index] = next;
}

int contains(struct HashSet* set, char key) {
    int index = hash(key, set->capacity);
    struct SetNode* curr = set->buckets[index];

    while(curr != NULL) {
        if(curr->key == key) {
            return 1;
        }
        curr = curr->next;
    }
    return 0;
}

void _remove(struct HashSet* set, char key) {
    int index = hash(key, set->capacity);
    struct SetNode* curr = set->buckets[index];
    struct SetNode* prev = NULL;

    while(curr != NULL) {
        if(curr->key == key) {
            if(prev == NULL) {
                set->buckets[index] = curr->next;
            } else {
                prev->next = curr->next;
            }
            free(curr);
            return;
        }
        prev = curr;
        curr = curr->next;
    }
}


int lengthOfLongestSubstring(char* s) {
    struct HashSet* set = createHashSet(DEFAULT_HASHSET_SIZE);
    int longest = 0;
    int l = 0;
    int r = 0;

    while(*(r + s) != '\0') {
        // keep going through the characters
        const char ch = (*(r+s));
        ++r;

        // printf("%c", ch);
        while(contains(set, ch)) {
            const char chr = (*(l+s));
            printf("%c", chr);
            _remove(set, chr);
            ++l;
        }

        insert(set, ch);

        const int currLen = (r - l);
        longest = currLen > longest ? currLen : longest;
    }

    freeHashSet(set);
    return longest;
}
