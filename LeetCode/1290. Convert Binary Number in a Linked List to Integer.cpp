/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
public:
    int getDecimalValue(ListNode* head) {
        int res{};
        while(head) {
            res = (res << 1) + head->val;
            head = head->next;
        }
        return res;
    }
};

/*
* So, we're shifting the bit left each time
*
*  - even tho we're reading left -> right, ( despite binary being read from right -> left )
*   - we're shifting each of them to the left each time
*     - so for ex. 10010
*     - we read 1 
*      -> when we read the first 0,we shift left, so we get 10, we push the value to the left most side
*      -> whenever you << 1 or *2 in binary, you shift left 
*
*/
