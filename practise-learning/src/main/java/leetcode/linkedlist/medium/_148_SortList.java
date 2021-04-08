package leetcode.linkedlist.medium;

/**
 * @version 1.0
 * @description leetcode
 * <p>
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 * <p>
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 * <p>
 * 输入：head = [-1,5,3,4,0]
 * 输出：[-1,0,3,4,5]
 * 示例 3：
 * <p>
 * 输入：head = []
 * 输出：[]
 * <p>
 * 提示：
 * <p>
 * 链表中节点的数目在范围 [0, 5 * 104] 内
 * -105 <= Node.val <= 105
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/4/8 20:36
 **/
public class _148_SortList {

    public static void main(String[] args) {
        NewListNode head = new NewListNode(4);
        head.next = new NewListNode(2);
        head.next.next = new NewListNode(1);
        head.next.next.next = new NewListNode(3);

        head = sortList(head);


        NewListNode p = head;
        while (p.next != null) {
            System.out.print(p.val + " ");
            p = p.next;
        }
    }

    public static NewListNode sortList(NewListNode head) {
        return quick(head, null);
    }

    /**
     * 快排实现链表排序
     * <p>
     * 链表相对于数组的优点在于，头尾操作快
     * <p>
     * 所以对于链表head，以head为基准遍历链表
     * 找到所有比head小的节点，然后头部插入到head之前
     * 找到所有比head大的节点，然后尾部插入到head之后
     */
    private static NewListNode quick(NewListNode head, NewListNode tail) {

        if (head == tail) {
            return head;
        }
        if (head.next == tail) {
            return head;
        }

        // 用于遍历的指针p
        NewListNode p = head.next;

        // 快排里面的左指针，右指针
        NewListNode left = head;
        NewListNode right = head;

        // 遍历链表
        while (p != tail) {
            // 保存p的next
            NewListNode pNext = p.next;
            // 当前p的值小于base值，头插到head的后面
            if (p.val < head.val) {
                p.next = left;
                left = p;
            }
            // 当前p的值大于base值，尾插到head的后面
            else {
                right.next = p;
                right = p;
            }
            // p向下移动
            p = pNext;
        }
        // 让right的next等于传入的尾指针，这样始终能够保证right是最后一个节点
        right.next = tail;

        // 递归head左边，这样能够得到头节点
        NewListNode node = quick(left, head);

        // 递归head右边，这样能够得到头节点的next节点
        head.next = quick(head.next, tail);

        return node;
    }

    /**
     * 归并排序
     */
    private NewListNode orderByMerge(NewListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 通过快慢指针找到链表的中心，从中心节点的next切割链表
        NewListNode slow = head;
        NewListNode fast = head.next;

        // 快指针一次移动两步，慢指针一次移动一步
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 这样偶数个节点，慢指针最终指向中间节点左边的节点
        // 奇数个节点，慢指针最终指向中间节点
        NewListNode cutNode = slow.next;
        // 从slow慢指针的next切断链表
        slow.next = null;

        // 一直递归下去，直到最后只剩下一个head节点，没法再切割链表了
        NewListNode left = orderByMerge(head);
        NewListNode right = orderByMerge(cutNode);

        // 完成上述两次递归后，left和right都是每次归并排序的最小不可分割的头节点
        // 从树的结构上来看，left和right必定来自于同一个父结点

        // 建立辅助头节点
        NewListNode h = new NewListNode();
        // 使用p节点来保存每次合并后的位置，同时移动p
        NewListNode p = h;
        // 开始从最小的两个叶子节点往上合并，同时排序
        while (left != null && right != null) {
            // 小的放前面
            // 每次只从left或right里面取一个小的加入p的后面，然后对应的指针后移一个位置
            // 完成依次加入p的操作
            if (left.val < right.val) {
                p.next = left;
                // left后移
                left = left.next;
            } else {
                p.next = right;
                right = right.next;
            }
            // 每次选择一个加入p后，p需要向后移动一个位置
            p = p.next;
        }
        // 如果left和right的链表长度不相等 (在总链表长度为偶数的情况下会出现)
        // 当一个链表已经用完后，剩下的链表自然全部就是大于前一个链表的节点了，直接加到p的后面
        if (left != null) {
            p.next = left;
        }
        if (right != null) {
            p.next = right;
        }
        return h.next;
    }
}