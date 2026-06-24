class Solution {
    public boolean isIsomorphic(String s, String t) {
        int[] sMap = new int[127];
        int[] tMap = new int[127];

        for(int i = 0; i < s.length(); ++i) {
            final char sChar = s.charAt(i);
            final char tChar = t.charAt(i);
            if(sMap[sChar] != tMap[tChar]) return false;

            sMap[sChar] = i + 1;
            tMap[tChar] = i + 1;
        }

        return true;
    }
}
