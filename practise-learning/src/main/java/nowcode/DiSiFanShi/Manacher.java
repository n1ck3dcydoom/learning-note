package nowcode.DiSiFanShi;

import java.util.Scanner;

/**
 * Created by n!Ck
 * Date: 2019-02-24
 * Time: 21:13
 * Description:
 * 求字符串中最长回文子串
 * 马拉车算法
 */
public class Manacher {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String str = scanner.nextLine();
            char[] strChars = str.toCharArray();

            // 1. 把原字符串S[i]每个字符间隔插入#，首尾也插入#，变为T[i]
            // 由于第一个和最后一个字符都是#号，且也需要搜索回文
            // 为了防止越界，我们还需要在首尾再加上非#号字符，开头需要加上另一个非#字符
            StringBuilder stringBuilder = new StringBuilder("$#");
            for (char strChar : strChars) {
                stringBuilder.append(strChar).append("#");
            }
            stringBuilder.append("&");
            char[] t = stringBuilder.toString().toCharArray();
            System.out.println(t);

            // 2. 我们定义mi为之前取得最大回文串的中心位置，而mr是最大回文串能到达的最右端的值
            // 使用数组P[i]保存当前新字符串T的T[i]处的回文半径
            // 如果P[i] = 1 ，则表示当前位置i的最长回文子串为本身
            // index  0  1  2  3  4  5  6  7  8  9  10 11 12
            // T[i]   #  a  #  b  #  a  #  a  #  b  #  a  #
            // P[i]   1  2  1  4  1  2  7  2  1  2  1  4  1
            // 数组P有一性质，P[i]-1就是该回文子串在原字符串S中的长度
            // 那就是P[i]-1就是该回文子串在原字符串S中的长度

            int[] p = new int[t.length];
            p[0] = 0;

            int mi = 0; // mi为之前最大回文串对应的中心点
            int mr = 0; // mr为之前回文串能达到的最右端的值
            int maxLen = 0; // maxLen为已知最长回文子串的长度
            int maxPoint = 0; // maxPoint为已知最长回文子串的中心点

            for (int i = 1; i < t.length; i++) {
                // 根据i的对称点j确定p[i]的值
                p[i] = mr > i ? Math.min(p[2 * mi - i], mr - i) : 1;
                // 从i-p[i]继续往两边搜索可能更长的回文串
                while ((i - p[i] >= 0) && (i + p[i] < t.length - 1) && t[i - p[i]] == t[i + p[i]]) {
                    p[i]++;
                }
                // 如果新的回文串最右端超过了已知回文串的最右端，则更新mr和mi的值
                if (i + p[i] > mr) {
                    mr = i + p[i];
                    mi = i;
                }
                // 如果新的回文串长度大于目前已知的最长长度，则更新最长值和最长子串的中心点
                if (p[i] > maxLen) {
                    maxLen = p[i];
                    maxPoint = i;
                }
            }

            // 3. 还原子串
            // 最长子串的长度是半径减1，起始位置是中间位置减去半径再除以2。
            String strrr = new String(strChars, (maxPoint - maxLen) / 2, maxLen - 1);
            System.out.println(strrr);
        }
    }
}
