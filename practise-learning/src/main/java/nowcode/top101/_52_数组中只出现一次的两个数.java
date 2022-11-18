package nowcode.top101;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by n!Ck
 * Date: 2022/11/18
 * Time: 17:30
 * Description:
 */

public class _52_数组中只出现一次的两个数 {

    public int[] FindNumsAppearOnce(int[] array) {
        // 异或运算支持交换律
        // a ^ b ^ c ^ b ^ c ^ d = b ^ b ^ c ^ c ^ a ^ d
        // b ^ b = 0
        // 0 ^ a = a
        // 所以 左 = 0 ^ 0 ^ a ^ d = a ^ d
        // 后面的想不到了，直接上 hash 吧
        Map<Integer, Integer> hash = new HashMap<>();
        for (int i : array) {
            if (hash.containsKey(i)) {
                hash.remove(i);
            } else {
                hash.put(i, i);
            }
        }
        List<Integer> res = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : hash.entrySet()) {
            res.add(entry.getKey());
        }
        int a = res.get(0);
        int b = res.get(1);
        return a > b ? new int[]{b, a} : new int[]{a, b};
    }
}
