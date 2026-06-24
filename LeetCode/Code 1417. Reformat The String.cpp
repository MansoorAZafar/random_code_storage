#include <cctype>
#include <cmath>
#include <vector>

class Solution {
public:
    string reformat(string s) {
        std::vector<char> letters; 
        std::vector<char> nums{};

        for(const char& ch : s) {
            if(isalpha(ch)) {
                letters.push_back(ch);
            } else {
                nums.push_back(ch);
            }
        }

        if(std::abs<int>((letters.size() - nums.size())) > 1) return "";

        std::string res{};
        bool letterFirst = letters.size() > nums.size();

        while(!letters.empty() || !nums.empty()) {
            if(letterFirst && !letters.empty()) {
                res += letters.back();
                letters.pop_back();
            } else if(!letterFirst && !nums.empty()) {
                res += nums.back();
                nums.pop_back();
            }
            letterFirst = !letterFirst;
        }

        return res;
    }
};
