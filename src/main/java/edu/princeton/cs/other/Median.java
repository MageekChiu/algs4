package edu.princeton.cs.other;


import static java.lang.System.out;

/**
 * 有两个大小为 m 和 n 的排序数组 nums1 和 nums2 。
 请找出两个排序数组的中位数并且总的运行时间复杂度为 O(log (m+n)) 。
 示例 1:
 nums1 = [1, 3]
 nums2 = [2]
 中位数是 2.0

 示例 2:
 nums1 = [1, 2]
 nums2 = [3, 4]
 中位数是 (2 + 3)/2 = 2.5
 *
 * @author Mageek Chiu
 */
class Median {

    /**
     * 题目要求O(log(m+n))的时间复杂度，一般来说都是分治法或者二分搜索。首先我们先分析下题目，假设两个有序序列共有n个元素
     * 根据中位数的定义我们要分两种情况考虑，当n为奇数时，搜寻第(n/2+1)个元素，当n为偶数时，搜寻第(n/2+1)和第(n/2)个元素，
     * 然后取他们的均值。
     * 进一步的，我们可以把这题抽象为“搜索两个有序序列的第k个元素”。
     * 如果我们解决了这个k元素问题，那中位数不过是k的取值不同罢了。

     对于单个有序数组，找其中的第k个元素特别好做，我们用割的思想就是：
     我们通过切一刀，能够把有序数组分成左右两个部分，切的那一刀就被称为割(Cut)，割的左右会有两个元素，
     分别是左边最大值和右边最小值。
     我们定义L = Max(LeftPart)，R = Min(RightPart)
     Ps. 割可以割在两个数中间，也可以割在1个数上，如果割在一个数上，那么这个数即属于左边，也属于右边。
     如果在k的位置割一下，然后A[k]就是L。换言之，就是如果左侧有k个元素，A[k]属于左边部分的最大值。
     双数组
     我们设:
     Ci为第i个数组的割。
     Li为第i个数组割后的左元素.
     Ri为第i个数组割后的右元素。

     如何从双数组里取出第k个元素：
        首先Li<=Ri是肯定的（因为数组有序，左边肯定小于右边）
        如果我们让L1<=R2 && L2<=R1
     那么左半边 全小于右半边，如果左边的元素个数相加刚好等于k，那么第k个元素就是Max(L1,L2)。
     如果 L1>R2，说明数组1的左边元素太大（多），我们把C1减小，把C2增大。L2>R1同理，把C1增大，C2减小。

     假设k=3
     对于
     [1 4 7 9]
     [2 3 5]
     设C1 = 2，那么C2 = k-C1 = 1
     [1 4/7 9]
     [2/3 5]

     这时候，L1(4)>R2(3)，说明C1要减小，C2要增大，C1 = 1，C2=k-C1 = 2
     [1/4 7 9]
     [2 3/5]

     这时候，满足了L1<=R2 && L2<=R1，第3个元素就是Max(1,3) = 3。
     如果对于上面的例子，把k改成4就恰好是中值。

     分治的思路
     有了上面的知识后，现在的问题就是如何利用分治的思想。
     怎么分？
     最快的分的方案是二分，有2个数组，我们对哪个做二分呢？
     根据之前的分析，我们知道了，只要C1或C2确定，另外一个也就确定了。这里，
     为了效率，我们肯定是选长度较短的做二分，假设为C1。

     怎么治？
     也比较简单，我们之前分析了：就是比较L1,L2和R1,R2。
     - L1>R2，把C1减小，C2增大。—> C1向左二分
     - L2>R1，把C1增大，C2减小。—> C1向右二分

     越界问题
     如果C1或C2已经到头了怎么办？
     这种情况出现在：如果有个数组完全小于或大于中值。可能有4种情况：
     - C1 = 0 —— 数组1整体都比中值大，L1 >R2，中值在2中
     - C2 = 0 —— 数组1整体都比中值小，L1

     */
    private static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int k = (m + n) / 2;
        if((m+n)%2==0){// 长度和为偶数，要求均值
            return (findKth(nums1,nums2,0,0,m,n,k)+findKth(nums1,nums2,0,0,m,n,k+1))/2;
        }   else {// 长度和为奇数，直接求值
            return findKth(nums1,nums2,0,0,m,n,k+1);
        }
    }

    private static double findKth(int[] arr1, int[] arr2, int start1, int start2, int len1, int len2, int k){
        // 保证arr1是较短的数组
        if(len1>len2){
            return findKth(arr2,arr1,start2,start1,len2,len1,k);
        }
        // 到这已经有 arr1 长度小于 arr2 了，看看是不是已经被排除成空的了
        if(len1==0){
            return arr2[start2 + k - 1];
        }
        if(k==1){
            return Math.min(arr1[start1],arr2[start2]);
        }
        int c1 = Math.min(k/2,len1) ;// arr1的割,可以定的随意一点
        int c2 = k - c1;// arr2的割
        if(arr1[start1 + c1-1]<arr2[start2 + c2-1]){// arr1的割小于arr2的割，
            return findKth(arr1,arr2,start1 + c1,start2,len1-c1,len2,k-c1);// 排除掉 arr1的c1个数
        } else if(arr1[start1 + c1-1]>arr2[start2 + c2-1]){ // arr1的割大于arr2的割，
            return findKth(arr1,arr2,start1,start2 + c2,len1,len2-c2,k-c2);// 排除掉 arr2的c2个数
        } else {// 相等随便挑一个返回
            return arr1[start1 + c1-1];
        }
    }

    protected void pr(){
        p1();
        out.print("parentpr");
    }


    protected void p1(){
        out.print("parentp1");
    }

    // 感受：归一化的能力亦即把中位数转化为number k问题，然后就是分治的思想，有序的列表都很容易使用分治的思想
    public static void main (String ...args){
        int[] nums = {1,3};
        int[] nums1 = {2,4,6,8,9};
        out.println( findMedianSortedArrays(nums,nums1));//4.0
//        int[] nums2 = {1,2};
//        int[] nums3 = {3,4};
//        out.println( findMedianSortedArrays(nums2,nums3));// 2.5
    }
}
