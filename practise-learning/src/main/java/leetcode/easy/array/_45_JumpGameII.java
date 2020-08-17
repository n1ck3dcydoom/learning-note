package leetcode.easy.array;

/**
 * @author zhanglei
 * @version 1.0
 * @description 给定一个非负整数数组，你最初位于数组的第一个位置。
 *              <p>
 *              数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *              <p>
 *              你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 *              <p>
 *              示例:
 *              <p>
 *              输入: [2,3,1,1,4] 输出: 2 解释: 跳到最后一个位置的最小跳跃数是 2。   从下标为 0 跳到下标为 1
 *              的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。 说明:
 *              <p>
 *              假设你总是可以到达数组的最后一个位置。
 *              <p>
 *              来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/jump-game-ii
 *              著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2020/8/17 21:22
 **/
public class _45_JumpGameII {

    public static void main(String[] args) {
        int[] nums = new int[] { 2, 3, 1, 1, 4, 2, 1 };
        System.out.println(jump(nums));
    }

    /**
     * 贪心算法，在当前可到达的范围内，每次找最大的数跳过去
     */
    public static int jump(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }
        // 当前坐标能到达的最远位置，初始化为nums[0]
        int currentMaxLength = nums[0];
        // 每一步跳到的具体位置
        int index = 0;
        // 需要跳的步数，最少都有一步
        int count = 1;
        // 不需要访问最后一个位置，如果当前跳到最后一格，不用再跳一次了
        while (currentMaxLength < nums.length - 1) {
            // 当前能走到的最远位置，需要遍历(当前位置，当前位置能到达的最远位置]区间(左开右闭，左+1)，找里面能跳的最远位置
            int maxLenght = 0;
            // 开始遍历[当前位置，当前位置能到达的最远位置]区间，贪心找里面能跳的最远位置
            for (int i = index + 1; i <= currentMaxLength; i++) {
                // 更新能走到的最远位置
                if (i + nums[i] > maxLenght) {
                    maxLenght = i + nums[i];
                    // 找到能去到最远位置的位置
                    index = i;
                }
            }
            // 跳到上面找到的能去到最远位置的位置
            count++;
            // 更新从当前位置能去到的最远位置
            currentMaxLength = index + nums[index];
        }
        return count;
    }
}