package leetcode.doublepointer.medium;

import java.util.Arrays;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * <p>
 * 元素的 频数 是该元素在一个数组中出现的次数。
 * <p>
 * 给你一个整数数组 nums 和一个整数 k 。在一步操作中，你可以选择 nums 的一个下标，并将该下标对应元素的值增加 1 。
 * <p>
 * 执行最多 k 次操作后，返回数组中最高频元素的 最大可能频数 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,4], k = 5
 * 输出：3
 * 解释：对第一个元素执行 3 次递增操作，对第二个元素执 2 次递增操作，此时 nums = [4,4,4] 。
 * 4 是数组中最高频元素，频数是 3 。
 * 示例 2：
 * <p>
 * 输入：nums = [1,4,8,13], k = 5
 * 输出：2
 * 解释：存在多种最优解决方案：
 * - 对第一个元素执行 3 次递增操作，此时 nums = [4,4,8,13] 。4 是数组中最高频元素，频数是 2 。
 * - 对第二个元素执行 4 次递增操作，此时 nums = [1,8,8,13] 。8 是数组中最高频元素，频数是 2 。
 * - 对第三个元素执行 5 次递增操作，此时 nums = [1,4,13,13] 。13 是数组中最高频元素，频数是 2 。
 * 示例 3：
 * <p>
 * 输入：nums = [3,9,6], k = 2
 * 输出：1
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 * 1 <= k <= 105
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/frequency-of-the-most-frequent-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/7/19 20:18
 **/
public class _1838_FrequencyOfTheMostFrequentElement {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 4, 8, 15};
        System.out.println(maxFrequency(nums, 5));
    }

    public static int maxFrequency(int[] nums, int k) {
        int n = nums.length;
        // 滑动窗口
        int l = 0, r = 1;
        // 总共操作次数计数
        int total = 0;
        // 滑动窗空的最大间距
        int res = 0;
        // 排序数组
        Arrays.sort(nums);
        while (r < n) {
            // 1，4，8，13
            // 假设r = 2此时nums[r]=8，要把前r-1到l个数(总共r-l个)全部变成8，需要执行(r-1)*(nums[r]-nums[r-1])次操作
            // 使用total保存总共的操作次数，即total += (r-l)*(nums[r]-nums[r-1])
            total += (r - l) * (nums[r] - nums[r - 1]);
            // 如果total > k之后，需要左端点右移，腾出更多的操作次数
            // 容易得知，当x<y的时候，把nums[x]变为nums[r]的开销比nums[y]变为nums[r]的开销更大
            // 即左端点右移能够提供更多的剩余k值
            while (total > k && l < r) {
                // 左端点右移可以释放的操作次数为nums[r]-nums[l]
                total -= nums[r] - nums[l++];
            }
            // 更新l和r的最大间距
            res = Math.max(res, r - l + 1);
            // 右端点继续扩大
            r++;
        }
        return res;
    }
}