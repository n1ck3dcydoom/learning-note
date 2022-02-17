package daimasuixianglu._7_backtrace;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _10_IncreasingSubsequences {

    private static List<List<Integer>> res = new ArrayList<>();
    private static List<Integer> path = new ArrayList<>();

    public static void main(String[] args) {
        findSubsequences(new int[]{4, 6, 7, 7});
        int a = 1;
    }

    public static List<List<Integer>> findSubsequences(int[] nums) {
        dfs(nums, 0);
        return res;
    }

    private static void dfs(int[] nums, int index) {
        // 访问路径大于等于2的子结点
        if (path.size() >= 2) {
            res.add(new ArrayList<>(path));
        }
        if (index == nums.length) {
            return;
        }
        // 使用集合去重
        Set<Integer> set = new HashSet<>();
        // 遍历选择
        for (int i = index; i < nums.length; i++) {
            // 已经使用过或者没法和前一个元素构成升序子序列，则跳过
            if (set.contains(nums[i]) || (path.size() > 0 && nums[i] < path.get(path.size() - 1))) {
                continue;
            }
            // 标记当前元素已经访问过，12323
            //                       ↑ ↑
            set.add(nums[i]);
            path.add(nums[i]);
            dfs(nums, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
