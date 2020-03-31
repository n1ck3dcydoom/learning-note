package algorithm.other;

import java.util.ArrayList;
import java.util.List;

import algorithm.structure.QueueByTwoStack;

/**
 * Created by n!Ck
 * Date: 2020/4/1
 * Time: 0:09
 * Description:
 */

public class TwoStackForQueue {
    private static List<Integer> numberList = new ArrayList<>();

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            numberList.add(i);
        }
        QueueByTwoStack queue = new QueueByTwoStack();
        try {
            // 压栈，
            numberList.forEach(e -> queue.push(e));
            // 出栈
            while (!queue.isEmpty()) {
                System.out.print(queue.pop() + " ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
