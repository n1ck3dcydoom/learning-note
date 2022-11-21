package nowcode.top101;

/**
 * Created by n!Ck
 * Date: 2022/11/20
 * Time: 17:26
 * Description:
 */

public class _83_字符串变形 {

    public static void main(String[] args) {
        System.out.println(trans("This is a sample", 16));
    }

    public static String trans(String s, int n) {
        StringBuilder sb = new StringBuilder();
        char[] cs = s.toCharArray();
        for (int i = 0; i < n; i++) {
            if (cs[i] == ' ') {
                sb.append(cs[i]);
            } else if ('a' <= cs[i] && cs[i] <= 'z') {
                sb.append((char) (cs[i] - 32));
            } else if ('A' <= cs[i] && cs[i] <= 'Z') {
                sb.append((char) (cs[i] + 32));
            }
        }
        // 反转整个字符串
        sb = sb.reverse();
        cs = sb.toString().toCharArray();
        // 翻转每个单词
        for (int i = 0; i < n; i++) {
            // 找到空格
            int j = i;
            while (j < n && cs[j] != ' ') {
                j++;
            }
            String tmp = sb.toString().substring(i, j);
            StringBuilder sb2 = new StringBuilder(tmp);
            sb2 = sb2.reverse();
            sb.replace(i, j, sb2.toString());
            i = j;
        }
        return sb.toString();
    }
}
