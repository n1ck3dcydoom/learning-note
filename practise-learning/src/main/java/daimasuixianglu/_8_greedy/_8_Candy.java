package daimasuixianglu._8_greedy;

import java.util.Arrays;

public class _8_Candy {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 4, 5, 2};
        System.out.println(candy(nums));
    }

    public static int candy(int[] ratings) {
        // 贪心，首先每个小朋友初始化1个糖果
        // 从前往后检查每个小朋友的右侧，如果r[i]>r[i-1]，则c[i]=c[i-1]+1
        // 从后往前检查每个小朋友的左侧，如果r[i]>r[i+1]，则c[i]=c[i+1]+1
        // 检查左侧为什么需要从后往前？
        // 为了使用上一轮的结果
        // 如果从前往后：例如当前i，需要检查i+1，此时的i+1还没有赋值
        // 如果从后往前，例如当前i，需要检查i+1，此时i+1在上一轮已经完成了赋值

        int n = ratings.length;
        // 初始化
        int[] candy = new int[n];
        Arrays.fill(candy, 1);
        // 检查右侧，从前往后遍历，为了在i的时候，使用i-1的结果
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candy[i] = candy[i - 1] + 1;
            }
        }
        // 检查左侧，从后往前遍历，为了在i的时候，使用i+1的结果
        for (int i = n - 2; i >= 0; i--) {
            // 第一遍扫描已经修改了candy数组
            // 第二次扫描的时候仅仅在左侧小朋友成绩更好，但是糖果数小于等于右侧小朋友的时候，才更新candy数组
            if (ratings[i] > ratings[i + 1] && candy[i] <= candy[i + 1]) {
                candy[i] = candy[i + 1] + 1;
            }
        }
        // 统计最后的结果
        int res = 0;
        for (int c : candy) {
            res += c;
        }
        return res;
    }
}
