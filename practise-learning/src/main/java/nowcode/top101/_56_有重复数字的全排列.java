package nowcode.top101;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by n!Ck
 * Date: 2022/11/19
 * Time: 13:16
 * Description:
 */

public class _56_有重复数字的全排列 {

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> res = permuteUnique(new int[]{1, 1, 2});
        for (ArrayList<Integer> list : res) {
            for (int i : list) {
                System.out.print(i + " ");
            }
            System.out.println("\n");
        }
    }

    public static ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        Arrays.sort(num);
        dfs(num, new boolean[num.length], new ArrayList<>(), res);
        return res;
    }

    private static void dfs(int[] num, boolean[] visited, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> res) {
        if (path.size() == num.length) {
            ArrayList<Integer> list = new ArrayList<>(path);
            res.add(list);
        }
        for (int i = 0; i < num.length; i++) {
            // 剪枝,跳过已经访问过的元素
            if (visited[i]) {
                continue;
            }
            // 剪枝,跳过重复元素,跳过的时候判断前一个重复元素是否有访问过,已经访问过才跳过
            if (i > 0 && visited[i - 1] && num[i] == num[i - 1]) {
                continue;
            }
            // 当前选择加入路径
            path.add(num[i]);
            // 标记已经访问过
            visited[i] = true;
            // 递归搜索所有数
            dfs(num, visited, path, res);
            // 撤销选择
            visited[i] = false;
            path.remove(path.size() - 1);
        }
    }
}
