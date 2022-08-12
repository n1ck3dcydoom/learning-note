package leetcode.string.hard;

import java.util.Arrays;

/**
 * Created by n!Ck
 * Date: 2022/8/12
 * Time: 09:45
 * Description:
 */

public class _899_OrderlyQueue {

    public static void main(String[] args) {
        System.out.println(orderlyQueue("baaca", 2));
    }

    public static String orderlyQueue(String s, int k) {
        // k==1 的时候,相当于环形链表,每次移动一个位置,循环一圈后找最小的字典序
        // k>1 的时候,一定可以交换两个相邻元素,相当于实现冒泡排序,所以可以直接按照最小字典序排序
        // 举个例子,例如 bdcae ,我们希望交换 c 和 a,依次执行如下操作
        // 将 bd 移到后面,使 ca 移到最前面,得到 caebd
        // 先移动 a,再移动 c, 实现交换,得到 ebdac
        // 其他位置复原,即将 e 移动到后面,得到 bdace,完成交换
        //
        // 作者：heren1229
        // 链接：https://leetcode.cn/problems/orderly-queue/solution/nao-jin-ji-zhuan-wan-by-heren1229-gg97/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权,非商业转载请注明出处。

        if (k == 1) {
            String min = s;
            StringBuilder sb = new StringBuilder(s);
            for (int i = 0; i < s.length(); i++) {
                sb.deleteCharAt(0);
                sb.append(s.charAt(i));
                min = min.compareTo(sb.toString()) < 0 ? min : sb.toString();
            }
            return min;
        }
        char[] cs = s.toCharArray();
        Arrays.sort(cs);
        return new String(cs);
    }
}
