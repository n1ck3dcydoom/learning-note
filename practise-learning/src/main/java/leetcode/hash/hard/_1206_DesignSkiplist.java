package leetcode.hash.hard;

import java.util.Arrays;
import java.util.Random;

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
    private final int MAX_LEVEL = 16;
    private final double RANDOM_LEVE_RACTOR = 0.5;
    // 指向跳表的第一个节点，头节点
    // 1                   ->                  null
    // 1         ->        5         ->        null
    // 1    ->   3    ->   5    ->   7    ->   null
    // 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> null
    // 最高高度为 4
    // 此时头节点是左下角的1，它一共有 MAX_LEVEL 层，前 4 层有值
    // 同理，对于节点 5 ，它一共有 MAX_LEVEL 层，前 3 层有值
    private Node head;
    // 记录当前跳表的最高层数
    private int level;

    class Node {
        // 保存当前节点的值
        int val;
        // 保存当前节点所在每一层的下一个节点的值,最多也就一个节点同时出现在每一层
        Node[] next = new Node[MAX_LEVEL];

        public Node(int val) {
            this.val = val;
        }
    }

    public _1206_DesignSkiplist() {

    }

    public boolean search(int target) {
        return false;
    }

    public void add(int num) {
        // 需要记录所有需要插入的层数
        Node[] pre = new Node[MAX_LEVEL];
        // 每一层都初始化为头节点
        Arrays.fill(pre, this.head);
        // 遍历指针
        Node p = this.head;
        // 从最高层开始往下查找，找到每一层的前驱节点
        for (int i = this.level - 1; i >= 0; i--) {
            // 遍历高层的所有节点，
            while (p.next[i] != null && p.next[i].val < num) {
                // 如果节点的值小于待插入元素，则继续往后查找，直到当前层末尾(p.next[i]==null)
                p = p.next[i];
            }
            // 直到某一层的某个节点值大于了 num，或者遍历结束
            // 此时的 p 就是待插入节点在当前 i 层的前驱节点
            pre[i] = p;
            // 此时 pre 记录了最高层之下每一层的前驱节点，但是实际上要不要在这些层插入元素
            // 还要取决于当前元素随机出来的所处层数
        }
        // 随机得到当前插入元素的所处的所有层
        int randomLevel = this.randomLevel();
        // 更新此时跳表的最高层
        this.level = Math.max(this.level, randomLevel);
        // 向随机出来的所有层中插入当前元素
        Node newNode = new Node(num);
        for (int i = 0; i < randomLevel; i++) {
            // 将前驱节点指向的 next 元素赋值给新节点的 next
            newNode.next[i] = pre[i].next[i];
            // 将前驱节点和新节点连接起来
            pre[i].next[i] = newNode;
        }
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

