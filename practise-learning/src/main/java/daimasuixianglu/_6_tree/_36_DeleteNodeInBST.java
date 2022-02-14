package daimasuixianglu._6_tree;

public class _36_DeleteNodeInBST {

    public TreeNode deleteNode(TreeNode root, int key) {
        // 是否需要遍历整棵树？
        // 只需要找到删除节点，执行删除操作后，返回新的根节点即可，不需要遍历整棵树
        // 遍历某条路径
        // if(dfs(root.left)) return
        // if(dfs(root.right)) return

        // 遍历整棵树
        // left = dfs(root.left)
        // right = dfs(root.right)
        // do something with left or right

        // 递归函数的返回值是什么？
        // 递归函数的返回值，是删除root节点后，root的父节点所应该重新指向的节点

        // 如果没有找到删除节点，返回null
        if (root == null) {
            return root;
        }
        // 找到删除节点
        if (root.val == key) {
            // 如果删除节点左右孩子都为空，返回null
            if (root.left == null && root.right == null) {
                return null;
            }
            // 如果删除节点有一侧孩子为空，返回另一侧孩子
            else if (root.left != null && root.right == null) {
                return root.left;
            } else if (root.right != null && root.left == null) {
                return root.right;
            }
            // 如果删除节点两侧孩子都不为空
            // 将左孩子放到右孩子最左侧的节点上，然后返回右孩子
            else {
                // 寻找右孩子的最左侧节点
                TreeNode right = root.right;
                while (right.left != null) {
                    right = right.left;
                }
                // 将左孩子接到右孩子的最左侧节点上
                right.left = root.left;
                // 返回右孩子
                return root.right;
            }
        }

        // 左子树中继续查找删除节点
        if (root.val > key) {
            root.left = deleteNode(root.left, key);
        }
        // 右子树中继续查找删除节点
        if (root.val < key) {
            root.right = deleteNode(root.right, key);
        }
        return root;
    }

    public TreeNode deleteNode1(TreeNode root, int key) {
        // 非BST树如何删除一个节点？
        if (root == null) {
            return root;
        }
        if (root.val == key) {
            // 左子树非空，右子树为空，直接返回左子树
            if (root.right == null) {
                // 第二次操作删除节点，当交换节点之后，仍然会继续搜索整棵树
                // 直到第二次在原删除节点的右孩子的最左侧节点发现了之前的删除节点
                // 这时候已经是一个叶子节点，直接返回null给其父节点，这样就完成了删除操作
                return root.left;
            }
            // 右子树非空的情况（包含左子树为空或者左子树非空）
            // 寻找右子树的最左节点
            TreeNode right = root.right;
            while (right.left != null) {
                right = right.left;
            }
            // 第一次操作删除节点，将删除节点和右孩子的最左侧节点（BST中的紧跟删除节点的下一个值）
            // 和删除节点交换（仅交换值，暂时不删除）
            // 此时删除节点已经放到其原始右孩子的最左侧
            int tmp = right.val;
            right.val = key;
            root.val = tmp;
        }
        // 查找左子树
        root.left = deleteNode1(root.left, key);
        // 查找右子树
        root.right = deleteNode1(root.right, key);
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);

        TreeNode p = deleteNode2(root, 3);
        int a = 1;
    }

    public static TreeNode deleteNode2(TreeNode root, int key) {
        // 迭代法删除
        // 同样是找到删除节点，然后把删除节点的左子树放到右子树的最左侧节点上
        if (root == null) {
            return null;
        }
        // 记录删除节点的前驱节点
        TreeNode pre = null;
        // 遍历BST查找删除节点
        TreeNode p = root;
        while (p != null) {
            if (p.val == key) {
                break;
            }
            // 更新前驱节点
            pre = p;
            if (p.val > key) {
                // 查找左子树
                p = p.left;
            } else if (p.val < key) {
                // 查找右子树
                p = p.right;
            }
        }
        // 如果删除节点就是根节点，特殊处理
        if (pre == null) {
            return delete0(p);
        }
        // 确定删除节点p是pre的左孩子还是右孩子
        if (pre.left != null && pre.left.val == key) {
            // 需要删除左孩子
            pre.left = delete0(p);
        }
        if (pre.right != null && pre.right.val == key) {
            // 需要删除右孩子
            pre.right = delete0(p);
        }
        return root;
    }

    private static TreeNode delete0(TreeNode root) {
        if (root == null) {
            return root;
        }
        // BST和普通二叉树，通用删除方法
        // 右孩子为空，直接返回左孩子（返回后root的父节点指向的root.left，root已经删掉）
        if (root.right == null) {
            return root.left;
        }
        // 寻找右孩子的最左侧节点
        TreeNode right = root.right;
        while (right.left != null) {
            right = right.left;
        }
        // 将左孩子接到右孩子最左侧的节点上
        right.left=root.left;
        // 返回交换后的右孩子
        return root.right;
    }
}
