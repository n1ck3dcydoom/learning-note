package leetcode.greedy.medium;

/**
 * Created by n!Ck
 * Date: 2022/1/12
 * Time: 0:19
 * Description:
 */

public class _334_IncreasingTripletSubsequence {

    public boolean increasingTriplet(int[] nums) {
        // 直接暴力查找
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] < nums[j] && nums[j] < nums[k]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean increasingTriplet1(int[] nums) {
        // 果不其然暴力超时了
        int n = nums.length;
        if (n < 3) {
            return false;
        }
        // 扫描一遍，维护遇到的最小值次小值，如果在后面还能找到比次小值更大的数，则返回true
        int fmin = nums[0];
        int smin = Integer.MAX_VALUE, max;
        for (int i = 1; i < n; i++) {
            int cur = nums[i];
            // 如果当前数比次小值还要大，说明存在递增三元组
            if (cur > smin) {
                return true;
            }
            // 如果当前值比次小值小，但是大于最小值，则更新次小值
            else if (fmin < cur && cur <= smin) {
                smin = cur;
            }
            // 如果当前值比最小值都还要小，则更新最小值
            // 为什么这一步成立?
            // 1. 当fmin < smin ，此时fmin的索引也是严格小于smin索引
            // 2. 当num < fmin时，更新fmin=num，此时smin的索引严格小于num的索引
            // 假设后面存在smin < num' ，此时num'的索引严格大于smin
            // 虽然从索引上来说 smin < num(fmin) < num'
            // 但是根据1说明在smin前面有更小的数fmin(曾经出现过，虽然num比它更小)
            // 也就是说有 fmin < smin < num'成立，而且所以上fmin < smin < num' 也是成立的
            else if (cur <= fmin) {
                fmin = cur;
            }
        }
        return false;
    }
}
