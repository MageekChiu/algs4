package edu.princeton.cs.algs4;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 桶排序
 * @author Mageek Chiu
 * @date 2018-01-24:16:47
 */
public class BucketSort {

    private static void bucketSort(int[] arr){
        int max = Integer.MIN_VALUE,min = Integer.MAX_VALUE;
        for (int anArr : arr) {
            max = Math.max(max, anArr);
            min = Math.min(min, anArr);
        }
        //桶数
        int bucketNum = (max - min) / arr.length + 1;
        ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<>(bucketNum);
        for(int i = 0; i < bucketNum; i++){
            bucketArr.add(new ArrayList<Integer>());
        }
        //将每个元素放入桶
        for (int anArr : arr) {
            int num = (anArr - min) / (arr.length);
            bucketArr.get(num).add(anArr);
        }
        //对每个桶进行排序,调用自带的排序
        for (ArrayList<Integer> aBucketArr : bucketArr) {
            Collections.sort(aBucketArr);
        }
        //打印结果
        for (ArrayList<Integer> anA : bucketArr) {StdOut.print(anA + "\t"); }
        StdOut.println();
    }

    public static void main(String[] args) {
        int[] A=new int[]{2,5,3,7,0,2,1,0,3,0,10,3,6};
        bucketSort(A);
    }
}
