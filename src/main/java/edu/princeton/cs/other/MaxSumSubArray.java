package edu.princeton.cs.other;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

/**
 * 给定一个整数数组，找到一个具有最大和的子数组，返回其最大和。
 * 样例
 * 给出数组[−2,2,−3,4,−1,2,1,−5,3]，符合要求的子数组为[4,−1,2,1]，其最大和为6
 maxSumSubArray
 *
 -------------------------------------------------------

 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 注意你不能在买入股票前卖出股票。
 示例 1:
 输入: [7,1,5,3,6,4]
 输出: 5
 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 示例 2:
 输入: [7,6,4,3,1]
 输出: 0
 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 maxProfit
 -------------------------------------------------------
 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 示例 1:
 输入: [7,1,5,3,6,4]
 输出: 7
 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 示例 2:
 输入: [1,2,3,4,5]
 输出: 4
 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 示例 3:
 输入: [7,6,4,3,1]
 输出: 0
 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 maxProfit1
 --------------------------------------------------------
 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 示例 1:
 输入: [3,3,5,0,0,3,1,4]
 输出: 6
 解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
 示例 2:
 输入: [1,2,3,4,5]
 输出: 4
 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 示例 3:
 输入: [7,6,4,3,1]
 输出: 0
 解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。
 maxProfit2
 --------------------------------------------------------
 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 示例 1:
 输入: [2,4,1], k = 2
 输出: 2
 解释: 在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 示例 2:
 输入: [3,2,6,5,0,3], k = 2
 输出: 7
 解释: 在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。

 maxProfit3

 * @author Mageek Chiu
 */
class MaxSumSubArray {

    /**
     * 遍历1遍即可，O(n)，直接丢弃为负数的一段，因为子段要求连续，所以为负的对后面的子段贡献总是为负
     * @param nums
     * @return
     */
    public static int maxSumSubArray(int[] nums) {
        int sum = 0,ans = Integer.MIN_VALUE;
        for (int num : nums) {
            sum += num;
            if (sum > ans) ans = sum;
            if (sum < 0 ) sum = 0;// 为负数，直接丢弃
        }
        return ans;
    }

    /**
     maxSumSubArray 带下标的版本
     */
    public static int maxSumSubArray0(int[] nums,int start,int length) {
        int sum = 0,ans = Integer.MIN_VALUE;
        for (int i= start;i<start+length;i++) {
            sum += nums[i];
            if (sum > ans) ans = sum;
            if (sum < 0 ) sum = 0;// 为负数，直接丢弃
        }
        return ans;
    }

    /**卖一次
     * 说白了就是上面那个问题，只是换了一个形式,O(n)
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        if (prices.length<2) return 0;// 排除特殊
        int[] tmp = new int[prices.length-1];// tmp i 表示 i天买，i+1天卖赚的钱
        for (int i = 1; i < prices.length; i++) {
            tmp[i-1] = prices[i]-prices[i-1];
        }
        int res = maxSumSubArray(tmp);
        return res>0?res:0;
    }


    /**卖多次，不限
     * 比上面的更简单，有正的就加
     * @param prices
     * @return
     */
    public static int maxProfit1(int[] prices) {
        if (prices.length<2) return 0;// 排除特殊
        int[] tmp = new int[prices.length-1];// tmp i 表示 i天买，i+1天卖赚的钱
        for (int i = 1; i < prices.length; i++) {
            tmp[i-1] = prices[i]-prices[i-1];
        }
        int ans = 0;
        for (int i : tmp) {
            if (i>0) ans+=i;
        }
        return ans;
    }

    /**
     * 找出 两个连续和大于0的子段 或者 一个子段，使得其和最大且大于0，否则返回0
     * 最多卖两次
     0,2,-5,0,3,-2,3,              3+3
     1,1,1,1,                       4
     -1,-2,-1,-2,                   0
     -6,4,-2,3,-2,                 4+3
     -1,1,-2,1,                    1+1


     可以先找一段最大的，然后看中间有没有负的，把负的去掉即可，
     但是这样也有可能有一大段有一个很大的负数导致没有成为最大段而被误排除了
     就比如 最后一个例子，1是最大段，但是 1,-2,1 就因为一个很大的负数:-2就误排除了

     所以这时候恐怕O(n)就不行了，得上动态规划了。DP 一般是 O(n^2)，
     这道题采用暴力解法的话，应该是O(n^3)，可以分析，i,j循环，内部遍历求和 一个3个for
     实际上想想就知道有太多重复计算，所以需要dp来进行保存


     * @param nums
     * @return
     */
    public static int maxSumSubArray1(int[] nums) {
        if (nums.length==1) return nums[0]>0?nums[0]:0;
        int sum = 0,ans = Integer.MIN_VALUE;
        int l=0,r=0,lastL=0;//最大段的起止点
        for (int i = 0;i<nums.length;i++) {
            sum += nums[i];
            if (sum > ans && sum>0) {
                ans = sum;
                r = i;
                l = lastL;
            }
            if (sum < 0 ){// 为负数，直接丢弃
                sum = 0;
                if (ans<0){
                    l = i+1;
                }
                lastL = i+1;
            }
        }
        // [l,r] 首尾必然是正的
//        System.out.println(l+","+r);
        if (l>r) return 0;
        if (l==r) return nums[l];
        boolean needAdd = false;
        // 在[l,r] 中找一段和最小且为负的,也就是取反后最大的
        int[] maxSum = new int[r-l+1];
        System.arraycopy(nums,l,maxSum,0,r-l+1);
        for (int i = 0; i < maxSum.length; i++) {
            if (maxSum[i]<0)  needAdd = true;// 只要有一个小于0就需要+
            maxSum[i] = -maxSum[i];
//            System.out.print(maxSum[i]+",");
        }
//        System.out.println();
        return needAdd ? ans+maxSumSubArray(maxSum):ans;
    }

