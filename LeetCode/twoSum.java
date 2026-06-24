class Solution {
    public int[] twoSum(int[] nums, int target) 
    {
        HashMap<Integer, Integer> cache = new HashMap<>();

        for(int i = 0; i < nums.length; ++i)
        {
            int key = target - nums[i];
            if(cache.containsKey(key))
                return new int[] {i, cache.get(key)};
            
            cache.put(nums[i], i);
        } 
        return new int[0];   
    }
}
