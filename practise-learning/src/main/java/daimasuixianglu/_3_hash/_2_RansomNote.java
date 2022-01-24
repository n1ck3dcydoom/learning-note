package daimasuixianglu._3_hash;

public class _2_RansomNote {

    public boolean canConstruct(String ransomNote, String magazine) {
        // hash计数
        int[] rhash = new int[26];
        for (char c : ransomNote.toCharArray()) {
            rhash[c - 'a']++;
        }
        int[] mhash = new int[26];
        for (char c : magazine.toCharArray()) {
            mhash[c - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (mhash[i] != 0) {
                rhash[i] = rhash[i] - mhash[i];
                if (rhash[i] < 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
