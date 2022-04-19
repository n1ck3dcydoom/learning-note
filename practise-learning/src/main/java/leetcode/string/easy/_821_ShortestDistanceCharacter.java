package leetcode.string.easy;

import java.util.List;
import java.util.ArrayList;

public class _821_ShortestDistanceCharacter {

    public static void main(String[] args) {
        int[] res = shortestToChar("loveleetcode", 'e');
        for (int i : res) {
            System.out.print(i + " ");
        }
    }

    public static int[] shortestToChar(String s, char c) {
        // 扫描第一遍，找到所有c的位置
        List<Integer> pos = new ArrayList<>();
        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == c) {
                pos.add(i);
            }
        }
        // 第二遍扫描，和每个c出现的位置作对比，去最小值
        int[] res = new int[cs.length];
        for (int i = 0; i < cs.length; i++) {
            int min = Integer.MAX_VALUE;
            for (Integer j : pos) {
                min = Math.min(min, Math.abs(j - i));
            }
            res[i] = min;
        }
        return res;
    }
}
