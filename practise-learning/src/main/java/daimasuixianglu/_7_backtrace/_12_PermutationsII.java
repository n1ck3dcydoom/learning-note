package daimasuixianglu._7_backtrace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _12_PermutationsII {

    private List<List<Integer>> res = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        Arrays.sort(nums);
        dfs(nums, visited);
        return res;
    }

    private void dfs(int[] nums, boolean[] visited) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 去重
            // 当nums[i] == nums[i-1] && visited[i-1]==false时
            // 说明当前同一层（因为i和i-1相等），前面的i-1已经完成了回溯（完成回溯之后就会撤销visited[i-1]==true的操作，使之重新变为visited[i-1]=false）
            // 所以此时visited[i-1]==false，表示前面有路径在遍历到当前层的时候，已经用过了i-1这个元素
            // 而nums[i-1] == nums[i] 所以需要去重

            // 那么问题来了，如果是nums[i-1] == nums[i= && visited[i-1] == true表示什么呢
            // 如果发现visited[i-1] == true，根据当前层的visited实质上是上一层的visited带下来的
            // 可以得到是当前路径上一层已经使用过nums[i-1]，进而在当前层有visited[i-1]，而nums[i]又等于nums[i-1]
            // 所以在树枝（路径）上的选择，需要把nums[i-1]去掉
            if (i > 0 && nums[i] == nums[i - 1] && visited[i - 1] == false) {
                continue;
            }
            // 树枝上重复元素去重
            // 为什么这里叫做树枝上的去重
            // 因为每次递归会带上当前层的visited到下一层，所以下一层拿到的visited实际上是上一层的
            // 当下一层在遍历数组的时候，发现visited已经访问过，实际上是上一层已经访问过
            // 所以这里是上下层的相同元素去重
            if (visited[i] == false) {
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
}
