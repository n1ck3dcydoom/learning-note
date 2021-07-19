package leetcode.pointoffer;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * <p>
 * 统计一个数字在排序数组中出现的次数。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: 0
 *  
 * <p>
 * 限制：
 * <p>
 * 0 <= 数组长度 <= 50000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/7/16 10:40
 **/
public class PointOffer_53 {

    /**
     * 朴素遍历
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     *
     * 未利用数组排序的特点
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] > target) {
                break;
            }
            if (nums[i] == target) {
                res++;
            }
        }
        return res;
    }
}