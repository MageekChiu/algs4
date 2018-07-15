package edu.princeton.cs.other;

import static java.lang.System.out;

/**
 * 给定两个非空链表来代表两个非负整数，位数按照逆序方式存储，它们的每个节点只存储单个数字。将这两数相加会返回一个新的链表。
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * 示例：
    输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
    输出：7 -> 0 -> 8
    原因：342 + 465 = 807

 就像你在纸上计算两个数字的和那样，我们首先从最低有效位也就是列表 l1l1 和 l2l2 的表头开始相加。
 由于每位数字都应当处于 0 \ldots 90…9 的范围内，我们计算两个数字的和时可能会出现“溢出”。
 例如，5 + 7 = 125+7=12。在这种情况下，我们会将当前位的数值设置为 22，并将进位 carry = 1carry=1 带入下一次迭代。
 进位 carrycarry 必定是 00 或 11，这是因为两个数字相加（考虑到进位）可能出现的最大和为 9 + 9 + 1 = 199+9+1=19。

 伪代码如下：

 将当前节点初始化为返回列表的哑节点。
 将进位 carrycarry 初始化为 00。
 将 pp 和 qq 分别初始化为列表 l1l1 和 l2l2 的头部。
 遍历列表 l1l1 和 l2l2 直至到达它们的尾端。
     将 xx 设为节点 pp 的值。如果 pp 已经到达 l1l1 的末尾，则将其值设置为 00。
     将 yy 设为节点 qq 的值。如果 qq 已经到达 l2l2 的末尾，则将其值设置为 00。
     设定 sum = x + y + carrysum=x+y+carry。
     更新进位的值，carry = sum / 10carry=sum/10。
     创建一个数值为 (sum \bmod 10)(summod10) 的新节点，并将其设置为当前节点的下一个节点，然后将当前节点前进到下一个节点。
     同时，将 pp 和 qq 前进到下一个节点。
 检查 carry = 1 是否成立，如果成立，则向返回列表追加一个含有数字 11 的新节点。
 返回哑节点的下一个节点。

 请注意，我们使用哑节点来简化代码。如果没有哑节点，则必须编写额外的条件语句来初始化表头的值。
 请特别注意以下情况：
 当一个列表比另一个列表长时。
 当一个列表为空时，即出现空列表。
 求和运算最后可能出现额外的进位，这一点很容易被遗忘

 复杂度分析

 时间复杂度：O(\max(m, n))O(max(m,n))，假设 mm 和 nn 分别表示 l1l1 和 l2l2 的长度，
 上面的算法最多重复 \max(m, n)max(m,n) 次。

 空间复杂度：O(\max(m, n))O(max(m,n))， 新列表的长度最多为 \max(m,n) + 1max(m,n)+1。

 拓展

 如果链表中的数字不是按逆序存储的呢？例如：
     (3→4→2)+(4→6→5)=8→0→7

 可以翻转列表转化为原问题

 *
 * @author Mageek Chiu
 */
class TwoListAdd {


    public static ListNode addTwoNumbers( ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);
        ListNode tail = res;
        int carry = 0;// 进位
        while (l1!=null && l2!=null){
            int result = l1.val + l2.val;// 本位相加
            result += carry;//加上进位
            int mod = result % 10;// 取余数就是本位该存的数
            ListNode thisRes = new ListNode(mod);
            tail.next = thisRes; tail = thisRes;
            carry = (result - mod)/10;// 得到进位
            l1=l1.next; l2=l2.next;
        }
        // 如果 某一个 较长
        ListNode noNull = l1==null ? (l2) : l1;
        while (noNull!=null){
            int result = noNull.val + carry;
            int mod = result % 10;// 取余数就是本位该存的数
            ListNode thisRes = new ListNode(mod);
            tail.next = thisRes; tail = thisRes;
            carry = (result - mod)/10;// 得到进位
            noNull=noNull.next;
        }
        if(carry!=0) tail.next = new ListNode(carry);
        return res.next;// 去掉第一个0
    }

    // 感受： 基本数学概念，然后就是各种特殊情况的考虑
    // 有联系的多考虑递归
    public static void main (String ...args){
        ListNode a = new ListNode(2);
        ListNode a1 = new ListNode(4);
        ListNode a2 = new ListNode(3);
        a.next=a1;a1.next=a2;

        ListNode b = new ListNode(5);
        ListNode b1 = new ListNode(6);
        ListNode b2 = new ListNode(4);
        b.next=b1;b1.next=b2;

//        // 两个列表相加
//        ListNode res = addTwoNumbers(a,b);// 342 + 465 = 807,所以输出 7,0,8
//        while (res!=null){//遍历打印
//            out.print(res.val+"->");
//            res = res.next;
//        }

//        out.println();

//        // 翻转列表
//        ListNode resV = revertList(a);
//        while (resV!=null){//遍历打印
//            out.print(resV.val+"->");
//            resV = resV.next;
//        }
//        out.println();

//        // 元素两两交换的列表
//        ListNode resS = swapPairs(a);
//        while (resS!=null){//遍历打印
//            out.print(resS.val+"->");
//            resS = resS.next;
//        }

//        //元素k个为一组进行翻转的列表
        ListNode resV = reverseKGroup(a,2);
        while (resV!=null){//遍历打印
            out.print(resV.val+"->");
            resV = resV.next;
        }

    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
        ListNode(int x,ListNode y) { val = x;next=y; }
    }

    // 简便的做法，复杂度为O(n),实际上每个循环都要判断，效率并不是高，只是代码整洁一些
    // 两数求和的一般模式：carry初始化为0，两数+carry求和，求carry，求mod就是当前位置的数
    public ListNode addTwoNumber(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }


    /**
     * 递归反转法：在反转当前节点之前先反转后续节点。这样从头结点开始，层层深入直到尾结点才开始反转指针域的指向。
     * 简单的说就是从尾结点开始，逆向反转各个结点的指针域指向，其过程图如下所示：
     head：是前一结点的指针域（PS：前一结点的指针域指向当前结点）
     head.getNext()：是当前结点的指针域（PS：当前结点的指针域指向下一结点）
     reHead：是反转后新链表的头结点（即原来单链表的尾结点）
     */
