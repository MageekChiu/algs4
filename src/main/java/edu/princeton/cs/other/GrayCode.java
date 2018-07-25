package edu.princeton.cs.other;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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

    /**

     有两种特殊字符。第一种字符可以用一比特0来表示。第二种字符可以用两比特(10 或 11)来表示。
     现给一个由若干比特组成的字符串。问最后一个字符是否必定为一个一比特字符。给定的字符串总是由0结束。
     示例 1:
     输入:
     bits = [1, 0, 0]
     输出: True
     解释:
     唯一的编码方式是一个两比特字符和一个一比特字符。所以最后一个字符是一比特字符。
     示例 2:
     输入:
     bits = [1, 1, 1, 0]
     输出: False
     解释:
     唯一的编码方式是两比特字符和两比特字符。所以最后一个字符不是一比特字符。
     注意:
     1 <= len(bits) <= 1000.
     bits[i] 总是0 或 1.
     */
    public static boolean isOneBitCharacter(int[] bits) {
        int len = bits.length;
        if (bits[len-1]!=0) return false;
        for (int i = 0; i < len-1;) {//最后一位不用管了
            if (bits[i]==0) i++;
            else {//bits[i]==1
                if (i+1<len-1) i+=2;
                else  return false;
            }
        }
        return  true;
    }

    /**
     给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。从0开始
     在杨辉三角中，每个数是它左上方和右上方的数的和。
     示例:
     输入: 3
     输出: [1,3,3,1]
     进阶：
     你可以优化你的算法到 O(k) 空间复杂度吗？

     思路 直接dp空间是 k^2，实际上可以直接用公式算

     第n行的m个数可表示为 C(n-1，m-1)，即为从n-1个不同元素中取m-1个元素的组合数。

     另外：
     用改进版的动态规划，将二维dp改为两个dp数组，cur和last，cur是从上一行last演化而来，
     注意交替变化，res始终记录当前算得的最新值。如果i还继续循环，会把当前行cur赋给res,last赋给cur做下一次计算用，cur赋给last
     表示下一次的上一行是当前行。 此处关键就是 dp[i] 只依赖于 dp[i-1] 所以可以把前面的去掉
     */
    public static List<Integer> getRow(int rowIndex) {
        int[] last = new int[rowIndex +1];
        int[] cur = new int[rowIndex + 1];
        for (int i = 0; i < rowIndex + 1; i++) {
            last[i] = 1;
            cur[i] =1;
        }
        int[] res = cur;
        for (int i = 2; i < rowIndex + 1; i++) {
            for (int j = 1; j < i; j++) {
                cur[j] = last[j] + last[j-1];
            }
            res = cur;
            cur = last;
            last = res;
        }
        ArrayList<Integer> r = new ArrayList<>();
        for (int i = 0; i < rowIndex + 1; i++) {
            r.add(res[i]);
        }
        return r;
    }

    

    // 感受：格雷码，典型的递归应用，递归和DP其实就是数学归纳法思想的应用
    public static void main (String ...args){
//        out.println(grayCodeString(2));
//        out.println(grayCode(2));

//        out.println(grayCodeString(3));
//        out.println(grayCode(3));

//        out.println(isOneBitCharacter(new int[]{1,0,0}));
//        out.println(isOneBitCharacter(new int[]{1,1,1,0}));


    }
}
