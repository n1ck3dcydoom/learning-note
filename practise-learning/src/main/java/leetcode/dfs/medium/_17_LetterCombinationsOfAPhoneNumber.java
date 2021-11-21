package leetcode.dfs.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description TODO
 * @date 2021/3/29 23:43
 **/
public class _17_LetterCombinationsOfAPhoneNumber {

    private HashMap<Character, String> map = new HashMap<>();

    {
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
    }

    /**
     * 回溯伪代码：
     * backtrack(路径，选择列表){
     *      if(满足条件){
     *      res.add(路径)
     *      return;
     *   }
     * for(选择，选择列表){
     *      do(做选择)
     *      backtrack(路径，选择列表)
     *      do(回退)
     *   }
     * }
     */
    public List<String> letterCombinations(String digits) {
        // 空号码直接返回
        if (digits == null || digits.length() == 0) {
            return Collections.emptyList();
        }
        // res保存每次满足题意的路径
        List<String> res = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        backTrack(res, digits, sb, 0);

        return res;
    }

    /**
     * res 保存每次路径，需要在每次递归时带入
     * digits 选择列表
     * sb 保存每次的选择后的中间结果
     * index 保存每次做出选择后的结果长度，有多少个数字，那么最终组合的字符串就有多长
     * 一旦发现做出选择后的当前路径长度等于digits的长度后，就做回退动作
     */
    private void backTrack(List<String> res, String digits, StringBuilder sb, int index) {
        // 上一轮做完选择后的长度index等于给定选择列表的长度，保存路径然后返回
        if (index == digits.length()) {
            res.add(sb.toString());
        } else {
            // 做出选择

            // 得到当前数字
            char currentChar = digits.charAt(index);
            // 得到当前数字对应的键盘上的字母
            String chars = this.map.get(currentChar);
            // 遍历当前字母,进行递归继续选择
            for (int i = 0; i < chars.length(); i++) {
                // 保存当前选择后的中间结果
                sb.append(chars.charAt(i));
                // 进行递归
                backTrack(res, digits, sb, index + 1);
                // 回退,删掉中间结果的最后一个字符
                sb.deleteCharAt(index);
            }
        }
    }
}
