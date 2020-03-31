package stack;

/**
 * Created by n!Ck
 * Date: 2019-01-18
 * Time: 16:47
 * Description:
 */
public class Stack<T> {

    /**
     * 线性表构成的栈
     * <p>
     * 数据域
     */
    private T[] t;

    /**
     * 线性表长度，栈的容量
     */
    private int length = 0;

    /**
     * 栈顶指针，-1表示空栈
     */
    private int top = -1;

    /**
     * 初始化一个指定大小的空栈
     */
    public void initStack(int size) {
        this.t = (T[]) new Object[size];
        this.length = size;
    }

    /**
     * 判空
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 入栈
     *
     * @param t 入栈元素
     * @return boolean
     * @throws StackOverflowError 栈溢出异常
     */
    public boolean push(T t) throws StackOverflowError {
        // 栈上溢出
        if (top + 1 >= length) {
            throw new StackOverflowError("stack overflow at top");
        }
        top++;
        this.t[top] = t;
        return true;
    }

    /**
     * 出栈
     *
     * @return T 出栈元素
     * @throws StackOverflowError 栈溢出异常
     */
    public T pop() throws StackOverflowError {
        // 栈下溢出
        if (top == -1) {
            throw new StackOverflowError("stack overflow at bottom");
        }
        T temp = this.t[top];
        this.t[top--] = null;
        return temp;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[ stack : ");
        if (t != null) {
            for (T t : t) {
                if (t != null) {
                    stringBuilder.append(t).append(" ");
                } else {
                    stringBuilder.append("null").append(" ");
                }
            }
        } else {
            stringBuilder.append("null ");
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
