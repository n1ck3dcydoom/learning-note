package stack;

/**
 * Created by n!Ck
 * Date: 2019-02-04
 * Time: 16:10
 * Description:
 */
public class TestRPN {
    public static void main(String[] args) {
        String postfix = "9 3 1 - 3 * + 10 2 / +";
        RPN rpn = new RPN(10);
        System.out.println(rpn.getResultByPostfix(postfix));
    }
}
