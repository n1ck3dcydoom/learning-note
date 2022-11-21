package nowcode.top101;

/**
 * Created by n!Ck
 * Date: 2022/11/20
 * Time: 20:05
 * Description:
 */

public class _88_判断是否是回文字符串 {

    public static boolean judge(String str) {
        // 直接双指针从两端往中间查找
        int n = str.length();
        char[] cs = str.toCharArray();
        int i = 0, j = n - 1;
        while (i <= j) {
            if (cs[i++] != cs[j--]) {
                return false;
            }
        }
        return true;
    }
}