//    public static Node Reverse1(Node head) {
//        // head看作是前一结点，head.getNext()是当前结点，reHead是反转后新链表的头结点
//        if (head == null || head.getNext() == null) {
//            return head;// 若为空链或者当前结点在尾结点，则直接还回
//        }
//        Node reHead = Reverse1(head.getNext());// 先反转后续节点head.getNext()
//        head.getNext().setNext(head);// 将当前结点的指针域指向前一结点
//        head.setNext(null);// 前一结点的指针域令为null;
//        return reHead;// 反转后新链表的头结点
//    }



    /**
     * 单向列表翻转，遍历
     * 归反转法是从后往前逆序反转指针域的指向，而遍历反转法是从前往后反转各个结点的指针域的指向。
     基本思路是：将当前节点cur的下一个节点 cur.getNext()缓存到temp后，然后更改当前节点指针指向上一结点pre。
     也就是说在反转当前结点指针指向前，先把当前结点的指针域用tmp临时保存，以便下一次使用，其过程可表示如下：
     pre：上一结点
     cur: 当前结点
     tmp: 临时结点，用于保存当前结点的指针域（即下一结点）
     * @param l1
     * @return
     */
    public static ListNode revertList(ListNode l1){

        if (l1==null) return null;
        ListNode pre = l1;
        ListNode cur = l1.next;
        ListNode tmp;

        // l1,l2,l3,l4,l5,l6
        while (cur != null){
            tmp = cur.next;// 保存下一个节点，一开始就是第三个节点
            cur.next = pre;// 反向
            pre = cur;// 反向
            cur = tmp;// 移动当前指针至下一个节点
        }
        l1.next = null;// 翻转后列表的尾部是空的
        return pre;
    }

    /**
     给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
     示例:
     给定 1->2->3->4, 你应该返回 2->1->4->3.
     说明:
     你的算法只能使用常数的额外空间。
     你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

     利用递归的思想，依次交换链表中的节点对。具体对于每个节点来说：

     若该节点为NULL，则直接返回NULL
     若该节点的下一个节点为NULL，则直接返回该节点
     否则交换该节点与下一个节点，利用辅助指针记录该节点的下一个节点，并递归的交换接下来的节点对

     非递归的思路
     思路：

     1.p代表上一对交换完成的节点中的后者。l代表即将进行交换节点对的左节点，r代表即将进行交换节点对的右节点。
     2.首先将l指向r的下一个节点；在将r指向l；最后将p指向r。即完成当前节点对的交换。

     // 感悟这种交换链表关键是就是理顺思路，循环体都是可复用的，因此每一步都要保存上一步的状态和必要的中间状态

     * @return
     */
    public static ListNode swapPairs(ListNode head) {
        if(head ==null) return null;
        if(head.next==null) return head;
        ListNode temp=head.next;
        head.next=swapPairs(temp.next);
        temp.next=head;
        return temp;
    }

    /**
     ，我们用head记录每段的开始位置，cur记录结束位置的下一个节点，然后我们调用reverse函数来将这段翻转，
     然后得到一个new_head，原来的head就变成了末尾，这时候后面接上递归调用下一段得到的新节点，返回new_head即可，参见代码如下
     * @param head
     * @param k
     * @return
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode cur = head;
        for (int i = 0; i < k; ++i) {
            if (cur==null) return head;
            cur = cur.next;
        }
        ListNode new_head = reverse(head, cur);
        head.next = reverseKGroup(cur, k);
        return new_head;
    }
    public static ListNode reverse(ListNode head, ListNode tail) {
        ListNode pre = tail;
        while (head != tail) {
            ListNode t = head.next;
            head.next = pre;
            pre = head;
            head = t;
        }
        return pre;
    }






}
