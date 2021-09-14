package leetcode.greedy.medium;

import java.util.ArrayList;
import java.util.List;

public class _524_LongestWordDictionaryThroughDeleting {

    public static void main(String[] args) {
        String s = "abpcplea";
        List<String> dic = new ArrayList<>();
        dic.add("ale");
        dic.add("apple");
        dic.add("monkey");
        dic.add("plea");
        System.out.println(findLongestWord(s, dic));
    }

    public static String findLongestWord(String s, List<String> dictionary) {
        // 找到dic中最长的，且字典序最小的字符串
        // 可以先对dic按照长度和字典序排序
        dictionary.sort((s1, s2) -> {
            // 如果两个串长度不相等，则长的在前面
            if (s1.length() != s2.length()) {
                return s2.length() - s1.length();
            } else {
                // 如果两个串长度相等，则按照字典序排序
                return s1.compareTo(s2);
            }
        });

        for (String str : dictionary) {
            // 删除s中的某些字符，可以得到dic中的字符串
            // 说明dic中的字符串是s的子串

            // 校验str是否是s的子串
            // 双指针
            // p指向s的字符，q指向str的字符
            // 如果p==q，那么p++，q++
            // 如果p!=q，那么p++
            // 如果q能够在p走完之前先走完，说明str是s的子串
            int p = 0, q = 0;
            while (q < str.length() && p < s.length()) {
                if (str.charAt(q) == s.charAt(p)) {
                    q++;
                }
                p++;

            }
            if (q == str.length()) {
                return str;
            }
        }
        return "";
    }
}
