package nowcode.LeXinFintech;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by n!Ck
 * Date: 2019-02-03
 * Time: 14:48
 * Description:
 * 给定一个字符串str，和一个字母ch，请实现相应的代码求出一个数组，使数组中每个数字表示该位置与字母ch之间的最短距离。
 * 比如str=”nowcode.LeXinFintech”  ch=”i”
 * 则输出为：[3,2,1,0,1,1,0,1,2,3,4,5]
 */
public class StrDistance {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char[] str = scanner.nextLine().toCharArray();
        char ch = scanner.nextLine().toCharArray()[0];
        int[] result = new int[str.length];

        // 确定str中ch的位置
        List<Integer> chPosArray = new ArrayList<>();
        for (int i = 0; i < str.length; i++) {
            if (str[i] == ch) {
                chPosArray.add(i);
            }
        }

        // 遍历str数组
        for (int i = 0, k = 0; i < str.length; i++) {
            if (chPosArray.contains(i)) {
                k++;
            } else if (i < chPosArray.get(0)) {
                // 头部
                result[i] = chPosArray.get(0) - i;
            } else if (i > chPosArray.get(chPosArray.size() - 1)) {
                // 尾部
                result[i] = i - chPosArray.get(chPosArray.size() - 1);
            } else {
                // 中间
                for (int j = i; j < chPosArray.get(k); j++) {
                    int m = j - chPosArray.get(k - 1);
                    int n = chPosArray.get(k) - j;
                    result[j] = m - n > 0 ? n : m;
                }
                // 让i跳到下一个ch之前，此次for循环结束后i会加1到达下一个ch的位置令k+1
                i = chPosArray.get(k) - 1;
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < result.length - 1; i++) {
            stringBuilder.append(result[i]).append(",");
        }
        stringBuilder.append(result[result.length - 1]).append("]");
        System.out.println(stringBuilder.toString());
    }
}
