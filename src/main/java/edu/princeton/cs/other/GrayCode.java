package edu.princeton.cs.other;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.lang.System.out;

/**
 格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。
 给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。格雷编码序列必须以 0 开头。
 示例 1:
 输入: 2
 输出: [0,1,3,2]
 解释:
 00 - 0
 01 - 1
 11 - 3
 10 - 2
 对于给定的 n，其格雷编码序列并不唯一。
 例如，[0,2,3,1] 也是一个有效的格雷编码序列。
 00 - 0
 10 - 2
 11 - 3
 01 - 1
 示例 2:
 输入: 0
 输出: [0]
 解释: 我们定义格雷编码序列必须以 0 开头。
 给定编码总位数为 n 的格雷编码序列，其长度为 2^n。当 n = 0 时，长度为 2^0 = 1。
 因此，当 n = 0 时，其格雷编码序列为 [0]。

 思路：
 1位格雷码有两个码字
 (n+1)位格雷码中的前2^n个码字等于n位格雷码的码字，按顺序书写，加前缀0
 (n+1)位格雷码中的后2^n个码字等于n位格雷码的码字，按逆序书写，加前缀1
 n+1位格雷码的集合 = n位格雷码集合(顺序)加前缀0 + n位格雷码集合(逆序)加前缀1

 * @author Mageek Chiu
 */
class GrayCode {

    public static List<String> grayCodeString(int n) {
        List<String> list = new ArrayList<>((int)Math.pow(2,n));
        if (n==0){
            list.add("0");
        }else if (n==1){
            list.add("0");list.add("1");
        }else {
            List<String> list1 = grayCodeString(n-1);
            for (int i = 0; i < list1.size(); i++) {
                list.add("0"+list1.get(i));
            }
            for (int i = list1.size() - 1; i >= 0; i--) {
                list.add("1"+(list1.get(i)));
            }
        }
        return list;
    }

    public static String reverse(String str){
        return new StringBuilder(str).reverse().toString();
    }

    public static List<Integer> grayCode(int n) {
        List<String> list = grayCodeString(n);
        List<Integer> list1 = new LinkedList<>();
        for (String s : list) {
            list1.add(Integer.parseInt(s,2));
        }
        return  list1;

    }

    // 感受：格雷码，典型的递归应用
    public static void main (String ...args){
//        out.println(grayCodeString(2));
//        out.println(grayCode(2));

        out.println(grayCodeString(3));
        out.println(grayCode(3));
    }
}
