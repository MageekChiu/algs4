package edu.princeton.cs.other;

/**
 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 你的算法时间复杂度必须是 O(log n) 级别。
 如果数组中不存在目标值，返回 [-1, -1]。
 示例 1:
 输入: nums = [5,7,7,8,8,10], target = 8
 输出: [3,4]
 示例 2:
 输入: nums = [5,7,7,8,8,10], target = 6
 输出: [-1,-1]


 * @author Mageek Chiu
 */
class SearchRange {
    public static int[] searchRange(int[] nums, int target) {
        return searchRange(nums,0,nums.length,target);
    }

    /**
     范围 [start,end)
     * @param nums
     * @param start
     * @param end
     * @param target
     * @return
     */
    public static int[] searchRange(int[] nums,int start,int end, int target){
        int l = start,r = end;
        if (nums.length<1 || start>end) return new int[]{-1,-1};
        if (start==end) return new int[]{target==nums[l]?l:-1,target==nums[r]?r:-1};

        int ans = ArrayRotated.binarySearch(nums,start,end,target);// 找到的第一个
        if (ans==-1) return new int[]{-1,-1};

        if ((ans > 0 && nums[ans]>nums[ans-1]) || ans==start){// 是开头
            l = ans;
        }else {// 左边还有该数
            l = searchRange(nums,start,ans,target)[0];
        }
        if (ans < nums.length-1 && nums[ans]<nums[ans+1] || ans == end-1){// 是结尾
            r = ans;
        }else{// 右边还有该数
            r = searchRange(nums,ans+1,end,target)[1];
        }
        return new int[]{l,r};
    }

//    大家对于二分查找并不陌生，一般意义上的二分查找，往往返回给我们的是目标元素在排序数组中出现的一个随机的位置，
// 但是在很多时候，我们却是需要目标元素的第一个和最后一个位置，才有意义。
// 举个例子来说，我们要求得目标元素在排序数组中出现的次数，假如利用一般的方法，逐个比较目标元素和数组元素，时间复杂度O（n），
// 不能够令我们满意，既然数组是排序的我们很容易想到二分查找，在这里我们能不能使用二分查找的算法呢，
// 答案是肯定的。只要我们能够利用二分查找找到目标元素出现的第一个和最后一个位置，就能够求得它出现的次数。
// 我们如何来求得目标元素出现的第一个和最后一个位置呢，其实很简单，我们只需要对于二分查找的退出条件，
// 做一个简单的设定就能得到我们理想的结果哦！
//    下面我们来看一下代码:
//
//    int GetFirstK(int *a, int _left, int _right, int dest)
//    {
//        if(_left > _right || a == NULL)
//        {
//            return 0;
//        }
//        int temp = 0;
//        int left = _left;
//        int right = _right;
//        while(left < right)
//        {
//            temp = (left + right) >> 1;
//            if(a[temp] < dest)
//            {
//                left = temp + 1;
//            }
//            else
//            {
//                right = temp;
//            }
//        }
//        return left;
//    }
//    在这里跟一般的二分查找的代码相比，仅仅是判断语句上做了一点细微的变化，序列是递增排列的，
// 当中间值小于目标元素的时候，目标元素在序列的右边：left = temp + 1；
//    其余的情况目标值在序列的左边：right = temp；我们要查找的第一个目标元素的位置，
// 一般的情况就是目标元素存在多个情况，由上述的两个判断条件，我们可以知道，如果查找到了目标元素，
// 并且该目标元素不是第一个的时候，此时left
//    去最后一次出现的位置，道理也是类似的：
//
//    int GetUpK(int *a, int _left, int _right, int dest)
//    {
//        if(_left > _right || a == NULL)
//        {
//            return 0;
//        }
//        int temp = 0;
//        int left = _left;
//        int right = _right;
//        while(left < right)
//        {
//            temp = (left + right + 1) >> 1; //保证取到中间靠后的位置
//            if(a[temp] > dest)
//            {
//                right = temp - 1;
//            }
//            else
//            {
//                left = temp;
//            }
//        }
//        return right;
//    }
//
//    大家可以看出，跟我们取第一个元素时候的判断条件恰好相反，而两种情况处理的方式我们可以归结为以下两句话：
//            1、当我们要找到目标元素出现的第一个位置时候：当中间值大于等于目标元素的时候，我们要保留当前中间值的位置，并且在左边继续查找。
//    这句话用条件语句表述就是：
//            if（a【mid】 >= dest)
//    right = mid;
//
//2、当我们要找目标元素出现的最后一个位置的时候：当中间值小于等于目标元素的时候，我们要保留中间值的位置，并且在右边继续查找。
//            if(a[mid] <= dest)
//    left = mid;

    // 感受：
    public static void main (String ...args){
//        int[] nums = {5,7,7,8,8,10};
//        int[] res = searchRange(nums,8);// [3,4]
//        System.out.println(res[0]+","+res[1]);

//        int[] nums1 = {5,7,7,8,8,10};
//        int[] res1 = searchRange(nums1,6);//[-1,-1]
//        out.println(res1[0]+","+res1[1]);

        int[] nums1 = {5,7,7,8,8};
        int[] res1 = searchRange(nums1,8);//[3,4]
        System.out.println(res1[0]+","+res1[1]);

//        int[] nums = {0,0,5,7,7,8,8};
//        int[] res = searchRange(nums,0);//[0,1]
//        System.out.println(res[0]+","+res[1]);

        int[] nums = {2,2};
        int[] res = searchRange(nums,2);//[0,1]
        System.out.println(res[0]+","+res[1]);
    }
}
