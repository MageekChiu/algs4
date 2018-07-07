package edu.princeton.cs.other;

import static java.lang.System.out;
/**
 *
 * @author Mageek Chiu
 */
class Template {

    public static int maxSumSubArray(int[] nums) {
        int len = nums.length,sum = 0,ans = Integer.MIN_VALUE;

        return ans;
    }

    // 感受：
    public static void main (String ...args){
        int[] nums = {-2,2,-3,4,-1,2,1,-5,3};// 符合要求的子数组为[4,−1,2,1]，其最大和为6
        int res = maxSumSubArray(nums);//
        out.println(res);
    }
}
