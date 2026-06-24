class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int cnt = 0;
        int l = 0;
        for(int r = 0; r < nums.length; ++r) {
            map.put(nums[r], map.getOrDefault(nums[r], 0) + 1);
            while(map.get(nums[r]) > k && l < nums.length) {
                map.replace(nums[l], map.get(nums[l]) - 1);
                ++l;
            }
            cnt = cnt > (r - l + 1) ? cnt : (r - l + 1);
        }
        return cnt;
    }
}
