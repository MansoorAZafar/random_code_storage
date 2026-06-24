class Solution {
public:
    int longestConsecutive(vector<int>& nums) {
        // Add the values to a set
        //  - This will help find the first value in a sequence
        std::unordered_set<int> values{nums.begin(), nums.end()};
        int sequenceLength{0};

        for(const int& value : values) {
            const int parentSequence = value - 1;
            if (!values.contains(parentSequence)) {
                // if there is no parent, this is the beginning of a sequence
                int length{1};

                // while the next value in the sequence exists
                //  increase the length
                while (values.contains((value + length))) {
                    ++length;
                }

                sequenceLength = sequenceLength > length ? sequenceLength : length;
            }
        }

        return sequenceLength;
    }
};
