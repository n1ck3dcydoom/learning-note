package leetcode.hash.easy;

public class _383_RansomNote {

    public static void main(String[] args) {
        //        System.out.println(canConstruct("a", "b"));
        System.out.println(canConstruct("aa", "ab"));
        System.out.println(canConstruct("aa", "aab"));
    }

    public static boolean canConstruct(String ransomNote, String magazine) {
        // hash计数
        int[] rhash = new int[26];
        int[] mhash = new int[26];

        for (char c : ransomNote.toCharArray()) {
            rhash[c - 'a']++;
        }
        for (char c : magazine.toCharArray()) {
            mhash[c - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (rhash[i] != 0 && mhash[i] < rhash[i]) {
                return false;
            }
        }
        return true;
    }
}
