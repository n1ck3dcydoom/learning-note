package daimasuixianglu._3_array;

import java.util.HashMap;
import java.util.Map;

public class _3_MinimumWindowSubstring {

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(minWindow(s, t));
    }

    public static String minWindow(String s, String t) {
        // 滑动窗口+hash计数

        // 保存t串出现的词频
        Map<Character, Integer> tmap = new HashMap<>();
        for (char c : t.toCharArray()) {
            tmap.put(c, tmap.getOrDefault(c, 0) + 1);
        }
        // 保存窗口内出现的词频
        Map<Character, Integer> smap = new HashMap<>();

        // 滑动窗口
        char[] sc = s.toCharArray();
        int res = Integer.MAX_VALUE;
        int ns = s.length();
        int need = t.length();
        int l = 0, r = 0;
        int minl = 0, minr = 0;
        while (r < ns) {
            // 这里决定了窗口的右端点是开区间[l,r)
            char c = sc[r++];
            // 如果当前窗口出现的词频不能满足t串的词频，扩大右端点
            if (need > 0) {
                // 右端点字符加入窗口词频hash
                smap.put(c, smap.getOrDefault(c, 0) + 1);
                // 如果右端点是t串所需要的字符，且当前窗口出现的频率还不能够满足t串当前字符出现的频率
                if (tmap.containsKey(c) && tmap.get(c) >= smap.get(c)) {
                    need--;
                }
            }
            // 当t串所需要的词频全部满足后，开始考虑缩小左端点，求满足条件的最小窗口长度
            while (need == 0) {
                // 从窗口词频中移除左端点
                c = sc[l];
                int cnt = smap.get(c) - 1;
                if (cnt == 0) {
                    smap.remove(c);
                } else {
                    smap.put(c, cnt);
                }
                // 如果移除的是一个t串所需要的字符
                if (tmap.containsKey(c) && tmap.get(c) > smap.getOrDefault(c, 0)) {
                    // 更新当前窗口长度，注意右端点为开区间[l,r)
                    int tempLen = r - l;
                    if (tempLen < res) {
                        // 记录更小窗口左右端点出现的位置
                        minl = l;
                        minr = r;
                        res = tempLen;
                    }
                    need++;
                }
                l++;
            }
        }
        // 如果没有满足的窗口，res仍然是默认值
        return res == Integer.MAX_VALUE ? "" : s.substring(minl, minr);
    }
}
