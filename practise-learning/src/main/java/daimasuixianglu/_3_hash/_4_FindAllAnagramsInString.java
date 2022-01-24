package daimasuixianglu._3_hash;

import java.util.ArrayList;
import java.util.List;

public class _4_FindAllAnagramsInString {

    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";
        List<Integer> res = findAnagrams(s, p);
        for (int i : res) {
            System.out.print(i + " ");
        }
    }

    public static List<Integer> findAnagrams(String s, String p) {
        // 滑动窗口+hash计数

        // 滑动窗口大小
        int len = p.length();
        // 目标串hash词频
        int[] phash = new int[26];
        for (char c : p.toCharArray()) {
            phash[c - 'a']++;
        }
        // 窗口hash词频
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i <= s.length() - len; i++) {
            // 统计当前窗口词频
            int[] whash = statistics(s, i, i + len - 1);
            if (compare(whash, phash)) {
                res.add(i);
            }
        }
        return res;
    }

    private static boolean compare(int[] c1, int[] c2) {
        for (int i = 0; i < c1.length; i++) {
            if (c1[i] != c2[i]) {
                return false;
            }
        }
        return true;
    }

    private static int[] statistics(String str, int s, int d) {
        int[] res = new int[26];
        char[] cs = str.toCharArray();
        for (int i = s; i <= d; i++) {
            res[cs[i] - 'a']++;
        }
        return res;
    }
}
