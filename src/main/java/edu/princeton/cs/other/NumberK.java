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
}
