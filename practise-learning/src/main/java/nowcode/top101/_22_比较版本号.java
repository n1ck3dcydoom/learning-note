package nowcode.top101;

/**
 * Created by n!Ck
 * Date: 2022/11/17
 * Time: 17:18
 * Description:
 */

public class _22_比较版本号 {

    public static void main(String[] args) {
        System.out.println(compare("1.0", "1.0.0"));
    }

    public static int compare(String version1, String version2) {
        String[] s1 = version1.split("\\.");
        String[] s2 = version2.split("\\.");
        int m = s1.length;
        int n = s2.length;

        int k = Math.min(m, n);
        int i = 0;
        while (i < k) {
            int v1 = Integer.parseInt(s1[i]);
            int v2 = Integer.parseInt(s2[i]);
            if (v1 < v2) {
                return -1;
            } else if (v1 > v2) {
                return 1;
            }
            i++;
        }
        // 如果版本号还有没比完的部分继续比较剩下的
        if (m > n) {
            int v2 = 0;
            while (i < m) {
                int v1 = Integer.parseInt(s1[i++]);
                if (v1 > v2) {
                    return 1;
                }
            }
        } else if (m < n) {
            int v1 = 0;
            while (i < n) {
                int v2 = Integer.parseInt(s2[i++]);
                if (v2 > v1) {
                    return -1;
                }
            }
        }
        return 0;
    }
}
