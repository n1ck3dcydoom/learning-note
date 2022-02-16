package daimasuixianglu._7_backtrace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _5_CombinationSumII {

    public static void main(String[] args) {
        int[] arr = new int[] { 10, 1, 2, 7, 6, 1, 5 };
        combinationSum2(arr, 8);
        int a = 1;
    }

    private static List<List<Integer>> res = new ArrayList<>();
    private static List<Integer> path = new ArrayList<>();

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // 不能使用重复数字
        // 对数组排序
        Arrays.sort(candidates);
        dfs(candidates, 0, 0, target);
        return res;
    }

    private static void dfs(int[] selects, int index, int sum, int target) {
        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < selects.length; i++) {
            // sum剪枝
            if (sum + selects[i] > target) {
                continue;
            }
            // 相同元素要跳过
            // 例如1，1，2，5，target=8
            // 第一次index=0，时selects[i]=1，i>index=false
            // 第二次的1会加入搜索，因为i=index，即选择为1，1
            // 当第一轮1结束搜索之后，i++，此时index=0，i=1
            // 条件i>index成立，且s[i]==s[i-1]，此时将会跳过第二个1，直接i+=
            // 第三次搜索，index=0，i=2，第二个1已经被跳过了
            if (i > index && selects[i] == selects[i - 1]) {
                continue;
            }
            // 做出选择
            path.add(selects[i]);
            // 递归搜索
            dfs(selects, i + 1, sum + selects[i], target);
            // 撤销选择
            path.remove(path.size() - 1);
        }
    }
}
