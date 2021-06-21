package leetcode.dfs.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * <p>
 * 二进制手表顶部有 4 个 LED 代表 小时（0-11），底部的 6 个 LED 代表 分钟（0-59）。每个 LED 代表一个 0 或 1，最低位在右侧。
 * <p>
 * 例如，下面的二进制手表读取 "3:25" 。
 * <p>
 * 给你一个整数 turnedOn ，表示当前亮着的 LED 的数量，返回二进制手表可以表示的所有可能时间。你可以 按任意顺序 返回答案。
 * <p>
 * 小时不会以零开头：
 * <p>
 * 例如，"01:00" 是无效的时间，正确的写法应该是 "1:00" 。
 * 分钟必须由两位数组成，可能会以零开头：
 * <p>
 * 例如，"10:2" 是无效的时间，正确的写法应该是 "10:02" 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：turnedOn = 1
 * 输出：["0:01","0:02","0:04","0:08","0:16","0:32","1:00","2:00","4:00","8:00"]
 * 示例 2：
 * <p>
 * 输入：turnedOn = 9
 * 输出：[]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= turnedOn <= 10
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-watch
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/6/21 21:42
 **/
public class _401_BinaryWatch {

    public static void main(String[] args) {
        List<String> res = readBinaryWatch(9);
        for (String str : res) {
            System.out.print(str + " ");
        }
    }

    public static List<String> readBinaryWatch(int turnedOn) {
        List<String> res = new ArrayList<>();
        // 所有灯对应的数组，前1，2，4，8代表小时，后1，2，4，8，16，32代表分钟
        int[] lights = new int[]{1, 2, 4, 8, 1, 2, 4, 8, 16, 32};

        dfs(res, lights, turnedOn, 0, 0, 0);
        return res;
    }

    /**
     * dfs
     * 结果集 res
     * 可供选择 小时数组、分时数组
     * 路径 剩余的灯和已选择的小时、分时数据
     */
    private static void dfs(List<String> res, int[] lights, int turnedOn, int index, int hour, int min) {
        if (hour > 11 || min > 59) {
            return;
        }
        // 如果亮着的灯都遍历完，记录路径并且返回上一次dfs
        if (turnedOn == 0) {
            StringBuilder sb = new StringBuilder();
            // 添加小时数据
            sb.append(hour).append(":");
            // 分时数据小于10的话需要在前面补0
            if (min < 10) {
                sb.append("0").append(min);
            } else {
                sb.append(min);
            }
            res.add(sb.toString());
            return;
        }
        // 遍历剩余的灯，需要通过一个指针记录当前遍历到的灯的位置
        for (int i = index; i < 10; i++) {
            // 做出选择，添加新选择到路径中
            if (i < 4) {
                hour += lights[i];
            } else {
                min += lights[i];
            }
            // 将当前新路径带入下一次dfs
            dfs(res, lights, turnedOn - 1, i + 1, hour, min);
            // 撤销选择
            if (i < 4) {
                hour -= lights[i];
            } else {
                min -= lights[i];
            }
        }
    }
}