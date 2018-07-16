package edu.princeton.cs.other;

import static java.lang.System.out;

/**
 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 示例 1:
 输入: 2.00000, 10
 输出: 1024.00000
 示例 2:
 输入: 2.10000, 3
 输出: 9.26100
 示例 3:
 输入: 2.00000, -2
 输出: 0.25000
 解释: 2-2 = 1/22 = 1/4 = 0.25
 说明:
 -100.0 < x < 100.0
 n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
 * @author Mageek Chiu
 */
class MyPow {

    // 这样直接循环在N很大的时候容易超时，O(n)
    public static double myPow0(double x, int n) {
        if (n>0){
            double result = x;
            while (--n>0){
                result *= x;
            }
            return result;
        }else if (n<0){
            double result = 1/x;
            while (++n<0){
                result /= x;
            }
            return result;
        }else {
            return 1;
        }
    }

    // O(logn) 最坏也是 O(n)  2147483647 与 2147483648（2^31次方） 2到30次方时就会剩一大串出去mod
    public static double myPow1(double x, int n) {
        if (n>0){
            double result = x;
            double i = 1,mod = 0;// 用double，int溢出就为0,i代表当前的次方
            while (2*i<=n){// 这样的话最后还是会剩一大串
                result *= result;
                i *= 2;
                mod = n-i;
            }
            while (mod-->0 ) result *= x;
            return result;
        }else if (n<0){
            double result = 1/x;
            double i = -1,mod = 0;
            while (2*i>=n){
                result *= result;
                i *= 2;
                mod = i-n;
            }
            while (mod-->0) result *= 1/x;
            return result;
        }else {
            return 1;
        }
    }
    public static double myPow(double x, int n) {
        double r = 1;
        if (n== Integer.MIN_VALUE){// minvalue不能转abs
            for (int i = n; i < 0; i++) {
                if (i % 2 == 0) {
                    x = x * x;
                    i = i / 2;
                }
                r = r * x;
            }
            return 1/r;
        }
        int len = Math.abs(n);
        for (int i = len; i > 0; i--) {
            if (i % 2 == 0) {
                x = x * x;
                i = i / 2;
            }
            r = r * x;
        }
        if (n < 0) {
            r = 1 / r;
        }
        return r;
    }
    // 感受：
    public static void main (String ...args){
//        out.println(myPow(2,10));
//        out.println(myPow(2,2));
//        out.println(myPow(2,-2));
//        out.println(myPow(2,-3));
//        out.println(myPow(2,0));
//        out.println(myPow(2.1,3));
//        out.println(myPow(34.00515,-3));
//        out.println(myPow(0.0001,2147483647));
        out.println(myPow(2,-2147483648));
        out.println(myPow(1,-2147483648));
//        out.println(myPow(-4.48392,6));
    }
}
