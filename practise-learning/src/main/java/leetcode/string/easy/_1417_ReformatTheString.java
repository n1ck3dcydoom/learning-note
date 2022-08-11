package leetcode.string.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by n!Ck
 * Date: 2022/8/11
 * Time: 09:24
 * Description:
 */

public class _1417_ReformatTheString {

    public static void main(String[] args) {
        System.out.println(reformat("123"));
    }

    public static String reformat(String s) {
        // 统计字母和数字的次数
        int nums = 0, chs = 0;
        List<Character> nlist = new ArrayList<>();
        List<Character> clist = new ArrayList<>();
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                nums++;
                nlist.add(c);
            } else {
                chs++;
                clist.add(c);
            }
        }
        // 如果相差超过 1,则无法构造符合条件的字符串
        if (Math.abs(nums - chs) > 1) {
            return "";
        }
        // 个数多的放到前面和末位,双指针构造
        StringBuilder sb = new StringBuilder();
        int i = 0, j = 0, k = 1;
        if (nums > chs) {
            sb.append(nlist.get(i++));
            while (i < nums || j < chs) {
                sb.append(k++ % 2 == 0 ? nlist.get(i++) : clist.get(j++));
            }
        } else {
            sb.append(clist.get(j++));
            while (i < nums || j < chs) {
                sb.append(k++ % 2 == 0 ? clist.get(j++) : nlist.get(i++));
            }
        }
        return sb.toString();
    }
}
