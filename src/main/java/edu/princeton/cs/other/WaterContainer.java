package edu.princeton.cs.other;

import java.util.Stack;

import static java.lang.Math.max;
import static java.lang.System.out;

/**

 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 画 n 条垂直线，使得垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

 注意：你不能倾斜容器，n 至少是2。

 * @author Mageek Chiu
 */
class WaterContainer {

    /**
     * 暴搜,时间复杂度 O(n^2)
     * @param height
     * @return
     */
//    public static int maxArea(int[] height) {
//        int len = height.length;
//        int[][] c = new int[len][len];
//        int max = 0,ii = 0,jj = 1;
//        for (int i = 0;i<len;i++){
//            for (int j = i+1;j<len;j++){
//                c[i][j] = Math.min(height[i],height[j])*(j-i);
//                if(c[i][j]>max){
//                    max = c[i][j];
//                    ii = i;
//                    jj = j;
//
//                }
//            }
//        }
////        System.out.println(ii+","+jj);
//        return max;
//    }

    /**
     双指针法
     算法
     这种方法背后的思路在于，两线段之间形成的区域总是会受到其中较短那条长度的限制。此外，两线段距离越远，得到的面积就越大。
     我们在由线段长度构成的数组中使用两个指针，一个放在开始，一个置于末尾。
     此外，我们会使用变量 maxareamaxarea 来持续存储到目前为止所获得的最大面积。
     在每一步中，我们会找出指针所指向的两条线段形成的区域，更新 maxareamaxarea，并将指向较短线段的指针向较长线段那端移动一步。

     最初我们考虑由最外围两条线段构成的区域。现在，为了使面积最大化，我们需要考虑更长的两条线段之间的区域。
     如果我们试图将指向较长线段的指针向内侧移动，矩形区域的面积将受限于较短的线段而不会获得任何增加。
     但是，在同样的条件下，移动指向较短线段的指针尽管造成了矩形宽度的减小，但却可能会有助于面积的增大。
     因为移动较短线段的指针会得到一条相对较长的线段，这可以克服由宽度减小而引起的面积减小。
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        int result = 0, l = 0, r = height.length - 1;
        while (l < r) {
            result = max(result, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r])// 移动较短的路径
                l++;
            else
                r--;
        }
        return result;
    }

    // 感受：
    public static void main (String ...args){
//        int[] nums = {3,5,8,4,2,6};//
//        int res = maxArea(nums);//
//        out.println(res);

//        int[] nums1 = {0,1,0,2,1,0,1,3,2,1,2,1};//6
//        out.println(trap(nums1));

//        int[] nums1 = {2,1,5,6,2,3};//10
//        int[] nums1 = {4,2,0,3,2,4,3,4};//10
//        int[] nums1 = {1,2};//2
//        int[] nums1 = {0,0};//2
//        int[] nums1 = {3,2,4,2,7,1,8,2,3};//
//        int[] nums1 = {3,3,3};//
//        int[] nums1 = {1,2,2};//
        int[] nums1 = {5,4,1,2};//
        out.println(largestRectangleArea1(nums1));
    }

    /**
     给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

     上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
     示例:
     输入: [0,1,0,2,1,0,1,3,2,1,2,1]
     输出: 6

     思路1: 本质就是找波峰，水量是波峰之间的容量，可以标记一下左右波峰，亦即一开始左波峰，下一个就是右，在下一个又是左,需要扫描两遍
     思路2：双指针，木桶原理，盛的水取决于最短板，我们设置两个指针从左右两端开始向中间靠拢，我们根据短的那一端向高的那一端靠拢。扫描一遍
     然后过程中更新左右最高的柱子，来求的中间的蓄水量。
     */
    public static int trap(int[] height) {
        int res = 0;
        int l = 0, r = height.length - 1;
        int lmax = 0, rmax = 0;
        while(l < r){
            lmax = max(lmax, height[l]);
            rmax = max(rmax, height[r]);
            if (lmax < rmax){
                res += lmax - height[l];
                l++;
            }else{
                res += rmax - height[r];
                r--;
            }
        }
        return res;

    }

