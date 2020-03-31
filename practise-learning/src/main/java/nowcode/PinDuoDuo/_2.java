package nowcode.PinDuoDuo;

import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by n!Ck
 * Date: 2019-03-10
 * Time: 16:34
 * Description:
 * <p>
 * 一套英文字母卡片总共52张，区分大小写
 * 随机从中取出若干张排成一排形成一个序列
 * 将序列中重复的字符去除，同一个字母的大小写只保留一个
 * <p>
 * 请问在所有的可能中，字母序最小的（不区分大小写）的序列第一张卡片上是哪个字母
 * <p>
 * 输入共一行，包含一个非空字符序列，长度为N   1<=N<=52 ：
 * 例如：xaBXY
 * <p>
 * 一行输出，表示字母序最小的序列的第一张卡，如果是大写则需要转换为小写
 * 输出a
 * <p>
 * 说明，因为去重后为abxy
 */
public class _2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine().toLowerCase();

        // 去重
        StringBuilder stringBuilder = new StringBuilder();
        HashSet<Character> set = new HashSet<>();
        for (int i = str.toCharArray().length - 1; i >= 0; i--) {
            char ch = str.charAt(i);
            if (set.contains(ch)) {
                continue;
            } else {
                stringBuilder.insert(0, ch);
                set.add(ch);
            }
        }
        System.out.println(stringBuilder.toString().toCharArray()[0]);
    }
}
