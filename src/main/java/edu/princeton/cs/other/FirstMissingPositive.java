package edu.princeton.cs.other;

import java.util.HashMap;
import java.util.Map;

import static java.lang.System.out;

/**
 给定一个未排序的整数数组，找出其中没有出现的最小的正整数。
 示例 1:
 输入: [1,2,0]
 输出: 3
 示例 2:
 输入: [3,4,-1,1]
 输出: 2
 示例 3:
 输入: [7,8,9,11,12]
 输出: 1

 说明:
 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间。

 * @author Mageek Chiu
 */
class FirstMissingPositive {

    /**
     * 思路：正数可以分为 1 - n)  ,(n - n+k) 连续, 则 n 就是第一个未出现的正数
     * 也可能 只有半截，也就是 只有 1 - n)  或者只有  (n - n+k)
     */
    public static int firstMissingPositive(int[] nums) {
        int min = Integer.MAX_VALUE,result = 0;
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]>0)                map.put(nums[i],1);
//            if (nums[i]>0 && nums[i]<min){//找到正数里面的最小值
//                min = nums[i];
//            }
        }
//        if (min>1) {// 只有右半截
//            result = 1;
//        }else {// 左右都有或者只有左半截，那么，n-1在，n不在，n就是第一个
//
//        }
        for (int i = 1;i<Integer.MAX_VALUE;i++){
            if (!map.containsKey(i)) return i;
        }

        return result;

    }
    // 分析：把当前数放到该放的位置即可，如1应该放到第0个位置，2应该放到第1个位置。
//    int firstMissingPositive(vector<int> A) {
//        // write your code here
//        int n = A.size();
//        for(int i=0;i<n;)
//        {
//            if(A[i]==i+1)
//                i++;
//            else
//            {
//                if(A[i]>=1&&A[i]<=n&& A[A[i]-1]!=A[i])
//                    swap(A[i],A[A[i]-1]);
//                else
//                    i++;
//            }
//        }
//        for(int i=0;i<n;i++)
//            if(A[i]!=i+1)
//                return i+1;
//        return n+1;
//    }


    // 感受：
    public static void main (String ...args){
        int[] nums = {1,2,0};//3
//        int[] nums = {3,4,-1,1};//2
//        int[] nums = {7,8,9,11,12};//1
        out.println(firstMissingPositive(nums));
    }
}
