package edu.princeton.cs.other;

import java.util.HashMap;
import java.util.Map;

import static java.lang.System.out;

/**
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

 * @author Mageek Chiu
 */
class ValidSudoku {

    /**
     每行每列都不能有重复数字
     每个九宫格不能有重复数字

     可以暴力解决

     遍历1遍即可
     用 9+9+9个 hashmap 来判断重复，为了便于判断可以搞成数组,但是java不支持map或者说泛型的数组
     可以用前缀来做成key，比如key r45 表示第4行的字符串5 对应的value 是 5 的index
     c36 表示 第3列的字符串6 对应的value是 6 的index
     m015表示位于0行1列的数字五的出现次数

     * @param nums
     * @return
     */
    public static boolean isValidSudoku(char[][] nums) {
        boolean noValid = false;
        Map<String,Integer> deDuplicate  = new HashMap<>();
        for (int i = 0;i<9;i++){
            for (int j = 0;j<9;j++){
                if (nums[i][j]=='.') continue;// 空白不用管
                if (deDuplicate.containsKey("r"+i+nums[i][j])||
                        deDuplicate.containsKey("c"+j+nums[i][j]) ||
                        deDuplicate.containsKey("m"+(i/3)+(j/3)+nums[i][j])){
                    noValid = true;// 三个条件违反一个就可以直接返回了
                    break;
                }else {// 否则记录一下
                    deDuplicate.put("r"+i+nums[i][j],1);
                    deDuplicate.put("c"+j+nums[i][j],1);
                    deDuplicate.put("m"+(i/3)+(j/3)+nums[i][j],1);
                }
            }
            if (noValid) break;
        }
        return !noValid;
    }

    // 感受：
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
        out.println(isValidSudoku(nums));// true

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
        out.println(isValidSudoku(nums1));// false
    }
}
