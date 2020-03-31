package stack;

import java.util.Random;

/**
 * Created by n!Ck
 * Date: 2019-01-18
 * Time: 17:10
 * Description:
 */
public class TestStack {
    public static void main(String[] args) {
        Stack<Integer> intStack = new Stack<>();
        intStack.initStack(10);

        System.out.println("初始化一个空栈，容量为10，： " + intStack.toString());

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            intStack.push(random.nextInt(100));
        }

        System.out.println("压入10个100以内的随机数： " + intStack.toString());

        for (int i = 0; i < 5; i++) {
            System.out.println(intStack.pop());
        }
        System.out.println("弹出栈前五个元素： " + intStack.toString());

        for (int i = 0; i < 3; i++) {
            int j = i + 7;
            intStack.push(j);
        }
        System.out.println("再依次压入7,8,9： " + intStack.toString());
    }
}
