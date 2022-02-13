// package daimasuixianglu._6_tree;
//
// public class _12_PopulatingNextRightPointersInEachNodeII {
//     public Node connect(Node root) {
//         if (root == null) {
//             return null;
//         }
//         Deque<Node> queue = new ArrayDeque<>();
//         queue.add(root);
//         while (!queue.isEmpty()) {
//             int size = queue.size();
//             while (size-- > 0) {
//                 Node node = queue.pollFirst();
//                 if (node.left != null) {
//                     queue.addLast(node.left);
//                 }
//                 if (node.right != null) {
//                     queue.addLast(node.right);
//                 }
//                 if (size + 1 == 1) {
//                     break;
//                 }
//                 Node next = queue.peekFirst();
//                 node.next = next;
//             }
//         }
//         return root;
//     }
// }
//
// class Node {
//     public int val;
//     public Node left;
//     public Node right;
//     public Node next;
//
//     public Node() {
//     }
//
//     public Node(int _val) {
//         val = _val;
//     }
//
//     public Node(int _val, Node _left, Node _right, Node _next) {
//         val = _val;
//         left = _left;
//         right = _right;
//         next = _next;
//     }
// }
