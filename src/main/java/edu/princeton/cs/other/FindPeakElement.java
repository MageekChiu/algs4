package edu.princeton.cs.other;

import static java.lang.System.out;

/**
 峰值元素是指其值大于左右相邻值的元素。
 给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。
 数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
 你可以假设 nums[-1] = nums[n] = -∞。
 示例 1:
 输入: nums = [1,2,3,1]
 输出: 2
 解释: 3 是峰值元素，你的函数应该返回其索引 2。
 示例 2:
 输入: nums = [1,2,1,3,5,6,4]
 输出: 1 或 5
 解释: 你的函数可以返回索引 1，其峰值元素为 2；
 或者返回索引 5， 其峰值元素为 6。
 说明:
 你的解法应该是 O(logN) 时间复杂度的。
 * @author Mageek Chiu
 */
class FindPeakElement {

    public static int findPeakElement(int[] nums) {
        if(nums.length == 1) return 0;

        int size = nums.length, low = 0, high = size-1, mid = 0;
        while(low <= high) {
            mid = (high - low) / 2 + low;
            if(nums[mid] > (mid - 1 < 0 ? Integer.MIN_VALUE : nums[mid - 1]) && nums[mid] > (mid + 1 > size  - 1 ? Integer.MIN_VALUE : nums[mid + 1])) {
                return mid;
            }
            else if(nums[mid] < (mid - 1 < 0 ? Integer.MIN_VALUE : nums[mid - 1])) {
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }
        return -1;
    }

    // 感受： O(logN)，联想到二分法,对于中点，如果左右两端任一比中点大，意味着那一方有坡（由于边界是负无穷！），
    // 这时候只要破定不是重复数字，那么这个坡就是一个峰
    public static void main (String ...args){
        int[] nums = {-2,2,-3,4,-1,2,1,-5,3};
        int res = findPeakElement(nums);//
        out.println(res);
    }
}
