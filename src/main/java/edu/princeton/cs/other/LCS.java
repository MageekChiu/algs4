package edu.princeton.cs.other;

import static java.lang.System.out;

/**
 * 最长公共子序列  longest common subsequence  lcs 定义：两个或多个已知数列的子序列集合中最长的就是最长公共子序列。
 * 比如数列A = “abcdef”和B = “adefcb”，那么两个数列的公共子序列集合有{”a","ab","abc","adef",等等}，
 * 其中最长的就是adef，这就是最长公共子序列。
 * 注意：最长公共子序列的公共子序列里的元素可以不相邻，但是公共子字符串必须是连接在一起的，
 * 比如A和B的公共子字符串是“def”。
 * 用动态规划法来求解最长公共子序列，因为最长公共子序列具有最有子结构性质，可以分成子问题来递归求最优解，
 * 最后组合子问题求解出问题。用c[i][j]记录X[i]与Y[j]
 *  的LCS 的长度，求解问题c[i,j]，可以分成c[i-1][j-1]、c[i-1][j]、c[i][j-1]子问题来求解，
    最长公共子串 lcs1
 * @author Mageek Chiu
 */
class LCS {

    /**
     * 动态规划
     假设Z=<z1,z2,⋯,zk>是X与Y的LCS， 我们观察到
     如果Xm=Yn，则Zk=Xm=Yn，有Zk−1是Xm−1与Yn−1的LCS；
     如果Xm≠Yn，则Zk是Xm与Yn−1的LCS，或者是Xm−1与Yn的LCS。
     因此，求解LCS的问题则变成递归求解的两个子问题。但是，上述的递归求解的办法中，重复的子问题多，效率低下。
     改进的办法——用空间换时间，用数组保存中间状态，方便后面的计算。这就是动态规划（DP)的核心思想了。
     DP求解LCS
     用二维数组c[i][j]记录串x1x2⋯xi与y1y2⋯yj的LCS长度，则可得到状态转移方程
     */
//    public static int lcs(String str1, String str2) {
    public static String lcs(String str1, String str2) {

        int len1 = str1.length();
        int len2 = str2.length();
        int c[][] = new int[len1+1][len2+1];
        int b[][] = new int[len1+1][len2+1];

        for (int i = 0; i <= len1; i++) {
            for( int j = 0; j <= len2; j++) {
                if(i == 0 || j == 0) {
                    c[i][j] = 0;
                } else if (str1.charAt(i-1) == str2.charAt(j-1)) {
                    c[i][j] = c[i-1][j-1] + 1;
                    // 记录搜索方向
                    b[i][j] = 0;// 左上
                } else {
                    c[i][j] = max(c[i - 1][j], c[i][j - 1]);
                    // 记录搜索方向
                    if (c[i - 1][j]>c[i][j - 1])
                        b[i][j] = 1;// 上
                    else
                        b[i][j] = -1;//左
                }
            }
        }
//        System.out.println("LCS: "+printLcs(str1,b,len1,len2));

//        return c[len1][len2];
        return printLcs(str1,b,len1,len2);
    }

    /**
     * 打印最长公共子序列
     * @return
     */
    public static String printLcs(String a,int[][] B,int i, int j){
        String res = "";
        if (i==0 || j==0) return "";
        if (B[i][j]==0){
            res += printLcs(a,B,i-1,j-1);
            res += a.substring(i-1,i);
        }else if(B[i][j]==1){
            res += printLcs(a,B,i-1,j);
        }else {
            res += printLcs(a,B,i,j-1);
        }
        return res;
    }

    private static int max(int a, int b){
        return (a > b) ? a : b;
    }


