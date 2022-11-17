package nowcode.top101;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import leetcode.data.TreeNode;

/**
 * Created by n!Ck
 * Date: 2022/11/17
 * Time: 19:17
 * Description:
 */

public class _26_二叉树层序遍历 {

    public static void main(String[] args) {
        System.out.println(levelOrder(null));
    }

    public static ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offerLast(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            ArrayList<Integer> list = new ArrayList<>();
            while (size-- > 0) {
                TreeNode node = queue.pollFirst();
                list.add(node.val);
                if (node.left != null) {
                    queue.offerLast(node.left);
                }
                if (node.right != null) {
                    queue.offerLast(node.right);
                }
            }
            res.add(list);
        }
        return res;
    }
}