    /**
     0,2,-5,0,3,-2,3,              3+3
     1,1,1,1,                       4
     -1,-2,-1,-2,                   0
     -6,4,-2,3,-2,                 4+3
     -1,1,-2,1,                    1+1

     dp[i][j] 表示 i 开头，j 结尾的子段的最大子段和, 所以 i<=j,

     dp[i][j] = [i]                 if i == j

     dp[i][j] = dp[i][j-1]          if [j]<=0

     dp[i][j] = dp[i][j-1]+[j]      if [j]>0

     这样不对，因为 [j]<0的时候，[i,j+1]已经被分断了，即使[j+1]>0也不能直接加

     应该采取减的方式 先算[0,len],然后递减，，，，其实和上面一样也不对。

     关键是 递归的时候，连续这个条件不一定能延续


     */
    public static int maxSumSubArray2(int nums[]){
        int len = nums.length;
        int[][] dp = new int[len][len];
        for (int i=0;i<len;i++){
            for (int j=len-1;j>=i;j--){

            }
        }
        for (int i=0;i<len;i++){
            for (int j=0;j<len;j++){
                out.print(dp[i][j]+", ");
            }
            out.println();
        }
        return 0;
    }

    /**
     把一个数组用指针分割成两段，每段求最大和，这个是n,然后移动指针 又是n 最终 n^2

     */
    public static int maxSumSubArray3(int[] nums){
        int len = nums.length,
                lSum,lMax =Integer.MIN_VALUE,
                rSum,rMax = Integer.MIN_VALUE,
                allMax=lMax,tmp;
        for (int i = 0;i<len;i++){// 从0开始，保证可以有一个完整的数组，亦即 1段和，一般是2段和

            // 数组拷贝太耗时，改成下面两行的截取就能通过了
//            int[] lArray = new int[i],rArray = new int[len-i];//每次重新分配
//            System.arraycopy(nums,0,lArray,0,i);
//            System.arraycopy(nums,i,rArray,0,len-i);
//            lSum = maxSumSubArray(lArray);
//            rSum = maxSumSubArray(rArray);

            lSum = maxSumSubArray0(nums,0,i);
            rSum = maxSumSubArray0(nums,i,len-i);

//            if (lSum>lMax) lMax = lSum;
//            if (rSum>rMax) rMax = rSum;
            tmp = (lSum>0?lSum:0) + (rSum>0?rSum:0);
            if (tmp>allMax) allMax = tmp;

        }
        return allMax;
    }

    public static int maxSumSubArray3(int[] nums,int start,int length){
        int lSum,rSum,
                allMax=Integer.MIN_VALUE,tmp;
        for (int i = start;i<start+length;i++){
            lSum = maxSumSubArray0(nums,0,i);
            rSum = maxSumSubArray0(nums,i,length-i);
            tmp = (lSum>0?lSum:0) + (rSum>0?rSum:0);
            if (tmp>allMax) allMax = tmp;
        }
        return allMax;
    }



    /**
     * 最多卖两次
     * @param prices
     * @return
     */
    public static int maxProfit2(int[] prices) {
        if (prices.length<2) return 0;// 排除特殊
        int[] tmp = new int[prices.length-1];// tmp i 表示 i天买，i+1天卖赚的钱
        for (int i = 1; i < prices.length; i++) {
            tmp[i-1] = prices[i]-prices[i-1];
        }

//        for (int i : tmp) {
//            System.out.print(i+",");
//        }
//        System.out.println();

//        return maxSumSubArray1(tmp);
//        return maxSumSubArray2(tmp);
        return maxSumSubArray3(tmp);
    }

    /**
     * indexList[i] 存的是 i 划分的 index list。由于是指数级别，所以k不会太大，可以认为不超过12
     */
    public static List<List<Integer>> indexList = new ArrayList<>(12);
    /**
     * resultList[i] 存的是 i 划分的最大结果中包含的每一段结果。
     */
    public static List<List<Integer>> resultList = new ArrayList<>(12);

