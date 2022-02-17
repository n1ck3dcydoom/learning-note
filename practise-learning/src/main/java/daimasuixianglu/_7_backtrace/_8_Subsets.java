package daimasuixianglu._7_backtrace;

import java.util.ArrayList;
import java.util.List;

public class _8_Subsets {

    private static List<List<Integer>> res = new ArrayList<>();
    private static List<Integer> path = new ArrayList<>();

    public static void main(String[] args) {
        subsets(new int[]{1, 2, 3});
        int a = 1;
    }

    public static List<List<Integer>> subsets(int[] nums) {
        // res.add(new ArrayList<>(path));
        dfs(nums, 0);
        return res;
    }

    private static void dfs(int[] nums, int index) {
        // 记录节点 空集也是子集
        res.add(new ArrayList<>(path));
        // 遍历的起始位置等于数组的长度，结束遍历
        if (index == nums.length) {
            // 如果在结束递归的时候记录节点，实际上记录的是叶子节点
            // res.add(new ArrayList<>(path));
            return;
        }
        // 选出元素放入集合构成子集
        // 子集元素不能重复
        for (int i = index; i < nums.length; i++) {
            path.add(nums[i]);
            dfs(nums, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
