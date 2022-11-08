package leetcode.array.easy;

/**
 * Created by n!Ck
 * Date: 2022/11/8
 * Time: 22:23
 * Description:
 */

public class _1684_CountTheNumberOfConsistentStrings {

    public int countConsistentStrings(String allowed, String[] words) {
        // // hash 计数
        // Set<Character> set = new HashSet<>();
        // char[] cs = allowed.toCharArray();
        // for (char c : cs) {
        //     set.add(c);
        // }
        //
        // int res = 0;
        // for (String w : words) {
        //     char[] cw = w.toCharArray();
        //     boolean find = true;
        //     for (char c : cw) {
        //         if (!set.contains(c)) {
        //             find = false;
        //             break;
        //         }
        //     }
        //     if (find) {
        //         res++;
        //     }
        // }
        // return res;

        // 26 位数组代替 hash
        boolean[] find = new boolean[26];
        char[] cs = allowed.toCharArray();
        for (char c : cs) {
            find[c - 'a'] = true;
        }

        int res = 0;
        for (String w : words) {
            char[] cw = w.toCharArray();
            boolean flag = true;
            for (char c : cw) {
                if (!find[c - 'a']) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                res++;
            }
        }
        return res;
    }
}
