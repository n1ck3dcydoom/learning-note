package leetcode.stack.easy;

/**
 * Created by n!Ck
 * Date: 2022/10/20
 * Time: 22:57
 * Description:
 */

public class _1700_NumberOfStudentsUnableToEatLunch {

    public int countStudents(int[] students, int[] sandwiches) {
        // 可以把学生看做是循环队列的形式,那么遍历的时候每个学生都可以顺序访问到
        // 而三明治每次只提供栈顶的类型
        // 所以只需要每次从学生队列里面移除和栈顶类型相同的学生
        // 直到栈空或者学生队列里面找不到与栈顶类型相同的学生了

        // 统计学生当中 0 和 1 的数量
        int[] counts = new int[2];
        for (int n : students) {
            if (n == 0) {
                counts[0]++;
            } else {
                counts[1]++;
            }
        }
        // 遍历所有三明治,栈模拟,从前往后遍历(从栈顶往栈底遍历)
        for (int s : sandwiches) {
            // 当前栈顶类型的学生已经没有了
            if (counts[s] == 0) {
                break;
            } else {
                counts[s]--;
            }
        }
        // 到最后肯定只剩一种类型的学生,另一种类型的全部都有三明治
        // 返回两者的剩余数量之和,有一种为 0 也不影响答案
        return counts[0] + counts[1];
    }
}