    /**
     给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
     求在该柱状图中，能够勾勒出来的矩形的最大面积。

     以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
     图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。

     亦即一段连续柱子中的最矮者乘以连续的长度

     DP依然超时
     */
    public static int largestRectangleAreaDP(int[] height) {
        int result = Integer.MIN_VALUE,len = height.length;
        if (len<1) return 0;
        if (len==1) return height[0];

        // DP 初始化
        int [][] c = new int[len][len];// i,j表示 起始点，包含, c[i][j]表示一段路程中的最矮者
        for (int i=0;i<len;i++){
            c[i][i] = height[i];
        }
        // 计算DP，看得出来DP是有些重复计算的
        for (int i=0;i<len;i++){
            for (int j=i+1;j<len;j++){// j>i
                c[i][j] = Math.min(c[i][j-1],height[j]);
            }
        }
        // 计算面积
        for (int i=0;i<len;i++){
            for (int j=i;j<len;j++){// j>i
                if(c[i][j]*(j-i+1)>result)
                    result = c[i][j]*(j-i+1);
            }
        }
        return result;
    }

    // 思路，从两边开始靠，只有越过波谷才有可能，所以需要找到左边的波谷和右边的波谷,波谷还有可能有很多
    // 4,2,0,3,2,4,3,4    而且要要左右交替找波谷,
    // 情况比较复杂，有可能只有一个波谷，左右波谷的先后顺序还比较重要，所以估计先左，先右来两次取大的就好了
    public static int largestRectangleArea1(int[] height) {
        int len = height.length, l = 0,r = len-1;
        if (len==0) return 0;
        if (len==1) return height[0];
        if (len==2) {
            return Math.max(Math.max(height[0], height[1]), Math.min(height[0], height[1]) * 2);
        }
        int minHeight = min(height,l,r),maxArea = len*minHeight;//最开始的面积
        while (l<=r){
            while (l+2<=r){
                if(height[l+1]<height[l] && height[l+1]<=height[l+2]){// l+1 是波谷
                    l++;// 这样 l 就是波谷
                    break;
                }else if(height[l+1]>height[l]){// l 就直接是波谷了
                    break;
                }
                l++;
            }
            // 现在 l 是波谷

            //波谷肯定要去掉一个小的
            if (height[l]<height[r]){
                l++;
            }else {
                r--;
            }
            maxArea = Math.max((r-l+1)*min(height,l,r),maxArea);

            while (l<=r-2){
                if(height[r-1]<height[r] && height[r-1]<=height[r-2]){
                    r--;
                    break;
                }else if(height[r-1]>height[r]){
                    break;
                }
                r--;
            }
            // 现在 r 是波谷

            //波谷肯定要去掉一个小的
            if (height[l]<height[r]){
                l++;
            }else {
                r--;
            }
            maxArea = Math.max((r-l+1)*min(height,l,r),maxArea);
        }
        return maxArea;
    }

