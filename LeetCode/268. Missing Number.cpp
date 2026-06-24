class Solution {
public:
    int missingNumber(vector<int>& nums) {

        int missingNum{0};
        for(int i = 0; i < nums.size(); ++i) {
            missingNum ^= nums[i];
            missingNum ^= i;
        }
        return missingNum ^ nums.size();
    }
};
/*
Note:
  XOR's order doesn't matter
  it's like addition's order
   - it doesn't matter what the order is
    we're 'storing' the cumalitive result 

    imagine
    [3,0,1]
    [0,1,2,3]

    3 and 3 will cancel out
    0 and 0 will cancel out
    1 and 1 will cancel out
*/
