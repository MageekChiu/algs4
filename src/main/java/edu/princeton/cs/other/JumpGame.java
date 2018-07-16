package edu.princeton.cs.other;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;

/**
 跳跃游戏 I
 给定一个非负整数数组，你最初位于数组的第一个位置。
 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 判断你是否能够到达最后一个位置。
 示例 1:
 输入: [2,3,1,1,4]
 输出: true
 解释: 从位置 0 到 1 跳 1 步, 然后跳 3 步到达最后一个位置。
 示例 2:
 输入: [3,2,1,0,4]
 输出: false
 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
-----------------------------------------------------
 跳跃游戏 II
 定一个非负整数数组，你最初位于数组的第一个位置。
 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 示例:
 输入: [2,3,1,1,4]
 输出: 2
 解释: 跳到最后一个位置的最小跳跃数是 2。
 从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 说明:
 假设你总是可以到达数组的最后一个位置。

 * @author Mageek Chiu
 */
class JumpGame {



    public static boolean canJump(int[] nums) {
//        return canJump0(nums,0);
        return canJump1(nums,0);
    }

    // 直接递归，如果数组太大容易超时，这也是深度优先
    public static boolean canJump0(int[] nums,int start){

        if (start == nums.length-1) return true;// 已经到了
        if (start > nums.length-1) return false;//跑出去了，回退到上一步去减小步数
        for(int i=nums[start];i>0;i--){// 从最大步数开始跳，当前的贪心
            if (canJump0(nums,start+i)) return true;
            // 无解就会回溯到当前位置，并减小步数
        }
        // 这里 i==0，也就是不能再往前跳了,只有处于最后一个位置才有解
        return start == nums.length - 1;

    }


    // 想办法剪枝,这样就能节省时间，可行
    public static boolean canJump1(int[] nums,int start){
        for(int i=nums[start];i>0;i--){// 从最大步数开始跳，当前的贪心
            int nextStart = start+i;
            if (nextStart == nums.length-1) return true;// 已经到了
            if (nextStart > nums.length-1) continue;//跑出去了,减小步数
            if (canJump1(nums,nextStart)) return true;// 递归查找
            // nextStart 无解就会回溯到当前位置，并减小步数
            int j;
            for (j=nextStart-1;j>start;j--){//依次减小
                if (nums[j]>nums[i]+i-j){// 说明 j 值得尝试
                    if (canJump1(nums,j)) return true;
                }
            }
//            这里 j==start，说明第i个无论跳多少步都无解，就不用继续循环了
            return start == nums.length - 1;
        }
        // 这里 i==0，也就是不能再往前跳了,只有处于最后一个位置才有解
        return start == nums.length - 1;
    }

    //维护一个当前能跳到的最大值maxJump, 若是maxJump 已经>=nums.length-1,
    // 说明能跳到最后一个点，return true.若是过程中maxJump <= i, 说明跳到当前点便不能往前，
    // 跳出loop, return false.
    public boolean canJump2(int[] nums) {
        int n = nums.length;
        // maxJump是维护的当前能跳到的最大位置
        int maxJump = 0;
        for (int i = 0; i < n; i++) {
            // i>maxJump表示无法到达i的位置,失败
            // maxJump >= (n - 1),此时的距离已经足够到达终点，成功
            if (i > maxJump || maxJump >= (n - 1))
                break;
            // nums[i]+i当前跳最远距离 maxJump为i之前跳最远距离
            maxJump = max(maxJump, i + nums[i]);
        }
        return maxJump >= (n - 1);
    }

    /**
     第一种是动态规划，定义数组dp，dp[i]表示到达i处的最小跳跃次数。关键在确定dp[i]的值，
     我们可以遍历i之前的元素值，找到第一次能跳到或跳过i的下标，然后在其基础上加1即可。代码如下：
     */
    public static int jump0(int[] nums){
        List<Integer> dp = new ArrayList<>();
        int n = nums.length;
        if(n==0)    return 0;
        for(int i=0;i<n;i++) {
            for(int j=0;j<i;++j){
                if(nums[j]+j>=i) {
                    dp.set(i,dp.get(j)+1);
                    break;
                }
            }
        }
        return dp.get(n-1);
    }


