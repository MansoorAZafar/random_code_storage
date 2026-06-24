public class Solution {
    public int[] TwoSum(int[] nums, int target) 
    {
        Dictionary<int, int> values = new();

        for(int i = 0; i < nums.Length; ++i)
        {
            int key = target - nums[i]; 
            if(values.ContainsKey(key))
                return new int[] {i, values[key]};

            values[nums[i]] = i;
        }
        return new int[0];
    }
}
