package algorithm.leetcode.primeAlgorithm.day6;

import java.util.HashMap;
import java.util.Map;

public class _567_PermutationInString {
    public static void main(String[] args) {
        String s1 = "adc";
        String s2 = "dcda";
        // 枚举法，枚举s1的全排列，在s2中检查是否包含任意一个排列
        System.out.println(checkInclusion(s1, s2));
    }

    public static boolean checkInclusion(String s1, String s2) {
        // 一个排列是否是一个子串，只需要排列的字符出现次数和子串字符出现次数完全相等
        // 只要字符出现次数完全相等，排列的位置可以随意调整为子串的相同顺序

        // 求s1的所有字符出现次数
        HashMap<Character, Integer> map1 = new HashMap<>();
        for (char ch : s1.toCharArray()) {
            map1.put(ch, map1.getOrDefault(ch, 0) + 1);
        }

        HashMap<Character, Integer> map2 = new HashMap<>();
        // 滑动窗口扫描s2，窗口长度固定为s1的长度
        // 初始化第一个窗口的字符map
        for (int i = 0; i < s1.length(); i++) {
            map2.put(s2.toCharArray()[i], map2.getOrDefault(s2.toCharArray()[i], 0) + 1);
        }
        for (int left = 0, right = s1.length() - 1; right < s2.length() - 1; right++) {
            // 左闭右闭的窗口
            // 比较两个map
            if (compareMap(map1, map2)) {
                return true;
            }
            // 不相等则窗口左端点移出，右端点进入
            if (map2.get(s2.toCharArray()[left]) == 1) {
                map2.remove(s2.toCharArray()[left]);
            } else {
                map2.put(s2.toCharArray()[left], map2.get(s2.toCharArray()[left]) - 1);
            }
            left++;
            map2.put(s2.toCharArray()[right + 1], map2.getOrDefault(s2.toCharArray()[right + 1], 0) + 1);
        }
        // 判断一下最后一个窗口是否满足条件
        return compareMap(map1, map2);
    }

    private static boolean compareMap(HashMap<Character, Integer> map1, HashMap<Character, Integer> map2) {
        if (map1 == null || map1.size() == 0) {
            return (map2 == null || map2.size() == 0) ? true : false;
        }
        if (map2 == null || map2.size() == 0) {
            return (map1 == null || map1.size() == 0) ? true : false;
        }

        if (map1.size() != map2.size()) {
            return false;
        }
        for (Map.Entry<Character, Integer> entry : map1.entrySet()) {
            char c = entry.getKey();
            int count = entry.getValue();
            if (map2.get(c) == null || map2.get(c) != count) {
                return false;
            }
        }
        return true;
    }
}