    /**
     * 前面提到了子串是一种特殊的子序列，因此同样可以用DP来解决。定义数组的存储含义对于后面推导转移方程显得尤为重要，
     * 糟糕的数组定义会导致异常繁杂的转移方程。考虑到子串的连续性，
     * 将二维数组c[i][j]用来记录具有这样特点的子串——结尾同时也为为串x1x2⋯xi与y1y2⋯yj的结尾——的长度。
     * @param str1
     * @param str2
     * @return
     */
//    public static int lcs1(String str1, String str2) {
    public static String lcs1(String str1, String str2) {

        int len1 = str1.length();
        int len2 = str2.length();
        int mark1=0,mark2=0;
        int result = 0;     //记录最长公共子串长度
        int c[][] = new int[len1+1][len2+1];

        for (int i = 0; i <= len1; i++) {
            for( int j = 0; j <= len2; j++) {
                if(i == 0 || j == 0) {
                    c[i][j] = 0;
                } else if (str1.charAt(i-1) == str2.charAt(j-1)) {
                    c[i][j] = c[i-1][j-1] + 1;
                    // 子序列以为可以利用前面的子序列，所以肯定是最后一个最大，子串不一定，所以要比较
                    if (c[i][j]>result){
                        result = c[i][j];
                        mark1 = i;
                        mark2=j;
                    }
                } else {// 不相等，直接置零
                    c[i][j] = 0;
                }
            }
        }

//        System.out.println("LCS1: "+printLcs1(str1,str2,mark1-1,mark2-1,result));

//        return result;
        return printLcs1(str1,str2,mark1-1,mark2-1,result);
    }

    /**
     * 打印最长公共子串
     * @return
     */
    public static String printLcs1(String a,String b,int i, int j,int len){
        String res = "";
        if (len==0) return "";
        while (i>=0&&j>=0){
            if (a.substring(i,i+1).equals(b.substring(j,j+1))){
                res = a.substring(i,i+1)+res;
                i--;j--;
            }else {// 不等直接就断了
                break;
            }
        }
        return res;

    }

    /**
     *  * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为1000。
     示例 1：
     输入: "babad"
     输出: "bab"
     注意: "aba"也是一个有效答案。
     示例 2：
     输入: "cbbd"
     输出: "bb"

     实际上就是最长公共子串问题,正串和反串的最大公共子串就是最大回文串
     注意上面这种想法是错误的，反例就是 "abcdasdfghjkldcba"
     我们可以看到，当 SS 的其他部分中存在非回文子串的反向副本时，最长公共子串法就会失败。
     为了纠正这一点，每当我们找到最长的公共子串的候选项时，都需要检查子串的索引是否与反向子串的原始索引相同。
     如果相同，那么我们尝试更新目前为止找到的最长回文子串；如果不是，我们就跳过这个候选项并继续寻找下一个候选。

     这给我们提供了一个复杂度为 O(n^2) 动态规划解法，它将占用 O(n^2) 的空间（可以改进为使用 O(n)O(n) 的空间）。
     请在这里阅读更多关于最长公共子串的内容。http://en.wikipedia.org/wiki/Longest_common_substring

     方法二：暴力法
     很明显，暴力法将选出所有子字符串可能的开始和结束位置，并检验它是不是回文。
     复杂度分析
     时间复杂度：O(n^3)假设 n 是输入字符串的长度，则  ​n(n−1)/2
     ​​  为此类子字符串(不包括字符本身是回文的一般解法)的总数。因为验证每个子字符串需要 O(n) 的时间，
     所以运行时间复杂度是 O(n^3)  空间复杂度：O(1)。

     方法三：动态规划
     为了改进暴力法，我们首先观察如何避免在验证回文时进行不必要的重复计算。“ababa” 这个示例。
     如果我们已经知道“bab” 是回文，那么很明显，“ababa” 一定是回文，因为它的左首字母和右尾字母是相同的。

     我们给出 P(i,j)的定义如下：

     P(i,j)=true,如果子串Si…Sj是回文子串,false,其它情况
     因此，

     P(i, j) =  P(i,j)=(P(i+1,j−1) and Si​​ ==S​j
     ​​ )

     基本示例如下：

     P(i,i)=true

     P(i,i+1)=(S​i​​ ==S​i+1​ )

     这产生了一个直观的动态规划解法，我们首先初始化一字母和二字母的回文，然后找到所有三字母回文，并依此类推…

     复杂度分析

     时间复杂度：O(n^2)这里给出我们的运行时间复杂度为 O(n^2)

     空间复杂度：O(n^2) 该方法使用 O(n^2)的空间来存储表。

     方法四：中心扩展算法
     事实上，只需使用恒定的空间，我们就可以在 O(n^2)的时间内解决这个问题。
     我们观察到回文中心的两侧互为镜像。因此，回文可以从它的中心展开，并且只有 2n - 1 个这样的中心。
     你可能会问，为什么会是 2n - 1 个，而不是 n 个中心？原因在于所含字母数为偶数的回文的中心可以处于两字母之间
     （例如 “abba” 的中心在两个 ‘b’ 之间）,可以分奇偶，也可以合并

     public String longestPalindrome(String s) {
     int start = 0, end = 0;
     for (int i = 0; i < s.length(); i++) {
     int len1 = expandAroundCenter(s, i, i);
     int len2 = expandAroundCenter(s, i, i + 1);
     int len = Math.max(len1, len2);
     if (len > end - start) {
     start = i - (len - 1) / 2;
     end = i + len / 2;
     }
     }
     return s.substring(start, end + 1);
     }

     private int expandAroundCenter(String s, int left, int right) {
     int L = left, R = right;
     while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
     L--;
     R++;
     }
     return R - L - 1;
     }

     方法五：Manacher 算法
     还有一个复杂度为 O(n)O(n) 的 Manacher 算法，你可以在这里找到详尽的解释。然而，这是一个非同寻常的算法，
     在45分钟的编码时间内提出这个算法将会是一个不折不扣的挑战。但是，请继续阅读并理解它，我保证这将是非常有趣的。

     * @param a
     * @return
     */
    public static String longestPalindromeWithBreak(String a) {
        StringBuilder builder = new StringBuilder();
        for (int i = a.length()-1;i>=0;i--)
            builder.append(a.substring(i,i+1));
        String b = builder.toString();//反串
        return lcs1(a,b);
    }



