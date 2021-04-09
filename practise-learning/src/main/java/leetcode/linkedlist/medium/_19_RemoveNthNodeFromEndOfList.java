package leetcode.linkedlist.medium;

/**
 * @version 1.0
 * @description leetcode
 * <p>
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * <p>
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * 示例 2：
 * <p>
 * 输入：head = [1], n = 1
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/4/9 23:18
 **/
public class _19_RemoveNthNodeFromEndOfList {
    public NewListNode removeNthFromEnd(NewListNode head, int n) {

        if (head == null) {
            return null;
        }


        // 删除第k个节点，必须要找到第k-1个节点的位置，即k的前驱节点
        // p = k-1
        // q = k
        // p.next = q.next
        // q = null

        // 双指针快慢指针，如果先开始让快指针和慢指针中间相差n

        // 如果快慢指针同时初始都指向head
        // 让快指针先移动n次，然后同时移动快慢指针，直到快指针指向null
        // 此时慢指针指向的是k节点，而非k节点的前驱节点


        NewListNode preHead = new NewListNode();
        preHead.next = head;

        NewListNode slow = preHead;
        NewListNode fast = preHead;

        // 让快指针先n+1步，快慢指针中间有间隔n个元素
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // 同时移动快慢指针，直到快指针指向null
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        // 慢指针指向k的前驱节点，删掉k
        NewListNode k = slow.next;
        slow.next = k.next;
        k = null;

        // 不能返回head，因为head也有可能成为被删掉的节点
        return preHead.next;
    }
}