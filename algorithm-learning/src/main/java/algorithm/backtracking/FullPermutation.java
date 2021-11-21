package algorithm.backtracking;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description 全排列问题
 * 回溯算法
 * <p>
 * 框架：
 * for 选择 in 选择列表:
 * # 做选择
 * 将该选择从选择列表移除
 * 路径.add(选择)
 * backtrack(路径, 选择列表)
 * # 撤销选择
 * 路径.remove(选择)
 * 将该选择再加入选择列表
 * @date 2020/4/28 23:09
 **/
public class FullPermutation {

    public static void main(String[] args) {
        int[] nums;
        int count;
        // 输入一组不重复的数字，返回它们的全排列组合
        try (Scanner scanner = new Scanner(System.in)) {
            count = scanner.nextInt();
            nums = new int[count];
            for (int i = 0; i < count; i++) {
                nums[i] = scanner.nextInt();
            }
        }
        // 选择列表nums[]中所有不为-1的元素
        // 路径track中的元素
        // 回溯点nums[]的所有元素出现在track中
        LinkedList<Integer> track = new LinkedList<>();
        fullPermutation(nums, track);
    }

    //    for 选择 in 选择列表:
    //        # 做选择
    //        将该选择从选择列表移除
    //
    //        路径.add(选择)
    //        backtrack(路径, 选择列表)
    //
    //        # 撤销选择
    //        路径.remove(选择)
    //        将该选择再加入选择列表

    /**
     * 回溯法
     *
     * @param nums  选择列表
     * @param track 路径
     */
    private static void fullPermutation(int[] nums, LinkedList<Integer> track) {
        // 结束条件
        if (nums.length == track.size()) {
            track.forEach(e -> System.out.print(e + " "));
            System.out.println("\n");
            return;
        }
        // 开始遍历选择
        for (int num : nums) {
            // 做出选择
            if (track.contains(num)) {
                continue;
            }
            // 路径中没有当前选择时，加入当前选择
            track.add(num);
            // 做出选择后开始递归
            fullPermutation(nums, track);
            // 递归完，即遇到回溯点之后
            // 将当前选择移除路径，然后重新加入选择列表
            track.removeLast();
        }
    }
}