    /**
     *
     * @param a
     * @return
     */
    public static String longestPalindrome(String a) {
        String s1 = longestPalindrome1(a);
        String s2 = longestPalindrome2(a);
        return s1.length()>s2.length()?s1:s2;

    }
    // 奇数的回文
    public static String longestPalindrome1(String a) {
        String ans = "";
        int maxLen = 0;
        for (int i = 0;i<a.length();i++){
            int s = i,e=i;
            while (s>=0 && e<a.length()){
                if (a.substring(s,s+1).equals(a.substring(e,e+1))){
                    if (e+1-s>maxLen){
                        ans = a.substring(s,e+1);
                        maxLen = e+1-s;
                    }
                }else {
                    break;
                }
                s--;
                e++;
            }
        }
        return ans;
    }
    // 偶数的回文
    public static String longestPalindrome2(String a) {
        String ans = "";
        int maxLen = 0;
        for (int i = 0;i<a.length();i++){
            int s = i,e=i+1;
            while (s>=0 && e<a.length()){
                if (a.substring(s,s+1).equals(a.substring(e,e+1))){
                    if (e+1-s>maxLen){
                        ans = a.substring(s,e+1);
                        maxLen = e+1-s;
                    }
                }else {
                    break;
                }
                s--;
                e++;
            }
        }
        return ans;
    }



    // 感受：动态规划和经典LCS的应用
    public static void main(String[] args)
    {
        // 最长子序列与子串
//        String s1 = "12345"; String s2 = "34567";// 345，345
//        String s1 = "1234567890"; String s2 = "345231567890";//  34 567890，567890
//        String s1 = "1234567890"; String s2 = "34523156890";//  34 56890，345或者890
//
//        System.out.println("Length of LCS is " + lcs(s1,s2 ));
//        System.out.println("Length of LCS1 is " + lcs1(s1,s2 ));


        String a = "babad";
        String res = longestPalindromeWithBreak(a);
        out.println(res);//bab或者aba   长度 3
        String res1 = longestPalindrome(a);
        out.println(res1);//bab或者aba   长度 3

        a = "cbbd";
        res = longestPalindromeWithBreak(a);
        out.println(res);//bb    长度 2
        res1 = longestPalindrome(a);
        out.println(res1);//bb    长度 2

        a = "abccbd";
        res = longestPalindromeWithBreak(a);
        out.println(res);//bccb    长度 4
        res1 = longestPalindrome(a);
        out.println(res1);//bccb    长度 4

        a = "abcdasdfghjkldcba";
        res = longestPalindromeWithBreak(a);
        out.println(res);//abcd    长度 4
        res1 = longestPalindrome(a);
        out.println(res1);//a    长度 1
    }

}
