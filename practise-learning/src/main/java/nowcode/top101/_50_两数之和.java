package nowcode.top101;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by n!Ck
 * Date: 2022/11/18
 * Time: 17:05
 * Description:
 */

public class _50_两数之和 {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 2, 4};
        System.out.println(twoSum(arr, 6));
    }

    public static int[] twoSum(int[] numbers, int target) {
        // hash
        Map<Integer, Integer> hash = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            if (hash.containsKey(target - numbers[i])) {
                return new int[]{hash.get(target - numbers[i]) + 1, i + 1};
            }
            hash.put(numbers[i], i);
        }
        return null;
    }
}
