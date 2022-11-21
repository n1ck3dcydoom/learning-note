package nowcode.top101;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by n!Ck
 * Date: 2022/11/19
 * Time: 13:05
 * Description:
 */

public class _55_没有重复项数字的全排列 {

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> res = permute(new int[]{1, 2, 3});
        for (ArrayList<Integer> list : res) {
            for (int i : list) {
                System.out.print(i + " ");
            }
            System.out.println("\n");
        }
    }

    public static ArrayList<ArrayList<Integer>> permute(int[] num) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        Arrays.sort(num);
        dfs(num, new boolean[num.length], new ArrayList<>(), res);
        return res;
    }

    private static void dfs(int[] num, boolean[] visited, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> res) {
        if (path.size() == num.length) {
            // 注意 path 是引用,拷贝一份出来
            ArrayList<Integer> list = new ArrayList<>(path);
            res.add(list);
        }
        // 递归搜索所有数
        for (int i = 0; i < num.length; i++) {
            // 剪枝,跳过已经访问过的元素
            if (visited[i]) {
                continue;
            }
            // 当前元素加入结果集
            path.add(num[i]);
            // 标记当前元素已经访问过
            visited[i] = true;
            // 递归搜索剩下的元素
            dfs(num, visited, path, res);
            // 撤销选择
            visited[i] = false;
            path.remove(path.size() - 1);
        }
    }
}
