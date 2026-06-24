class Solution {
public:
    int findClosestNumber(vector<int>& nums) 
    {
        int minValue = std::numeric_limits<int>::max();
        for(int i = 0; i < nums.size(); ++i)
        {
            // Check if the current number is closer to zero
            if (std::abs(nums[i]) < std::abs(minValue)) {
                minValue = nums[i];
                continue;
            } 
            // If two numbers have the same distance to zero, prefer the positive one
            if (std::abs(nums[i]) == std::abs(minValue) && nums[i] > minValue)
                minValue = nums[i];
        }
        return minValue;
    }
};
