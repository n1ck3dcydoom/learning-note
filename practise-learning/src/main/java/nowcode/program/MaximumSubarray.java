package nowcode.program;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description TODO
 * @date 2021/4/18 15:01
 **/
public class MaximumSubarray {
    public static void main(String[] args) {
        int[] nums = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        Stack<Integer> path = new Stack<>();
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, 24, 0, res, path, 0);
        System.out.println(111);
    }

    private static void dfs(int[] nums, int sum, int start, List<List<Integer>> res, Stack<Integer> path,
                            int currentSum) {
        if (nums == null || nums.length == 0) {
            return;
        }
        if (currentSum == sum) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < nums.length; i++) {
            if (currentSum + nums[i] <= sum) {
                path.push(nums[i]);
                dfs(nums, sum, i + 1, res, path, currentSum + nums[i]);
                path.pop();
            }
        }
    }
}
