class Solution {
    public int[] runningSum(int[] nums) 
    {
        int running_sum = 0;
        for(int i = 0; i < nums.length; ++i)
        {
            running_sum += nums[i];
            nums[i] = running_sum;
        }    
        return nums;
    }
}
