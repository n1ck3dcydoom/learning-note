package nowcode.top101;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import leetcode.data.TreeNode;

/**
 * Created by n!Ck
 * Date: 2022/11/17
 * Time: 19:22
 * Description:
 */

public class _27_按照之字形打印二叉树 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        System.out.println(Print(root));
    }

    public static ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        // 仍然是层序遍历,只不过每层的顺序和上一层相反
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (pRoot == null) {
            return res;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offerLast(pRoot);

        int odd = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            ArrayList<Integer> list = new ArrayList<>();
            while (size-- > 0) {
                TreeNode node = queue.pollFirst();
                if (odd % 2 == 1) {
                    list.add(node.val);
                } else {
                    list.add(0, node.val);
                }
                if (node.left != null) {
                    queue.offerLast(node.left);
                }
                if (node.right != null) {
                    queue.offerLast(node.right);
                }
            }
            res.add(list);
            odd++;
        }
        return res;
    }
}
