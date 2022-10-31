package leetcode.array.easy;

/**
 * Created by n!Ck
 * Date: 2022/11/1
 * Time: 00:12
 * Description:
 */

public class _1662_CheckIfTwoStringArraysAreEquivalent {

    public static void main(String[] args) {
        String[] w1 = new String[]{"abc", "d", "defg"};
        String[] w2 = new String[]{"abcddef"};
        System.out.println(arrayStringsAreEqual(w1, w2));
    }

    public static boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        // 不拼接所有字符串,直接遍历比较,中途遇到不相等可以提前返回,节约空间
        // 记录 w1 第 i 个字符串,w2 第 j 个字符串
        int i = 0, j = 0;
        // 记录 w1 第 i 个字符串第 p 个位置,记录 w2 第 j 个字符串第 q 个位置
        int p = 0, q = 0;
        while (i < word1.length && j < word2.length) {
            // 中途有个位置不相等,则直接返回
            if (word1[i].charAt(p) != word2[j].charAt(q)) {
                return false;
            }
            // w1 第 i 个字符串的位置 p 已经完成比较,后移
            p++;
            // 需要判断 p 是否达到 w1[i] 的末尾
            if (p == word1[i].length()) {
                // 比较下一个字符串 w[i+1]
                i++;
                // 重置 p 在下一个字符串 w[i+1] 的位置
                p = 0;
            }

            // 同理 w2 也需要做相同操作
            q++;
            if (q == word2[j].length()) {
                j++;
                q = 0;
            }
        }
        // 判断 w1[i] 和 w2[j] 有没有遍历完所有子字符串
        return word1.length == i && word2.length == j;
    }
}
