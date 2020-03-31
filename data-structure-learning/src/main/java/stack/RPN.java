package stack;

/**
 * Created by n!Ck
 * Date: 2019-02-04
 * Time: 15:51
 * Description: 后缀表达式 栈实现
 */
public class RPN {
    /**
     * 数字栈
     */
    private Stack<Double> numberStack = new Stack<>();
    /**
     * 运算符栈
     */
    private Stack<Character> opStack = new Stack<>();

    /**
     * 初始化数字栈和运算符栈
     *
     * @param length 栈空间大小
     */
    public RPN(int length) {
        numberStack.initStack(length);
        opStack.initStack(length);
    }

    /**
     * 计算后缀表达式的值
     * <p>
     * 算法：从左到右遍历中缀表达式，遇到数字就压入数字栈，遇到运算符就弹出数字栈上面的两个数进行运算，运算结果压入数字栈
     * 遇到 - 或者 / 运算时，第一个弹出的数作为（减数/除数），第二个弹出的数作为（被减数/被除数）
     *
     * @param postfix 后缀表达式
     * @return Double 表达式的值
     */
    public Double getResultByPostfix(String postfix) {

        String[] postfixCharArray = postfix.split(" ");
        for (String str : postfixCharArray) {
            char ch = str.toCharArray()[0];
            if (str.toCharArray().length > 1) {
                int temp = 0;
                for (int i = 0; i < str.toCharArray().length; i++) {
                    temp += ((int) str.toCharArray()[i] - 48) * Math.pow(10, i + 1);
                }
                numberStack.push((double) temp);
            } else if (Character.isDigit(ch)) {
                numberStack.push((double) ch - 48);
            } else {
                double a = numberStack.pop();
                double b = numberStack.pop();
                switch (ch) {
                    case '+':
                        numberStack.push(b + a);
                        break;
                    case '-':
                        numberStack.push(b - a);
                        break;
                    case '*':
                        numberStack.push(b * a);
                        break;
                    case '/':
                        numberStack.push(b / a);
                        break;
                    default:
                }
            }
        }
        return numberStack.pop();
    }
}
