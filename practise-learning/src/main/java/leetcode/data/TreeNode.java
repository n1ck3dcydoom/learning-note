package leetcode.data;

/**
 * @author zhanglei
 * @version 1.0
 * @description TODO
 * @date 2020/4/26 23:55
 **/
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {

    }

    public TreeNode(int x) {
        this.val = x;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = left;
    }
}