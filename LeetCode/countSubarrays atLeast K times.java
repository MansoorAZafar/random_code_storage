class Solution {
    public long countSubarrays(int[] nums, int k) 
    {
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        int l = 0, count = 0;
        long ans = 0;
        for(int r = 0; r < nums.length; ++r) {
            if(nums[r] == max) ++count;
            while(count >= k) {
                if(nums[l++] == max) --count;
                ans += nums.length - r;
            }
        }
        return ans;
    }
}
