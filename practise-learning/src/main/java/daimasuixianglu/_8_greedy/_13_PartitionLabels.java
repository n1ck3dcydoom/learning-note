package daimasuixianglu._8_greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _13_PartitionLabels {

    public static void main(String[] args) {
        partitionLabels("ababcbacadefegdehijhklij");
        int a = 1;
    }

    public static List<Integer> partitionLabels(String s) {
        // 扫描一遍统计字母最后出现的位置，记录map
        // 再次扫描，记录区间起点start和游标i
        // 如果游标i当前的字符最后出现的位置index更大，则说明当前区间需要向后继续扩大
        // 直到游标i找到了字符最后出现的位置index，记录start和index的区间
        // 更新start，游标i继续往后扫描

        // 记录每个字母最后出现的位置index
        Map<Character, Integer> hash = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            hash.put(c, Math.max(i, hash.getOrDefault(c, 0)));
        }
        // 再次扫描字符串，记录区间
        int start = 0;
        int index = 0;
        // int res = 0;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 判断游标i对应字母的最后出现位置，和之前记录的其他字母最后出现的位置index谁更大
            index = Math.max(index, hash.get(c));
            // 如果游标对应字母最后出现的位置等于之前记录的index，则说明此时的区间就是包含当前字母的最多出现的"最短"区间
            if (i == index) {
                res.add(i - start + 1);
                start = i + 1;
            }
        }
        return res;
    }
}
