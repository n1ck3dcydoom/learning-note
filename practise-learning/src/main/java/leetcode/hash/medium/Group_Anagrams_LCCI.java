package leetcode.hash.medium;

import java.util.*;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * <p>
 * 编写一种方法，对字符串数组进行排序，将所有变位词组合在一起。变位词是指字母相同，但排列不同的字符串。
 * <p>
 * 注意：本题相对原题稍作修改
 * <p>
 * 示例:
 * <p>
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * 输出:
 * [
 * ["ate","eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * 说明：
 * <p>
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/group-anagrams-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/7/22 21:05
 **/
public class Group_Anagrams_LCCI {

    public static void main(String[] args) {
        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        groupAnagrams(strs);
        groupAnagrams1(strs);
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();

        // 每组变为词里面包含的字母一定都是相同的，可以把变为词按照字典序排序后作为唯一key，value为相同变为词的list数组
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            // 按照字典序排序
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            // 得到排序后的变为词key
            String sortedStr = new String(chars);
            // 得到当前变为词的组合
            List<String> list = map.getOrDefault(sortedStr, new ArrayList<>());
            list.add(str);
            map.put(sortedStr, list);
        }
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            res.add(entry.getValue());
        }

        return res;
    }

    public static List<List<String>> groupAnagrams1(String[] strs) {
        List<List<String>> res = new ArrayList<>();

        // 对每个字符串出现的26个字母做计数，然后对每个计数后的字母做字符串拼接作为key
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] chars = new int[26];
            // 对str出现的字母计数，互为变为词的两个单词的字母计数也一定相同
            for (int i = 0; i < str.length(); i++) {
                chars[str.charAt(i) - 'a']++;
            }
            // 对出现的字母计数做字符拼接，作为key
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                while (chars[i] != 0) {
                    sb.append((char) (i + 'a'));
                    chars[i]--;
                }
            }
            // 得到当前变为词的组合
            List<String> list = map.getOrDefault(sb.toString(), new ArrayList<>());
            list.add(str);
            map.put(sb.toString(), list);
        }
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            res.add(entry.getValue());
        }
        return res;
    }
}