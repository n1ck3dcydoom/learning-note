package leetcode.array.easy;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * <p>
 * 给你一个二维整数数组 ranges 和两个整数 left 和 right 。每个 ranges[i] = [starti, endi] 表示一个从 starti 到 endi 的 闭区间 。
 * <p>
 * 如果闭区间 [left, right] 内每个整数都被 ranges 中 至少一个 区间覆盖，那么请你返回 true ，否则返回 false 。
 * <p>
 * 已知区间 ranges[i] = [starti, endi] ，如果整数 x 满足 starti <= x <= endi ，那么我们称整数x 被覆盖了。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：ranges = [[1,2],[3,4],[5,6]], left = 2, right = 5
 * 输出：true
 * 解释：2 到 5 的每个整数都被覆盖了：
 * - 2 被第一个区间覆盖。
 * - 3 和 4 被第二个区间覆盖。
 * - 5 被第三个区间覆盖。
 * 示例 2：
 * <p>
 * 输入：ranges = [[1,10],[10,20]], left = 21, right = 21
 * 输出：false
 * 解释：21 没有被任何一个区间覆盖。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= ranges.length <= 50
 * 1 <= starti <= endi <= 50
 * 1 <= left <= right <= 50
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/check-if-all-the-integers-in-a-range-are-covered
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/7/26 19:01
 **/
public class _1893 {

    public static void main(String[] args) {
        int[][] ranges = new int[][]{{1, 50}};
        System.out.println(isCovered(ranges, 1, 50));
        System.out.println(isCovered1(ranges, 1, 50));

    }

    /**
     * 暴力
     * 遍历所有ranges，使用hash表保存每个出现过的数字
     * 然后检查 [left, right]，只要有一个不存在就返回false
     */
    public static boolean isCovered(int[][] ranges, int left, int right) {
        HashSet<Integer> set = new HashSet<>();

        for (int[] range : ranges) {
            for (int i = range[0]; i <= range[range.length - 1]; i++) {
                set.add(i);
            }
        }
        for (int i = left; i <= right; i++) {
            if (!set.contains(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 对ranges按照左端点从小到大排序
     * 遍历排序后的ranges数组，每次遍历的区间为[l, r]
     * 对于给定区间[left, right]，如果l <= left && left <= r，可以得到从[left, r]的所有数都被覆盖了
     * 需要检查剩余的区间[r+1, right]是否被覆盖
     * 每次更新左端点，如果左端点left能够大于右端点right，则说明ranges能够覆盖到给定区间[left, right]
     */
    public static boolean isCovered1(int[][] ranges, int left, int right) {
        // 对ranges按照左端点从小到大排序
        Arrays.sort(ranges, (r1, r2) -> r1[0] - r2[0]);
        // 遍历每一个ranges
        for (int[] range : ranges) {
            int l = range[0];
            int r = range[range.length - 1];
            // 能够覆盖[left, r]的区间，修改left=r+1，继续检查下一个区间
            if (l <= left && left <= r) {
                left = r + 1;
            }
            // left落在了[l, r]区间内，说明[left, r]区间已经全部覆盖
            // 需要检查[r+1, right]是否被覆盖
            // 移动左端点left = r + 1
            // 即检查r+1是否大于right
            // ？ ？ ？ 为什么不检查r+1 是否等于 right呢
            // 因为每次得到[left, r]被覆盖后，移动left = r+1
            // 如果r==right，那么left=r+1已经大于right
            // 如果r>right，那么left=r+1也已经大于right
            // 如果r<right，那么left=r+1不能得到left>right，需要继续检查下一个range区间
            // 每次检查left是否大于右端点
            if (left > right) {
                return true;
            }
        }
        return false;
    }
}