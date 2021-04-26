package leetcode.dfs.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * <p>
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3]
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/4/26 21:23
 **/
public class _46_Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        Stack<Integer> path = new Stack<>();
        int[] visited = new int[nums.length];
        dfs(nums, res, visited, path);
        return res;
    }

    private void dfs(int[] nums, List<List<Integer>> res, int[] visited, Stack<Integer> path) {
        // 如果当前路径的元素个数等于给定数组nums的长度，保存当前路径值，返回上一层
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 遍历数组，找到还没有被访问过的元素进行dfs
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] == 0) {
                // 标记当前元素已经被访问过
                visited[i] = 1;
                // 加入路径
                path.push(nums[i]);
                // 进入下一层dfs
                dfs(nums, res, visited, path);
                // 当前元素移除路径
                path.pop();
                // 标记当前元素路径还未被访问
                visited[i] = 0;
            }
        }

    }
}