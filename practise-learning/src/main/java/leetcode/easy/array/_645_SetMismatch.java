package leetcode.easy.array;

import java.util.HashSet;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * <p>
 * 集合 s 包含从 1 到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个数字复制了成了集合里面的另外一个数字的值，导致集合 丢失了一个数字 并且 有一个数字重复 。
 * <p>
 * 给定一个数组 nums 代表了集合 S 发生错误后的结果。
 * <p>
 * 请你找出重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,2,4]
 * 输出：[2,3]
 * 示例 2：
 * <p>
 * 输入：nums = [1,1]
 * 输出：[1,2]
 *  
 * <p>
 * 提示：
 * <p>
 * 2 <= nums.length <= 10^4
 * 1 <= nums[i] <= 10^4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/set-mismatch
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/7/12 16:04
 **/
public class _645_SetMismatch {

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 3, 4, 6, 5};
        int[] res = findErrorNums(nums);
        System.out.println(111);
    }

    public static int[] findErrorNums(int[] nums) {
        int[] res = new int[2];

        // 原数组之和
        int sum = 0;
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            sum += num;
            set.add(num);
        }
        // 去重后数组之和
        int distinctSum = 0;
        for (int num : set) {
            distinctSum += num;
        }
        // 1~n之和
        int nSum = 0;
        for (int i = 1; i <= nums.length; i++) {
            nSum += i;
        }
        // 重复元素等于原数组之和-去重后数组之和
        res[0] = sum - distinctSum;
        // 缺少的元素等于1~n之和-去重后数组之和
        res[1] = nSum - distinctSum;

        return res;
    }
}