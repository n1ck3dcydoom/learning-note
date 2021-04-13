package leetcode.easy.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @description leetcode
 * <p>
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：[3,2,3]
 * 输出：3
 * 示例 2：
 * <p>
 * 输入：[2,2,1,1,1,2,2]
 * 输出：2
 * <p>
 * 进阶：
 * <p>
 * 尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/majority-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/4/13 19:30
 **/
public class _169_MajorityElement {
    public int majorityElement(int[] nums) {
        int n = nums.length;

        // 使用map保存所有出现过的数字和次数
        HashMap<Integer, Integer> countMap = new HashMap<>();

        // 遍历数组
        for (int num : nums) {
            if (!countMap.containsKey(num)) {
                countMap.put(num, 1);
            } else {
                countMap.put(num, countMap.get(num) + 1);
            }
        }

        // 遍历map找到次数大于 ⌊ n/2 ⌋ 的元素
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() > n / 2) {
                return entry.getKey();
            }
        }
        return 0;
    }

    private int majorityElement1(int[] nums) {
        // 摩尔投票算法

        // 相同阵营的元素，个数+1
        // 遇到不同阵营的元素，个数-1
        // 如果遇到不同阵营时，个数=0，则把当前阵营个数置1
        // 一直抵消到最后的阵营即为众数
        int count = 1;
        int currentInt = nums[0];

        int n = nums.length;
        for (int i = 1; i < n; i++) {
            if (currentInt == nums[i]) {
                count++;
            } else {
                if (count == 0) {
                    currentInt = nums[i];
                    count = 1;
                } else {
                    count--;
                }
            }
        }

        return currentInt;
    }
}