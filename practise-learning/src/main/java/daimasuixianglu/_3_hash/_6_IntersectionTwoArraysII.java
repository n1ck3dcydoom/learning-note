package daimasuixianglu._3_hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _6_IntersectionTwoArraysII {

    public int[] intersect(int[] nums1, int[] nums2) {
        // hash计数
        Map<Integer, Integer> hash1 = new HashMap<>();
        Map<Integer, Integer> hash2 = new HashMap<>();
        for (int n : nums1) {
            hash1.put(n, hash1.getOrDefault(n, 0) + 1);
        }
        for (int n : nums2) {
            hash2.put(n, hash2.getOrDefault(n, 0) + 1);
        }
        List<Integer> res = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : hash1.entrySet()) {
            if (hash2.containsKey(entry.getKey())) {
                int count = Math.min(entry.getValue(), hash2.get(entry.getKey()));
                for (int i = 0; i < count; i++) {
                    res.add(entry.getKey());
                }
            }
        }
        return res.stream().mapToInt(i -> i).toArray();
    }
}
