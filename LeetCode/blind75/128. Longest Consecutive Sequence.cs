public class Solution {
    public int LongestConsecutive(int[] nums) {
        HashSet<int> numSet = new(nums);

        int longestSequence = 0;

        foreach(int num in numSet) {
            int parentSequence = num - 1;
            if(!numSet.Contains(parentSequence)) {
                // if the value is the beginning of a sequence
                int length = 1;
                while (numSet.Contains(num + length)) {
                    // while the next value in the sequence is there
                    ++length;
                }
                longestSequence = Math.Max(length, longestSequence);
            }
        }
        return longestSequence;
    }
}
