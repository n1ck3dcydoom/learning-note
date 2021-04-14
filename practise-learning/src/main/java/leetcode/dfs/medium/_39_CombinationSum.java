package leetcode.dfs.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description
 *
 * 给定一个无重复元素的数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
 *
 * candidates中的数字可以无限制重复被选取。
 *
 * 说明：
 *
 * 所有数字（包括target）都是正整数。
 * 解集不能包含重复的组合。
 * 示例1：
 *
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * 示例2：
 *
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为：
 * [
 *  [2,2,2,2],
 *  [2,3,3],
 *  [3,5]
 * ]
 *
 *
 * 提示：
 *
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都是独一无二的。
 * 1 <= target <= 500
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 2021/4/14 22:55
 **/
public class _39_CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();

        int n = candidates.length;
        if (n == 0) {
            return res;
        }
        Stack<Integer> path = new Stack<>();
        dfs(candidates, target, res, path);
        return res;
    }

    /**
     * 递归传入的条件，没有全局变量保存最终的路径结果，所以需要在每次递归的时候带上这个保存结果
     * <p>
     * 题目给出的搜索范围candidates和目标也需要在递归中带上
     */
    private void dfs(int[] candidates, int target, List<List<Integer>> res, Stack<Integer> path) {
        // 递归搜索结束的条件

        // 搜索树的根节点从target出发，下面每次有n个子节点，每个子节点的权值等于carditates[n]
        // 每次搜索时，把target减去搜索选择的边的权值，如果结果等于0，表示该路径满足题意，装入res后返回上一层搜索
        // 如果target减去边的权值后，结果为负数，则表示当前路径上不存在刚好等于target的情况，返回上一层搜索
        if (target < 0) {
            return;
        }
        if (target == 0) {
            // 这里需要add的路径，所以每次搜索时需要把当前路径也带下来
            // 使用队列保存搜索路径，因为每次回溯都是弹出最后一个加入路径的节点
            res.add(new ArrayList<>(path));
        }
        // 题目说了每个数都可以重复使用，遍历每个选择继续dfs
        for (int candidate : candidates) {
            // 当前边加入路径
            path.push(candidate);
            // 进行dfs
            dfs(candidates, target - candidate, res, path);
            // 回溯，弹出最后一个加入路径的边
            path.pop();
        }
    }


    /**
     * 上述dfs会输出重复的路径
     */
    private void dfs1(int[] candidates, int target, int start, List<List<Integer>> res, Stack<Integer> path) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 题目说了每个数都可以重复使用，遍历每个选择继续dfs
        // 这里可能造成边的重复选择

        // 画出搜索树，很容易发现
        // 整个搜索树是一颗满n叉树，n等于选择列表的长度
        // 这里面就造成了边的重复选择，所以需要剪枝

        // 举例：选择列表 = [2,3,7,6]
        // 第一层：
        // 从根节点7出发，从左往右第一个子节点是7-2=5，第二个子节点是7-3=4，第三个子节点是7-6=1，第四个子节点是7-7=0
        // 第二层
        // 从根节点5出发，第一个子节点是5-2=3，第二个子节点是5-3=2，第三个子节点是5-7=-2
        // 从根节点4出发，注意：第一个子节点不能再是2了，因为再上一个路径上2已经被选择过了，所以第一个子节点应该是4-3=1....... 以此类推

        // 所以需要加入搜索的开始位置，用于判断标记开始位置之前的节点在当前路径下是已经搜索过的了
        for (int i = start; i < candidates.length; i++) {
            // 如果选择当前边后，target等于负数了，则不满足题意，不进行dfs，完成剪枝操作
            if (candidates[i] <= target) {
                // 当前边加入路径
                path.push(candidates[i]);
                // 进行dfs
                dfs1(candidates, target - candidates[i], i, res, path);
                // 回溯，弹出最后一个加入路径的边
                path.pop();
            }
        }
    }

}