    public static int largestRectangleArea2(int[] height) {
        int len = height.length, l = 0,r = len-1;
        if (len==0) return 0;
        if (len==1) return height[0];
        if (len==2) {
            return Math.max(Math.max(height[0], height[1]), Math.min(height[0], height[1]) * 2);
        }
        int minHeight = min(height,l,r),maxArea = len*minHeight;//最开始的面积
        while (l<=r){
            while (l<=r-2){
                if(height[r-1]<height[r] && height[r-1]<=height[r-2]){
                    r--;
                    break;
                }else if(height[r-1]>height[r]){
                    break;
                }
                r--;
            }
            // 现在 r 是波谷

            //波谷肯定要去掉一个小的
            if (height[l]<height[r]){
                l++;
            }else {
                r--;
            }
            maxArea = Math.max((r-l+1)*min(height,l,r),maxArea);

            while (l+2<=r){
                if(height[l+1]<height[l] && height[l+1]<=height[l+2]){// l+1 是波谷
                    l++;// 这样 l 就是波谷
                    break;
                }else if(height[l+1]>height[l]){// l 就直接是波谷了
                    break;
                }
                l++;
            }
            // 现在 l 是波谷

            //波谷肯定要去掉一个小的
            if (height[l]<height[r]){
                l++;
            }else {
                r--;
            }
            maxArea = Math.max((r-l+1)*min(height,l,r),maxArea);

        }
        return maxArea;
    }
    // 最容易想到的解法是brute force，然后是动态规划。然而，最神奇的基于堆栈的O（n）解法，可能很多人听说过，或是大概知道（你还能回忆起来吗？）
    // 细节和思路到底是怎样的？我们今天就和大家来聊一聊。
    //基本思路（如下图）：从第一个柱子（编号1）开始，找出所有的后一个比前一个上手的柱子（从编号1到13），
    // 直到遇到一个高度下降的柱子（编号14）。而且把下降之前的编号（从1到13）推入到一个堆栈中。
    // 然后计算栈顶编号柱子的高度（所有A1A2线之上的柱子们，从编号3到13）比下降柱子（14）高的所有矩形的面积（
    // 因为14不可能和比它高的柱子组成更大的矩形），保留最大的。从栈顶开始，比如，13，12-13，...，8-13，7-13，...，直到3-13。
    // 但是14能参与比它矮的较远的编号2（2-14，就是B1B2）和编号1（1-14，就是C1C2）组成矩形。
    // 可以看出，堆栈中保留了到当前为止，所有将要参与可能矩形计算的所有柱子的编号。有了这些编号，我们可以计算所有可能的矩形的面积，
    // 从而求出最大的。注意了，堆栈中的编号的柱子高度时递增的
    //http://chuansong.me/n/390896436960
    public int largestRectangleAreaN(int[] heights) {
        if(heights == null || heights.length==0){
            return 0;
        }
        //最大值
        int maxValue = 0;
        Stack<Integer> res = new Stack<Integer>();
        int i=0;
        for(;i<heights.length;i++){
            //栈为空，或栈顶元素top对应的heights[top]小于heights[i]，入栈
            if(res.empty() || heights[res.peek()] <= heights[i])
                res.push(i);
            else{
                //heights[i]>heights[top]，栈顶元素出栈
                int top = res.pop();
                //求矩形长度
                int index = res.empty() ? -1:res.peek();
                maxValue = Math.max(maxValue,heights[top] * (i-1-index));
                //再次比较 heights[i]与此时的栈顶元素
                i--;
            }
        }
        while(!res.empty()){
            //对每个栈顶元素top 求矩形面积
            int top = res.pop();
            int index = res.empty() ? -1:res.peek();
            maxValue = Math.max(maxValue,heights[top] * (i-1-index));
        }
        return maxValue;

    }

    /**
     一个无序数组，对于区间[l，r]，求所有区间中sum(l,r)乘以min(l,r)的积最大的值。

     思路：
     求一个数组中右边第一个比他大的数，（单调栈）
     具体到这个问题，创建一个数据结构保留未解决的子问题，根据特点选择栈。
     1、栈里面保留是索引，而非元素，其实这是一个很关键的地方，索引的信息要比内容多，因为可以索引本身就可以确定内容。要牢记这一特点
     2、初始栈，里面为第一个元素
     3、如果栈不为空，而且当前处理元素比栈顶元素大，则栈顶元素对应的第一个比它大的值，就是该元素
        4、弹出栈顶元素，继续处理栈里的元素，直至为空或当前处理元素不大于栈顶元素
     5、将当前元素压入栈
     6、循环3~5

     数组中的每一个数，所有以它为最小值的区间里sum最大的一定是它左边和右边分别到比他大的第一个数那里，所以遍历一遍即可。
     先预处理出前缀和，然后用单调栈正着和反着各走一遍找出每个数左边和右边第一个比他大的数，最后枚举每一个数算sum*min即可。
     */
//    public static int largestSum(int[] a){
//
//    }

    public static int min(int[] ints,int start,int end){//包含首尾
        int minHeight = Integer.MAX_VALUE;
        for (int i = start; i <= end; i++) {
            if (ints[i]<minHeight) minHeight = ints[i];
        }
        return minHeight;
    }



}
