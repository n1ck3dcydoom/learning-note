package daimasuixianglu._8_greedy;

import java.util.Arrays;

/**
 * @author n!Ck
 * @version 1.0
 * @description TODO
 * @date 2022/2/18 20:24
 **/

public class _1_AssignCookies {

    public int findContentChildren(int[] g, int[] s) {
        // 饼干大小和胃口排序后遍历，尽可能找最符合胃口的饼干
        Arrays.sort(g);
        Arrays.sort(s);

        int index = 0;
        for (int i = 0; i < s.length; i++) {
            // 如果所有小孩都已经得到满足
            if (index == g.length) {
                return index;
            }
            if (s[i] >= g[index]) {
                index++;
            }
        }
        return index;
    }
}
