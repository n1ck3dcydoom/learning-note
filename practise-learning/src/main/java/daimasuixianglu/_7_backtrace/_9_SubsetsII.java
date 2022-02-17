package daimasuixianglu._7_backtrace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _9_SubsetsII {

    private List<List<Integer>> res = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // 不能重复访问，排序
        Arrays.sort(nums);
        dfs(nums, 0);
        return res;
    }

    private void dfs(int[] nums, int index) {
        // 结束遍历前加入结果集，访问树中的每个节点
        res.add(new ArrayList<>(path));
        // 如果遍历的起始位置等于数组长度，结束遍历
        if (nums.length == index) {
            // 如果在结束遍历的时候才加入结果集，实际上访问的是叶子节点
            // res.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < nums.length; i++) {
            // 剪枝，避免重复选择一个元素
            if (i > index && nums[i] == nums[i - 1]) {
                continue;
            }
            // 做出选择
            path.add(nums[i]);
            // 递归遍历
            dfs(nums, i + 1);
            // 撤销选择
            path.remove(path.size() - 1);
        }
    }
}
