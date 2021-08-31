package algorithm.structure;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description TODO
 * @date 2021/9/2 20:09
 **/
public class PerfectNode {
    public int val;
    public PerfectNode left;
    public PerfectNode right;
    public PerfectNode next;

    public PerfectNode() {
    }

    public PerfectNode(int _val) {
        val = _val;
    }

    public PerfectNode(int _val, PerfectNode _left, PerfectNode _right, PerfectNode _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}