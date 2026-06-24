class Solution {
    private void swap(int[] nums, int i, int j)
    {
        nums[i] += nums[j];
        nums[j] = nums[i] - nums[j];
        nums[i] -= nums[j];
    }
    
    public List<Integer> findDuplicates(int[] nums) 
    {
        ArrayList<Integer> res = new ArrayList<>();
        int i = 0;
        int n = nums.length;
        while(i < n)
        {
            int correct = nums[i] - 1;
            if(nums[i] != nums[correct]) swap(nums, i, correct);
            else ++i;
        }
        for(int j = 0; j < n; ++j) if(nums[j] != j + 1) res.add(nums[j]);

        return res;
    }
}
