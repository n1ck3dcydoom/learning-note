package daimasuixianglu._8_greedy;

public class _5_JumpGameII {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 1, 1, 4};
        System.out.println(jump(nums));
    }

    public static int jump(int[] nums) {
        // 贪心，从i出发，能够走最远距离为i+nums[i]
        // 遍历i~i+nums[i]，找到下一个能走得最远的位置k，从i跳到k

        int n = nums.length;
        if (n == 1) {
            return 0;
        }
        // 找从i出发能够走到的最远位置
        // 遇到当前位置能够到达的最远位置后，决定要不要从i跳到k
        int maxPos = 0;
        int curMaxPos = 0;
        int step = 0;
        for (int i = 0; i <= maxPos; i++) {
            maxPos = Math.max(i + nums[i], maxPos);
            // 遇到从i能够到达的最远位置后，更新下一次能够到达的最远距离，步数+1
            if (i == curMaxPos) {
                curMaxPos = maxPos;
                // 如果最远距离已经覆盖最后一个位置了
                if (curMaxPos >= n - 1) {
                    return ++step;
                }
                step++;
            }
        }
        return step;
    }
}
