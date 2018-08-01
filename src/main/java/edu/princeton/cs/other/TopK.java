package edu.princeton.cs.other;

import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.algs4.StdRandom;

import static java.lang.System.out;

/**
 * 实例： 给定线性无序集中n个元素和一个整数k，1≤k≤n，要求找出这n个元素中第k大的元素
 * 思路： 借鉴快速排序的思想，任选一个`pivotIndex`，则`pivotValue` = `array[pivotIndex]`，
 * 经过一次划分后，`pivotValue`存储在`storeIndex`的位置，比`pivoteValue`大的在后面，比`pivotValue`小的存储在前面。
 * 那么`storeIndex`位置的`pivotValue`就肯定是第`storeIndex`大的数，如果K<storeIndex,那么说明第K大一定在左边，
 * 那么再对左边进行划分即可，右边类似。递归即可得到第K大的值。
 * @author Mageek Chiu
 */
class TopK {

    /**
        一个无序数组的 数字大小 topK 问题

     思路：二分即可
     */
    public static Comparable numberK(Comparable[] integers, int k, int left, int right) {
        int storeIndex = Quick.partitionReverse(integers,left,right);
        if (storeIndex > k){// 在左边
            return numberK(integers,k,left,storeIndex-1);
        }else if (storeIndex < k){// 在右边
            return numberK(integers,k,storeIndex+1,right);
        }else {// storeIndex == k  就是它了
            return integers[storeIndex];// pivotValue 小于storeIndex左边的，大于storeIndex右边的
        }
    }

    public static void main (String ...args){
        Integer[] integers = {11,1,2,3,12,4,5,15,6,13,7,8,14,9,10} ; int len = integers.length;
        StdRandom.shuffle(integers);//打乱数组，消除输入依赖
        int k = 5 ; Comparable res = numberK(integers,k-1,0,len-1);//第k大的数 是11， 第5大，下标是4，所以求k-1
        out.println(res);
        k = 1; res = numberK(integers,k-1,0,len-1);//第k大的数 是15， 第1大，下标是0，所以求k-1
        out.println(res);
        k = 9; res = numberK(integers,k-1,0,len-1);//第k大的数 是7， 第9大，下标是8，所以求k-1
        out.println(res);
    }

    /**
     两个大小分别为n和m的有序的数组，找出这两个数组放在一起后第k大的数。

     思路1：归并的思路，此时复杂度O(k)，k很大时很耗费时间
     思路2：二分，最优
     */
//    public static Comparable numberK(int[]a,int[]b,int k) {
//
//    }


    // 在大规模数据处理中，经常会遇到的一类问题：在海量数据中找出出现频率最好的前k个数，或者从海量数据中找出最大的前k个数，
    // 这类问题通常被称为top K问题。例如，在搜索引擎中，统计搜索最热门的10个查询词；在歌曲库中统计下载最高的前10首歌等，游戏玩家排名前十的榜单等
    // 针对top K类问题，通常比较好的方案是分治+Trie树/hash+小顶堆（就是上面提到的最小堆），
    // 即先将数据集按照Hash方法分解成多个小数据集，然后使用Trie树或者Hash统计每个小数据集中的query词频，
    // 之后用小顶堆求出每个数据集中出现频率最高（大小最大）的前K个数，最后在所有top K中求出最终的top K。

    // 有1亿个浮点数，如果找出其中最大的10000个？
    // 第一种方法最容易想到的方法是将数据全部排序，然后在排序后的集合中进行查找，最快的排序算法的时间复杂度一般为O（nlogn）
    // 第二种方法为局部淘汰法，该方法与排序方法类似，用一个容器保存前10000个数，然后将剩余的所有数字——与容器内的最小数字相比，
    // 如果所有后续的元素都比容器内的10000个数还小，那么容器内这个10000个数就是最大10000个数。
    // 如果某一后续元素比容器内最小数字大，则删掉容器内最小元素，并将该元素插入容器，最后遍历完这1亿个数，得到的结果容器中保存的数即为最终结果了。
    // 此时的时间复杂度为O（n+m^2），其中m为容器的大小，可以把这个容器优化为小顶堆 O（n+mlogm）
    // 第三种方法是分治法，将1亿个数据分成100份，每份100万个数据，找到每份数据中最大的10000个，最后在剩下的100*10000个数据里面找出最大的10000个。
    // 第四种方法是Hash法。如果这1亿个书里面有很多重复的数，先通过Hash法，把这1亿个数字去重复，这样如果重复率很高的话，
    // 会减少很大的内存用量，从而缩小运算空间，然后通过分治法或最小堆法查找最大的10000个数。

