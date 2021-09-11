package algorithm.leetcode.primeDataStructure.day6;

public class _383_RansomNote {

    public static void main(String[] args) {
        System.out.println(canConstruct("a", "b"));
        System.out.println(canConstruct("aa", "ab"));
        System.out.println(canConstruct("aa", "aab"));
    }

    public static boolean canConstruct(String ransomNote, String magazine) {
        int[] index1 = new int[26];
        int[] index2 = new int[26];
        for (int i = 0; i < ransomNote.length(); i++) {
            index1[ransomNote.charAt(i) - 'a']++;
        }
        for (int i = 0; i < magazine.length(); i++) {
            index2[magazine.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (index1[i] > index2[i]) {
                return false;
            }
        }
        return true;
    }
}
