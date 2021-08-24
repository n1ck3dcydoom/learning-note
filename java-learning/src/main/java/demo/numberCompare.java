package demo;

/**
 * Created by n!Ck
 * Date: 2019-01-11
 * Time: 20:51
 * Description:
 */

/**
 * 对于基本数据类型的比较，有可能会发生隐式转换，一般把精度小的转换为精度大的
 * <p>
 * 这里只是针对的基本数据类型
 * <p>
 * 如果两个操作数其中有一个是double类型，另一个操作就会转换为double类型。
 * <p>
 * 否则，如果其中一个操作数是float类型，另一个将会转换为float类型。
 * <p>
 * 否则，如果其中一个操作数是long类型，另一个会转换为long类型。
 */
public class numberCompare {
    public static void main(String[] args) {
        double d1 = 41.0d;
        float f1 = 41.0f;
        int xi = 41;
        long xl = 41L;
        System.out.println(xi == d1); // true
        System.out.println(xi == f1); // true
        System.out.println(f1 == d1); // ture
        System.out.println(xi == xl); // true
        System.out.println(xl == f1); // true
        System.out.println(xl == d1); // true
    }
}
