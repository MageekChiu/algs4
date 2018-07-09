package edu.princeton.cs.other;

import java.util.*;

/**

 2SUM/3SUM/4SUM问题 - CSDN博客: https://blog.csdn.net/haolexiao/article/details/70768526
 求和问题总结(2Sum, 3Sum, 4Sum, K Sum) - 算法与数据结构知识库: http://lib.csdn.net/article/datastructure/13655

 nSUM问题是指，在一个数组中，找出n个数相加和等于给定的数，这个叫做nSUM问题。
 常见的有2SUM，3SUM，4SUM问题，还有各种SUM问题的变种.
 Leetcode上SUM问题包括：
 1. 2SUM
 15. 3Sum
 16. 3Sum Closest
 18. 4Sum
 454. 4Sum II

 2SUM问题
 最常见的是2SUM问题（1. 2SUM），就是数组中找出两个数相加和为给定的数。
 最简单的办法是用两个for循环，如果num[i]+num[j]==target，就得到了一个解。时间复杂度是O(N^2)。
 这道题有两种优化思路：
 1. 一种思路先排序，从首尾搜索两个数的和，并逐步逼近。
 2. 另外一种思路是固定一个数A，看SUM-A是否在这个数组之中。

 对于第一种思路如下：
 此方法是先将数组从小到大排序
 设置两个指针，一个指向数组开头，一个指向数组结尾，从两边往中间走。直到扫到满足题意的为止或者两个指针相遇为止。
 此时这种搜索方法就是类似于杨氏矩阵的搜索方法，就是从 杨氏矩阵的左下角开始搜索，直到找到为止。
 如果此时题目条件变为如果没有找出最接近的2SUM和问题，解决方法跟上面是一样的
 用这种方法2SUM问题的时间复杂度是O(nlogn)的，主要在于排序的时间。

 int j = 0, k = n - 1;
 while(j < k) {
     if(arr[j] + arr[k] == target) {
     res.push_back(arr[j]);
     res.push_back(arr[k]);
         ++j;
         --k;
    }
     else if(arr[j] + arr[k] < target) {
        ++j;
     }
     else {
        --k;
     }
 }
 上面的代码参考编程

 第二种思路方法如下：
 对数组中的每个数建立一个map/hash_map 然后再扫一遍这个数组，判断target-nums[i]是否存在，如果存在则说明有，
 不存在继续找。当然这样做的话，需要处理一个细节：判重的问题。

 第一种方法的思路还是比较好的，鲁棒性好，而且写起来比较容易，但是因为预处理——排序的时间复杂度占了大头，
 所以其总时间复杂度为O(nlogn)
 第二种方法，时间复杂度低，但是需要处理重复情况，略麻烦

 Hash解法(Other)：
 其实比如2sum还是有线性解法的，就是用hashmap, 这样你check某个值存在不存在就是常数时间，那么给定一个sum, 只要线性扫描,
 对每一个number判断sum – num存在不存在就可以了。注意这个算法对有重复元素的序列也是适用的。
 比如 2 3 3 4 那么hashtable可以使 hash(2) = 1; hash(3) = 1, hash(4) =1其他都是0,  那么check的时候，
 扫到两次3都是check sum – 3在不在hashtable中，注意最后返回所有符合的pair的时候也还是要去重。
 这样子推广的话 3sum 其实也有O(N^2)的类似hash算法，这点和之前是没有提高的，但是4sum就会有更快的一个算法。
 4sum的hash算法：
 O(N^2)把所有pair存入hash表，并且每个hash值下面可以跟一个list做成map， map[hashvalue] = list，
 每个list中的元素就是一个pair, 这个pair的和就是这个hash值，那么接下来求4sum就变成了在所有的pair value中求 2sum，
 这个就成了线性算法了，注意这里的线性又是针对pair数量(N^2)的线性，所以整体上这个算法是O(N^2)，而且因为我们挂了list,
 所以只要符合4sum的我们都可以找到对应的是哪四个数字。
 关于hash的解法我研究还不是很多，以后要是有更深入的研究再更新。
 这篇文章主要想从一般的K sum问题的角度总结那些比较经典的求和问题比如leetcode里面的2sum, 3sum(closest), 4sum等问题,
 文章先直接给出K Sum的问题描述和算法(递归解法), 然后将这个一般性的方法套用到具体的K, 比如leetcode中的2Sum, 3Sum, 4Sum问题.
 同时我们也给出另一种哈希算法的讨论. 那么这篇文章基本上还是自己想到什么写什么，有疏忽不对的地方请大家指正，


 3SUM问题
 然后对于3 Sum问题，解决方法就是最外层遍历一遍，等于选出一个数，
 之后的数组中转化为找和为target-nums[i]的2SUM问题。
 因为此时排序复杂度在3SUM问题中已经不占据主要复杂度了，所以直接采用思路1的方法就好。

 注意比如3sum的时候，先整体排一次序，然后枚举第三个数字的时候不需要重复， 比如排好序以后的数字是 a b c d e f,
 那么第一次枚举a, 在剩下的b c d e f中进行2 sum,target1=target0-a, 完了以后第二次枚举b, 只需要在 c d e f中进行2sum好了，
 而不是在a c d e f中进行2sum, 这个大家可以自己体会一下，想通了还是挺有帮助的。
 K Sum可以写一个递归程序很优雅的解决，具体大家可以自己试一试。写递归的时候注意不要重复排序就行了。

 对于3SUM问题，上面这个解法的时间复杂度为O(n2)
 其实对于3SUM问题，另外一种求解思路就是，先预处理一遍数组中两两相加的结果，然后再遍历每一个数nums[i]，
 判断target-nums[i]是否在预处理的那个和中，不过这种方法的复杂度也是O(n2)主要是预处理的复杂度。


 4SUM问题
 对于4Sum问题又衍生出了两种思路：
 1. 先遍历第一个数，然后固定第一个数之后，转化为剩下元素的3SUM问题
 2. 先遍历两个数，求和，然后转化为剩下元素的2SUM问题

 第一种思路
 其算法复杂度是稳定的O(n3)，最外层遍历一遍O(n)，然后转为3SUM问题之后又是O(n2)。
 这种方法相当于4SUM调用3SUM，然后3SUM再调用2SUM，这样函数调用有点多，不方便
 具体写出来的形式，可以写成最外层两个循环，即固定为两个数之后，再化为2SUM。

 第二种思路
 因为本质上我们是最外层两个循环之后，找是否有target-now的值，我们可以事先做好预处理，
 即空间换时间，先循环两次，找出两个数所有可能的和，存到map里（这里可以unordered_map）。
 这两等于是两个O(n2)的时间复杂度相加和，所以最后时间复杂度为O(n2)；
 但是此时需要有一个判重的问题，所以需要map中存如下数
 mp[num[i]+num[j]].push_back(make_pair(i,j));
 然后再判重。

 因为需要判重，所以最糟糕情况下其时间复杂度为O(n4)
 那么如果没有判重问题，是否就可以解决了呢？
 那就是454. 4Sum II问题
 这道题是在四个数组中，各选出一个数，使其和为某一定值。
 则可以按照上述方法，讲前两个数组所有可能的和做一个map，然后遍历后两个数组所有可能的和，所以这个是O(n2)的算法。

 nSUM问题的推广和复杂的分析
 按照上面的算法复杂度思路，4~6SUM问题，都可以用O(n3)来解决。

 比如对于6SUM问题，先用一个O(n3)的方法，将其中所有三个数相加的可能的情况都保存下来，这一步的时间复杂度是O(n3)。
 接下来用两种方法都行：
 1. 遍历三个数，然后看剩下的和是否在保存的可能性中。这一步的时间复杂度是O(n3)
 2. 直接在保存的可能性中遍历，遍历到SUM1之后，看target-SUM1是否也在这个可能性中。这一步的时间复杂度是O(n2)






 K Sum求解方法, 适用leetcode 2Sum, 3Sum, 4Sum：
 方法一： 暴力，就是枚举所有的K-subset, 那么这样的复杂度就是 从N选出K个，复杂度是O(N^K)

 方法二： 排序，这个算法可以考虑最简单的case, 2sum，这是个经典问题，方法就是先排序，
 然后利用头尾指针找到两个数使得他们的和等于target, 这个2sum算法网上一搜就有，这里不赘述了，给出2sum的核心代码：

 //2 sum
 int i = starting; //头指针
 int j = num.size() - 1; //尾指针
 while(i &lt; j) {
 int sum = num[i] + num[j];
 if(sum == target) {
 store num[i] and num[j] somewhere;
 if(we need only one such pair of numbers)
 break;
 otherwise
 do ++i, --j;
 }
 else if(sum &lt; target)
 ++i;
 else
 --j;
 }
 2sum的算法复杂度是O(N log N) 因为排序用了N log N以及头尾指针的搜索是线性的，所以总体是O(N log N)，
 好了现在考虑3sum, 有了2sum其实3sum就不难了，这样想：先取出一个数，那么我只要在剩下的数字里面找到两个数字使得
 他们的和等于(target – 那个取出的数)就可以了吧。所以3sum就退化成了2sum, 取出一个数字，这样的数字有N个，
 所以3sum的算法复杂度就是O(N^2 ), 注意这里复杂度是N平方，因为你排序只需要排一次，后面的工作都是取出一个数字，
 然后找剩下的两个数字，找两个数字是2sum用头尾指针线性扫,是Q(N)的，这里很容易错误的将复杂度算成O(N^2 log N)，这个是不对的。
 我们继续的话4sum也就可以退化成3sum问题(copyright @sigmainfy)，那么以此类推，K-sum一步一步退化，
 最后也就是解决一个2sum的问题，K sum的复杂度是O(n^(K-1))。 这个界好像是最好的界了，
 也就是K-sum问题最好也就能做到O(n^(K-1))复杂度，之前有看到过有人说可以严格数学证明，这里就不深入研究了。

 更新: 感谢网友Hatch提供他的K Sum源代码, 经供参考:


 class Solution {
 public:
 vector&lt; vector &gt; findZeroSumInSortedArr(vector &num, int begin, int count, int target)
 {
 vector ret;
 vector tuple;
 set visited;
 if (count == 2)
 {
 int i = begin, j = num.size()-1;
 while (i &lt; j)
 {
 int sum = num[i] + num[j];
 if (sum == target && visited.find(num[i]) == visited.end())
 {
 tuple.clear();
 visited.insert(num[i]);
 visited.insert(num[j]);
 tuple.push_back(num[i]);
 tuple.push_back(num[j]);
 ret.push_back(tuple);
 i++; j–;
 }
 else if (sum &lt; target)
 {
 i++;
 }
 else
 {
 j–;
 }
 }
 }
 else
 {
 for (int i=begin; i&lt;num.size(); i++)
 {
 if (visited.find(num[i]) == visited.end())
 {
 visited.insert(num[i]);
 vector subRet = findZeroSumInSortedArr(num, i+1, count-1, target-num[i]);
 if (!subRet.empty())
 {
 for (int j=0; j&lt;subRet.size(); j++)
 {
 subRet[j].insert(subRet[j].begin(), num[i]);
 }

 ret.insert(ret.end(), subRet.begin(), subRet.end());
 }
 }
 }
 }

 return ret;
 }

 vector threeSum(vector &num) {
 sort(num.begin(), num.end());
 return findZeroSumInSortedArr(num, 0, 3, 0);
 }

 vector fourSum(vector &num, int target) {
 sort(num.begin(), num.end());
 return findZeroSumInSortedArr(num, 0, 4, target);
 }
 };
 K Sum (2Sum, 3Sum, 4Sum) 算法优化(Optimization)：
 这里讲两点，第一，注意比如3sum的时候，先整体排一次序，然后枚举第三个数字的时候不需要重复，
 比如排好序以后的数字是 a b c d e f, 那么第一次枚举a, 在剩下的b c d e f中进行2 sum, 完了以后第二次枚举b,
 只需要在 c d e f中进行2sum好了，而不是在a c d e f中进行2sum, 这个大家可以自己体会一下，想通了还是挺有帮助的。
 第二，K Sum可以写一个递归程序很优雅的解决，具体大家可以自己试一试。写递归的时候注意不要重复排序就行了。

 K Sum (2Sum, 3Sum, 4Sum) 算法之3sum源代码(不使用std::set)和相关开放问题讨论：
 因为已经收到好几个网友的邮件需要3sum的源代码, 那么还是贴一下吧, 下面的代码是可以通过leetcode OJ的代码
 (又重新写了一遍, 于Jan, 11, 2014 Accepted), 就当是K sum的完整的一个case study吧, 顺便解释一下上面的排序这个注意点,
 同时我也有关于结果去重的问题可以和大家讨论一下, 也请大家集思广益, 发表意见, 首先看源代码如下：


 class Solution {
 public:
 vector threeSum(vector &num) {
 vector vecResult;
 if(num.size() &lt; 3)
 return vecResult;

 vector vecTriple(3, 0);
 sort(num.begin(), num.end());
 int iCurrentValue = num[0];
 int iCount = num.size() - 2; // (1) trick 1
 for(int i = 0; i &lt; iCount; ++i) {
 if(i && num[i] == iCurrentValue) { // (2) trick 2: trying to avoid repeating triples
 continue;
 }
 // do 2 sum
 vecTriple[0] = num[i];
 int j = i + 1;
 int k = num.size() - 1;
 while(j &lt; k) {
 int iSum = num[j] + num[k];
 if(iSum + vecTriple[0] == 0) {
 vecTriple[1] = num[j];
 vecTriple[2] = num[k];
 vecResult.push_back(vecTriple); // copy constructor
 ++j;
 --k;
 }
 else if(iSum + vecTriple[0] &lt; 0)
 ++j;
 else
 --k;
 }
 iCurrentValue = num[i];
 }
 // trick 3: indeed remove all repeated triplets
 // trick 4: already sorted, no need to sort the triplets at all, think about why?
 vector&lt; vector &gt;::iterator it = unique(vecResult.begin(), vecResult.end());
 vecResult.resize( distance(vecResult.begin(), it) );
 return vecResult;
 }
 };
 首先呢, 在K Sum问题中都有个结果去重的问题, 前文也说了, 如果输入中就有重复元素的话, 最后结果都需要去重,
 去重有好几个办法, 可以利用std::set的性质(如leetcode上3sum的文章, 但是他那个文章的问题是, set没用好,
 导致最终复杂度其实是O(N^2 * log N), 而非真正的O(N^2) ), 可以利用排序(如本文的方法)等, 去重本身不难(copyright @sigmainfy),
 难的是不利用任何排序或者std::set直接得到没有重复的triplet结果集. 本人试图通过已经排好序这个性质来做到这一点
 (试图不用trick 3和4下面的两条语句),　但是经过验证这样的代码(没有trick 3, 4下面的两行代码, 直接return vecResult)
 也不能保证结果没有重复，于是不得不加上了trick 3, 4，还是需要通过在结果集上进一步去重. 笔者对于这个问题一直没有很好的想法,
 希望这里的代码能抛砖引玉, 大家也讨论一下有没有办法, 或者利用排序的性质或者利用其它方法, 直接得到没有重复元素的triplet结果集,
 不需要去重这个步骤.

 那么还是解释一下源代码里面有四个trick, 以及笔者试图不利用任何std::set或者排序而做到去重的想法. 第一个无关紧要顺带的小trick 1
 , 是说我们排好序以后, 只需要检测到倒数第三个数字就行了, 因为剩下的只有一种triplet 由最后三个数字组成.
 接下来三个trick都是和排序以及最后结果的去重问题有关的, 我一起说.

 笔者为了达到不需要在最后的结果集做额外的去重, 尝试了以下努力: 首先对输入数组整体排序, 然后使用之前提到的3sum的算法,
 每次按照顺序先定下triplet的第一个数字, 然后在数组后面寻找2sum, 由于已经排好序, 为了防止重复, 我们要保证triplet的第一个数字
 没有重复, 举个例子, -3, – 3, 2, 1, 那么第二个-3不应该再被选为我们的第一个数字了, 因为在第一个-3定下来寻找2 sum的时候,
 我们一定已经找到了所有以-3为第一个数字的triplet(trick 2).  但是这样做尽管可以避免一部分的重复, 但是还有另一种重复无法避免:
 -3, -3, -3, 6, 那么在定下第一个-3的时候, 我们已经有两组重复triplet <-3, -3, 6>， 如何在不使用std::set的情况下避免这类重复,
 笔者至今没有很好的想法. 大家有和建议? 望不吝赐教！

 更新: 感谢网友stayshan的留言提醒, 根据他的留言, 不用在最后再去重. 于是可以把trick 3, 4下面的两行代码去掉,
 然后把while里面的copy constructor这条语句加上是否和前一个元素重复的判断变成下面的代码就行了.

 这样的做法当然比我上面的代码更加优雅, 虽然本质其实是一样的, 只不过去重的阶段变化了, 进一步的, 我想探讨的是,
 我们能不能通过”不产生任何重复的triplet”的方法直接得到没有重复的triplet集合? 网友stayshan提到的方法其实还是可能
 生成重复的triplet, 然后通过和已有的triplet集合判断去重, 笔者在这里试图所做的尝试更加确切的讲是想找到一种方法,
 可以保证不生成重复的triplet. 现有的方法似乎都是post-processing, i.e., 生成了重复的triplet以后进行去重.
 笔者想在这里探讨从而找到一种我觉得可以叫他pre-processing的方法, 能够通过一定的性质(可能是排序的性质等)保证不会生成triplet,
 从而达到不需任何去重的后处理(post-processing)手段. 感觉笔者抛出的砖已经引出了挺好的思路了啊, 太好了, 大家有啥更好的建议,
 还请指教啊 ：）


 class Solution {
 public:
 vector threeSum(vector &num) {
 // same as above
 // ...
 for(int i = 0; i &lt; iCount; ++i) {
 // same as above
 // ...
 while(j &lt; k) {
 int iSum = num[j] + num[k];
 if(iSum + vecTriple[0] == 0) {
 vecTriple[1] = num[j];
 vecTriple[2] = num[k];
 if(vecResult.size() == 0 || vecTriple != vecResult[vecResult.size() - 1])
 vecResult.push_back(vecTriple); // copy constructor
 ++j;
 --k;
 }
 else if(iSum + vecTriple[0] &lt; 0)
 ++j;
 else
 --k;
 }
 iCurrentValue = num[i];
 }
 return vecResult;
 }
 };
 Hash解法(Other)：
 其实比如2sum还是有线性解法的，就是用hashmap, 这样你check某个值存在不存在就是常数时间，那么给定一个sum, 只要线性扫描,
 对每一个number判断sum – num存在不存在就可以了。注意这个算法对(copyright @sigmainfy)有重复元素的序列也是适用的。
 比如 2 3 3 4 那么hashtable可以使 hash(2) = 1; hash(3) = 1, hash(4) =1其他都是0,  那么check的时候，扫到两次3都是
 check sum – 3在不在hashtable中，注意最后返回所有符合的pair的时候也还是要去重。这样子推广的话 3sum 其实也有O(N^2)的
 类似hash算法，这点和之前是没有提高的，但是4sum就会有更快的一个算法。

 4sum的hash算法：
 O(N^2)把所有pair存入hash表，并且每个hash值下面可以跟一个list做成map， map[hashvalue] = list，每个list中的元素就是一个pair,
 这个pair的和就是这个hash值，那么接下来求4sum就变成了在所有的pair value中求 2sum，这个就成了线性算法了，
 注意这里的线性又是针对pair数量(N^2)的线性，所以整体上这个算法是O(N^2)，而且因为我们挂了list, 所以只要符合4sum的我们都可以
 找到对应的是哪四个数字。

 到这里为止有人提出这个算法不对 (感谢Jun提出这点!! See the comment below), 因为这里的算法似乎无法检查取出来的四个数字
 是否有重复的, 也就是说在转换成2sum问题得到的那些个pair中, 有可能会有重复元素, 比如说原来数组中的第一个元素其实是重复
 了两次才使得4 sum满足要求, 那么这样得到的四元组(四个数字的和等于给定的值), 其实只有三个原数组元素, 其中第一个元素用了两次,
 那么这样就不对了. 如果仅从我上面的描述来看, 确实是没有办法检查重复的, 但是仔细想想我们只要在map中存pair的的时候记录下的
 不是原数组对应的值, 而是原数组的id,就可以避免这个问题了. 更加具体的, map[hashvalue] = list, 每个list的元素就是一个pair,
 这个pair<int, int> 中的pair是原来的array id, 使得这两个id对应到元素组中的元素值的和就是这个hash值. 那么问题就没有了,
 我们在转换成的2sum寻找所有pair value的2sum的同时要检查最后得到的四元组<id1, id2, id3, id4>没有重复id. 这样问题就解决了.

 更新: 关于4Sum的Hash解法, 感谢网友Tenos和hahaer的评论, 笔者再三思考, 思来想去>_<对于hahaer提出的所有元素都是0,
 而且Target也是0的这个case, 我想问题可能在这里.

 首先如果要找出所有唯一的四元组(id1, id2, id3, id4)也就是id级别的四元组, 那么时间复杂度只能是O(N^4). 推理如下:
 如果要找到所有的唯一的四元组(id1, id2, id3, id4)的话, 是一定要O(N^4)时间的, 因为在这个case里面, 就是一个组合问题,
 在N个id里面任意取出4个不同的id, 都是符合我们条件的四元组, 光是这样, 答案就有 O(N^4)个, N个里面取四个的组合种数.

 可是! 如果大家再去看看leetcode的题目的话, 其实题目要求是返回元素组成的四元组(而不是要求id组成的四元组唯一),
 也就是元素级别的四元组(参考网友Jun和AmazingCaddy和我在评论中的讨论)在这个case中, 返回(0, 0, 0, 0)就好了,
 而不是返回(id1， id2, id3, id4)也就是不用去管id的问题. 如果是这样的话我们就不需要比较id了, 利用set之类的post-processing的
 方法是可以得到唯一的(0, 0, 0, 0)的.

 还是抛砖引玉吧, 如果大家在这个问题上还有什么想法, 还请留言指点.

 结束语：
 这篇文章主要想从一般的K sum问题的角度总结那些比较经典的求和问题比如leetcode里面的2sum, 3sum(closest), 4sum等问题,
 文章先直接给出K Sum的问题描述和算法(递归解法), 然后将这个一般性的方法套用到具体的K, 比如leetcode中的2Sum, 3Sum, 4Sum问题.
 同时我们也给出另一种哈希算法的讨论. 那么这篇文章基本上还是自己想到什么写什么，有疏忽不对的地方请大家指正，也欢迎留言讨论，


 * @author Mageek Chiu
 * @date 2018/7/6 0029:12:21
 */