    /**
     * 2 划分
     * @param nums
     * @return
     */
    public static int maxSumSubArray40(int[] nums){
        List<Integer> tempIndex = new ArrayList<>(1);
        List<Integer> tempResult = new ArrayList<>(2);
        int len = nums.length,
                lSum , rSum ,
                allMax=Integer.MIN_VALUE,tmp;
        for (int i = 0;i<len;i++){
            lSum = maxSumSubArray0(nums,0,i);
            rSum = maxSumSubArray0(nums,i,len-i);// i 分割线处这个元素是划分在右边的
            tmp = (lSum>0?lSum:0) + (rSum>0?rSum:0);
            if (tmp>allMax) {
                allMax = tmp;
                tempIndex.set(0,i);
                tempResult.set(0,lSum);
                tempResult.set(1,rSum);
            }
        }
        indexList.set(2,tempIndex);
        resultList.set(2,tempResult);
        return allMax;
    }

    public static int maxSumSubArray4(int k,int[] nums){
        if (k<=0) return 0;// 特殊的
        if (k==1) return maxSumSubArray(nums);// 可以直接返回的
        if (k==2) return maxSumSubArray40(nums);//可以直接返回的，也是递归截止条件

        List<Integer> tempIndex = new ArrayList<>(k-1);//k 划分有 k-1 个 index
        List<Integer> tempResult = new ArrayList<>(k);//k 划分有 k 个 resultList
        int len = nums.length,
                lSum , rSum ,
                allMax=Integer.MIN_VALUE,tmp;

        if (indexList.get(k-1)==null){// k-1划分还没准备好
            maxSumSubArray4(k-1,nums);
        }
//        到这里，由于递归关系，k-1及其以下的划分都已经找好了
        List<Integer> subIndex = indexList.get(k-1);// k-2 个 index
        List<Integer> subResult = resultList.get(k-1);// k-1 个 result，最大下标 k-2

        for (int i = 0;i<k-1;i++){
            int l,r;// 把这一段划分为2段，
            if (i==0){
                l = 0;
                r = subIndex.get(i);
            }else if (i==k-2){
                l = subIndex.get(i);
                r = nums.length;
            }else {
                l = subIndex.get(i-1);
                r = subIndex.get(i);
            }

            int newResult = maxSumSubArray3(nums,l,r-l);
            if (newResult>=subResult.get(i)){// 可以更新了

            }

        }

        indexList.set(2,tempIndex);
        resultList.set(2,tempResult);
        return allMax;
    }

    /**
     * 最多卖k次
     * @param prices
     * @return
     */
    public static int maxProfit3(int k,int[] prices) {
        if (prices.length<2) return 0;// 排除特殊
        int[] tmp = new int[prices.length-1];// tmp i 表示 i天买，i+1天卖赚的钱
        for (int i = 1; i < prices.length; i++) {
            tmp[i-1] = prices[i]-prices[i-1];
        }
        return maxSumSubArray4(k,tmp);
    }


        // 感受，问题转化的能力，买卖股票转为最大子段和问题，最大 2 子段和又可以利用最大 1 子段和问题
        // 把问题归一化。
        // 那么最大 k 子段和呢？想着 K sum问题可以利用 K-1 sum ,一层一层可以回到 3 sum(n^2),2 sum(n)
        // 那么子段和问题也应该类似，仔细想想，k-1 段中，再找一段划分成两段不就是k段了嘛
        public static void main (String ...args){
//        int[] nums = {-2,2,-3,4,-1,2,1,-5,3};// 符合要求的子数组为[4,−1,2,1]，其最大和为6
//        int res = maxSumSubArray(nums);//
//        out.println(res);
//        int[] nums1 = {-1,2,3,4,-3,7,-7};// 符合要求的子数组为[2.3.4.-3.7]，其最大和为13
//        int res1 = maxSumSubArray(nums1);//
//        out.println(res1);
//
//        out.println(maxProfit(new int[]{7,1,5,3,6,4}));//5
//        out.println(maxProfit(new int[]{7,2,9,1,3,6,7}));//7
//        out.println(maxProfit(new int[]{7,6,4,3,1}));//0

//        out.println(maxProfit1(new int[]{7,1,5,3,6,4}));//7
//        out.println(maxProfit1(new int[]{1,2,3,4,5}));//4
//        out.println(maxProfit1(new int[]{7,6,4,3,1}));//0
//        out.println(maxProfit1(new int[]{7}));//0

//        out.println(maxProfit2(new int[]{3,3,5,0,0,3,1,4}));//6
//        out.println(maxProfit2(new int[]{1,2,3,4,5}));//4
//        out.println(maxProfit2(new int[]{7,6,4,3,1}));//0
//        out.println(maxProfit2(new int[]{7,1,5,3,6,4}));//7
//        out.println(maxProfit2(new int[]{7}));//0
//        out.println(maxProfit2(new int[]{1,2}));//1
//        out.println(maxProfit2(new int[]{1,4,2}));//3
//        out.println(maxProfit2(new int[]{2,1,2,0,1}));//2

//            out.println(maxProfit3(2,new int[]{2,4,1}));//2
//            out.println(maxProfit3(2,new int[]{3,2,6,5,0,3}));//7
            out.println(maxProfit3(4,new int[]{1,2,4,2,5,7,2,4,9,0}));//

        }


}
