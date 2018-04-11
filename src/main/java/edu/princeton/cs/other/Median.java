package edu.princeton.cs.other;


import java.util.concurrent.Exchanger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.System.out;

/**
 * 有两个大小为 m 和 n 的排序数组 nums1 和 nums2 。
 请找出两个排序数组的中位数并且总的运行时间复杂度为 O(log (m+n)) 。
 示例 1:
 nums1 = [1, 3]
 nums2 = [2]
 中位数是 2.0

 示例 2:
 nums1 = [1, 2]
 nums2 = [3, 4]
 中位数是 (2 + 3)/2 = 2.5
 *
 * @author Mageek Chiu
 */
class Median {

    /**
     * 题目要求O(log(m+n))的时间复杂度，一般来说都是分治法或者二分搜索。首先我们先分析下题目，假设两个有序序列共有n个元素
     * 根据中位数的定义我们要分两种情况考虑，当n为奇数时，搜寻第(n/2+1)个元素，当n为偶数时，搜寻第(n/2+1)和第(n/2)个元素，然后取他们的均值。
     * 进一步的，我们可以把这题抽象为“搜索两个有序序列的第k个元素”。如果我们解决了这个k元素问题，那中位数不过是k的取值不同罢了。
     *

     */
    private static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int k = (m + n) / 2;
        if((m+n)%2==0){
            return (findKth(nums1,nums2,0,0,m,n,k)+findKth(nums1,nums2,0,0,m,n,k+1))/2;
        }   else {
            return findKth(nums1,nums2,0,0,m,n,k+1);
        }
    }

    private static double findKth(int[] arr1, int[] arr2, int start1, int start2, int len1, int len2, int k){
        // 保证arr1是较短的数组
        if(len1>len2){
            return findKth(arr2,arr1,start2,start1,len2,len1,k);
        }
        if(len1==0){
            return arr2[start2 + k - 1];
        }
        if(k==1){
            return Math.min(arr1[start1],arr2[start2]);
        }
        int p1 = Math.min(k/2,len1) ;
        int p2 = k - p1;
        if(arr1[start1 + p1-1]<arr2[start2 + p2-1]){
            return findKth(arr1,arr2,start1 + p1,start2,len1-p1,len2,k-p1);
        } else if(arr1[start1 + p1-1]>arr2[start2 + p2-1]){
            return findKth(arr1,arr2,start1,start2 + p2,len1,len2-p2,k-p2);
        } else {
            return arr1[start1 + p1-1];
        }
    }

    protected void pr(){
        p1();
        out.print("parentpr");
    }


    protected void p1(){
        out.print("parentp1");
    }


    public static void main (String ...args){
        int[] nums = {1,3};
        int[] nums1 = {2};
        out.println( findMedianSortedArrays(nums,nums1));//2.0
        int[] nums2 = {1,2};
        int[] nums3 = {3,4};
        out.println( findMedianSortedArrays(nums2,nums3));// 2.5
    }
}
