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

    /**
     给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
     示例 1:
     输入:
     [
         [1,1,1],
         [1,0,1],
         [1,1,1]
     ]
     输出:
     [
         [1,0,1],
         [0,0,0],
         [1,0,1]
     ]
     示例 2:
     输入:
     [
         [0,1,2,0],
         [3,4,5,2],
         [1,3,1,5]
     ]
     输出:
     [
         [0,0,0,0],
         [0,4,5,0],
         [0,3,1,0]
     ]
     进阶:
     一个直接的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。// 直接全部复制即可
     一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。//声明两个数组，记录需要置零的行与列
     你能想出一个常数空间的解决方案吗？//

     实际上，我们只需要直到哪些行，哪些列需要被置0就行了，最简单的方法就是建两个大小分别为M和N的数组，来记录哪些行哪些列应该被置0。
     那有没有可能不用额外空间呢？我们其实可以借用原矩阵的首行和首列来存储这个信息。
     这个方法的缺点在于，如果我们直接将0存入首行或首列来表示相应行和列要置0的话，我们很难判断首行或者首列自己是不是该置0。
     这里我们用两个boolean变量记录下首行和首列原本有没有0，然后在其他位置置完0后，再单独根据boolean变量来处理首行和首列，就避免了干扰的问题。

     就像那个数组去重 removeDuplicates 一样，可以直接利用已有空间
     * @param matrix
     */
    public static void setZeroes(int[][] matrix) {
        if(matrix.length == 0) return;
        boolean firstRowZero = false, firstColZero = false;
        // 记录哪些行哪些列需要置0，并判断首行首列是否需要置0
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(i != 0 && j != 0 && matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                } else if (matrix[i][j] == 0){
                    // 如果首行或首列出现0，则标记其需要置0，否则沿用上次值
                    firstRowZero = i == 0 ? true : firstRowZero;
                    firstColZero = j == 0 ? true : firstColZero;
                }
            }
        }
        // 将除首行首列的位置置0
        for(int i = 1; i < matrix.length; i++){
            for(int j = 1; j < matrix[0].length; j++){
                if(matrix[0][j] == 0 || matrix[i][0] == 0){
                    matrix[i][j] = 0;
                }
            }
        }
        // 如果必要，将首列置0
        for(int i = 0; firstColZero && i < matrix.length; i++){
            matrix[i][0] = 0;
        }
        // 如果必要，将首行置0
        for(int j = 0; firstRowZero && j < matrix[0].length; j++){
            matrix[0][j] = 0;
        }
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
