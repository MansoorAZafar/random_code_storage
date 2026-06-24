public class Solution {
    public int RemoveElement(int[] nums, int val) 
    {
        int l = 0;
        for(int r = 0; r < nums.Length; ++r)
            if(nums[r] != val) nums[l++] = nums[r];
        return l;
    }
}
