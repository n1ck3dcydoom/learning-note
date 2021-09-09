package algorithm.leetcode.primeDataStructure.day4;

import java.util.ArrayList;
import java.util.List;

public class _118_PascalsTriangle {

    public static void main(String[] args) {
        List<List<Integer>> res = generate(5);
        for (List<Integer> list : res) {
            for (int i : list) {
                System.out.print(i + " ");
            }
            System.out.println("\n");
        }
    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> cur = new ArrayList<>();
            if (i == 0) {
                cur.add(1);
            } else {
                List<Integer> pre = res.get(i - 1);
                for (int j = 0; j <= i; j++) {
                    if (j == 0) {
                        cur.add(1);
                    } else if (j == i) {
                        cur.add(1);
                    } else {
                        cur.add(pre.get(j - 1) + pre.get(j));
                    }
                }
            }
            res.add(cur);
        }
        return res;
    }
}
