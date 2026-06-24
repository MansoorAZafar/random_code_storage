/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     public int val;
 *     public ListNode next;
 *     public ListNode(int val=0, ListNode next=null) {
 *         this.val = val;
 *         this.next = next;
 *     }
 * }
 */
public class Solution {
    public ListNode RemoveNodes(ListNode head) 
    {
        if(head == null) return head;

        head = this.ReverseList(head);
        
        int max = head.val;
        ListNode current = head;

        while(current.next != null)
        {
            if(current.next.val < max)
                current.next = current.next.next;
            else
            {
                current = current.next;
                max = current.val;
            }
        }

        return this.ReverseList(head);
    }

    private ListNode ReverseList(ListNode head)
    {
        ListNode prev = null;
        ListNode next = null;
        
        while(head != null)
        {
            next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    
}
