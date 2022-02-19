package daimasuixianglu._8_greedy;

public class _4_JumpGame {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 1, 1, 4};
        boolean can = canJump(nums);
        int a = 1;
    }

    public static boolean canJump(int[] nums) {
        // 贪心，判断每次能够走到的最远距离是否能够达到最后一个位置
        int n = nums.length;
        int maxPos = 0;
        // 在i能够走到的最大范围内不断地寻找更远的距离，所以终点是maxPos，而非n
        for (int i = 0; i <= maxPos; i++) {
            // 更新当前能够走到的最远距离
            maxPos = Math.max(i + nums[i], maxPos);
            // 判断是否能够覆盖到最后一个位置
            if (maxPos >= n - 1) {
                return true;
            }
        }
        return false;
        // 上述for循环可以修改为遍历每个位置，跳出循环的条件需要修改下
        // for (int i = 0; i < n; i++) {
        //     // 如果i遍历到了一个比最大距离还要远的位置，说明通过前面的努力已经只能走到maxPos
        //     // 永远走不到n-1了
        //     if (i > maxPos) {
        //         return false;
        //     }
        //     // 更新最远距离
        //     maxPos = Math.max(i + nums[i], maxPos);
        // }
        // return true;
    }
}
