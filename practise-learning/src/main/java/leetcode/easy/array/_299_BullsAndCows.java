package leetcode.easy.array;

/**
 * @author zhanglei
 * @version 1.0
 * @description 你在和朋友一起玩 猜数字（Bulls and Cows）游戏，该游戏规则如下：
 *
 * 你写出一个秘密数字，并请朋友猜这个数字是多少。
 * 朋友每猜测一次，你就会给他一个提示，告诉他的猜测数字中有多少位属于数字和确切位置都猜对了（称为“Bulls”, 公牛），有多少位属于数字猜对了但是位置不对（称为“Cows”, 奶牛）。
 * 朋友根据提示继续猜，直到猜出秘密数字。
 * 请写出一个根据秘密数字和朋友的猜测数返回提示的函数，返回字符串的格式为 xAyB ，x 和 y 都是数字，A 表示公牛，用 B 表示奶牛。
 *
 * xA 表示有 x 位数字出现在秘密数字中，且位置都与秘密数字一致。
 * yB 表示有 y 位数字出现在秘密数字中，但位置与秘密数字不一致。
 * 请注意秘密数字和朋友的猜测数都可能含有重复数字，每位数字只能统计一次。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/bulls-and-cows
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2020/7/14 10:42
 **/
public class _299_BullsAndCows {

    public static void main(String[] args) {
        String secret = "112341";
        String guess = "011115";
        System.out.println(getHint(secret, guess));
    }

    public static String getHint(String secret, String guess) {
        int bulls = 0;
        int cows = 0;
        // 用数组存放0-9出现的次数
        int[] indexSecret = new int[10];
        int[] indexGuess = new int[10];
        // 第一次遍历secret找索引相同，且数字相同（找Bulls）
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                bulls++;
            } else {
                indexSecret[secret.charAt(i) - '0']++;
                indexGuess[guess.charAt(i) - '0']++;
            }
        }
        // 第二次遍历guess找相同数字，但是位置不同（找Cows）
        for (int i = 0; i < 10; i++) {
            //                    0  1  2  3  4  5
            // s = 1 '1' 2341    [0][2][1][1][1][0]...
            // g = 0 '1' 1115    [1][3][0][0][0][1]
            // 在第二个1去掉后的数组表示在索引不同的地方，1在s中出现了2次，1在g中出现了3次
            // 这时只需要取出现次数少的频次即可
            cows += Math.min(indexSecret[i], indexGuess[i]);
        }
        return bulls + "A" + cows + "B";
    }
}