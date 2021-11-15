package leetcode.math.medium;

public class _319_BulbSwitcher {
    public static void main(String[] args) {
        System.out.println(solution1(11));
    }

    private static int solution(int n) {
        // 投机取巧，超时
        int count = 0;
        for (int i = 1; i <= n; i++) {
            String sqrt = String.valueOf(Math.sqrt(i));
            int index = sqrt.indexOf(".");
            if ("0".equals(sqrt.substring(index + 1))) {
                count++;
            }
        }
        return count;
    }

    private static int solution1(int n) {
        // n有多少个完全平方数
        // 100以内有多少个完全平方数
        // 1、4、9、16、25、36、49、64、81、100 共10个
        // 120以内呢
        // 1、4、9、16、25、36、49、64、81、100 还是10个
        // 121以内呢
        // 1、4、9、16、25、36、49、64、81、100、121  共11个
        // 总结规律就是，完全平方数的个数 = sqrt(n)向下取整即可
        return (int) Math.sqrt(n);
    }
}
