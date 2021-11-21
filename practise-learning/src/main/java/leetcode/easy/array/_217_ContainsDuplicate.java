package leetcode.easy.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description 给定一个整数数组，判断是否存在重复元素。
 * <p>
 * 如果任意一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3,1]
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: [1,2,3,4]
 * 输出: false
 * 示例 3:
 * <p>
 * 输入: [1,1,1,3,3,4,3,2,4,2]
 * 输出: true
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/contains-duplicate
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2020/7/23 18:41
 **/
public class _217_ContainsDuplicate {

    public static void main(String[] args) {
        int[] nums = new int[]{1};
        System.out.println(containsDuplicate1(nums));
    }

    public static boolean containsDuplicate(int[] nums) {
        int len = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>(len);
        for (int i = 0; i < len; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], 1);
            } else {
                map.put(nums[i], map.get(nums[i]) + 1);
            }

        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() != 1) {
                return true;
            }
        }
        return false;
    }

    // 计数
    public static boolean containsDuplicate1(int[] nums) {
        int len = nums.length;
        int[] index = new int[len + 1];
        for (int num : nums) {
            index[num]++;
        }
        for (int i = 0; i < len; i++) {
            if (index[i] >= 2) {
                return true;
            }
        }
        return false;
    }
}