package algorithm.leetcode.primeDataStructure.day2;

public class _88_MergeSortedArray {

    public static void main(String[] args) {
        int[] nums1 = new int[] { 1, 2, 3, 0, 0, 0 };
        int[] nums2 = new int[] { 2, 5, 6 };

        merge(nums1, 3, nums2, 3);

        for (int i = 0; i < nums1.length; i++) {
            System.out.print(nums1[i] + " ");
        }
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        // 双指针
        int p = 0, q = 0;

        int[] res = new int[m + n];
        int pos = 0;
        while (p < m && q < n) {
            if (nums1[p] >= nums2[q]) {
                res[pos++] = nums2[q++];
            } else {
                res[pos++] = nums1[p++];
            }
        }
        // nums1先走完，剩下的全是nums2
        if (p == m) {
            while (q < n) {
                res[pos++] = nums2[q++];
            }
        } else if (q == n) {
            while (p < m) {
                res[pos++] = nums1[p++];
            }
        }
        for (int i = 0; i < m + n; i++) {
            nums1[i] = res[i];
        }
    }
}
