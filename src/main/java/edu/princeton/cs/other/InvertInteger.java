package edu.princeton.cs.other;

import static java.lang.System.out;

/**
 给定一个 32 位有符号整数，将整数中的数字进行反转。

 示例 1:
 输入: 123
 输出: 321

 示例 2:
 输入: -123
 输出: -321

 示例 3:
 输入: 120
 输出: 21

 注意:
 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。根据这个假设，如果反转后的整数溢出，则返回 0。
 *
 * @author Mageek Chiu
 */
class InvertInteger {

    public static int reverse(int x) {
        String a = String.valueOf(x);
        String res = "";
        if (x<0){
            res += "-";
            a = a.substring(1,a.length());
        }
        for (int i=a.length()-1;i>=0;i--){
            res += a.substring(i,i+1);
        }
        try {
            int ans = Integer.parseInt(res);
            return ans;
        }catch (Exception e){
            return 0;
        }
    }

    // 感受：
    public static void main (String ...args){
        int a = -123;
        int res = reverse(a);//
        out.println(res);

        int b = 45678;
        int resb = reverse(b);//
        out.println(resb);
    }
}
