package edu.princeton.cs.other;

/**
 给定一个 n × n 的二维矩阵表示一个图像。
 将图像顺时针旋转 90 度。
 说明：
 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 示例 1:
 给定 matrix =
 [
     [1,2,3],
     [4,5,6],
     [7,8,9]
 ],
 原地旋转输入矩阵，使其变为:
 [
     [7,4,1],
     [8,5,2],
     [9,6,3]
 ]
 示例 2:
 给定 matrix =
 [
     [ 5, 1, 9,11],
     [ 2, 4, 8,10],
     [13, 3, 6, 7],
     [15,14,12,16]
 ],
 原地旋转输入矩阵，使其变为:
 [
     [15,13, 2, 5],
     [14, 3, 4, 1],
     [12, 6, 8, 9],
     [16, 7,10,11]
 ]
 * @author Mageek Chiu
 */
class RotateImage {

    public static void rotate(int[][] matrix) {
//        int n = matrix.length-1;
//        for (int i = 0; i <= n; i++) {
//            for (int j = 0; j <= n; j++) {
//                // 不能这样简单交换，因为需要需要[n-j][i]的i.j倒是对了，
//                // 但是需要[i][j]的[j][n-i]就不对了。。。
            //  在推下去就会发现一个循环，所以循环内部只要4个交换就行了
//                int tmp = matrix[i][j];
//                matrix[i][j] = matrix[n-j][i];
//                matrix[n-j][i] = tmp;
//            }
//        }
//        int count = 0;
//        int i = 0,j = 0;
//        int tmp =  matrix[n-j][i],tmp2;
//        while (count<(n+1)*(n+1)){
//            tmp2=matrix[i][j];//保存住当前值
//            matrix[i][j] = tmp;// 当前位置该存的值
//            tmp = tmp2;
//            i = j;j = n-i;// 当前值该放到的下一个位置
//            count++;
//        }

        //只需要按照 正方形四个顶点 顺次旋转即可
        if(matrix == null){
            return;
        }
        int n = matrix.length;
        for(int i=0; i<n/2; i++){
            for(int j=i; j<n-1-i; j++){
                int temp = matrix[i][j];
                matrix[i][j]=matrix[n-1-j][i];
                matrix[n-1-j][i]=matrix[n-1-i][n-1-j];
                matrix[n-1-i][n-1-j]=matrix[j][n-1-i];
                matrix[j][n-1-i]=temp;
            }
        }
    }

    // 感受：基本概念的理解,旋转本质就是 新的行数就是原来的列数，新的列数就是原来的行数与总行数-1（lastIndex）的补
    // 比如 4*4  第0行变成了第3列，第1行变成了2列，第2行变成了第1列
    // 比如 3*3 第0行变成了第2列
    // 所以 令 n = lastIndex，a`[i][j] = a[n-j][i]
    public static void main (String ...args){
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        rotate(matrix);//
        for (int i = 0; i < matrix.length; i++) {
            for (int i1 = 0; i1 < matrix[i].length; i1++) {
                System.out.print(matrix[i][i1]+",");
            }
            System.out.println();
        }

        int[][] matrix1 = {
                {5, 1, 9,11},
                {2, 4, 8,10},
                {13, 3, 6, 7},
                {15,14,12,16}
        };
        rotate(matrix1);//
        for (int i = 0; i < matrix1.length; i++) {
            for (int i1 = 0; i1 < matrix1[i].length; i1++) {
                System.out.print(matrix1[i][i1]+",");
            }
            System.out.println();
        }
    }
}
