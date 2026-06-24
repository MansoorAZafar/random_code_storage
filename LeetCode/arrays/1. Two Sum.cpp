class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        // number, index
        std::unordered_map<int, int> map{};
        for(int i = 0; i < nums.size(); ++i) {
            int key = target - nums[i];
            if(map.contains(key)) {
                // return the index of the key and current index
                return {map[key], i};
            }
            // store the index for the current value
            map[nums[i]] = i;
        }
        return {};
    }
};
