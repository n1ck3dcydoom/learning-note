package leetcode.array.easy;

import java.util.Arrays;

/**
 * Created by n!Ck
 * Date: 2022/8/24
 * Time: 09:11
 * Description:
 */

public class _1460_MakeTwoArraysEqualByReversingSubarrays {

    public boolean canBeEqual(int[] target, int[] arr) {
        // 因为可以翻转任意长度的非空子数组,如果每次翻转相邻两个子数组,那么就可以实现冒泡排序
        // 只需要将两个数组排序后比较是否相等即可
        Arrays.sort(target);
        Arrays.sort(arr);
        for (int i = 0; i < target.length; i++) {
            if (target[i] != arr[i]) {
                return false;
            }
        }
        return true;
    }
}
