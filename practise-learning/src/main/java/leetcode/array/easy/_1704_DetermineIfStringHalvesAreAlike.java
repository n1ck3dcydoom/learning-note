package leetcode.array.easy;

/**
 * Created by n!Ck
 * Date: 2022/11/11
 * Time: 22:55
 * Description:
 */

public class _1704_DetermineIfStringHalvesAreAlike {

    public boolean halvesAreAlike(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length;

        int a = 0, b = 0;
        for (int i = 0; i < n; i++) {
            boolean flag = cs[i] == 'a' || cs[i] == 'e' || cs[i] == 'i' || cs[i] == 'o' || cs[i] == 'u' ||
                    cs[i] == 'A' || cs[i] == 'E' || cs[i] == 'I' || cs[i] == 'O' || cs[i] == 'U';
            if (i < n / 2) {
                if (flag) {
                    a++;
                }
            } else {
                if (flag) {
                    b++;
                }
            }
        }
        return a == b;
    }
}
