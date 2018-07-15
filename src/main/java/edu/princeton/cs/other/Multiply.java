package edu.princeton.cs.other;

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
-----------------------------------------------------------------------------
 给定一个非负整数组成的非空数组，在该数的基础上加一，返回一个新的数组。
 最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。
 你可以假设除了整数 0 之外，这个整数不会以零开头。
 示例 1:
 输入: [1,2,3]
 输出: [1,2,4]
 解释: 输入数组表示数字 123。
 示例 2:
 输入: [4,3,2,1]
 输出: [4,3,2,2]
 解释: 输入数组表示数字 4321。
 -----------------------------------------------------------------------------
 给定两个二进制字符串，返回他们的和（用二进制表示）。
 输入为非空字符串且只包含数字 1 和 0。
 示例 1:
 输入: a = "11", b = "1"
 输出: "100"
 示例 2:
 输入: a = "1010", b = "1011"
 输出: "10101"

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

    public static int[] plusOne(int[] digits) {
        int len = digits.length;
        int[] result = new int[len+1];//可能溢出
        int start = len;// 从result末尾开始填充
        int sum = digits[start-1]+1;
        int mod = sum % 10;
        int carry = sum / 10;
        result[start--] = (mod);
        while (start>0){
            sum = digits[start-1]+carry;
            mod = sum % 10;
            carry = sum / 10;
            result[start--] = (mod);
        }
        if (carry>0) result[0]=carry;
        if (result[0]==0){// 要去掉第一个0
            System.arraycopy(result,1,digits,0,len);
            return digits;
        }else {
            return result;
        }
    }

    public static String addBinary(String a, String b) {
        int len1 = a.length(),len2 = b.length();
        StringBuilder sb = new StringBuilder();
        int i = len1-1,j = len2-1,carry = 0;
        while (i>=0 || j>=0){
            int x = (i>=0) ? a.charAt(i)-'0' : 0;
            int y = (j>=0) ? b.charAt(j)-'0' : 0;
            int sum = carry + x + y;
            carry = sum / 2;
            sb.append(sum % 2);
            i--;j--;
        }
        if (carry>0) sb.append(carry);// 进位，变长了
        return sb.reverse().toString();
    }

    // 感受：基本概念的本质，乘法本质就是遍历两个数依次相乘
    public static void main (String ...args){
//        String i1 = "123";String i2 = "456";//56088
//        String i1 = "498828660196";String i2 = "840477629533";//419254329864656431168468
//        out.println( multiply(i1,i2));

////        int[] nums = {3,4,2};
//        int[] nums = {0};
////        int[] nums = {9,9,9};
//        int[] res = plusOne(nums);//
//        for (int i = 0; i < res.length; i++) {
//            System.out.println(res[i]);
//        }

        System.out.println(addBinary("11","1"));//100
        System.out.println(addBinary("100","10"));//110
        System.out.println(addBinary("1010","1011"));//10101


    }
}
