package algorithm.leetcode.primeAlgorithm.day11;

import java.util.List;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

public class _77_Combinations {

    public static void main(String[] args) {
        List<List<Integer>> res = combine1(4, 2);
        for (List<Integer> list : res) {
            for (int i : list) {
                System.out.print(i + " ");
            }
            System.out.print("\n");
        }
    }

    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        doCombine(res, new ArrayList<>(), k, list, 0);
        return Collections.emptyList();
    }

    /**
     * 递归回溯 1、当前路径：path，已经选择的数字 2、剩余选择：还没选的数list，还差多少个数字 3、做出选择/撤销选择
     * 
     * @return
     */
    private static void doCombine(List<List<Integer>> res, List<Integer> path, int k, List<Integer> list, int index) {
        // 如果当前路径path的元素个数已经等于k，返回当前路径
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 遍历剩下的所有选择
        for (int i = index; i < list.size(); i++) {
            // 做出选择
            path.add(list.get(i));
            // 进入递归
            doCombine(res, path, k, list, i + 1);
            // 撤销选择
            path.remove(path.size() - 1);
        }
    }

    public static List<List<Integer>> combine1(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        doCombine1(res, new ArrayDeque<>(), k, n, 1);
        return res;
    }

    private static void doCombine1(List<List<Integer>> res, Deque<Integer> path, int k, int n, int index) {
        // 如果当前路径path的元素个数已经等于k，返回当前路径
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 遍历剩下的选择，带剪枝
        // 当n=7，k=4时，从5开始搜索已经没有意义了，因为5，6，7不够k=4
        // 如果当前路径path长度为1，那么还需要3个数，搜索起点最大是5
        // 如果当前路径path长度为2，那么还需要2个数，搜索起点最大是6
        // 如果当前路径path长度为3，那么还需要1个数，搜索起点最大是7
        // size k-size n-(k-size)+1
        // 所以i的遍历上限即为n-(k-size)+1
        for (int i = index; i <= n - (k - path.size()) + 1; i++) {
            path.addLast(i);
            doCombine1(res, path, k, n, i + 1);
            path.pollLast();
        }
    }
}
