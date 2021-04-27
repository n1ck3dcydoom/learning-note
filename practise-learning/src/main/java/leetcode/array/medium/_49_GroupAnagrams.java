package leetcode.array.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * <p>
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * <p>
 * 示例:
 * <p>
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
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
 * 链接：https://leetcode-cn.com/problems/group-anagrams
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/4/27 21:21
 **/
public class _49_GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        // 不同的单词，但是由相同字母组合组成
        // 所以对每个单词按照字母顺序重新排序后，异位词一定相同

        // 保存每个单词重新排序后的映射关系
        HashMap<String, List<String>> map = new HashMap<>();
        // 对每个单词按照字母顺序重新排序
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            // 排序后的新单词作为key放入map中
            String newString = new String(chars);
            if (map.containsKey(newString)) {
                List<String> temp = map.get(newString);
                // 把排序前的字符放入list中，而非排序后的异位词
                temp.add(str);
            } else {
                List<String> temp = new ArrayList<>();
                temp.add(str);
                map.put(newString, temp);
            }
        }
        return new ArrayList<>(map.values());
    }
}