    // 实际上，最优的解决方案应该是最符合实际设计需求的方案，在时间应用中，可能有足够大的内存，
    // 那么直接将数据扔到内存中一次性处理即可，也可能机器有多个核，这样可以采用多线程处理整个数据集。
    // top K问题很适合采用MapReduce框架解决，用户只需编写一个Map函数和两个Reduce 函数，然后提交到Hadoop（采用Mapchain和Reducechain）上即可解决该问题。
    // 具体而言，就是首先根据数据值或者把数据hash(MD5)后的值按照范围划分到不同的机器上，最好可以让数据划分后一次读入内存，
    // 这样不同的机器负责处理不同的数值范围，实际上就是Map。得到结果后，各个机器只需拿出各自出现次数最多的前N个数据，然后汇总，
    // 选出所有的数据中出现次数最多的前N个数据，这实际上就是Reduce过程。对于Map函数，采用Hash算法，将Hash值相同的数据交给同一个Reduce task；
    // 对于第一个Reduce函数，采用HashMap统计出每个词出现的频率，对于第二个Reduce 函数，统计所有Reduce task，输出数据中的top K即可。
    //


    // 在2.5亿个正整数中找出不重复的整数。
    // 思路一：
    // 分治法 + HashMap (HashMap 不要局限在 Java 语言)
    // 将 2.5 亿个整数，分批操作，例如分成 250 万一批，共100批次。每批使用循环遍历一次，存入 HashMap<int1,int2> 里面，
    // int1 对应这个数，int2 对应它出现的次数，没出现就默认是 1 次。每操作完一批，就进行当前的 HashMap 的去重操作，读出 int2 > 1 的，排除掉。
    // 接下来的批次，以此类推，得出 100，剩下的自然就是不重复的。
    // 思路二： 位图法 Bitmap(一个 bit 仅会是 0 或 1)  对于此题，我们可以设计每两个 bit 位，标示一个数的出现情况。
    // 00表示没有出现，01表示出现一次，10表示出现多次。
    // 2.5 亿个正整数，首先我们要知道是正整数，我们就不需要考虑负数，也就是无符号，无符号的整形占四个字节。

    /**
     很多个数字，可能有几十亿个，找出其中最大的500个
     这是 大小topK

     思路：小顶堆，复杂度是O(n*lg500)
     先拿500个数建堆，然后一次添加剩余元素，如果大于堆顶的数（500中最小的），将这个数替换堆顶，并调整结构使之仍然是一个最小堆，
     这样，遍历完后，堆中的500个数就是所需的最大的10000个。建堆时间复杂度是O（mlogm），算法的时间复杂度为O（nmlogm）（n为几十亿，m为500）。

     优化的方法：可以把所有10亿个数据分组存放，比如分别放在2000个文件中。
     这样处理就可以分别在每个文件的数据中找出最大的500个数，合并到一起在再找出最终的结果。
     */
    public static int[] topK(int[]a,int k) {
        int[] result = new int[k];

        return result;
    }

    /**
     很多个数字，可能有几十亿个，找出其中出现次数最大的500个
     这是 频率topK

     思路1：用hash分在不同机器上分别统计每台机器的topK
     然后在所有机器的tok中选择最终的topK
     这个思路适合批处理数据

     思路2：用小顶堆,还是先用hash 求出次数，然后用小顶堆比较次数
     这个思路适合实时更新的数据
     */
    public static int[] topFK(int[]a,int k) {
        int[] result = new int[k];

        return result;
    }


    /**
     * 一亿个数据排序
     * 一亿条数据的排序处理 - CSDN博客: https://blog.csdn.net/sun_xp_1988/article/details/51448185
     * 分而治之，最后归并
     * 1000万可以直接排序，一亿就要分成10份
     */



}
