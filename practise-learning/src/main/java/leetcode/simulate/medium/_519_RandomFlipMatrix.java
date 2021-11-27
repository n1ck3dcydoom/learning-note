package leetcode.simulate.medium;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class _519_RandomFlipMatrix {

    private final int m;
    private final int n;
    private int total;

    private final Random r;
    private Map<Integer, Integer> hash;

    public _519_RandomFlipMatrix(int m, int n) {
        this.m = m;
        this.n = n;
        this.total = m * n;
        this.r = new Random();
        this.hash = new HashMap<>();
    }

    public int[] flip() {
        // 将二维数组压缩成一维数组
        // int[i][j] = int[i*n+j]

        // 从一维数组中随机取一个数
        int x = r.nextInt(total--);
        // 借用total来表示last，last=total-1
        // 检查x是否已经被用过
        // 规定每个没有被用过的数，在其映射关系为x->x
        // 如果x在某一轮已经和y交换过了，那么x的映射关系为x->y
        int idx = hash.getOrDefault(x, x);
        // 把idx和last做交换，保证last之前的元素都是0，之后的元素(包括last)都是1
        hash.put(x, hash.getOrDefault(total, total));
        // 已知压缩后的一维数组索引为x，根据上面的公式还原到二维数组对应的i=x/n  j=x%n
        return new int[]{idx / n, idx % n};
    }

    public void reset() {
        this.hash.clear();
        this.total = this.m * this.n;
    }
}
