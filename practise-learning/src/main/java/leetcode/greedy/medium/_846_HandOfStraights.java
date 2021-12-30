package leetcode.greedy.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class _846_HandOfStraights {

    public static void main(String[] args) {
        int[] h1 = new int[]{1, 2, 3, 4, 5};
        System.out.println(isNStraightHand(h1, 4));
    }

    public static boolean isNStraightHand(int[] hand, int groupSize) {
        // 排序+贪心
        Arrays.sort(hand);
        // hash计数
        Map<Integer, Integer> hash = new HashMap<>();
        for (int num : hand) {
            hash.put(num, hash.getOrDefault(num, 0) + 1);
        }
        for (int h : hand) {
            if (hash.get(h) > 0) {
                for (int j = 0; j < groupSize; j++) {
                    if (hash.getOrDefault(h + j, 0) > 0) {
                        hash.put(h + j, hash.get(h + j) - 1);
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
