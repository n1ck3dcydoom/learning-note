package leetcode.easy.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanglei
 * @version 1.0
 * @description 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *
 * 示例:
 *
 * 输入: 5
 * 输出:
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/pascals-triangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2020/7/20 19:28
 **/
public class _118_PascalsTriangle {

    public static void main(String[] args) {
        generate(5);
    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        // 第0个直接返回
        if (numRows == 0) {
            return res;
        }
        List<Integer> list0 = new ArrayList<>();
        list0.add(1);
        res.add(list0);

        for (int i = 1; i < numRows; i++) {
            List<Integer> list = new ArrayList<>(i);
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    list.add(1);
                } else {
                    list.add(res.get(i - 1).get(j - 1) + res.get(i - 1).get(j));
                }
            }
            res.add(list);
        }
        return res;
    }
}