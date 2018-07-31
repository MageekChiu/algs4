package edu.princeton.cs.other;

import java.util.LinkedList;
import java.util.List;

import static java.lang.System.out;

/**
 字典树相关
 */
public class Dict {
    // 1到n按照字典序输出，如1到12输出是：1, 10, 11, 12, 2, 3, 4, 5, 6, 7,8, 9
    // 刚开始思路是dfs
    // 既然是字典序，那么很自然，我们可以考虑使用字典树来实现，
    // 但是，这里并不需要真的生成这个字典树，而只需要计算对应分支的节点数就行了。（但是要想象自己已经建立了Trie树）
    // 计算分支节点数，那么很简单，节点数就是上级节点*10，总的节点数= 1 + （1 * 10） + （1 * 10 * 10） + （1 * 10  * 10 * 10） +……，
    // 这里需要注意最后的边界，n以内的节点数，那么，最后相加的时候必须要把n+1 ~ (1 * 10 * 10 *……)这几个数去掉。
    // 既然知道了如何计算字典树分支的节点数，要想知道第m个数是什么，那么也就是找第m个节点，
    // 首先从1开始，如果1分支的节点数>m，那么第m个数肯定是以1开头，进一步搜索其子节点，搜索子节点时不用再搜索1了，所以是搜索1分支的第m-1个节点。
    // 如果1分支的节点数<m， 那么所查找的数肯定不是1开头，那么开始搜索2分支，在2分支中，所要找的数应该是第（m-（1分支节点数））个数。

    public static List<Integer> dict(int n,int m){
        List<Integer> res = new LinkedList<>();

        long ret = 1;
        while(m != 0) {
            long cntOfChild = getCntOfChild(ret, n);
            if(cntOfChild >= m) {
                //当子节点数大于等于m时，第m个数就在子节点中寻找，res*10为子节点的第一个，m递减直到m=0就找到了那个数
                m --;
                if(m == 0)	break;
                ret = ret * 10;
            } else {
                //当子节点数小于m时，第m个数就要在右边的节点中寻找子节点，m减掉当前子节点数，结果加1就到了右边相邻的节点
                m -= cntOfChild;
                ret ++;
            }
        }
        System.out.println(ret);
        return res;
    }

    // 找到小于n的，以ret开头的树的个数
    // 就是遍历trie数到ret的时候的子数中节点的个数
    private static long getCntOfChild(long ret, long n) {
        long sum = 1, t = 10;
        for(; ret * t <=n; t *= 10) {
            if(ret * t + t - 1 <= n)
                sum += t;
            else
                sum += n - ret * t + 1;
        }
        return sum;
    }

// 给n个数字，求所有两两组合中异或和最大的值。这是一道经典的字典树问题，
// 我首先答了O(n2)的暴力算法，然后装作思考一阵并略有所悟的样子回答了字典树的思路，并说明了其复杂的为O(nlgn)。
// 然后面试官要求开始写代码，由于长时间没有手写字典树，写的磕磕绊绊，最后把大概思路算是基本写出来了。




    public static void main(String[] args) throws Exception {
//        out.println(dict(12));
//        out.println(dict(66));
        out.println(dict(100,100));
//        out.println(dict(12,3));
//        out.println(dict(8));
    }
}