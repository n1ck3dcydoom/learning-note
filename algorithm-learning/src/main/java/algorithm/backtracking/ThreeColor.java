package algorithm.backtracking;

import java.util.ArrayList;

import algorithm.structure.TreeNode;

public class ThreeColor {

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> list1 = new ArrayList<Integer>();
        if (root == null) {
            return list;
        }
        find(list, list1, root, target);
        return list;
    }

    public void find(ArrayList<ArrayList<Integer>> list, ArrayList<Integer> list1, TreeNode root, int target) {
        if (root == null) {
            return;
        }
        list1.add(root.val);
        target -= root.val;
        if (target == 0 && root.left == null && root.right == null) {
            list.add(new ArrayList<Integer>(list1));
        }
        find(list, list1, root.left, target);
        find(list, list1, root.right, target);
        // 复原才能回溯
        list1.remove(list1.size() - 1);
    }
}