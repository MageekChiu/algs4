package edu.princeton.cs.other;

import static java.lang.System.out;

/**
 给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数 。
 你可以对一个单词进行如下三种操作：
 插入一个字符
 删除一个字符
 替换一个字符
 示例 1:
 输入:  "horse",  "ros"
 输出: 3
 解释:
 horse -> rorse (将 'h' 替换为 'r')
 rorse -> rose (删除 'r')
 rose -> ros (删除 'e')
 示例 2:
 输入: word1 = "intention", word2 = "execution"
 输出: 5
 解释:
 intention -> inention (删除 't')
 inention -> enention (将 'i' 替换为 'e')
 enention -> exention (将 'n' 替换为 'x')
 exention -> exection (将 'n' 替换为 'c')
 exection -> execution (插入 'u')

 思路：
 Levenshtein 距离，又称编辑距离，指的是两个字符串之间，由一个转换成另一个所需的最少编辑操作次数。
 许可的编辑操作包括将一个字符替换成另一个字符，插入一个字符，删除一个字符。
 编辑距离的算法是首先由俄国科学家Levenshtein提出的，故又叫Levenshtein Distance。


 给定两个字符串S和T,问最少操作多少次可以把字符串T变成S？

 例如： S＝  “ABCF”   T = “DBFG”
 那么我们可以
 (1) 把D改为A
 (2) 删掉G
 (3) 加入C
 所以答案是3。

 分析： 这个最少的操作次数，通常被称之为编辑距离。“编辑距离”一次本身具有最短的意思在里面。因为题目有“最短”这样的关键词，
 首先我们想到的是BFS。是的，当S的距离为m, T的距离为n的时候，我们可以找到这样的操作次数的界限：

 (1) 把T中字符全删了，再添加S的全部字符，操作次数m + n。
 (2) 把T中字符删或加成m个，再修改 操作次数最多 |n – m| + m。

 虽然，我们找到了这样的上界，BFS从实际角度并不可行，因为搜索空间是指数的，这取决于S中的字符种类——具体的数量级不好估计。
 这个问题之所以难，是难在有“添加”“删除”这样的操作，很麻烦。我们试试换个角度理解问题，把它看成字符串对齐的问题，
 事实上从生物信息学对比基因的角度，我们可以这样理解问题。

 给定字符串S和T，我们可以用一种特殊字符促成两个字符串的对齐。我们加的特殊字符是“-”,
 我们允许在S和T中任意添加这种特殊字符使得它长度相同，然后让这两个串“对齐”，最终两个串相同位置出现了不同字符，就扣1分，我们要使得这两个串对齐扣分尽量少。

 对于例子 我们实际上采取了这样的对齐方式：

 12345
 ABCF-
 DB-FG

 注意：如果要对齐，两个“-”相对是没有意义的，所以我们要求不出现这种情况。
 那么看一下：
 (1) S,T对应位置都是普通字符，相同，则不扣分。 例如位置2，4
 (2) S,T对应位置都是普通字符，不同，则扣1分。 例如位置1
 (3) S在该位置是特殊字符，T在该位置是普通字符，则扣1分，例如位置5
 (4) S在该位置是普通字符，T在该位置是特殊字符，则扣1分，例如位置3

 我们来看看扣分项目对应什么？
 (1) 不扣分，直接对应
 (2) 对应把T中对应位置的字符修改
 (3) 对应在T中删除该字符
 (4) 对应在T中添加该字符
 好了，目标明确，感觉像不像 LCS？我们尝试一下：
 设f(i,j)表示S的前i位和T的前j位对齐后的最少扣分。
 那我们来看看最后一位，对齐的情况
 (1) S[i] == T[j], 这时前i – 1和j – 1位都已经对齐了，这部分肯定要最少扣分。这种情况下最少的扣分是f(i-1,j-1)
 (2) 和（1）类似，S[i]≠T[j]，这种情况下最少的扣分是f(i -1, j – 1) + 1
 (3) S的前i位和T的前（j – 1）位已经对齐了，这部分扣分也要最少。这种情况下最少的扣分是f(i,j-1) + 1
 (4) S的前(i-1)位已经和T的前j位对齐了，这部分扣分要最少。这种情况下最少的扣分是f(i,j-1) + 1

 具体f(i,j)取什么值，显然是要看哪种情况的扣分最少。
 为了方便，我们定义函数same(i,j)表示如果S[i] == T[j]则为0，否则为1。

 我们来表示一下递推式：

 f(i,j) = min(f(i – 1, j – 1) + same(i,j), f(i – 1,j ) + 1, f(i, j – 1) + 1)

 初值是什么？

 f(0, j) = j
 f(i, 0) = i

 这时因为对于S的前0位，我们只能在之前加入“-”，或者说把T全部删掉了。类似地，对于T地前0位，我们只能把S的字符都加进来，别无选择。
 注意上述两个式子的重合点 f(0,0) = 0也符合我们的定义，并不矛盾。

 时间复杂度？ O(m * n)，空间复杂度？ O(m * n)。同样我们发现到f(i,j)只与本行和上一行有关，可以省掉一维的空间复杂度，从而达到O(n)。
 * @author Mageek Chiu
 */
class EditorDistance {

    public static int minDistance(String str1, String str2) {
        return levenshtein(str1,str2);

    }
    public static int levenshtein(String str1,String str2) {
        //计算两个字符串的长度。
        int len1 = str1.length();
        int len2 = str2.length();
        //建立上面说的数组，比字符长度大一个空间
        int[][] dif = new int[len1 + 1][len2 + 1];
        //赋初值，步骤B。
        for (int a = 0; a <= len1; a++) {
            dif[a][0] = a;
        }
        for (int a = 0; a <= len2; a++) {
            dif[0][a] = a;
        }
        //计算两个字符是否一样，计算左上的值
        int temp;
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    temp = 0;
                } else {
                    temp = 1;
                }
                //取三个值中最小的
                dif[i][j] = min(dif[i - 1][j - 1] + temp, dif[i][j - 1] + 1,
                        dif[i - 1][j] + 1);
            }
        }
//        System.out.println("字符串\""+str1+"\"与\""+str2+"\"的比较");
        //取数组右下角的值，同样不同位置代表不同字符串的比较
//        System.out.println("差异步骤："+dif[len1][len2]);
        //计算相似度
        float similarity =1 - (float) dif[len1][len2] / Math.max(str1.length(), str2.length());
//        System.out.println("相似度："+similarity);
//        return similarity;
        return  dif[len1][len2];
    }

    //得到最小值
    private static int min(int... is) {
        int min = Integer.MAX_VALUE;
        for (int i : is) {
            if (min > i) {
                min = i;
            }
        }
        return min;
    }

    // 感受：
    public static void main (String ...args){
        out.println(minDistance("horse","ros"));//3
        out.println(minDistance("intention","execution"));//5
    }
}