class NSum {

    public static List<List<Integer>> threeSum(int[] nums) {
        return threeSum(nums,0);
    }

    public static List<List<Integer>> threeSum(int[] nums, int target) {
        List<List<Integer>> res = new LinkedList<>();
        Map<Integer,String> map = new HashMap<>();

        Arrays.sort(nums);// 先排序

        for (int i = 0; i < nums.length; i++) {
            int newTarget = target - nums[i];
            int[] arr = new int[nums.length-i];
            System.arraycopy(nums,i,arr,0,nums.length-i);
            int[] newRes = TowNumberSum.twoSum(arr,newTarget);
            if (newRes[0]==0 && newRes[1]==0){
                continue;
            }else{
                List thisRes = new LinkedList<Integer>(){};
                thisRes. add(i);
                thisRes. add(newRes[0]);
                thisRes. add(newRes[1]);
                res.add(thisRes);
            }
        }

        return res;
    }


    // 感受： hash表的利用，然后就是归一化的应用，就是4可以用3,3可以用2这种思想
    public static void main (String ...args){
        int[] nums = {-1, 0, 1, 2, -1, -4};int target = 0;
        List<List<Integer>> res = threeSum(nums,target);
        System.out.println(res.get(0).get(0)+","+res.get(0).get(1)+","+res.get(0).get(2));

    }
}
