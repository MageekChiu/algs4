package edu.princeton.cs.other;

/**

 你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。
 由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。

 你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。
 实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。

 示例:
 给定 n = 5，并且 version = 4 是第一个错误的版本。
 调用 isBadVersion(3) -> false
 调用 isBadVersion(5) -> true
 调用 isBadVersion(4) -> true

 所以，4 是第一个错误的版本。

 * @author Mageek Chiu
 */
class FirstBadVersion {

    public static int firstBadVersion(int n) {
        int l = 1,r = n,m;
        while (l<=r){
//            m = (l+r)/2;// 这样可能溢出
            m = l+(r-l)/2;// 这样不会溢出
            if (m==1 && isBadVersion(1) ) return 1;
            if (isBadVersion(m)&& !isBadVersion(m-1)){
                return m;
            }else if (isBadVersion(m)&&isBadVersion(m-1)){
                r = m-1;
            }else {
                l = m+1;
            }
        }
        return r;
    }

    public static  boolean isBadVersion(int n){
//        if ( n >= 2 ) return true;
        if ( n >= 1702766719 ) return true;
        return false;
    }

    // 感悟：二分查找的各种变形，首尾之类的
    public static void main(String... args){
//        System.out.println(firstBadVersion(2));
        System.out.println(firstBadVersion(2126753390));
    }

}
