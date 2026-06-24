class Solution {
    public long countSubarrays(int[] nums, int minK, int maxK) 
    {
        long ans = 0;
        int maxi = -1;
        int mini = -1;
        
        for(int r = 0, l = 0; r < nums.length; ++r) {
            if(nums[r] < minK || nums[r] > maxK) {
                l = r+1;
                continue;
            } 
            if (nums[r]==maxK) maxi=r; // position for maxK
            if (nums[r]==minK) mini=r; // position for minK
            ans += Math.max((Math.min(maxi, mini)-l+1),0);
        }
        return ans;
    }
}
