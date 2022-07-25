import java.util.*;

/**
 * Created by n!Ck
 * Date: 2022/5/2
 * Time: 13:10
 * Description:
 */

public class MinDp {

    public static void main(String[] args) {

        Map<Integer, Node> map = new HashMap<>();
        List<Node> list = new ArrayList<>();
        // 根节点
        Node head = new Node(-1);
        map.put(1, head);
        list.add(head);

        Scanner s = new Scanner(System.in);
        while (!s.hasNext("#")) {
            // ui vi wi  表示节点ui到vi权值为wi
            // 1 2 3   p2=1,c2=3
            // 2 3 4   p3=2,c3=4
            // 1 4 5   p4=1,c4=5
            // 4 5 1   p5=4,c5=1
            // 4 6 2   p6=4,c6=2
            int ui = s.nextInt();
            int vi = s.nextInt();
            int wi = s.nextInt();

            Node unode = map.getOrDefault(ui, new Node(-1));
            // vi肯定是新节点，直接创建
            Node vnode = new Node(wi);
            map.put(vi, vnode);
            list.add(vnode);
            unode.nodes.add(vnode);
            unode.size++;
        }
        // 定义dp[i]表示，以i为根节点的树断开其所有叶子结点所需要的最小花费
        int[] dp = new int[map.size()];
        int res = dfs(head, dp, map, list, 0);

        System.out.println(res);
    }

    public static int dfs(Node head, int[] dp, Map<Integer, Node> map, List<Node> list, int index) {
        // 到达叶子结点
        if (head.nodes.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        // 从所有孩子的cost和孩子的权值选择最小值，然后求和得到当前节点的cost
        int td = Integer.MAX_VALUE;
        for (int i = 0; i < head.nodes.size(); i++) {
            int c = dfs(head.nodes.get(i), dp, map, list, index);
            td = Math.min(td, Math.min(c, head.nodes.get(i).edge));
        }
        return td;
    }
}

class Node {
    public int edge;
    public int size;
    public List<Node> nodes;
    public List<Integer> cost;

    public Node(int edge) {
        this.edge = edge;
        nodes = new ArrayList<>();
        cost = new ArrayList<>();
    }
}
