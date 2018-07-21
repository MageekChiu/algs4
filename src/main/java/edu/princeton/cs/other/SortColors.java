package edu.princeton.cs.other;

import static java.lang.System.out;

/**
 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 注意:
 不能使用代码库中的排序函数来解决这道题。
 示例:
 输入: [2,0,2,1,1,0]
 输出: [0,0,1,1,2,2]
 进阶：
 一个直观的解决方案是使用计数排序的两趟扫描算法。
 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
 你能想出一个仅使用常数空间的一趟扫描算法吗？

 思路：遇上0就往前边扔，遇上2就往后边扔，记住这两索引和被填的数就行，1不用管，最后填上就行
 但是要注意，为了不丢失要采取元素交换的方式而非直接覆盖
 * @author Mageek Chiu
 */
class SortColors {

    public static void sortColors(int[] nums) {
        int i0 = 0, i2 = nums.length - 1;
         for (int i = 0; i <= i2; ++i) {
             if (nums[i] == 0) {
                 swap(nums,i, i0++);
             } else if (nums[i] == 2) {
                 swap(nums,i--, i2--);
             }
         }
    }
    public static void swap(int[] nums,int i, int j){
        if (i==j|| nums[i]==nums[j]) return;
        nums[i] = nums[i]^nums[j];
        nums[j] = nums[i]^nums[j];
        nums[i] = nums[i]^nums[j];
    }

    // 感受：
    public static void main (String ...args){
//        int[] nums = {2,0,2,1,1,0};
        int[] nums = {2};
//        int[] nums = {2,2,0,2,1,1,0,0,2,1,2,1};
        sortColors(nums);//
        for (int num : nums) {
            out.print(num);
        }
    }
}
