package daimasuixianglu._7_backtrace;

import java.util.ArrayList;
import java.util.List;

public class _4_CombinationSum {

    private List<List<Integer>> res = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        dfs(candidates, path, 0, 0, target);
        return res;
    }

    private void dfs(int[] selects, List<Integer> path, int index, int sum, int target) {
        // 如果当前和超过了目标，结束搜索
        // if (sum > target) {
        // return;
        // }
        // 当前和等于目标，记录当前选择
        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 当前和小于目标，继续递归搜索
        for (int i = index; i < selects.length; i++) {
            // sum剪枝
            if (sum + selects[i] > target) {
                continue;
            }
            // 做出选择
            path.add(selects[i]);
            // 递归搜索
            dfs(selects, path, i, sum + selects[i], target);
            // 撤销选择
            path.remove(path.size() - 1);
        }
    }
}
