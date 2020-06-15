package leetcode.data;

import java.util.List;

/**
 * @author zhanglei
 * @version 1.0
 * @description TODO
 * @date 2020/6/16 0:31
 **/
public class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}