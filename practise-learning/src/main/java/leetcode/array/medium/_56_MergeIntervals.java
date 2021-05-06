package leetcode.array.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0
 * @description leetcode
 * <p>
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2：
 * <p>
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 104
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-intervals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/5/6 20:00
 **/
public class _56_MergeIntervals {
    public int[][] merge(int[][] intervals) {
        // 按照第一个元素排序，如果第一个元素相等，按照第二个元素排序
        Arrays.sort(intervals, (n1, n2) -> {
            if (n1[0] == n2[0]) {
                return n1[1] - n2[1];
            } else {
                return n1[0] - n2[0];
            }
        });
        List<int[]> res = new ArrayList<>();
        // 最坏的情况，输入的每一个区间都互相不重合
        int[][] resArray = new int[intervals.length][];
        // 保存最后一个区间的指针
        // 由于第一次总是需要把第一个区间加入res集合中，所以起始的最后一个区间位置不能是0
        int index = -1;
        // 遍历输入数组
        for (int[] array : intervals) {
            // 如果res结果为空，或者当前遍历的区间的起始位置大于了res最后一个区间的结束位置
            //                             [8,10]                    [1,6]   即8>6
            // 直接加入res数组，不需要合并
            if (index == -1 || array[0] > resArray[index][1]) {
                // 先让index后移，再赋值
                resArray[++index] = array;
            } else {
                // 找到res集合中最后一个加入的或者合并的区间，索引就是上面的index
                // 然后根据合并的条件判断是否需要向右扩展
                resArray[index][1] = Math.max(resArray[index][1], array[1]);
            }
        }
        // 遍历输入数组
        //        for (int[] array : intervals) {
        //            // 如果res结果为空，或者当前遍历的区间的起始位置大于了res最后一个区间的结束位置
        //            //                             [8,10]                    [1,6]   即8>6
        //            // 直接加入res数组，不需要合并
        //            if (res.size() == 0 || array[0] > res.get(res.size() - 1)[1]) {
        //                res.add(array);
        //            } else {
        //                // 获得res中最后一个区间
        //                int[] lastArray = res.get(res.size() - 1);
        //                res.remove(res.size() - 1);
        //                // 判断合并后的最后一个区间的结束位置是否需要向右扩展
        //                // [1,3] [2,6] -> [1,6]
        //                // [3,7] [4,5] -> [3,7]
        //                // [3,7] [5,7] -> [3,7]
        //                lastArray[1] = Math.max(lastArray[1], array[1]);
        //                res.add(lastArray);
        //            }
        //        }
        //        return res.toArray(new int[res.size()][]);
        return Arrays.copyOf(resArray, index + 1);
    }
}