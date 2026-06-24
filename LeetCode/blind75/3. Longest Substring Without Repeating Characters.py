class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        count = set()
        l = 0

        longest = 0
        for r, c in enumerate(s):
            
            while c in count:
                count.remove(s[l])
                l += 1
            
            count.add(c)
            longest = max(longest, r-l + 1)

        return longest
            

        
