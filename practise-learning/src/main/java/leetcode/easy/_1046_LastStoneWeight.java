package leetcode.easy;

import java.util.Arrays;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description 有一堆石头，每块石头的重量都是正整数。
 * <p>
 * 每一回合，从中选出两块 最重的 石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 * <p>
 * 如果 x == y，那么两块石头都会被完全粉碎；
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。
 * <p>
 * 示例：
 * <p>
 * 输入：[2,7,4,1,8,1]
 * 输出：1
 * 解释：
 * 先选出 7 和 8，得到 1，所以数组转换为 [2,4,1,1,1]，
 * 再选出 2 和 4，得到 2，所以数组转换为 [2,1,1,1]，
 * 接着是 2 和 1，得到 1，所以数组转换为 [1,1,1]，
 * 最后选出 1 和 1，得到 0，最终数组转换为 [1]，这就是最后剩下那块石头的重量。
 * <p>
 * 提示：
 * <p>
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/last-stone-weight
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/1/4 20:56
 **/
public class _1046_LastStoneWeight {

    public static void main(String[] args) {
        int[] stones = new int[]{2, 7, 4, 1, 8, 1};
        System.out.println(lastStoneWeight(stones));
    }

    // 1、从小到大排序后，比较最后两个石头
    // 2、如果差值等于0，表示摧毁两块石头，倒数第一和倒数第二位置放0，然后执行1
    // 3、如果差值不等于0，则把差值放到倒数第二个位置，0放到最后一个位置，然后执行1
    // 需要一个count变量记录所剩石头的个数，第二步count-2，第三步count-1
    // 如果count=0，则返回0，如果count=1，则返回最后一个石头的重量
    public static int lastStoneWeight(int[] stones) {
        if (stones == null || stones.length == 0) {
            return 0;
        }
        int n = stones.length;
        if (n == 1) {
            return stones[0];
        }
        int count = n;
        Arrays.sort(stones);
        while (count > 1) {
            int last = n - 1;
            int preLast = last - 1;
            if (stones[last] == stones[preLast]) {
                stones[last] = 0;
                stones[preLast] = 0;
                count -= 2;
            } else {
                stones[preLast] = stones[last] - stones[preLast];
                stones[last] = 0;
                count -= 1;
            }
            // 排序
            Arrays.sort(stones);
        }
        if (count == 0) {
            return 0;
        } else {
            return stones[n - 1];
        }
    }
}