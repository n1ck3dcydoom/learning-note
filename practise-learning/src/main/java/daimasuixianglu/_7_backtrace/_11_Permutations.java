package daimasuixianglu._7_backtrace;

import java.util.ArrayList;
import java.util.List;

public class _11_Permutations {

    private List<List<Integer>> res = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        dfs(nums, visited);
        return res;
    }

    private void dfs(int[] nums, boolean[] visited) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 全排列需要考虑每个数，每层都要从0开始遍历，跳过自己
        for (int i = 0; i < nums.length; i++) {
            // TODO 跳过已经使用过的数
            if (visited[i]) {
                continue;
            }
            // 做出选择
            path.add(nums[i]);
            visited[i] = true;
            // 递归搜索
            dfs(nums, visited);
            // 撤销选择
            path.remove(path.size() - 1);
            visited[i] = false;
        }
    }
}
