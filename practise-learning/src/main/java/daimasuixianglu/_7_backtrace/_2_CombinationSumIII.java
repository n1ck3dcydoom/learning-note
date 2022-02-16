package daimasuixianglu._7_backtrace;

import java.util.ArrayList;
import java.util.List;

public class _2_CombinationSumIII {

    public static void main(String[] args) {
        List<List<Integer>> res = combinationSum3(3, 7);
        int a = 1;
    }

    private static List<List<Integer>> res = new ArrayList<>();
    private static List<Integer> path = new ArrayList<>();

    public static List<List<Integer>> combinationSum3(int k, int n) {
        dfs(1, 0, n, k);
        return res;
    }

    private static void dfs(int index, int sum, int n, int k) {
        // 剪枝，即使选择个数不够k个，但是当前和已经超过了n，也不再往下搜索了
        if (sum > n) {
            return;
        }
        // 剪枝，当选择个数已经等于k个时，如果当前和仍然不够n，也不再往下搜索了
        if (path.size() == k && sum < n) {
            return;
        }
        // 路径中的选择个数等于k，且和等于n
        if (path.size() == k && sum == n) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 遍历剩下的选择
        // 限定了每种选择只能是[1,9]之间的正整数
        // 若n=7，k=4
        // 剪枝，若已经选择1个数，还剩下k-1=3个数，i最大起点为9-(k-1)+1=7，即789刚好够3个数
        // 7再往后从8开始，89已经无法凑够3个数，不用继续遍历下去了
        for (int i = index; i <= 9 - (k - path.size()) + 1; i++) {
            // 做出选择
            path.add(i);
            // 递归搜索
            dfs(i + 1, sum + i, n, k);
            // 回溯
            path.remove(path.size() - 1);
        }
    }
}
