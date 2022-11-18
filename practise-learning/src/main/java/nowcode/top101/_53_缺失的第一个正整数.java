package nowcode.top101;

/**
 * Created by n!Ck
 * Date: 2022/11/18
 * Time: 17:47
 * Description:
 */

public class _53_缺失的第一个正整数 {

    public int minNumberDisappeared(int[] nums) {
        // 原地哈希
        int n = nums.length;
        // 跳过下标 0
        int[] hash = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            hash[i] = -1;
        }
        for (int num : nums) {
            // 跳过所有负数
            // 跳过所有超过 n 的数 ,例如对于 34567 来说,映射关系为 [-1,-1,-1,3,4]
            if (num > 0 && num <= n) {
                hash[num] = num;
            }
        }
        for (int i = 1; i <= n; i++) {
            if (hash[i] == -1) {
                return i;
            }
        }
        // 前 1~n 个数都映射了,则缺少的就是 n+1
        return n + 1;
    }
}
