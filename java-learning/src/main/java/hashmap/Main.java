package hashmap;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        HashMap<String, String> hash = new HashMap<>(6);
    }

    /**
     * 用于快速定位hashmap中某个键值对的位置
     *
     * @param h hashcode
     * @param l map中Node[] 长度
     * @return 位置索引
     */
    private static int indexFor(int h, int l) {
        // jdk 8:
        // l must be a non-zero power of 2
        // l必须是一个非0的2的幂
        return h & (l - 1);
    }
}
