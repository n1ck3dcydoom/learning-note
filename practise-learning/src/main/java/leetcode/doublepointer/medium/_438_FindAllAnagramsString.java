package leetcode.doublepointer.medium;

import java.util.*;

/**
 * Created by n1ck3dcydoom
 * Date: 2021/11/29
 * Time: 23:25
 * Description: TODO
 */
public class _438_FindAllAnagramsString {

    public static void main(String[] args) {
        String s1 = "cbaebabacd";
        String p1 = "abc";

        String s2 = "abab";
        String p2 = "ab";

        List<Integer> l1 = findAnagrams1(s1, p1);
        for (int i : l1) {
            System.out.print(i + " ");
        }
        System.out.println("\n");

        List<Integer> l2 = findAnagrams(s2, p2);
        for (int i : l2) {
            System.out.print(i + " ");
        }
        System.out.println("\n");
    }

    public static List<Integer> findAnagrams(String s, String p) {
        // 双指针遍历
        // hash判断
        if (s.length() < p.length()) {
            return Collections.emptyList();
        }

        Map<Character, Integer> phash = new HashMap<>();
        for (char c : p.toCharArray()) {
            phash.put(c, phash.getOrDefault(c, 0) + 1);
        }
        Map<Character, Integer> subhash = new HashMap<>();

        int m = s.length();
        int n = p.length();
        int r = n - 1;
        List<Integer> res = new ArrayList<>();
        while (r < m) {
            subhash.clear();
            String sub = s.substring(r - n + 1, r + 1);
            for (char c : sub.toCharArray()) {
                subhash.put(c, subhash.getOrDefault(c, 0) + 1);
            }
            boolean equal = true;
            for (Map.Entry<Character, Integer> entry : subhash.entrySet()) {
                if (!phash.containsKey(entry.getKey())) {
                    equal = false;
                    break;
                }
                if (phash.get(entry.getKey()).intValue() != entry.getValue()) {
                    equal = false;
                    break;
                }
            }
            if (equal) {
                res.add(r - n + 1);
            }
            r++;
        }
        return res;
    }

    public static List<Integer> findAnagrams1(String s, String p) {
        // 不用每次重新构建subhash
        // 使用int[26]代替hash
        if (s.length() < p.length()) {
            return Collections.emptyList();
        }

        List<Integer> res = new ArrayList<>();
        // 初始化phash
        int[] shash = new int[26];
        int[] phash = new int[26];
        for (int i = 0; i < p.length(); i++) {
            phash[p.charAt(i) - 'a']++;
            shash[s.charAt(i) - 'a']++;
        }
        if (equals(phash, shash)) {
            res.add(0);
        }
        int l = p.length();
        for (int index = 0; index + l - 1 < s.length(); index++) {
            // 每次窗口右移1个单位时
            // 右端点加入的字符更新shash
            shash[s.charAt(index + l) - 'a']++;
            // 左端点移除的字符页需要更新shash
            shash[s.charAt(index) - 'a']--;
            if (equals(phash, shash)) {
                res.add(index + 1);
            }
        }
        return res;
    }

    private static boolean equals(int[] s, int[] p) {
        for (int i = 0; i < s.length; i++) {
            if (s[i] != p[i]) {
                return false;
            }
        }
        return true;
    }
}
