package leetcode.dfs.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * <p>
 * 给定一个字符串数组 arr，字符串 s 是将 arr 某一子序列字符串连接所得的字符串，如果 s 中的每一个字符都只出现过一次，那么它就是一个可行解。
 * <p>
 * 请返回所有可行解 s 中最长长度。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = ["un","iq","ue"]
 * 输出：4
 * 解释：所有可能的串联组合是 "","un","iq","ue","uniq" 和 "ique"，最大长度为 4。
 * 示例 2：
 * <p>
 * 输入：arr = ["cha","r","act","ers"]
 * 输出：6
 * 解释：可能的解答有 "chaers" 和 "acters"。
 * 示例 3：
 * <p>
 * 输入：arr = ["abcdefghijklmnopqrstuvwxyz"]
 * 输出：26
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/6/24 19:26
 **/
public class _1239_MaximumLengthOfAConcatenatedStringWithUniqueCharacters {

    private static int res = 0;

    public static void main(String[] args) {
        List<String> arr = new ArrayList<>();
        arr.add("");
        System.out.println(maxLength(arr));
    }

    public static int maxLength(List<String> arr) {
        // 首先处理下本身含有重复字符的子串
        arr = arr.stream().filter(string -> filte1(string)).collect(Collectors.toList());
        dfs(arr, new ArrayList<>(), 0);
        return res;
    }

    /**
     * dfs遍历所有字串，如果加入当前串后有出现重复字符，则跳过，否则加入当前路径
     *
     * @param arr   选择剩余的子串加入当前路径
     * @param path  当前路径
     * @param index 当前选择的索引，避免重复选择
     */
    private static void dfs(List<String> arr, List<String> path, int index) {
        // 更新当前路径下的结果
        res = Math.max(res, getString(path).length());
        // 遍历完所有子串后返回上一次dfs
        if (index == arr.size()) {
            return;
        }
        // 遍历当前剩余的选择
        for (int i = index; i < arr.size(); i++) {
            if (filte2(getString(path), arr.get(i))) {
                // 做出选择
                path.add(arr.get(i));
                // 进行递归
                dfs(arr, path, i + 1);
                // 撤销选择
                path.remove(path.size() - 1);
            }
        }
    }

    private static String getString(List<String> list) {
        StringBuilder pathString = new StringBuilder();
        for (String str : list) {
            pathString.append(str);
        }
        return pathString.toString();
    }

    private static boolean filte1(String subStr) {
        HashSet<Character> set = new HashSet<>(26);
        for (char c : subStr.toCharArray()) {
            if (set.contains(c)) {
                return false;
            } else {
                set.add(c);
            }
        }
        return true;
    }

    private static boolean filte2(String stra, String strb) {
        // 得到a的字母组合
        int[] aChars = new int[26];
        for (char c : stra.toCharArray()) {
            aChars[c - 'a']++;
        }
        // 检查a有没有包含b的字符
        for (char c : strb.toCharArray()) {
            if (aChars[c - 'a'] > 0) {
                return false;
            }
        }
        return true;
    }
}