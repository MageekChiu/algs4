package edu.princeton.cs.other;

import static java.lang.System.out;

/**

 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。画 n 条垂直线，使得垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
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
     我们在由线段长度构成的数组中使用两个指针，一个放在开始，一个置于末尾。 此外，我们会使用变量 maxareamaxarea 来持续存储到目前为止所获得的最大面积。
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
            result = Math.max(result, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r])// 移动较短的路径
                l++;
            else
                r--;
        }
        return result;
    }

    // 感受：
    public static void main (String ...args){
        int[] nums = {3,5,8,4,2,6};//
        int res = maxArea(nums);//
        out.println(res);
    }
}
