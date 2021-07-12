package leetcode.easy;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * <p>
 * 数组中占比超过一半的元素称之为主要元素。给你一个 整数 数组，找出其中的主要元素。若没有，返回 -1 。请设计时间复杂度为 O(N) 、空间复杂度为 O(1) 的解决方案。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：[1,2,5,9,5,9,5,5,5]
 * 输出：5
 * 示例 2：
 * <p>
 * 输入：[3,2]
 * 输出：-1
 * 示例 3：
 * <p>
 * 输入：[2,2,1,1,1,2,2]
 * 输出：2
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-majority-element-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/7/12 10:12
 **/
public class _17_10_FindMajorityElementLCCI {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 2, 1, 1, 1, 2, 2};
        System.out.println(majorityElement(nums));
    }

    public static int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        // 摩尔投票算法
        // 每次记录当前元素的出现次数，遇到相同则+1，不同则-1，如果为0，则替换为新的元素，初始为1

        int count = 1;
        int cur = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                cur = nums[i];
                count = 1;
            } else {
                if (nums[i] == cur) {
                    count++;
                } else {
                    count--;
                }
            }
        }
        // 如果不存在众数，则cur可能是数组的最后一个值，nums=[1,2,3]
        // 需要再次遍历nums数组，统计cur的出现次数，如果次数大于等于一半，才可能是众数
        for (int num : nums) {
            if (num == cur) {
                count++;
            }
        }
        return count >= nums.length / 2 ? cur : -1;
    }
}