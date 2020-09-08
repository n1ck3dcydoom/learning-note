package leetcode.easy.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanglei
 * @version 1.0
 * @description TODO
 * @date 2020/9/8 20:12
 **/
public class _448_FindAllNumbersDisappearedInAnArray {

    public static void main(String[] args) {
        int[] nums = new int[]{4, 3, 2, 7, 8, 2, 3, 1};
        for (Integer i : findDisappearedNumbers(nums)) {
            System.out.print(i + " ");
        }
    }

    public static List<Integer> findDisappearedNumbers(int[] nums) {
        int temp;
        for (int i = 0; i < nums.length; i++) {
            // 如果当前数不等于其下标索引，交换，
            while (nums[nums[i] - 1] != nums[i]) {
                temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        // 遍历找到索引不对应的数
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                res.add(i + 1);
            }
        }
        return res;
    }
}
// i = 1 , i < n=7
//  0  1  2  3  4  5  6  7
//  4  3  2  7  8  2  3  1
//  1  2  3  4  5  6  7  8