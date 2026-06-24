/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */
 #define MAX_LEN 30
 #include <math.h>

int getNumFromBinary(const int* binary, int len) {
    int sum = 0;
    int power = 0;
    for(int i = len - 1; i >= 0; i--) {
        sum += pow(2, power++) * binary[i];
    }
    return sum;
}

int getDecimalValue(struct ListNode* head) {
    
    int binary[MAX_LEN];
    memset(binary, 0x7f, sizeof(int) * MAX_LEN);
    int idx = 0;
    int len = 0;
    while(head != NULL) {
        ++len;
        binary[idx++] = head->val;
        head = head->next;
    }

    return getNumFromBinary(binary, len);
}

// Better Solution in cpp 
