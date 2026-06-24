class Solution:
    def longestConsecutive(self, nums: List[int]) -> int:
        numSet = set(nums)
        longestSequence = 0

        for num in numSet:
            parentSequence = num - 1
            if parentSequence not in numSet:
                length = 1

                while (num + length) in numSet:
                    length += 1

                longestSequence = max(length, longestSequence)

        return longestSequence
        
