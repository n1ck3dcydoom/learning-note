package algorithm.leetcode.primeAlgorithm.day11;

import java.util.Deque;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class _46_Permutations {

    public static void main(String[] args) {
        int[] nums = new int[] { 1, 2, 3 };
        List<List<Integer>> res = permute(nums);
        for (List<Integer> list : res) {
            for (int i : list) {
                System.out.print(i + " ");
            }
            System.out.println("\n");
        }
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        doPermute(res, new ArrayDeque<>(), nums, used);
        return res;
    }

    private static void doPermute(List<List<Integer>> res, Deque<Integer> path, int[] nums, boolean[] used) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 排列需要挑选出所有的数，组合则是从剩下的数中进行选择
        // 使用used[]记录已经选择的数
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                // 做出选择
                path.addLast(nums[i]);
                // 将选择标记为已使用
                used[i] = true;
                // 加入递归
                doPermute(res, path, nums, used);
                // 撤销选择
                path.pollLast();
                used[i] = false;
            }
        }
    }
}
