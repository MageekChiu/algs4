package edu.princeton.cs.algs4;

/**
 * 计数排序
 * @author Mageek Chiu
 * @date 2018-01-24:16:47
 */
public class CountSort {

    private static void show(int[] a) {
        for (int anA : a) {StdOut.print(anA + "\t"); }
        StdOut.println();
    }

    private static int[] countSort(int[] A,int k){
        int[] C=new int[k+1];//构造C数组
        int length=A.length,sum=0;//获取A数组大小用于构造B数组
        for (int anArray : A) { C[anArray] += 1;}// 统计A中各元素个数，存入C数组
        for(int i=0;i<k+1;i++){ //修改C数组，使得A中小于等于元素i的元素有C[i]个，亦即i在B中的自然序号（减去1得到数组序号）
            sum+=C[i];
            C[i]=sum;
        }
        int[] B=new int[length];//构造B数组
        for(int i=length-1;i>=0;i--){//倒序遍历A数组（保证稳定性，因为相同的元素中靠后的个体的序号也相对较大），构造B数组
            B[C[A[i]]-1]=A[i];//将A中该元素放到排序后数组B中指定的位置
            C[A[i]]--;//将C中该元素-1，方便存放下一个同样大小的元素
        }
        return B;//将排序好的数组返回，完成排序
    }

    public static void main(String[] args) {
        int[] A=new int[]{2,5,3,7,0,2,1,0,3,0,10,3,6};
        int[] B=countSort(A, 10);
        show(A);
        show(B);
    }
}
