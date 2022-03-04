package redis.面试场景.Redis基本应用场景;

import java.util.LinkedHashMap;

/**
 * @author zhanglei
 * @version 1.0
 * @description TODO
 * @date 2022/3/4 14:19
 **/

public class Main {

    public static void main(String[] args) {
        LinkedHashMap<String, String> cache = new LinkedHashMap<>();
        cache.put("t", "test");
        cache.put("a", "apple");
        System.out.println(cache.size());
    }
}
