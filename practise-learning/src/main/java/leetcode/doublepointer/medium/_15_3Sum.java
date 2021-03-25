package leetcode.doublepointer.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0
 * @description leetcode
 * <p>
 * 给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 示例 2：
 * <p>
 * 输入：nums = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：nums = [0]
 * 输出：[]
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/3/25 23:29
 **/
public class _15_3Sum {
    public List<List<Integer>> threeSum(int[] nums) {
        // 返回结果
        List<List<Integer>> res = new ArrayList<>();

        // 对nums数组进行排序
        Arrays.sort(nums);

        int n = nums.length;

        // 特殊情况特殊考虑
        // 如果nums数组长度小于3，肯定没法凑出3个数相加
        if (n < 3) {
            return res;
        }

        // 遍历nums数组，如果nums[i]都已经大于0了，后面三数之和肯定也不可能等于0，直接break掉
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                break;
            }
            // 去重，如果num[i]和后面的相同，这里就存在重复，只需要找到最后一个相同的位置即可
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 双指针，从i后面开始找两个数，p从i+1开始往后，q从n-1开始往前，直到p=q结束
            int p = i + 1; // 左指针
            int q = n - 1; // 右指针
            while (p < q) {
                int sum = nums[i] + nums[p] + nums[q];
                if (sum == 0) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[p]);
                    temp.add(nums[q]);
                    res.add(temp);
                    // 如果当前组合满足sum==0了，考虑做指针p的后面若干项如果也等于nums[p]的话，就存在重复数据
                    while (p < q && nums[p] == nums[p + 1]) {
                        p++;
                    }
                    // 同理，考虑做指针q的前面若干项如果也等于nums[q]的话，就存在重复数据
                    while (p < q && nums[q] == nums[q - 1]) {
                        q--;
                    }
                    // 如果p的后面项不等于p，且q的前面项不等于q，则左右指针移动一个位置
                    p++;
                    q--;
                }
                // 如果当前三个数的和大于0了，由于p的值较小，q的值较大，为了减小和，只能让较大的q减小一点
                else if (sum > 0) {
                    q--;
                }
                // 如果当前三个数的和大于0了，由于p的值较小，q的值较大，为了增大和，只能让较小的p增大一点
                else if (sum < 0) {
                    p++;
                }

            }
        }
        return res;
    }
}