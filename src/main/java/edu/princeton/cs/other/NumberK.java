package edu.princeton.cs.other;

import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.algs4.StdRandom;

import static java.lang.System.out;

/**
 * 实例： 给定线性无序集中n个元素和一个整数k，1≤k≤n，要求找出这n个元素中第k大的元素
 * 思路： 借鉴快速排序的思想，任选一个`pivotIndex`，则`pivotValue` = `array[pivotIndex]`，
 * 经过一次划分后，`pivotValue`存储在`storeIndex`的位置，比`pivoteValue`大的在后面，比`pivotValue`小的存储在前面。
 * 那么`storeIndex`位置的`pivotValue`就肯定是第`storeIndex`大的数，如果K<storeIndex,那么说明第K大一定在左边，
 * 那么再对左边进行划分即可，右边类似。递归即可得到第K大的值。
 * @author Mageek Chiu
 */
class NumberK {

    /**
        一个无序数组的 数字大小 topK 问题

     思路：二分即可
     */
    public static Comparable numberK(Comparable[] integers, int k, int left, int right) {
        int storeIndex = Quick.partitionReverse(integers,left,right);
        if (storeIndex > k){// 在左边
            return numberK(integers,k,left,storeIndex-1);
        }else if (storeIndex < k){// 在右边
            return numberK(integers,k,storeIndex+1,right);
        }else {// storeIndex == k  就是它了
            return integers[storeIndex];// pivotValue 小于storeIndex左边的，大于storeIndex右边的
        }
    }

    public static void main (String ...args){
        Integer[] integers = {11,1,2,3,12,4,5,15,6,13,7,8,14,9,10} ; int len = integers.length;
        StdRandom.shuffle(integers);//打乱数组，消除输入依赖
        int k = 5 ; Comparable res = numberK(integers,k-1,0,len-1);//第k大的数 是11， 第5大，下标是4，所以求k-1
        out.println(res);
        k = 1; res = numberK(integers,k-1,0,len-1);//第k大的数 是15， 第1大，下标是0，所以求k-1
        out.println(res);
        k = 9; res = numberK(integers,k-1,0,len-1);//第k大的数 是7， 第9大，下标是8，所以求k-1
        out.println(res);
    }

    /**
     两个大小分别为n和m的有序的数组，找出这两个数组放在一起后第k大的数。

     思路1：归并的思路，此时复杂度O(k)，k很大时很耗费时间
     思路2：二分，最优
     */
//    public static Comparable numberK(int[]a,int[]b,int k) {
//
//    }

    /**
     很多个数字，可能有几十亿个，找出其中最大的500个
     这是 数字topK

     思路：大顶堆，复杂度是O(n*lg500)
     */
//    public static Comparable topK(int[]a,int k) {
//
//    }

    /**
     很多个数字，可能有几十亿个，找出其中出现次数最大的500个
     这是频率topK

     思路：用hash分在不同机器上分别统计每台机器的topK
     然后在所有机器的tok中选择最终的topK
     */
//    public static Comparable topKF(int[]a,int k) {
//
//    }


}
