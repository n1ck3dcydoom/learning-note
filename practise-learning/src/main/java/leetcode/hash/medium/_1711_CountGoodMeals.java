package leetcode.hash.medium;

import java.util.HashMap;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * <p>
 * 大餐 是指 恰好包含两道不同餐品 的一餐，其美味程度之和等于 2 的幂。
 * <p>
 * 你可以搭配 任意 两道餐品做一顿大餐。
 * <p>
 * 给你一个整数数组 deliciousness ，其中 deliciousness[i] 是第 i​​​​​​​​​​​​​​ 道餐品的美味程度，返回你可以用数组中的餐品做出的不同 大餐 的数量。结果需要对 10^9 + 7 取余。
 * <p>
 * 注意，只要餐品下标不同，就可以认为是不同的餐品，即便它们的美味程度相同。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：deliciousness = [1,3,5,7,9]
 * 输出：4
 * 解释：大餐的美味程度组合为 (1,3) 、(1,7) 、(3,5) 和 (7,9) 。
 * 它们各自的美味程度之和分别为 4 、8 、8 和 16 ，都是 2 的幂。
 * 示例 2：
 * <p>
 * 输入：deliciousness = [1,1,1,3,3,3,7]
 * 输出：15
 * 解释：大餐的美味程度组合为 3 种 (1,1) ，9 种 (1,3) ，和 3 种 (1,7) 。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= deliciousness.length <= 10^5
 * 0 <= deliciousness[i] <= 2^20
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-good-meals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/7/7 10:34
 **/
public class _1711_CountGoodMeals {

    public static void main(String[] args) {
        int[] d = new int[]{1, 1, 1, 3, 3, 3, 7};
        System.out.println(countPairs(d));
    }

    public static int countPairs(int[] deliciousness) {
        int mod = 1000000007;

        int n = deliciousness.length;
        long res = 0;

        // 使用hash表存储每个元素出现的次数
        HashMap<Integer, Integer> hash = new HashMap<>();
        // 遍历菜品
        for (int i : deliciousness) {
            // 枚举所有可能出现的2的幂次方
            // 题目中给出的数据是deliciousness[i] <= 2^20
            // 最大的2的幂次方也不会超过2^21，即两个2^20
            int power = 1;
            for (int j = 0, p = 1; j <= 21; j++) {
                if (power >= i) {
                    res += hash.getOrDefault(power - i, 0);
                }
                power *= 2;
            }
            hash.put(i, hash.getOrDefault(i, 0) + 1);
        }
        return (int) (res % mod);
    }

    /**
     * 判断一个数是否是2的幂次方
     * 所有2的整数幂次方的二进制都是以1开头，其余位数0
     * <p>
     * 小于1的自然不是2的幂次方
     */
    private boolean isPowerOfTwo(long i) {
        String binaryString = Long.toBinaryString(i);
        if (i < 1) {
            return false;
        }
        return binaryString.lastIndexOf("1") == 0;
    }

    private static boolean isPowerOfTow1(long i) {
        if (i == 0) {
            return false;
        }
        return (i & (i - 1)) == 0;
    }
}