    // 第二种是贪心算法，遍历数组，找到当前能到达的最远距离curr，则，在这个距离中，不断的更新能到达的最远距离maxlen，
    // 遍历数组，若出了curr的范围，则计数器加1，并重新更新curr，至于i是如何出cur的范围不考虑。
    //                       curr maxLen
    // 如：{   3,   3,   1,   1,   4}，
    // 第一个3能达到的最远距离是curr，遍历数组，记下3到curr之间最新的最大距离maxlen，
    // 当i大于curr时，我们可以一步到达maxlen所在的位置，也就是说，我们不关心，从3到curr是如何到达的。
    // ------------------------------------------------------
    public static int jump(int[] nums) {
        int curr=0,maxLen=0,count=0;
        for(int i=0;i<nums.length;++i){
            if(i>curr) {
                curr=maxLen;
                count++;
            }
            maxLen= max(maxLen,i+nums[i]);
        }
        return count;
    }

    //要理解这个算法，首先明白，这个题只要我们求跳数，怎么跳，最后距离是多少，都没让求的。
//    大牛这个算法的思想主要是，扫描数组（废话。。。），以确定当前最远能覆盖的节点，放入curr。然后继续扫描，直到当前的路程超过了上一次算出的覆盖范围，那么更新覆盖范围，同时更新条数，因为我们是经过了多一跳才能继续前进的。
//    形象地说，这个是在争取每跳最远的greedy。
//    http://www.cnblogs.com/lichen782/p/leetcode_Jump_Game_II.html
//    O(n)的
//    这道题让我们明白一个道理：
//    不要做无必要的计算。
//    对了，有同学会问，那为啥要用last，直接用curr跳不就行了。直接用curr跳那每次都是跳最远的，但是最优路径不不一定是这样。

    /**
     * We use "last" to keep track of the maximum distance that has been reached
     * by using the minimum steps "ret", whereas "curr" is the maximum distance
     * that can be reached by using "ret+1" steps. Thus,
     * curr = max(i+A[i]) where 0 <= i <= last.
     */
    // 局部解在全局解中，怎么好好定义这个局部解是一个艺术，不是简单的当前跳最远就行
    public static int jump1(int A[]){
        int ret = 0;
        int last = 0;
        int curr = 0;
        for (int i = 0; i < A.length; ++i) {
            if (i > last) {// 想到达i必须再跳一步了
                last = curr;
                ++ret;
            }
            curr = max(curr, i+A[i]);
        }
        return ret;
    }



    // 感受：
    public static void main (String ...args) throws IOException {
//        int[] nums = {2,3,1,1,4};
//        int[] nums = {2,0};
//        int[] nums = {3,2,4,4,5,6,3,6,2,3,6,2,3,34,6,7,8,6,8,22,4,22,3,2,3,4,5,1,3,6,1,2,3,5,4,4,5,6,3,6,2,3,6,2,3,34,6,7,8,6,8,22,4,22,3,4,4,5,6,3,6,2,3,6,2,3,34,6,7,8,6,8,22,4,22,3,4,4,5,6,3,6,2,3,6,2,3,34,6,7,8,6,8,22,4,22,3,3,21,1,2,3,4,5,6,7,8,3,5,8,9,1,2,3,7,3,4,6,7,2,3,6,2,4,5,2,0,1,2,0,2,3,3,2,1};

//        int[] nums = {3,2,1,0,4};

//        String fileName = "D:\\workspace\\Java\\algs4\\src\\main\\java\\edu\\princeton\\cs\\other\\1.txt";
//        File file = new File(fileName);
//        BufferedReader reader = new BufferedReader(new FileReader(file));
//        String tempString,finalString;
//        StringBuilder finalStringBuilder= new StringBuilder();
//        while ((tempString = reader.readLine()) != null) {
//            finalStringBuilder.append(tempString);
//        }
//        finalString = finalStringBuilder.toString();
//        String[] numStr = finalString.split(",");
//        int[] nums = new int[numStr.length];
//        for (int i = 0; i < nums.length; i++) {
//            nums[i] = Integer.parseInt(numStr[i]);
//        }

//        out.println(canJump(nums));


        int[] nums = {2,3,1,1,4};//2
//        int[] nums = {0};//0
        System.out.println(jump(nums));

    }
}
