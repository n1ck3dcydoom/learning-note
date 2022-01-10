package leetcode.dfs.medium;

/**
 * Created by n!Ck
 * Date: 2022/1/10
 * Time: 23:04
 * Description:
 */

public class _306_AdditiveNumber {

    public static void main(String[] args) {
        System.out.println(isAdditiveNumber("11235813213455890144"));
    }

    public static boolean isAdditiveNumber(String num) {
        return dfs(num, 0, 0L, 0L, 0);
    }

    private static boolean dfs(String num, int index, long prepre, long pre, int count) {
        if (index == num.length()) {
            // 搜索到末尾的时候，判断前面是否有两个已经找到的数
            return count > 2;
        }
        long cur = 0;
        // dfs搜索
        for (int i = index; i < num.length(); i++) {
            char c = num.charAt(i);
            // 前导0非法，但是0可以单独作为一个数使用
            if (num.charAt(index) == '0' && i > index) {
                return false;
            }
            // 获取当前数
            // 溢出
            // long cur = Long.parseLong(num.substring(index, i + 1));
            cur = cur * 10 + c - '0';
            // 如果前面已经找到两个及以上的数，说明当前已经开始搜索后面的数（至少是第三个数）
            if (count >= 2) {
                // 如果当前数大于prepre和pre之和，剪枝
                if (cur > prepre + pre) {
                    return false;
                }
                // 如果当前数小于prepre和pre之和，继续往后添加更多的数构成新cur
                if (cur < prepre + pre) {
                    continue;
                }
            }
            // 将当前数cur作为pre，前一个数pre作为前两个数prepre继续带入dfs搜索
            if (dfs(num, i + 1, pre, cur, count + 1)) {
                // 找到合法的可能性就返回
                return true;
            }
        }
        return false;
    }
}
