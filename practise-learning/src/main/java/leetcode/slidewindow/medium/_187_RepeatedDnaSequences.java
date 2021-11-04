package leetcode.slidewindow.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _187_RepeatedDnaSequences {

    public static void main(String[] args) {
        List<String> res1 = findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT");
        for (String str : res1) {
            System.out.println(str + " ");
        }
        List<String> res2 = findRepeatedDnaSequences("AAAAAAAAAAA");
        for (String str : res2) {
            System.out.println(str + " ");
        }
    }

    public static List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<>();
        // 长度小于10的就不考虑了
        if (s == null || s.length() < 10) {
            return res;
        }

        // 滑动窗口遍历整个序列，直到窗口右端点达到末尾
        // 使用hash保存每个出现的序列，出现重复后就放入结果集
        HashMap<String, Integer> hash = new HashMap<>();
        // 窗口长度固定为10
        // i直接从索引为10开始
        for (int i = 10; i <= s.length(); i++) {
            String sequence = s.substring(i - 10, i);
            hash.put(sequence, hash.getOrDefault(sequence, 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : hash.entrySet()) {
            if (entry.getValue() > 1) {
                res.add(entry.getKey());
            }
        }
        return res;
    }
}
