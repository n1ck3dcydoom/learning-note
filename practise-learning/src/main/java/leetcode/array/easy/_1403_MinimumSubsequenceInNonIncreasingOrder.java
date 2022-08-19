package leetcode.array.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by n!Ck
 * Date: 2022/8/19
 * Time: 09:39
 * Description:
 */

public class _1403_MinimumSubsequenceInNonIncreasingOrder {

    public static void main(String[] args) {
        System.out.println(minSubsequence(new int[]{4, 3, 10, 9, 8}));
        System.out.println(minSubsequence(new int[]{4, 4, 7, 6, 7}));
    }

    public static List<Integer> minSubsequence(int[] nums) {
        // 贪心,每次选最小的数字,如果剩余数字之和大于等于选出来的数字之后,则剩余数字构成的非递增序列就是答案

        // 排序,由于需要构造非递增序列,这里采用倒序排序后从后往前遍历,这样找到分割点时,从 0-分割点 的子序列就是非递增的
        nums = Arrays.stream(nums).boxed().sorted((n1, n2) -> n2 - n1).mapToInt(i -> i).toArray();
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        int halfSum = sum % 2 == 0 ? sum / 2 : (sum + 1) / 2;

        // 逆向搜索
        int reverseSum = 0;
        int index;
        for (index = nums.length - 1; index >= 0; index--) {
            reverseSum += nums[index];
            if (reverseSum >= halfSum) {
                break;
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i <= index; i++) {
            res.add(nums[i]);
        }
        return res;
    }
}
