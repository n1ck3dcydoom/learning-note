package algorithm.leetcode.primeAlgorithm.day6;

import java.util.HashMap;

public class _3_LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        String s = "dvfd";
        System.out.println(lengthOfLongestSubstring(s));
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || "".equals(s)) {
            return 0;
        }
        // 滑动窗口
        int res = 0;
        char[] chars = s.toCharArray();
        // 使用map保存字符出现的位置，重复出现后更新最后一次出现的位置
        HashMap<Character, Integer> map = new HashMap<>();
        // 左端点
        int left = 0;
        for (int right = 0; right < chars.length; right++) {
            // 如果出现了重复字符
            if (map.containsKey(chars[right])) {
                // 更新left到第一次出现重复字符的左边第一个位置
                // 有可能第一次出现重复字符的位置比当前的left还要小，那么不更新left
                // 01234
                // abcab 
                // 当right=3时，出现重复字符a，第一次出现a的位置在0，此时left=0，更新left = map.get(chars[right])+1 = 0 + 1 = 1
                // 01234
                // abbac
                // 当right=2时，出现重复字符b，第一次出现b的位置在1，此时left=0，更新left = map.get(chars[right]+1) = 1 + 1 = 2
                // 当right=3时，出现重复字符a，第一次出现a的位置在0，此时left=2，不再更新left
                left = Math.max(left, map.get(chars[right]) + 1);
            }
            // 每次都要更新字符出现的位置
            map.put(chars[right], right);
            // 计算当前长度，窗口为左闭右闭
            res = Math.max(res, right - left + 1);
        }
        return res;
    }
}
