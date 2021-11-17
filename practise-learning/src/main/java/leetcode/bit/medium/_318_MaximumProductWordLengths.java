package leetcode.bit.medium;

import java.util.ArrayList;
import java.util.List;

public class _318_MaximumProductWordLengths {

    public static void main(String[] args) {
        String[] ws1 = new String[]{"abcw", "baz", "foo", "bar", "xtfn", "abcdef"};
        System.out.println(maxProduct(ws1));
        String[] ws2 = new String[]{"a", "ab", "abc", "d", "cd", "bcd", "abcd"};
        System.out.println(maxProduct(ws2));
        String[] ws3 = new String[]{"a", "aa", "aaa", "aaaa"};
        System.out.println(maxProduct(ws3));
    }

    public static int maxProduct(String[] words) {
        // 将每个word转化为26位长的二进制串
        // 对应字母出现过则对应二进制串位置1，重复出现也置1
        int n = words.length;
        List<int[]> bword = new ArrayList<>();
        for (String word : words) {
            int[] bits = new int[26];
            for (char c : word.toCharArray()) {
                bits[c - 'a'] = 1;
            }
            bword.add(bits);
        }
        // O(n^2)嵌套遍历bword
        // 将两个单词的二进制串做与运算，如果出现了1，则说明有重复的字母出现
        int res = 0;
        for (int i = 0; i < n - 1; i++) {
            int[] pre = bword.get(i);
            for (int j = i + 1; j < n; j++) {
                int[] back = bword.get(j);
                boolean f = true;
                for (int k = 0; k < 26; k++) {
                    // 按位做与运算
                    if ((pre[k] & back[k]) == 1) {
                        f = false;
                        break;
                    }
                }
                if (f) {
                    res = Math.max(res, words[i].length() * words[j].length());
                }
            }
        }
        return res;
    }
}
