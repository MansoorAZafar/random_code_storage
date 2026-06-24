class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        std::unordered_set<int> set{};
        int res{};
        int l{};

        for(int r = 0; r < s.length(); ++r) {
            while(set.contains(s[r])) {
                set.erase(s[l]);
                ++l;
            }
            set.insert(s[r]);
            res = std::max(res, r-l+1);
        }
        return res;
    }
};
