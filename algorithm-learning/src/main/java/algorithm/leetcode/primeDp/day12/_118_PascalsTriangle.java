package algorithm.leetcode.primeDp.day12;

import java.util.ArrayList;
import java.util.List;

public class _118_PascalsTriangle {

    public static void main(String[] args) {
        System.out.println(generate(5));
    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();

        List<Integer> l0 = new ArrayList<>();
        l0.add(1);
        res.add(l0);
        if (numRows == 1) {
            return res;
        }
        for (int i = 2; i <= numRows; i++) {
            List<Integer> l = new ArrayList<>();
            List<Integer> pre = res.get(i - 2);
            for (int j = 1; j <= i; j++) {
                if (j == 1 || j == i) {
                    l.add(1);
                } else {
                    l.add(pre.get(j - 1) + pre.get(j - 2));
                }
            }
            res.add(l);
        }
        return res;
    }
}