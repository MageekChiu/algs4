package edu.princeton.cs.other;

import java.util.Arrays;

import static java.lang.System.out;

/**
 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 说明：
    你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 示例 1:
 输入: [2,2,1]
 输出: 1
 示例 2:
 输入: [4,1,2,1,2]
 输出: 4

 思路，基本的就是hashmap 遍历一遍即可，但是会消耗 n 的空间
       计数排序也会消耗 额外空间
       类似可以考虑bitmap，但是这个数组可能由负数


 不使用额外空间有两种思想，一种是确实只需要O(1)的空间，还有一种是可以重复利用本身的空间。

 再考虑一下hashMap,出现一个合法的马上清除的话效果较好，但是最坏也是 n/2 的空间

 利用 A^B^B = A 和 A^0 = A 的性质

 顺便归纳一下位运算
 与，A & 1 = A
 或，A | 0 = A   这里 1、0 要有对应位数
 异或，可以理解为不进位加法 A ^  A = 0，A ^ 0 = A   这里 1、0 随意位数
 非，逐位取反 ~A

 -----------------------------------------------------------------------------
 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
 说明：
 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 示例 1:
   输入: [2,2,3,2]
   输出: 3
 示例 2:
   输入: [0,1,0,1,0,1,99]
   输出: 99

 思路：如果数组中的元素都是三个三个出现的，那么从二进制表示的角度，每个位上的1加起来，应该可以整除3。
 如果有一个数x只出现一次，会是什么情况呢？
 如果某个特定位上的1加起来，可以被3整除，说明对应x的那位是0，因为如果是1，不可能被3整除
 如果某个特定位上的1加起来，不可以被3整除，说明对应x的那位是1
 根据上面的描述，我们可以开辟一个大小为32的数组，第0个元素表示，A中所有元素的二进制表示的最低位的和，依次类推。
 最后，再转换为十进制数即可。这里要说明的是，用一个大小为32的整数数组表示，同样空间是O(1)的。

 https://www.cnblogs.com/youxin/p/3349834.html

 思路2：其他元素都出现了三次，按位计算每一位上1的个数，结果模3为1的那些位就是所求数二进制1所在的位。
 int result = 0;
 for(int i=0;i<32;i++){
     int mask = 1<<i;
     int count = 0;
     for(int j=0;j<nums.length;j++){
        if((mask&nums[j])!=0)
            count++;
     }
     if(count%3==1)
        result = mask|result;// 或在这里可以理解为加法
 }
 return result;

 归纳一下左移右移
 value << num
 　　num 指定要移位值value 移动的位数。
 　　左移的规则只记住一点：丢弃最高位，0补最低位
 　　如果移动的位数超过了该类型的最大位数，那么编译器会对移动的位数取模。如对int型移动33位，实际上只移动了1位。
 在数字没有溢出的前提下，对于正数和负数，左移一位都相当于乘以2的1次方，左移n位就相当于乘以2的n次方

 value >> num
 　　num 指定要移位值value 移动的位数。
 　　右移的规则只记住一点：符号位不变，左边补上符号位
 右移一位相当于除2，右移n位相当于除以2的n次方。

 value >>> num
 　　num 指定要移位值value 移动的位数。
 　　无符号右移的规则只记住一点：忽略了符号位扩展，0补最高位
 　 无符号右移规则和右移运算是一样的，只是填充时不管左边的数字是正是负都用0来填充，无符号右移运算只针对负数计算，因为对于正数来说这种运算没有意义
 -----------------------------------------------------------------------------

 给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。
 示例 :
 输入: [1,2,1,3,2,5]
 输出: [3,5]
 注意：
     结果输出的顺序并不重要，对于上面的例子， [5, 3] 也是正确答案。
     你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？

 思路：这道题主要和Ⅰ类似，但是这里有两个不同的数，所以需要从这两个数的异或值反推得到结果。
 整数的二进制在java中是补码表示，所以我们可以用n&-n得到n的二进制最右边的一个1。
 这样对于这个为1的位置，肯定可以分辨出这两个数，因为一定有两个数在这个位置1个为1，另一个为0。
 所以遍历整个数组，和这个数做&操作，就可以将两个数分开到两组中。因为其他出现两次的数，异或操作还是为0，对结果没有影响，
 所以对每个遍历到的数做异或操作即可。

 理解：
 还是先将全部数异或一下，得到的结果n就是我们要的两个数亦或的结果。
 然后反推这两个数。n&-n(不好理解的话可以换为n & ~(n-1)理解)得到两个数不同的最低位。
 接着将全部数分成两个组，一组为该位上是0的，另一组为该位上是1的。把两组分别组内亦或，就可以得到我们要的两个数。

 * @author Mageek Chiu
 */
class SingleNumber {
    public static int singleNumber(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            ans ^=num;
        }
        return ans;
    }

    public static int singleNumber1(int[] nums) {
        int[] bits = new int[32];
        Arrays.fill(bits,0);//每 1 bit 出现次数初始化为0
        for(int i=0;i<32;i++){
            for (int num : nums) {
                int bit = (num >> i) & 1;//找到num[j]的第i位，也就是右移，和1 与才能只保留这1 bit
                bits[i] += bit;
            }
        }
        int result=0;
        for(int i=0;i<32;i++){
            if(bits[i]%3!=0)
                result += Math.pow(2,i);//复原这个数
        }
        return  result;
    }

    public static int[] singleNumber2(int[] nums) {
        int sum = 0;    //记录所有异或的值，即两个只出现一次数的异或
        for (int num : nums) {
            sum ^= num;
        }

        int[] res = new int[2];
        sum &= -sum;    //得出两个数异或结果的最右边的一个1，其他的为零，这样进行&操作就可以将两个不同的数分到不同的两组去
        for (int num : nums) {
            if ((sum & num) == 0)
                res[0] ^= num;
            else
                res[1] ^= num;
        }
        return res;
    }
//    public static int singleNumber(int[] nums) {
//        BitSet bitSet = new BitSet();//按需增长
//        for (int num : nums) {
//            if(bitSet.get(num)){//第二次出现
//                bitSet.set(num,false);
//            }else {
//                bitSet.set(num,true);//第一次出现
//            }
//        }
//        // 为 true的只出现了1次
//        for (int i= 0;i<bitSet.length();i++){
//            if (bitSet.get(i)){
//                return i;
//            }
//        }
//        return -1;
//    }

    // 感受：
    public static void main (String ...args){
//        out.println(singleNumber(new int[]{4,1,2,1,2}));//4
//        out.println(singleNumber(new int[]{2,2,1}));//1
//        out.println(singleNumber(new int[]{-1}));//-1
//        out.println(singleNumber(new int[]{2,2,1,1,10,5,10,5,6,2,2}));//6
        out.println(singleNumber1(new int[]{2,2,2,1,1,1,10,10,5,10,5,5,6}));//6
        out.println(singleNumber1(new int[]{2,2,2,1}));//1
        out.println(singleNumber1(new int[]{2,2,2,15,1,4,12,1,12,3,4,12,1,3,4,3}));//15

    }
}
