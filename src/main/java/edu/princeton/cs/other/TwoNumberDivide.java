package edu.princeton.cs.other;

import static java.lang.System.out;

/**

 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 返回被除数 dividend 除以除数 divisor 得到的商。
 示例 1:
 输入: dividend = 10, divisor = 3
 输出: 3
 示例 2:
 输入: dividend = 7, divisor = -3
 输出: -2
 说明:
 被除数和除数均为 32 位有符号整数。
 除数不为 0。
 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。

 *
 * @author Mageek Chiu
 */
class TwoNumberDivide {

    /**
     思路
     有一个最简单直观的方法，设置一个i=1，比较dividend和divisor大小，如果满足dividend>divisor，
     就令divisor=divisor+divisor，i++，继续判断dividend>divisor，继续执行循环，直到不满足条件时候输出i的大小。

     这种方法最简单直观，但是想想也直到，这个肯定在处理时间上超时。

     那么还有什么方法类似于乘除法呢，想到了一种，移位。例如：现在定义一个数int i=2，那么如果执行i=i<<1，
     那么此时的i就等于4，相当于乘以二了。为了快速得到结果，我们可以加入移位运算，怎么用？用在哪？

     现在我们提出一种方法，举个简单的例子：假设输入了两个数，被除数为13，除数为2，下面我们一步一步的解释这种方法：

     1、让dividend=13，divisor=2。设一个全局变量的result=0，存储最终结果，设置一个临时结果为re=1，
     现在这个1表示divisor的值的大小，等于一个原始除数。

     2、判断dividend是否大于divisor左移一位，即dividend > divisor<< 1是否成立？
     成立则说明除数大小左移一位（即乘以2）也不会超过被除数，那么执行第3步，否则，执行第4步。

     3、re=re<<，divisor=divisor<<，返回执行第2步。

     4、判断当前被除数减去当前的除数是否大于等于原始的除数，
     即判断dividend-divisor>=2是否成立，成立，则说明还有再除以2的空间，只不过现在的除数已经变了，
     被除数的一部分也已经被除过了，那么就执行第5步，否则，执行第6步；如果不成立，执行第6步。

     5、先把临时结果re的值加到最终结果result中去，然后让dividend=dividend-divisor，
     然后把divisor复位，即让divisor=2，重新执行第2步。

     6、返回结果值result。

     下面是每一步时，被除数dividend、除数divisor、中间结果re和最终结果result的状态：

     步骤　　dividend　　divisor　　re　　result

     1　　        13　　         2　　      1　　    0

     2　　        13　　    2<<=4　　1<<=2　  0

     3　　 　　13　　　4<<=8　　2<<=4　  0

     4　　    13-8=5　　 复位为2    复位为1   re+result=4+0=4

     5　　        5　　       2<<=4        1<<=2     4

     6　　     5-4=1　　  复位为2　复位为1     re+result=2+4=6

     然后就比较不了了，那么结果为6，bingo！
     * @param dividend
     * @param divisor
     * @return
     */
    public static int divide(int dividend, int divisor) {
        //特殊情况:当除数为0时，一定是内存溢出
        if(divisor==0) return Integer.MAX_VALUE;
        //判断符号位
        int sign = 1;
        if((dividend>0 && divisor<0) || (dividend<0 && divisor>0)) sign = -1;
        //定义为长整型的目的是为了在取绝对值时不会内存溢出
        //sor1作为局部的除数出现
        long dend = Math.abs((long)dividend), sor = Math.abs((long)divisor), sor1=sor;
        //特殊情况：当被除数为0或者被除数小于除数时，返回值一定是0
        if(dividend==0 || dend<sor) return 0;
        //把最终结果定义成全局变量
        long result = 0;
        while(true){
            //定义一个中间结果，用于对全局结果的累加
            long re = 1;
            //如果当前的被除数还大于当前除数左移一位之后的数（即乘以2），那么进入循环
            while(dend > sor1<<1){
                //符合条件情况下，除数左移一位，中间结果也左移一位（中间结果的大小 等于 当前的sor1/sor的结果数）
                re=re<<1;
                sor1=sor1<<1;
            }
            result = result+re;
            //此时说明中间除数已经到了一个临界点，即当前除数小于当前被除数，但左移一位就会大于被除数。
            //判断dend与当前除数之间的差值是否大于等于原始除数，如果等于，说明还有除的空间，那么更新除数和被除数之后重返循环，否则结束
            if((dend-sor1) >= sor){
                dend=dend-sor1;
                sor1=sor;
            }else break;
        }
        //判断内存溢出
        if(result*sign>Integer.MAX_VALUE || result*sign<Integer.MIN_VALUE) return Integer.MAX_VALUE;
        return (int)result*sign;
    }

    // 感受：左移右移的概念与应用
    public static void main (String ...args){
        out.println(divide(7,-3));// -2
        out.println(divide(10,3));// 3
//        out.println(divide(Integer.MAX_VALUE,10));
    }

}
