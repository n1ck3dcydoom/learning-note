package demo.sometest;

/**
 * Created by n!Ck
 * Date: 2019-02-28
 * Time: 12:57
 * Description:
 * 三目运算符的表达式精度自动转化
 */
public class TestTernaryOperator {
    public static void main(String[] args) {
        int a = 5;
        System.out.println("a = " + ((a > 5) ? 10.0 : 5));
        // 最后输出的结果是 a = 5.0
        // 而不是 a = 5
        // 因为在三目运算符中，java会根据表达式的值进行自动转换
        // 因为表达式2的精度为浮点数，所以表达式3de精度会被自动转换为浮点数

        char x = 'x';
        int i = 10;
        System.out.println("a = " + (false ? i : x)); // 120
        System.out.println("a = " + (false ? x : 97)); // ASCII码中 'a' = 97
        // 由于100是一个常量表达式。
        // 若三目运算符中的两个表达式有一个是常量表达式，另一个是类型T（本题中为char）的表达式
        // 且常量表达式可以被T表示，则输出结果是T类型，所以97会被转化为char类型，从而输出a ( 'a' = 97 )

        char x1 = 'x';
        System.out.println("x1 = " + (false ? 10000 : x1)); // x
        // char的范围为0-65535，10000在范围内，所以输出x

        char x2 = 'x';
        System.out.println("x2 = " + (false ? 100000 : x2)); // 120
        // 100000不在char的范围内，所以输出x的ASCII码 120
    }
}
