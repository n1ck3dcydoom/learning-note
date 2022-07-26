package leetcode.hash.hard;

import java.util.Random;
import java.util.RandomAccess;

/**
 * Created by n!Ck
 * Date: 2022/7/26
 * Time: 19:47
 * Description:
 */

public class _1206_DesignSkiplist {

    // 考虑到一共只有 5*10^4=50000 个元素
    // 第 1 层 50000,第 2 层 25000,第 3 层 12500,第 4 层 6250,第 5 层 3125,第 6 层 1562,第 7 层 781,第 8 层 390,第 9 层 195
    // 第 10 层 92,第 11 层 46,第 12 层 23,第 13 层 11,第 14 层 5, 第 15 层 2,第 16 层 1
    // 如果按照每一层是下一层的一半元素个数,只需要 16 层就够了
    private int MAX_LEVEL = 16;
    private double RANDOM_LEVE_RACTOR = 0.5;

    class SkipListNode {
        // 保存当前节点的值
        int val;
        // 保存当前节点所在每一层的下一个节点的值,最多也就一个节点同时出现在每一层
        SkipListNode[] next = new SkipListNode[MAX_LEVEL];
    }

    public _1206_DesignSkiplist() {

    }

    public boolean search(int target) {
        return false;
    }

    public void add(int num) {

    }

    public boolean erase(int num) {
        return false;
    }

    private int randomLevel() {
        Random r = new Random(System.currentTimeMillis());
        // 第一层插入所有元素
        int lv = 1;
        // 保证每一层元素个数都是下一层的一半
        // 1 出现的概率为 1
        // 2 出现的概率为 1/2
        // 3 出现的概率为 1/4
        // ...
        while (r.nextDouble() < this.RANDOM_LEVE_RACTOR && lv < this.MAX_LEVEL) {
            lv++;
        }
        return lv;
    }
}

