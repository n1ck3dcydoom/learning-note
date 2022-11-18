package nowcode.top101;

/**
 * Created by n!Ck
 * Date: 2022/11/18
 * Time: 17:17
 * Description:
 */

public class _51_数组中出现次数超过一半的数字 {

    public static void main(String[] args) {
        System.out.println(MoreThanHalfNum_Solution(new int[]{1, 2, 3, 2, 2, 2, 5, 4, 2}));
    }

    public static int MoreThanHalfNum_Solution(int[] array) {
        // Map<Integer, Integer> hash = new HashMap<>();
        // int n = array.length;
        //
        // for (int i : array) {
        //     if (hash.getOrDefault(i, 0) + 1 > n / 2) {
        //         return i;
        //     } else {
        //         hash.put(i, hash.getOrDefault(i, 0) + 1);
        //     }
        // }
        // return -1;

        // 超过一半的数即众数，最优算法是摩尔投票算法
        if (array.length == 1) {
            return array[0];
        }
        int num = array[0];
        int count = 1;
        for (int i = 1; i < array.length; i++) {
            // 票数到 0 后更新候选人
            if (count == 0) {
                num = array[i];
                count++;
            } else {
                // 如果相同则 +1
                // 如果不相同则 -1
                count += num == array[i] ? 1 : -1;
            }
        }
        // 最后的候选人就是众数
        return num;
    }
}
