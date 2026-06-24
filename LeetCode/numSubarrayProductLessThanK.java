class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) 
    {
        if(k <= 1) return 0;
        int cnt = 0;
        int left = 0;
        int runningProduct = 1;
        for(int right = 0; right < nums.length; ++right) {
            runningProduct *= nums[right]; // add the current val to the window
            
            //if window is too big, we want to cut of leftmost value
            while(runningProduct >= k) runningProduct /= nums[left++];
            cnt += 1 + (right - left);
        }
        return cnt;
    }
}
