package leetcode.array.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by n!Ck
 * Date: 2022/9/22
 * Time: 22:11
 * Description:
 */

public class _1640_CheckArrayFormationThroughConcatenation {

    public static void main(String[] args) {
        int[] arr = new int[]{91, 52, 13, 4, 64, 78};
        int[][] pieces = new int[][]{{78}, {4, 64}, {91, 52, 13}};
        System.out.println(canFormArray(arr, pieces));
    }

    public static boolean canFormArray(int[] arr, int[][] pieces) {
        // 使用hash表记录pieces每个数组开头元素和和数组的关系
        Map<Integer, int[]> hash = new HashMap<>();
        for (int[] piece : pieces) {
            // 题干保证每个元素都不相同，不用考虑hash冲突
            hash.put(piece[0], piece);
        }
        // 题干允许以 “任何顺序” 连接pieces的数组，但是不允许对pieces数组里面的元素重新排序
        // 遍历arr，检查hash里面数组的首个元素是否出现，即以所有piece[0]将arr切割为piece数组
        // [91,52,13,4,64,78]  [[78],[4,64],[91,52,13]]
        // [91,52,13],[4,64],[78]
        // [91         4      78]

        // 检查piece的时候令i自增，遍历arr的for循环不再更新i
        for (int i = 0; i < arr.length; ) {
            // 如果hash没有包含arr[i]的首元素，直接返回false
            if (!hash.containsKey(arr[i])) {
                return false;
            }
            // 检查piece数组是否完全和arr后k个元素匹配
            int[] piece = hash.get(arr[i]);
            for (int j = 0; j < piece.length; j++, i++) {
                if (piece[j] != arr[i]) {
                    return false;
                }
            }
        }
        return true;
    }
}
