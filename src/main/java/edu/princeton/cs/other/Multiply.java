package edu.princeton.cs.other;

import static java.lang.System.out;

/**
 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 示例 1:
 输入: num1 = "2", num2 = "3"
 输出: "6"
 示例 2:
 输入: num1 = "123", num2 = "456"
 输出: "56088"
 说明：
 num1 和 num2 的长度小于110。
 num1 和 num2 只包含数字 0-9。
 num1 和 num2 均不以零开头，除非是数字 0 本身。
 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。


 * @author Mageek Chiu
 */
class Multiply {
//
//    public static String multiply(String num1, String num2) {
//        long a = 0L,b=0L;
//        int position = 10,pow = 0;
//        for (int i = num1.length()-1;i>=0;i--){
//            a += ((num1.charAt(i)-'0')*Math.pow(position,pow++));
//        }
//        position = 10;pow = 0;
//        for (int j = num2.length()-1;j>=0;j--){
//            b +=(num2.charAt(j)-'0')*Math.pow(position,pow++);
//        }
////        System.out.println(a+","+b);
//        return String.valueOf(a*b);// 两个long相乘会溢出
//
//    }


    public static String multiply(String num1, String num2) {
        int len1 = num1.length(),len2 = num2.length();
        int[] result = new int[220];// 最长不过110+110位
        for (int i = len1-1;i>=0;i--){
            int powI = len1-1-i;
            for (int j = len2-1;j>=0;j--){
                int powJ = len2-1-j;
                int tmp = (num2.charAt(j)-'0')*(num1.charAt(i)-'0');//不会唱过2位
                add(result,powI+powJ,tmp);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();

        int end = 0;
        for (int i = 0; i<result.length; i++) {
            stringBuilder.append(result[i]);
            if (result[i]!=0) end =i;
        }
        stringBuilder.reverse();
        return stringBuilder.toString().substring(result.length-end-1);
    }

    public static void  add(int[] result,int start,int tmp){
        int mod ,carry ;
        int sum = result[start]+tmp;
        mod = sum % 10;
        carry = sum / 10;
        result[start++] = (mod);
        while (carry>0){
            sum = result[start]+carry;
            mod = sum % 10;
            carry = sum / 10;
            result[start++] = (mod);
        }
    }

    // 感受：基本概念的本质，乘法本质就是遍历两个数依次相乘
    public static void main (String ...args){
//        String i1 = "123";String i2 = "456";//56088
        String i1 = "498828660196";String i2 = "840477629533";//419254329864656431168468
        out.println( multiply(i1,i2));
    }
}
