class Solution {
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> numSet = new HashSet<Integer>();
        
        for(int i = 0; i < nums.length; ++i) {
            numSet.add(nums[i]);
        }
        int sequenceLength = 0;

        for(int value : numSet) {
            final int parentSequence = value - 1;
            if (!numSet.contains(parentSequence)) {
                // this value is the start of a sequence
                int length = 1;

                // while the next value in the sequence is found
                while (numSet.contains((value + length))) {
                    ++length;
                }

                sequenceLength = Math.max(length, sequenceLength);
            }
        }
        return sequenceLength;
    }
}
