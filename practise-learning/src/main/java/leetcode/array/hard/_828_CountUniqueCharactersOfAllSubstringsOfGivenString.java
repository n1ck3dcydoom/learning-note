package leetcode.array.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by n!Ck
 * Date: 2022/9/6
 * Time: 13:54
 * Description:
 */

public class _828_CountUniqueCharactersOfAllSubstringsOfGivenString {

    public static void main(String[] args) {
        // System.out.println(uniqueLetterString1("ABC"));
        // System.out.println(uniqueLetterString1("ABA"));
        System.out.println(uniqueLetterString1("LEETCODE"));
    }

    public static int uniqueLetterString(String s) {
        // 查找每个子串的唯一字母个数 等效于查找原串内每个字母对所有子串的贡献
        // 对于某个字母 A,记录下上一次出现的位置,当前的位置,下一次出现的位置 (i,j,k)
        // 对于前面以当前 A 结尾的所有子串共有 (j-i) 个,A 能够贡献 (j-i) 次
        // 对于后面以当前 A 开头的所有子串共有 (k-j) 个,A 能够贡献 (k-j) 次
        // 对于包含 A 的所有子串共有 (j-i)*(k-j) 个,笛卡尔积

        char[] cs = s.toCharArray();
        // 遍历字符串 l[i] 和 r[i] 记录前面和后面最后一次出现 s[i] 的位置
        int n = s.length();
        int[] l = new int[n];
        int[] r = new int[n];
        for (int i = 0; i < n; i++) {
            char c = cs[i];
            // 记录当前位置
            l[i] = i;
            // 往左查找
            int p = i - 1;
            while (p >= 0) {
                if (cs[p] == c) {
                    l[i] = p;
                    break;
                }
                p--;
            }
            // 考虑边界情况
            if (p < 0) {
                l[i] = p;
            }

            // 记录当前位置
            r[i] = i;
            // 往右查找
            int q = i + 1;
            while (q < n) {
                if (cs[q] == c) {
                    r[i] = q;
                    break;
                }
                q++;
            }
            // 考虑边界情况
            if (q == n) {
                r[i] = q;
            }
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            res += (i - l[i]) * (r[i] - i);
        }

        return res;
    }

    public static int uniqueLetterString1(String s) {
        char[] cs = s.toCharArray();
        // 使用 hash 保存每个字符出现的位置
        Map<Character, List<Integer>> hash = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (!hash.containsKey(cs[i])) {
                hash.put(cs[i], new ArrayList<>());
                // 加入边界条件-1
                hash.get(cs[i]).add(-1);
            }
            // 记录当前位置
            hash.get(cs[i]).add(i);
        }

        int res = 0;
        for (Map.Entry<Character, List<Integer>> entry : hash.entrySet()) {
            List<Integer> list = entry.getValue();
            // 加入边界条件 cs.length
            list.add(cs.length);

            // 由于边界条件影响,第一个元素和最后一个元素都是边界条件,不需要遍历
            for (int i = 1; i < list.size() - 1; i++) {
                // 对于例子 L E E T C O D E
                //        0 1 2 3 4 5 6 7
                // 如果没有添加边界条件 -1 和 cs.length,则 list 仅仅记录了元素出现的位置
                // 对于 L 来说,其 list=[0]
                // 如果添加了边界条件,对于 L 来说,其 list=[-1,0,cs.length]
                // 这样保证了每个元素都至少出现了 "3" 次
                res += (list.get(i) - list.get(i - 1)) * (list.get(i + 1) - list.get(i));
            }
        }
        return res;
    }
}
