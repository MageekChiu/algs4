package edu.princeton.cs.other;

/**
 * @author Mageek Chiu
 * @date 2018/3/18 0018:16:20
 */
public class Radix10ToK {

    private static String charString = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * 十进制数到N进制
     *
     采用除留取余，逆序排列。
     例如：10进制65036转为16进制
     65036 除 16，余数 12(C)，商4064
     4064 除 16，余数 0(0)，商254
     254 除 16，余数 14(E)，商15
     15除16，余数 15(F)，商0，结束
     得16进制为 FE0C
     *
     * @param number 十进制数
     * @param N 其他进制
     * @return 其他进制数
     */
    private static String radix10ToK(Long number, int N){

        Long rest = number;
        StringBuilder result = new StringBuilder(0);
        while (rest != 0) {
            result.append(charString.charAt(new Long((rest % N)).intValue()));
            rest = rest / N;
        }
        return result.length() == 0 ? "0":result.reverse().toString();
    }

    /**
     * 其他进制转10进制
     从低位到高位按权展开即可。
     例如：8进制1356 转为10进制
     6*8^0 + 5 * 8^1 + 3 * 8^2 + 1 * 8^3 = 750
     * @param number 数
     * @param N 进制
     * @return 10进制数
     */
    private static long radixKTo10(String number,int N){
        char ch[] = number.toCharArray();
        int len = ch.length;
        long result = 0;
        if (N == 10) {
            return Long.parseLong(number);
        }
        long base = 1;
        for (int i = len - 1; i >= 0; i--) {
            int index = charString.indexOf(ch[i]);
            result += index * base;
            base *= N;
        }
        return result;
    }

    public static void  main(String args[]){
        System.out.println(radix10ToK(17L,16));// 11
        System.out.println(radix10ToK(165L,16));// a5
        System.out.println(radix10ToK(165L,2));// 10100101

        System.out.println(radixKTo10("24",16));// 36
        System.out.println(radixKTo10("24",8));//  20
        System.out.println(radixKTo10("0110",2));// 6

    }
}
