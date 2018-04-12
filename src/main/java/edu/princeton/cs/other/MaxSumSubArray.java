package edu.princeton.cs.other;

import edu.princeton.cs.algs4.Quick;

import static java.lang.System.out;

/**
 * 给定一个整数数组，找到一个具有最大和的子数组，返回其最大和。
 * 样例
 * 给出数组[−2,2,−3,4,−1,2,1,−5,3]，符合要求的子数组为[4,−1,2,1]，其最大和为6
 * @author Mageek Chiu
 */
class MaxSumSubArray {

    public static int maxSumSubArray(int[] nums) {
        int len = nums.length,sum = 0,ans = Integer.MIN_VALUE;
        for (int num : nums) {
            sum += num;
            if (sum > ans) ans = sum;
            if (sum < 0 ) sum = 0;// 为负数，直接丢弃
        }
        return ans;
    }

    public static void main (String ...args){
        int[] nums = {-2,2,-3,4,-1,2,1,-5,3};// 符合要求的子数组为[4,−1,2,1]，其最大和为6
        int res = maxSumSubArray(nums);//
        out.println(res);
        int[] nums1 = {-1,2,3,4,-3,7,-7};// 符合要求的子数组为[2.3.4.-3.7]，其最大和为13
        int res1 = maxSumSubArray(nums1);//
        out.println(res1);
    }
}
