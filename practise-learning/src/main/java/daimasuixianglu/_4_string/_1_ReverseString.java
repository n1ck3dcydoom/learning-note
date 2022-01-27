package daimasuixianglu._4_string;

public class _1_ReverseString {

    public void reverseString(char[] s) {
        // 双指针，从头尾分别往中间遍历，直到r<=l
        int n = s.length;
        int l = 0, r = n - 1;
        while (l < r) {
            char temp = s[l];
            s[l] = s[r];
            s[r] = temp;
            l++;
            r--;
        }
    }
}
