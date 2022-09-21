package leetcode.dfs.medium;

import java.util.Arrays;

/**
 * Created by n!Ck
 * Date: 2022/9/21
 * Time: 23:40
 * Description:
 */

public class _698_PartitionToKEqualSumSubsets {

    public static void main(String[] args) {
        System.out.println(canPartitionKSubsets(new int[]{4, 3, 2, 3, 5, 2, 1}, 4));
    }

    public static boolean canPartitionKSubsets(int[] nums, int k) {
        // 可行性剪枝
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if (sum % k != 0) {
            return false;
        }
        // 每个分组的大小
        int size = sum / k;

        // 根据贪心的思路，排序后每次取最大的数放到分组里面
        // 再依次取较小的数放到分组，直到刚好装满分组
        // 依次填充所有分组后，即有解
        // 如果无法填充所有分组，即无解
        Arrays.sort(nums);

        return dfs(nums, k, 0, size, nums.length - 1, new boolean[nums.length]);
    }

    private static boolean dfs(int[] nums, int k, int cur, int size, int index, boolean[] visited) {
        // 如果所有分组都填充，则有解
        if (k == 0) {
            return true;
        }
        // 填充完当前分组，则开始填充下一个分组
        if (cur == size) {
            // 继续从最后一个最大元素往前查找，使用visited数组剪枝
            return dfs(nums, k - 1, 0, size, nums.length - 1, visited);
        }
        // 尝试从最大的数开始填充
        for (int i = index; i >= 0; i--) {
            // 跳过已经使用过的元素
            if (visited[i]) {
                continue;
            }
            // 判断当前数放入分组是否会超额
            if (cur + nums[i] > size) {
                continue;
            }
            // 尝试使用当前数填充分组
            visited[i] = true;
            // 继续搜索如果能够找到解
            // 由于贪心策略，明确知道对于已经放入分组的元素i，如果还有其他元素也能够放进来，只能够出现在i的左边（比i更小）
            if (dfs(nums, k, cur + nums[i], size, i - 1, visited)) {
                return true;
            }
            // 回溯
            visited[i] = false;
            // 如果回溯后当前分组等于0，则说明存在元素找不到合适的分组放置，即无解
            if (cur == 0) {
                return false;
            }
        }

        return false;
    }
}
