package daimasuixianglu._6_tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class _17_CountCompleteTreeNodes {

    /**
     * 递归计算
     * 1递归函数的定义，给定一个根节点root，返回该树的所有节点个数
     * 2终止递归的条件，如果null节点的个数为0
     */
    public int countNodes(TreeNode root) {
        // 递归结束的条件，空树的节点个数为0
        if (root == null) {
            return 0;
        }
        // 递归框架，由于需要最后统计左子树和右子树的节点个数，所以采用后序遍历
        int ln = countNodes(root.left);
        int rn = countNodes(root.right);
        // 1表示当前节点，ln左子树节点，rn右子树节点
        return 1 + ln + rn;
    }

    /**
     * 统一迭代法
     */
    public int countNodes1(TreeNode root) {
        int res = 0;
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.addLast(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            // 后序遍历（左右中），入栈顺序（中右左），出栈顺序（左右中）
            if (node != null) {
                // 中
                stack.addLast(node);
                stack.addLast(null);
                // 右
                if (node.right != null) {
                    stack.addLast(node.right);
                }
                // 左
                if (node.left != null) {
                    stack.addLast(node.left);
                }
            } else {
                // 弹出空节点
                // queue.pollLast();
                // 弹出处理节点
                node = stack.pollLast();
                res++;
            }
        }
        return res;
    }

    /**
     * bfs搜索
     */
    public int countNodes2(TreeNode root) {
        int res = 0;
        if (root == null) {
            return res;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            res += size;
            while (size-- > 0) {
                TreeNode node = queue.pollFirst();
                if (node.left != null) {
                    queue.addLast(node.left);
                }
                if (node.right != null) {
                    queue.addLast(node.right);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);

        System.out.println(countNodes3(root));
    }

    /**
     * 利用完全二叉树的性质，其左孩子或右孩子一定也是完全二叉树
     * 1. 满二叉树，节点个数=2^h-1
     * 2. 非满二叉树，递归其左孩子和右孩子，总能找到1的情况
     */
    public static int countNodes3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 左孩子
        TreeNode left = root.left;
        int dl = 0;
        // 右孩子
        TreeNode right = root.right;
        int dr = 0;
        // 满二叉树的性质，其左孩子和右孩子的深度一定相等
        while (left != null) {
            dl++;
            left = left.left;
        }
        while (right != null) {
            dr++;
            right = right.right;
        }
        // 如果是一棵满二叉树，由公式计算节点个数
        if (dl == dr) {
            // 实际上，上面的dl和dr比root真正的深度要小1
            // 这里的dl记录的是root的左孩子的深度
            // dr记录的是root的右孩子的深度
            // 对于root而言，他的深度其实是dl+1或者dr+1（满二叉树的情况）
            // return (int)Math.pow(2,dl+1)-1 这才是公式的原始写法，其中dl+1表示root树的深度
            // 2^x等于2*[2^(x-1)]，2<<x相当于2*2^x，所以带入x=dl+1得到2^(dl+1)=2 << dl
            return 2 << dl - 1;
        }
        // 如果不是满二叉树，则递归查找左孩子和右孩子，直到子树是一棵满二叉树
        int rl = countNodes3(root.left);
        int rr = countNodes3(root.right);
        return 1 + rl + rr;
    }
}
