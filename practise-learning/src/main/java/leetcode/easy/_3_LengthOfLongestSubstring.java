package leetcode.easy;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by n!Ck
 * Datek: 2019-03-03
 * Time: 14:25
 * Description:
 * <p>
 * 3. 无重复字符的最长子串
 * <p>
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */
public class _3_LengthOfLongestSubstring {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();

        HashMap<Character, Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 0, j = 0; j < str.length(); j++) {
            if (map.containsKey(str.charAt(j)) && (map.get(str.charAt(j)) >= i)) {
                i = map.get(str.charAt(j)) + 1;
            }
            max = Math.max(max, j - i + 1);
            map.put(str.charAt(j), j);
        }
        System.out.println(max);
    }
}
