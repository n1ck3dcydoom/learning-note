package leetcode.array.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by n!Ck
 * Date: 2022/9/19
 * Time: 20:12
 * Description:
 */

public class _1636_SortArrayByIncreasingFrequency {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(frequencySort(new int[]{-1, 1, -6, 4, 5, -6, 1, 4, 1})));
    }

    public static int[] frequencySort(int[] nums) {
        // hash 计数
        Map<Integer, Integer> hash = new HashMap<>();
        for (int n : nums) {
            hash.put(n, hash.getOrDefault(n, 0) + 1);
        }
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
        list.sort((a, b) -> {
            if (hash.get(a) > hash.get(b)) {
                return 1;
            } else if (hash.get(a) < hash.get(b)) {
                return -1;
            } else {
                return b - a;
            }
        });
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
