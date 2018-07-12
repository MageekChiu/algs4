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
