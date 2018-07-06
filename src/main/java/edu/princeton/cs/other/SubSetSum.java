package edu.princeton.cs.other;

import java.util.Arrays;

import static java.lang.System.out;

/**
 * 子集和问题：对于给定的正整数的集合S={ X1 ，X2 ，…，Xn } 和正整数C，求计算S 的一个子集S1使得S1所有元素和为C
 * @author Mageek Chiu
 */
class SubSetSum {

    public static int SubSetSum(int[] nums,int C) {
        int len = nums.length,sum = 0,ans = Integer.MIN_VALUE;

        return ans;
    }

    public static void main (String ...args){
        int[] S1 = {-2,2,3,4,-1,4,1,-5,6}; int C = 13;//
        Arrays.sort(S1);
        int res = SubSetSum(S1,C);//
        out.println(res);
    }
}
