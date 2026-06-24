// Optimal Solution O(n log n)
  // logn for the __builtin_popcount()
// can replace it for get_set_bits() func in bubble sort solution
class Solution {
public:
    bool canSortArray(vector<int>& nums) {
        int prev_segment_max{-1};
        int curr_segment_max{nums[0]};
        int curr_segment_min{nums[0]};
        int set_bit_cnt{__builtin_popcount(nums[0])};

        for(int i = 1; i < nums.size(); ++i){
            if(set_bit_cnt == __builtin_popcount(nums[i])) {
                //Assign the max and minimum
                // max
                curr_segment_max = nums[i] > curr_segment_max ? nums[i] : curr_segment_max;
                // min
                curr_segment_min = nums[i] < curr_segment_min ? nums[i] : curr_segment_min;
            } else {
                if (prev_segment_max > curr_segment_min) return false;

                prev_segment_max = curr_segment_max;
                set_bit_cnt = __builtin_popcount(nums[i]);
                curr_segment_min = nums[i];
                curr_segment_max = nums[i];
            }
        }
        return curr_segment_min >= prev_segment_max;
    }
};

//Bubble Sort Soltuon O(n^2 + logn)
class Solution {
public:
    
    int get_set_bits(int num)
    {
        int cnt{};
        while(num)
        {
            num &= (num-1);
            ++cnt;
        }
        return cnt;
    }

    bool canSortArray(vector<int>& nums) 
    {
        for(int i = 0; i < nums.size() - 1; ++i)
        {
            for(int j = 0; j < nums.size() - 1; ++j)
            {
                int c1 = this->get_set_bits(nums[j]);
                int c2 = this->get_set_bits(nums[j+1]);

                if(c1 == c2 && nums[j] > nums[j+1])
                {
                    int tmp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = tmp;
                }
            }
        }

        for(int i = 0; i < nums.size() - 1; ++i)
            if (nums[i] > nums[i+1])
                return false;

        return true;
    }
};
