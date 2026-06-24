int longestConsecutive(int* nums, int numsSize) {
    //un = num of values in the unique array
    if(numsSize == 0) return 0;
    
    const unsigned MAX_S = 4 * numsSize;
    int set[MAX_S], unique[numsSize], uN = 0;
    memset(set, 0x7f, sizeof(set));
    const int INF = set[0];

    #define HASH(n) ((unsigned)(n) % MAX_S)
    #define PROBE(hash, n) { int step = 1; while( set[hash] != INF && set[hash] != n ) hash = (hash + step++) % MAX_S; }

    // get all the unique numbers
    for(int i = 0; i < numsSize; ++i) {
        int hash = HASH(nums[i]);
        PROBE(hash, nums[i]);
        
        if(set[hash] == INF) {
            unique[uN++] = nums[i];
        }
        set[hash] = nums[i];
    }

    int res = 0;
    for(int i = 0; i < uN; ++i) {
        const int parent = unique[i] - 1;

        int hash = HASH(parent);
        PROBE(hash, parent);

        if(set[hash] == INF) {
            // if the value is the parent
            int len = 0;
            
            for (int j = unique[i]; true; j++, len++) {
                hash = HASH(j);
                PROBE(hash, j);
                if (set[hash] == INF) break;
            }
            /*
            Can also use this as the conditon
            do {
                ++len;
                const int n = unique[i] + len;
                hash = HASH(n);
                PROBE(hash, n);
            }while(set[hash] != INF);
                */

            if(len > res) res = len;
        }
    }
    return res;
}
