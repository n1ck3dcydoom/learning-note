package daimasuixianglu._7_backtrace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _3_LetterCombinationsPhoneNumber {

    private List<String> res = new ArrayList<>();
    private Map<Character, char[]> memo = new HashMap<>();
    private StringBuilder sb = new StringBuilder();

    public List<String> letterCombinations(String digits) {

        if (digits.length() == 0) {
            return res;
        }

        memo.put('2', new char[] { 'a', 'b', 'c' });
        memo.put('3', new char[] { 'd', 'e', 'f' });
        memo.put('4', new char[] { 'g', 'h', 'i' });
        memo.put('5', new char[] { 'j', 'k', 'i' });
        memo.put('6', new char[] { 'm', 'n', 'o' });
        memo.put('7', new char[] { 'p', 'q', 'r', 's' });
        memo.put('8', new char[] { 't', 'u', 'v' });
        memo.put('9', new char[] { 'w', 'x', 'y', 'z' });

        dfs(0, digits);
        return res;
    }

    private void dfs(int index, String digits) {
        // 当输入索引index > digits.length 可以认为所以数字键盘都已经查找过了
        if (index == digits.length()) {
            res.add(sb.toString());
            return;
        }
        // 遍历当前数字对应的字母
        char[] chars = memo.get(digits.charAt(index));
        for (int i = 0; i < chars.length; i++) {
            // 做出选择
            sb.append(chars[i]);
            // 递归查找
            dfs(index + 1, digits);
            // 撤销选择
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
