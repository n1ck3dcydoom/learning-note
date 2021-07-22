package leetcode.linkedlist.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * <p>
 * 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
 * <p>
 * 构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。复制链表中的指针都不应指向原链表中的节点 。
 * <p>
 * 例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。
 * <p>
 * 返回复制链表的头节点。
 * <p>
 * 用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
 * <p>
 * val：一个表示 Node.val 的整数。
 * random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
 * 你的代码 只 接受原链表的头节点 head 作为传入参数。
 * <p>
 * 示例 1：
 * <p>
 * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 示例 2：
 * <p>
 * 输入：head = [[1,1],[2,1]]
 * 输出：[[1,1],[2,1]]
 * 示例 3：
 * <p>
 * 输入：head = [[3,null],[3,0],[3,null]]
 * 输出：[[3,null],[3,0],[3,null]]
 * 示例 4：
 * <p>
 * 输入：head = []
 * 输出：[]
 * 解释：给定的链表为空（空指针），因此返回 null。
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= n <= 1000
 * -10000 <= Node.val <= 10000
 * Node.random 为空（null）或指向链表中的节点。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/copy-list-with-random-pointer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/7/22 16:28
 **/
public class _138_CopyListWithRandomPointer {

    public static void main(String[] args) {
        RandomNode head = new RandomNode(7);
        RandomNode n2 = new RandomNode(13);
        RandomNode n3 = new RandomNode(11);
        RandomNode n4 = new RandomNode(10);
        RandomNode n5 = new RandomNode(1);

        // 更新主链路
        head.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        // 更新随机链路
        head.random = null;
        n2.random = head;
        n3.random = n5;
        n4.random = n3;
        n5.random = head;

        copyRandomList(head);
        copyRandomList1(head);
        copyRandomList2(head);
    }

    public static RandomNode copyRandomList(RandomNode head) {

        List<RandomNode> list = new ArrayList<>();
        RandomNode[] newList = new RandomNode[1000];
        int n = 0;

        // p指针用于遍历head链表
        RandomNode p = head;
        // q指针用于构建新的newHead链表
        RandomNode q;
        // pre指针用于保存构建新的newHead链表时，q指针的前驱节点
        RandomNode preq = new RandomNode(0);
        // dummyHead指针用于保存新链表头节点的前驱节点;
        RandomNode dummyHead = preq;
        // 第一遍遍历，先把主链路构建出来
        while (p != null) {
            // 新建当前节点
            q = new RandomNode(p.val);
            // 更新前驱节点的next指针
            preq.next = q;
            // 更新前驱节点指向当前节点
            preq = q;
            // newHead的对象放入list根据索引保存起来
            newList[n++] = q;
            // 保存head的随机指向关系和节点顺序
            list.add(p);
            // 更新原head节点的p指针
            p = p.next;
        }

        // 第二遍遍历，得到随机指针指向的索引
        List<Integer> randomIndex = new ArrayList<>();
        p = head;
        while (p != null) {
            randomIndex.add(p.random == null ? null : list.indexOf(p.random));
            p = p.next;
        }

        // 第三遍遍历，更新newHead的随机指针关系
        q = dummyHead.next;
        int i = 0;
        while (q != null) {
            q.random = randomIndex.get(i) == null ? null : newList[randomIndex.get(i)];
            i++;
            q = q.next;
        }

        return dummyHead.next;
    }

    public static RandomNode copyRandomList1(RandomNode head) {
        // 使用hash表保存旧节点和新节点之间的关系
        HashMap<RandomNode, RandomNode> map = new HashMap<>();

        RandomNode p = head;
        // 第一次遍历确定旧节点和新节点之间的关系
        while (p != null) {
            RandomNode np = new RandomNode(p.val);
            map.put(p, np);
            p = p.next;
        }
        // 第二次遍历确定旧节点的主链路关系和随机节点关系
        p = head;
        RandomNode q = new RandomNode(-1);
        while (p != null) {
            // 更新主链路节点
            q = map.get(p);
            // 更新主链路节点的next节点
            q.next = map.get(p.next);
            // 更新主链路节点的随机节点
            q.random = map.get(p.random);
            p = p.next;
        }
        // 返回新主链路节点的头节点
        return map.get(head);
    }

    public static RandomNode copyRandomList2(RandomNode head) {

        if (head == null) {
            return null;
        }

        // 使用head.next做中专，节约出hash表的空间

        // 1、对原链表做深拷贝，新节点紧跟原链表的后面
        // 原链表： 7 -> 13 -> 11 -> 10 -> 1
        // 拷贝后： 7 -> 7' -> 13 -> 13' -> 11 -> 11' -> 10 -> 10' -> 1 -> 1'
        // 2、对原链表的随机节点做深拷贝
        // 对于拷贝后的链表list，有如下规律：
        // 对于索引i，如果i是偶数节点，则list[i]是原链表的节点，且list[i+1]就是深拷贝过后的新节点
        // 那么新节点的随机节点有如下关系：list[i+1].random = list[i].random.next
        // 7.random = 13 那么 7'.random = 7.random.next = 13'

        RandomNode p = head;
        while (p != null) {
            RandomNode q = new RandomNode(p.val);
            q.next = p.next;
            p.next = q;
            p = q.next;
        }
        // 设置新节点的random节点
        p = head;
        while (p != null) {
            // p.next就是新节点的random等于p的random的新节点
            // 即list[i+1].random = list[i].random.next
            if (p.random != null) {
                p.next.random = p.random.next;
            }
            // p是遍历原链表的，由于中间插入了p'，所以需要p=p.next.next
            p = p.next.next;
        }
        // 拆分链表
        p = head;
        RandomNode dummy = new RandomNode(-1);
        RandomNode q = dummy;
        while (p != null) {
            q.next = p.next;
            q = q.next;
            // 保持原链表结构不变
            p.next = q.next;
            p = q.next;
        }
        return dummy.next;
    }
}


class RandomNode {

    /**
     * 当前节点的值
     */
    int val;

    /**
     * 指向当前节点的下一个节点
     */
    RandomNode next;

    /**
     * 随机指向链表中的其他某个节点，或者指向null
     */
    RandomNode random;

    public RandomNode(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}