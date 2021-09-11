package algorithm.leetcode.primeDataStructure.day6;

public class _242_ValidAnagram {

    public static void main(String[] args) {
        System.out.println(isAnagram("anagram", "nagaram"));
        System.out.println(isAnagram("rat", "car"));
    }

    public static boolean isAnagram(String s, String t) {
        int[] index1 = new int[26];
        int[] index2 = new int[26];
        for (int i = 0; i < s.length(); i++) {
            index1[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            index2[t.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (index1[i] != index2[i]) {
                return false;
            }
        }
        return true;
    }
}
