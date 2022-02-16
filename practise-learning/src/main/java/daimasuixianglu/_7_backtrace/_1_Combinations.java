package daimasuixianglu._7_backtrace;

import java.util.ArrayList;
import java.util.List;

public class _1_Combinations {

    private List<List<Integer>> res = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(1, n, k);
        return res;
    }

    private void dfs(int index, int n, int k) {
        // 路径中的选择个数已经等于k
        if (path.size() == k) {
            // 注意引用
            res.add(new ArrayList<>(path));
            return;
        }
        // 遍历剩下的选择
        // for (int i = index; i <= n; i++) {
        // // 做出选择，加入路径
        // path.add(i);
        // // 进行递归
        // dfs(i + 1, n, k);
        // // 撤销选择
        // path.remove(path.size() - 1);
        // }
        // 剪枝 1 2 3 4 5 6 7 n=4
        // 如果n=7，k=4，那么从n=5开始已经没有意义了，因为567不够4个数
        // 如果此时path的大小为1，还需要k-1=3个数，则最大的遍历起点在n-(k-path.size())+1=5
        // 因为567刚好够剩下的3个数，从6开始，67已经没有遍历的需要了
        for (int i = index; i <= n - (k - path.size()) + 1; i++) {
            // 做出选择，加入路径
            path.add(i);
            // 进行递归
            dfs(i + 1, n, k);
            // 撤销选择
            path.remove(path.size() - 1);
        }
    }
}
