package leetcode.math.medium;

/**
 * Created by n!Ck
 * Date: 2022/11/4
 * Time: 23:13
 * Description:
 */

public class _754_ReachNumber {

    public static void main(String[] args) {
        System.out.println(reachNumber(2));
    }

    public static int reachNumber(int target) {
        // 往一个方向每次走 1,2,3,4,5,...,k,最后到达 t 的位置
        // 即 sum(1,2,3,...,k) == t,求最小的 k 值
        // 对于每次,可以选择 + 或者 - 两个方向
        // 如果 t < 0,则相当于 sum == -t, 里面的每个方向取反即可,根据对称性只用考虑一个方向即可
        // 分情况讨论

        int sum = 0;
        int k = 0;
        int t = Math.abs(target);
        // 为了总次数更小,尽可能累加前面的每一步
        while (sum < t) {
            sum += k++;
        }
        // sum == t
        if (sum == t) {
            return k - 1;
        }
        // sum > t
        // 检查超出的距离 over
        // 如果 over 是个偶数
        // 那么在前面一定能够找到一步 k0 满足 k0 = over / 2,此时在 k0 步反向走即可构造出 -2k0 即 -over 的步长
        // 相当于原来的 k0 反向走,用于构造出 -over 的步长低效最后一步超出的 over
        // 最终步数仍然等于 k 步
        int over = sum - target;
        if (over % 2 == 0) {
            return k - 1;
        }
        // 如果 over 是个奇数
        // 如果第 k 步的步长是个偶数,那么 k+1 步的步长就是奇数,此时再多走一步,就能让 over 变成偶数,回到上面的情况
        // 如果第 k 步的步长是个奇数,那么 k+1 步的步长就是偶数,多走一步的 over 仍然是奇数
        // 此时还需要多走一步 k+2,这样 over 再次变为偶数,回到上面的情况
        return (k - 1) % 2 == 0 ? k : k + 1;
    }
}
