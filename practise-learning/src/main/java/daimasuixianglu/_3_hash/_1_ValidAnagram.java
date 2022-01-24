package daimasuixianglu._3_hash;

public class _1_ValidAnagram {

    public static void main(String[] args) {
        // s = "anagram", t = "nagaram"
        String s = "anagram";
        String t = "nagaram";
        System.out.println(isAnagram(s, t));
    }

    public static boolean isAnagram(String s, String t) {
        // hash计数
        int[] shash = new int[26];
        for (char c : s.toCharArray()) {
            shash[c - 'a']++;
        }
        int[] thash = new int[26];
        for (char c : t.toCharArray()) {
            thash[c - 'a']++;
        }
        // 比较
        for (int i = 0; i < 26; i++) {
            if (shash[i] != thash[i]) {
                return false;
            }
        }
        return true;
    }
}
