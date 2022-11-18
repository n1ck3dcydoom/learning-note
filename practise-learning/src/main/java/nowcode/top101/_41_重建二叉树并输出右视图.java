package nowcode.top101;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import leetcode.data.TreeNode;

/**
 * Created by n!Ck
 * Date: 2022/11/18
 * Time: 11:01
 * Description:
 */

public class _41_重建二叉树并输出右视图 {

    public int[] solve(int[] xianxu, int[] zhongxu) {
        int n = xianxu.length;
        // 首先根据前序和中序重建二叉树
        TreeNode root = rebuild(xianxu, zhongxu, 0, n - 1, 0, n - 1);
        // 接着 bfs 扫描每层的最后一个节点输出
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offerLast(root);
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> l = new ArrayList<>();
            while (size-- > 0) {
                TreeNode e = queue.pollFirst();
                l.add(e.val);
                if (e.left != null) {
                    queue.offerLast(e.left);
                }
                if (e.right != null) {
                    queue.offerLast(e.right);
                }
            }
            list.add(l.get(l.size() - 1));
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    private TreeNode rebuild(int[] pre, int[] in, int pl, int pr, int il, int ir) {
        if (pl > pr) {
            return null;
        }
        // 前序遍历的第一个节点就是 root 节点
        TreeNode root = new TreeNode(pre[pl]);

        // 在中序遍历中找到根节点的位置，并将中序遍历划分为左右两个部分
        for (int i = 0; i < in.length; i++) {
            if (pre[pl] == in[i]) {
                // 计算左子树的节点个数
                int l = i - il;
                // 构建左右子树
                root.left = rebuild(pre, in, pl + 1, pl + l, il, il + l - 1);
                root.right = rebuild(pre, in, pl + l + 1, pr, i + 1, ir);
            }
        }
        return root;
    }
}
