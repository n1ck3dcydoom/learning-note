package algorithm.leetcode.primeDataStructure.day3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _350_IntersectionOfTwoArrays_II {

    public static void main(String[] args) {
        int[] nums1 = new int[] { 4, 9, 5 };
        int[] nums2 = new int[] { 9, 4, 9, 8, 4 };
        int[] res = intersect(nums1, nums2);
        if (res != null && res.length > 0) {
            for (int i = 0; i < res.length; i++) {
                System.out.print(res[i] + " ");
            }
        }
    }

    public static int[] intersect(int[] nums1, int[] nums2) {
        // 排序后双指针遍历
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int p = 0, q = 0;
        int n = nums1.length;
        int m = nums2.length;

        while (p < n && q < m) {
            // 把相等的数放入集合，同时移动两个指针
            if (nums1[p] == nums2[q]) {
                list.add(nums1[p]);
                p++;
                q++;
            }
            // 不相等每次小的指针后移
            else if (nums1[p] > nums2[q]) {
                q++;
            } else {
                p++;
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}
