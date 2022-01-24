package daimasuixianglu._3_hash;

import java.util.HashSet;
import java.util.Set;

public class _5_IntersectionTwoArrays {

    public static void main(String[] args) {
        int[] n1 = new int[] { 4,9,5 };
        int[] n2 = new int[] { 9,4,9,8,4 };
        int[] res = intersection(n1, n2);
        int a = 1;
    }

    public static int[] intersection(int[] nums1, int[] nums2) {
        // 数组hash
        Set<Integer> s = new HashSet<>();
        for (int n : nums1) {
            s.add(n);
        }
        Set<Integer> res = new HashSet<>();
        for (int n : nums2) {
            if (s.contains(n)) {
                res.add(n);
            }
        }
        int[] arr = new int[res.size()];
        if (arr.length == 0) {
            return arr;
        }
        return res.stream().mapToInt(i -> i).toArray();
    }
}
