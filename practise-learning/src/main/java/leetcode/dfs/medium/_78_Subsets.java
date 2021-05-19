package leetcode.dfs.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @version 1.0
 * @description leetcode
 * <p>
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * <p>
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * 示例 2：
 * <p>
 * 输入：nums = [0]
 * 输出：[[],[0]]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * nums 中的所有元素 互不相同
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subsets
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/5/19 15:05
 **/
public class _78_Subsets {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        List<List<Integer>> res = subsets(nums);
        System.out.println(111);
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, res, 0, new Stack<>());
        return res;
    }

    private static void dfs(int[] nums, List<List<Integer>> res, int start, Stack<Integer> path) {
        // 把当前路径加入返回列表
        res.add(new ArrayList<>(path));
        // 遍历剩下的选择
        for (int i = start; i < nums.length; i++) {
            // 路径加入当前选择
            path.push(nums[i]);
            // 带入路径进入递归
            // 通过start来完成剪枝，保证数组前面遍历过的元素，不会重复访问
            dfs(nums, res, i + 1, path);
            // 把之前加入的选择从当前路径移除
            path.pop();
        }
    }
}