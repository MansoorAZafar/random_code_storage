class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        // [value, index]
        std::unordered_map<int, int> map{};

        for(int i = 0; i < nums.size(); ++i) {
            int key = target - nums[i];
            if(map.contains(key)) {
                return {i, map[key]};
            }
            map[nums[i]] = i;
        }
        return {};
    }
};
