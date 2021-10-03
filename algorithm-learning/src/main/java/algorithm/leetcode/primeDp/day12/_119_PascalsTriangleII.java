package algorithm.leetcode.primeDp.day12;

import java.util.ArrayList;
import java.util.List;

public class _119_PascalsTriangleII {

    public static void main(String[] args) {
        System.out.println(getRow(3));
    }

    public static List<Integer> getRow(int rowIndex) {
        List<List<Integer>> res = new ArrayList<>();

        List<Integer> l0 = new ArrayList<>();
        l0.add(1);
        res.add(l0);
        if (rowIndex == 0) {
            return res.get(rowIndex);
        }
        for (int i = 1; i <= rowIndex; i++) {
            List<Integer> l = new ArrayList<>();
            List<Integer> pre = res.get(i - 1);
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    l.add(1);
                } else {
                    l.add(pre.get(j) + pre.get(j - 1));
                }
            }
            res.add(l);
        }
        return res.get(rowIndex);
    }
}