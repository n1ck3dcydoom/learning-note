package nowcode.top101;

/**
 * Created by n!Ck
 * Date: 2022/11/20
 * Time: 19:58
 * Description:
 */

public class _87_合并两个有序数组 {

    public static void main(String[] args) {
        int[] a = new int[]{4, 5, 6, 0, 0, 0};
        int[] b = new int[]{1, 2, 3};
        merge(a, 3, b, 3);
        System.out.println(a);

        int[] a1 = new int[]{1, 2, 3, 0, 0, 0};
        int[] b1 = new int[]{2, 5, 6};
        merge(a1, 3, b1, 3);
        System.out.println(a1);
    }

    public static void merge(int A[], int m, int B[], int n) {
        if (n == 0) {
            return;
        }
        int i = m - 1, j = n - 1;
        int k = m + n - 1;
        // 原地复制,从后往前遍历
        while (i >= 0 && j >= 0) {
            if (A[i] <= B[j]) {
                A[k--] = B[j--];
            } else {
                A[k--] = A[i--];
            }
        }
        // 将剩余的数组继续合并入 A
        // 而且只需要合并 B
        while (j >= 0) {
            A[k--] = B[j--];
        }
    }
}
