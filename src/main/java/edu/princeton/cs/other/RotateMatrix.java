package edu.princeton.cs.other;

import java.util.LinkedList;
import java.util.List;

/**
 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 示例 1:
 输入:
 {
     { 1, 2, 3 },
     { 4, 5, 6 },
     { 7, 8, 9 }
 }
 输出: {1,2,3,6,9,8,7,4,5}

 示例 2:
 输入:
 {
     {1, 2, 3, 4},
     {5, 6, 7, 8},
     {9,10,11,12}
 }
 输出: {1,2,3,4,8,12,11,10,9,5,6,7}
 ---------------------------------------------------------------
 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。

 示例:

 输入: 3
 输出:
 [
     [ 1, 2, 3 ],
     [ 8, 9, 4 ],
     [ 7, 6, 5 ]
 ]

 * @author Mageek Chiu
 */
class RotateMatrix {

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new LinkedList<>();
        int m = matrix.length ;//行
        if (m<1) return list;
        int n = matrix[0].length;//列
        int head = 0;//0 1 2 3  当前移动方向：右 下 左 上
        int[] border = new int[]{n-1,m-1,0,1};// 右下左上的border 可以取等
        int count = m*n,i=0,j=0;
        while (count>0){
            switch (head){
                case 0://右
                    while (j<=border[0]){
                        list.add(matrix[i][j++]);
                        count--;
                    }
                    j--;
                    i++;
                    border[0] -=1;
                    head = 1;
                    break;
                case 1://下
                    while (i<=border[1]){
                        list.add(matrix[i++][j]);
                        count--;
                    }
                    i--;
                    j--;
                    border[1] -=1;
                    head = 2;
                    break;
                case 2://左
                    while (j>=border[2]){
                        list.add(matrix[i][j--]);
                        count--;
                    }
                    j++;
                    i--;
                    border[2] +=1;
                    head = 3;
                    break;
                case 3://上
                    while (i>=border[3]){
                        list.add(matrix[i--][j]);
                        count--;
                    }
                    i++;
                    j++;
                    border[3] +=1;
                    head = 0;
                    break;
            }
        }
        return list;

    }

    public static int[][] generateMatrix(int n) {
        int [][] matrix = new int[n][n];
        int head = 0;//0 1 2 3  当前移动方向：右 下 左 上
        int[] border = new int[]{n-1,n-1,0,1};// 右下左上的border 可以取等
        int count = 1,i=0,j=0;
        while (count<=n*n){
            switch (head){
                case 0://右
                    while (j<=border[0]){
                        matrix[i][j++] = count++;
                    }
                    j--;
                    i++;
                    border[0] -=1;
                    head = 1;
                    break;
                case 1://下
                    while (i<=border[1]){
                        matrix[i++][j] = count++;
                    }
                    i--;
                    j--;
                    border[1] -=1;
                    head = 2;
                    break;
                case 2://左
                    while (j>=border[2]){
                        matrix[i][j--] = count++;
                    }
                    j++;
                    i--;
                    border[2] +=1;
                    head = 3;
                    break;
                case 3://上
                    while (i>=border[3]){
                        matrix[i--][j] = count++;
                    }
                    i++;
                    j++;
                    border[3] +=1;
                    head = 0;
                    break;
            }
        }
        return matrix;

    }

    // 感受：新鲜概念的理解与使用
    public static void main (String ...args){
//        int[][] input = {
//             { 1, 2, 3 },
//             { 4, 5, 6 },
//             { 7, 8, 9 }
//            };
//        out.println(spiralOrder(input));

//        int[][] input1 = {
//                { 1, 2, 3, 4 },
//                { 5, 6, 7, 8 },
//                { 9,10,11,12 }
//        };
//        out.println(spiralOrder(input1));
//
//        int[][] input1 = {
//                { 1, 2, 3, 4 },
//        };
//        out.println(spiralOrder(input1));

//
//        int[][] input1 = {
//                { 1, 2, 3, 4 },
//                { 5,6,7,8 }
//        };
//        out.println(spiralOrder(input1));

//        int[][] input = {
//                { 1, 2, 3 },
//                { 4, 5, 6 },
//                { 7, 8, 9 }
//        };
//        out.println(spiralOrder(input));

        int[][] a = generateMatrix(3);
        for (int[] ints : a) {
            for (int anInt : ints) {
                System.out.print(anInt+",");
            }
            System.out.println();
        }




    }
}
