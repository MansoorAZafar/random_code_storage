/**
 * Note: The returned array must be malloced, assume caller calls free().
 */

#define MAX_HASH_SIZE 10000

struct HashNode;
struct HashMap;

struct HashMap {
    struct HashNode** buckets;
    int capacity;
};

struct HashNode {
    int key;
    int value;
    struct HashNode* next;
};

int Hash(int key, int capacity) {
    int h = key % capacity;
    return h < 0 ? h + capacity : h;
}

struct HashMap* createHashMap(int capacity) {
    struct HashMap* map = (struct HashMap*)malloc(sizeof(struct HashMap));
    map->buckets = (struct HashNode**)malloc(sizeof(struct HashNode*) * capacity);
    map->capacity = capacity;

    for(int i = 0; i < capacity; ++i) {
        map->buckets[i] = NULL;
    }
    return map;
}

void insert(struct HashMap* map, int key, int value) {
    int index = Hash(key, map->capacity);
    struct HashNode* newNode = (struct HashNode*) malloc(sizeof(struct HashNode));
    newNode->next = map->buckets[index];
    newNode->key = key;
    newNode->value = value;

    map->buckets[index] = newNode;
}

int contains(struct HashMap* map, int key) {
    int index = Hash(key, map->capacity);
    struct HashNode* current = map->buckets[index];

    while(current != NULL) {
        if(current->key == key) {
            return 1;
        }
        current = current->next;
    } 

    return 0;
}

int get(struct HashMap* map, int key) {
    int index = Hash(key, map->capacity);
    struct HashNode* current = map->buckets[index];

    while(current != NULL) {
        if(current->key == key) {
            return current->value;
        }
        current = current->next;
    }
    return -1;
}

void cleanUpHashMap(struct HashMap* map) {
    for(int i = 0; i < map->capacity; ++i) { 
        struct HashNode* current = map->buckets[i];
    
        while(current != NULL) {
            struct HashNode* next = current->next;
            free(current);
            current = next;
        }
    }
    free(map->buckets);
    free(map);
}


int* twoSum(int* nums, int numsSize, int target, int* returnSize) {
    struct HashMap* map = createHashMap(numsSize > MAX_HASH_SIZE ? MAX_HASH_SIZE : numsSize);
    int* res = (int*)malloc(sizeof(int) * 2);

    for(int i = 0; i < numsSize; ++i) {
        int key = target - nums[i];
        if(contains(map, key)) {
            res[0] = i;
            res[1] = get(map, key);
            *returnSize = 2;
            cleanUpHashMap(map);
            return res;
        }
        insert(map, nums[i], i);
    }
    cleanUpHashMap(map);
    return NULL;
}

///////////// Alternative Solution
/**
 * Note: The returned array must be malloced, assume caller calls free().
 */

#include <stdlib.h>

struct Node {
    int key;
    int val;
};

int* twoSum(int* nums, int numsSize, int target, int* returnSize) {
    const int MOD = numsSize * 2;
    struct Node** set = malloc(sizeof(struct Node*) * MOD);
    int* res = malloc(2 * sizeof(int));
    *returnSize = 2;

    #define HASH(n) ((unsigned)(n) % MOD)
    #define PROBE(hash, n) { int step = 1; while (set[hash] != NULL && set[hash]->key != (n)) hash = (hash + step++) % MOD; }
    #define CLEAN_UP() { for(int i = 0; i < MOD; ++i) if (set[i] != NULL) free(set[i]); free(set); }
    #define INIT() { for(int i = 0; i < MOD; ++i) set[i] = NULL; }

    INIT();

    for (int i = 0; i < numsSize; ++i) {
        int key = target - nums[i];
        int hash = HASH(key);
        PROBE(hash, key);

        if (set[hash] != NULL) {
            res[0] = set[hash]->val;
            res[1] = i;
            CLEAN_UP();
            return res;
        }

        struct Node* node = malloc(sizeof(struct Node));
        node->key = nums[i];
        node->val = i;

        hash = HASH(nums[i]);
        PROBE(hash, nums[i]);
        set[hash] = node;
    }

    CLEAN_UP();
    free(res);
    *returnSize = 0;
    return NULL;
}

