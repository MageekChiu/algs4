package edu.princeton.cs.other;

import java.util.HashMap;
import java.util.Map;

/**
 1.
 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。

 数字 1-9 在每一行只能出现一次。
 数字 1-9 在每一列只能出现一次。
 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。

 数独部分空格内已填入了数字，空白格用 '.' 表示。
 说明:
    一个有效的数独（部分已被填充）不一定是可解的。
    只需要根据以上规则，验证已经填入的数字是否有效即可。
    给定数独序列只包含数字 1-9 和字符 '.' 。
    给定数独永远是 9x9 形式的。
-------------------------------------------------

 2.
 编写一个程序，通过已填充的空格来解决数独问题。
 一个数独的解法需遵循如下规则：
     数字 1-9 在每一行只能出现一次。
     数字 1-9 在每一列只能出现一次。
     数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 空白格用 '.' 表示。
 一个数独。
 答案被标成红色。
 Note:
     给定的数独序列只包含数字 1-9 和字符 '.' 。
     你可以假设给定的数独只有唯一解。
     给定数独永远是 9x9 形式的。
 * @author Mageek Chiu
 */
class ValidSudoku {

    /**
     1. 每行每列都不能有重复数字
     每个九宫格不能有重复数字
     可以暴力解决
     但是只需要遍历1遍即可
     用 9+9+9个 hashmap 来判断重复，为了便于判断可以搞成数组,但是java不支持map或者说泛型的数组
     可以用前缀来做成key，比如key r45 表示第4行的字符串5 对应的value 是 5 的index
     c36 表示 第3列的字符串6 对应的value是 6 的index
     m015表示位于0行1列的数字五的index
     其实不用存index，只要置为1即可

     */
    public static boolean isValidSudoku(char[][] nums) {
        boolean notValid = false;
        Map<String,Integer> deDuplicate  = new HashMap<>();
        for (int i = 0;i<9;i++){
            for (int j = 0;j<9;j++){
                if (nums[i][j]=='.') continue;// 空白不用管
                if (deDuplicate.containsKey("r"+i+nums[i][j]) ||
                        deDuplicate.containsKey("c"+j+nums[i][j]) ||
                        deDuplicate.containsKey("m"+(i/3)+(j/3)+nums[i][j]) ){
                    notValid = true;// 三个条件违反一个就可以直接返回了
                    break;
                }else {// 否则记录一下
                    deDuplicate.put("r"+i+nums[i][j],1);
                    deDuplicate.put("c"+j+nums[i][j],1);
                    deDuplicate.put("m"+(i/3)+(j/3)+nums[i][j],1);
                }
            }
            if (notValid) break;
        }
        return !notValid;
    }

    public static  Map<String,Integer> deDuplicate = null;

    public static boolean isValidSudoku(char[][] nums,int s,int t) {
        if (deDuplicate==null){
            deDuplicate = new HashMap<>();
            for (int i = 0;i<9;i++) {
                for (int j = 0; j < 9; j++) {
                    if (nums[i][j] == '.') continue;// 空白不用管
                    deDuplicate.put("r" + i + nums[i][j], 1);
                    deDuplicate.put("c" + j + nums[i][j], 1);
                    deDuplicate.put("m" + (i / 3) + (j / 3) + nums[i][j], 1);
                }
            }
        }
        return !(deDuplicate.containsKey("r"+s+nums[s][t]) ||
                deDuplicate.containsKey("c"+t+nums[s][t]) ||
                deDuplicate.containsKey("m"+(s/3)+(t/3)+nums[s][t]));

    }
    /**
     2. 思路，这个感觉涉及到搜索和回溯法了
     */
    public static boolean solveSudoku(char[][] nums ){
        for (int i = 0;i<9;i++){
            for (int j = 0;j<9;j++){
                if (nums[i][j]=='.'){// 空才需要填充，否则不需要填充
                    int k;// 待填充的数
                    for (k = 1;k<=9;k++){
                        nums[i][j] = (char) ('0'+k);
                        if (isValidSudoku(nums,i,j) && solveSudoku(nums)){
                            return true;
                        }
                        nums[i][j] = '.';
                    }
                    return false;// 马上返回，剪枝
                }
            }
        }
        return false;
    }

    // 感受：哈希表的应用，hash表可以通过key的前缀实现命名空间，从而实现类似 hash 数组的功能
    // 刷题既能锻炼数据结构与算法能力，还能熟悉基本语法、用法，同时还能动脑筋，收获很多
    public static void main (String ...args){
        char[][] nums = {
          {'5','3','.','.','7','.','.','.','.'},
          {'6','.','.','1','9','5','.','.','.'},
          {'.','9','8','.','.','.','.','6','.'},
          {'8','.','.','.','6','.','.','.','3'},
          {'4','.','.','8','.','3','.','.','1'},
          {'7','.','.','.','2','.','.','.','6'},
          {'.','6','.','.','.','.','2','8','.'},
          {'.','.','.','4','1','9','.','.','5'},
          {'.','.','.','.','8','.','.','7','9'}
        };
        char[][] nums1 = {
          {'8','3','.','.','7','.','.','.','.'},
          {'6','.','.','1','9','5','.','.','.'},
          {'.','9','8','.','.','.','.','6','.'},
          {'8','.','.','.','6','.','.','.','3'},
          {'4','.','.','8','.','3','.','.','1'},
          {'7','.','.','.','2','.','.','.','6'},
          {'.','6','.','.','.','.','2','8','.'},
          {'.','.','.','4','1','9','.','.','5'},
          {'.','.','.','.','8','.','.','7','9'}
        };

//        System.out.println(isValidSudoku(nums));// true
//        System.out.println(isValidSudoku(nums1));// false

        for (int i = 0; i < nums.length; i++) {
            for (int i1 = 0; i1 < nums[i].length; i1++) {
                System.out.print(nums[i][i1]+" ,");
            }
            System.out.println();
        }
        solveSudoku(nums);
        System.out.println("--------answer---------");
        for (int i = 0; i < nums.length; i++) {
            for (int i1 = 0; i1 < nums[i].length; i1++) {
                System.out.print(nums[i][i1]+" ,");
            }
            System.out.println();
        }
    }
}
