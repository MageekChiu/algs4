package edu.princeton.cs.other;


import java.util.*;

import static java.lang.System.out;

/**
 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 一个二叉搜索树具有如下特征：

     节点的左子树只包含小于当前节点的数。
     节点的右子树只包含大于当前节点的数。
     所有左子树和右子树自身必须也是二叉搜索树。
 示例 1:

     输入:
        2
       / \
      1   3
     输出: true
 示例 2:

 输入:
       5
      / \
     1   4
        / \
       3   6
 输出: false
 解释: 输入为: [5,1,4,null,null,3,6]。
 根节点的值为 5 ，但是其右子节点值为 4


 * @author Mageek Chiu
 */
public class BST {

    public static boolean IsSubtreeLessThan(TreeNode t, int val) {
        return t == null || (t.val < val && IsSubtreeLessThan(t.left, val) && IsSubtreeLessThan(t.right, val));
    }
    public static boolean IsSubtreeMoreThan(TreeNode t, int val) {
        return t == null || (t.val > val && IsSubtreeMoreThan(t.left, val) && IsSubtreeMoreThan(t.right, val));
    }
    //判断过程：
    //（1）从根节点开始判断：左右两个节点的大小是否符合：左节点值<根节点值<右节点值，
    // 并依次遍历判断整棵树左子树所有节点和右子树所有节点是否满足二叉搜索树性质(1)和(2);
    //（2）根节点检测完成后，开始左右子树的遍历，判断它们是不是二叉搜索树
    public boolean isValidBST(TreeNode root) {
        return root == null ||
                (IsSubtreeLessThan(root.left, root.val) && IsSubtreeMoreThan(root.right, root.val)
                && isValidBST(root.left) && isValidBST(root.right));
    }


    // 二叉搜索树的中序遍历是一个递增序列，因此先中序遍历这颗树，然后对得到的序列判断是否递增即可。
    public boolean isValidBST1(TreeNode root) {
        if(root == null){
            return true;
        }
        List<Integer> list = new ArrayList<Integer>();
        inorderTraversal(root,list);

        for(int i=0;i<list.size()-1;i++){
            if(list.get(i) >= list.get(i+1))
                return false;
        }

        return true;
    }
    // 中序遍历 先遍历左子树，然后遍历根结点，最后遍历右子树。
    private void inorderTraversal(TreeNode root, List<Integer> list){
        if(root == null){
            return ;
        }
        inorderTraversal(root.left,list);
        list.add(root.val);
        inorderTraversal(root.right,list);
    }

    // 前序遍历 先遍历根结点，然后遍历左子树，最后遍历右子树。递归算法很简单，你可以通过迭代算法完成吗？
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        preorderTraversal(root,list);
        return list;
    }
    private void preorderTraversal(TreeNode root, List<Integer> list){
        if(root == null){
            return ;
        }
        list.add(root.val);
        preorderTraversal(root.left,list);
        preorderTraversal(root.right,list);
    }
    // 后序遍历 先遍历左子树，然后遍历右子树，最后遍历根节点。
    private void postorderTraversal(TreeNode root, List<Integer> list){
        if(root == null){
            return ;
        }
        preorderTraversal(root.left,list);
        preorderTraversal(root.right,list);
        list.add(root.val);
    }

    // 层序遍历
    public static List<Integer> levelOrder(TreeNode Node) {
        List<Integer> list = new LinkedList<>();
        if (Node == null) {
            return list;
        }
        int depth = depth(Node);
        for (int i = 1; i <= depth; i++) {
            levelOrder(Node, i,list);
        }
        return list;
    }
    public static void levelOrder(TreeNode Node, int level, List<Integer> list) {
        if (Node == null || level < 1) {
            return;
        }
        if (level == 1) {
//            out.print(Node.val + "  ");
            list.add(Node.val);
            return;
        }
        // 左子树
        levelOrder(Node.left, level - 1,list);
        // 右子树
        levelOrder(Node.right, level - 1,list);
    }
    // 求二叉树的深度
    public static int depth(TreeNode Node) {
        if (Node == null) {
            return 0;
        }
        int l = depth(Node.left);
        int r = depth(Node.right);
        if (l > r) {
            return l + 1;
        } else {
            return r + 1;
        }
    }


    // 实现Java中非递归实现二叉树的前序、中序、后序、层序遍历，在非递归实现中，借助了栈来帮助实现遍历。
    // 前序和中序比较类似，也简单一些，但是后序遍历需要两个栈来进行辅助，稍微复杂一些，层序遍历中借助了一个队列来进行实现。
    // 前序遍历,迭代,
    // 前序，中序和后序遍历都是深度优先遍历的特例
    public static List<Integer> preorderTraversal2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new LinkedList<>();
        while(root != null || !stack.empty()) {
            while(root != null) {
//                System.out.print(root.val + "   ");
                res.add(root.val);
                stack.push(root);
//                System.out.println(stack);
                root = root.left;
            }
            if(!stack.empty()) {
                root = stack.pop();
//                System.out.println(stack);
                root = root.right;
            }
        }
        return  res;
    }

    private void inorderTraversal2(TreeNode Node, List<Integer> list){
        Stack<TreeNode> stack = new Stack<>();
        while(Node != null || !stack.empty()){
            while (Node != null) {
                stack.push(Node);
                Node = Node.left;
            }
            if(!stack.empty()) {
                Node = stack.pop();
//                out.print(Node.val + "   ");
                list.add(Node.val);
                Node = Node.right;
            }
        }
    }

    private void postorderTraversal2(TreeNode Node, List<Integer> list){
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        int i = 1;
        while(Node != null || !stack1.empty()) {
            while (Node != null) {
                stack1.push(Node);
                stack2.push(0);
                Node = Node.left;
            }

            while(!stack1.empty() && stack2.peek() == i) {
                stack2.pop();
                int val = stack1.pop().val;
                list.add(val);
            }

            if(!stack1.empty()) {
                stack2.pop();
                stack2.push(1);
                Node = stack1.peek();
                Node = Node.right;
            }
        }
    }

    // 图的广度优先遍历算法可以理解成是二叉树的层序遍历算法的一种扩展，就好像图是树的扩展一样
    // 两种算法都是利用队列的先进先出的特性来设计的，不同的在于图中没有根节点，
    // 你可以随便选择一个节点，当作起始节点，和二叉树的一样入队，访问，出队，判断顶点是否有邻接顶点，
    // 如果有邻接顶点，就一次把邻接顶点入队，循环这个过程，当队列为空的时候，本次遍历完成，如果还有没有被访问到的顶点
    // 就再从没有被访问的顶点中随便选择一个作为起始顶点继续遍历。在广度优先遍历算法中使用图的邻接表表示方式的话遍历的速度会比较快。
    public List<Integer> levelOrder2(TreeNode Node) {
        List<Integer> levelQueue = new LinkedList<>();
        if (Node == null) return levelQueue;

        TreeNode liveNode;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(Node);//头结点作为活节点

        while (queue.size() != 0) {//只要队列不空
            liveNode = queue.poll();//活节点出队，其实这里判断一下就可以多访问几个了，也就是可以一次访问一整层
            levelQueue.add(liveNode.val);//访问活节点
            //活节点的子节点入队
            if (liveNode.left != null) {
                queue.offer(liveNode.left);
            }
            if (liveNode.right != null) {
                queue.offer(liveNode.right);
            }
        }
        return levelQueue;
    }

    // 层序遍历，带有层数
    // 大体思路还是基本相同的，建立一个queue，然后先把根节点放进去，这时候找根节点的左右两个子节点，
    // 这时候去掉根节点，此时queue里的元素就是下一层的所有节点，每次都保证queue里面的元素只有当前层
    // 用一个for循环遍历它们，然后存到一个一维向量里，遍历完之后再把这个一维向量存到二维向量里，以此类推，可以完成层序遍历。
    public List<List<Integer>> levelOrder3(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();//为了让层有序
        if (root == null) return result;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            List<Integer> oneLevel = new LinkedList<>();
            int size = q.size();
            for (int i = 0; i < size; ++i) {
                TreeNode node = q.poll();
                oneLevel.add(node.val);
                if (node.left!=null) q.offer(node.left);
                if (node.right!=null) q.offer(node.right);
            }
            result.add(oneLevel);
        }

//        List<List<Integer>> result1 = new ArrayList<>();
//        for (int i = result.size() - 1; i >= 0; i--) {
//            result1.add(result.get(i));
//        }
//        return  result1;//反序

        return result;
    }

    // 返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
    public static List<List<Integer>> levelOrder6(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) return result;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean reverse = false;//是否需要反序
        while (!q.isEmpty()) {
            List<Integer> oneLevel = new LinkedList<>();
            int size = q.size();
            for (int i = 0; i < size; ++i) {//每次循环都是完整的一层
                TreeNode node = q.poll();
                oneLevel.add(node.val);
                if (node.left!=null) q.offer(node.left);
                if (node.right!=null) q.offer(node.right);
            }
            if (reverse){
                Collections.reverse(oneLevel);
                reverse = false;
            }else {
                reverse = true;
            }
            result.add(oneLevel);
        }
        return result;
    }


    // 层序遍历，求每层的均值
    public static List<Double> levelOrder5(TreeNode root) {
        List<Double> result = new LinkedList<>();//ArrayList add过程需要扩容，会超时
        if (root == null) return result;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            double oneLevel = 0;
            int size = q.size();
            for (int i = 0; i < size; ++i) {
                TreeNode node = q.poll();

                oneLevel = node.val*1.0/(i+1)+oneLevel*(i*1.0/(i+1));//均值标准求法，不会溢出，直接做sum很容易溢出

                if (node.left!=null) q.offer(node.left);
                if (node.right!=null) q.offer(node.right);
            }
            result.add(oneLevel);
        }
        return result;
    }


    // 层序遍历，带有层数 递归实现
    // 核心就在于我们需要一个二维数组，和一个变量level，当level递归到上一层的个数时，我们新建一个空层，继续往里面加数字。
    public List<List<Integer>> levelOrder4(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        levelOrder4(root, 0, res);
        return res;
    }
    private void levelOrder4(TreeNode root, int level, List<List<Integer>> res) {
        if (root==null) return;
        if (res.size() == level) res.add(new ArrayList<>());
        res.get(level).add(root.val);
        if (root.left!=null) levelOrder4(root.left, level + 1, res);
        if (root.right!=null) levelOrder4(root.right, level + 1, res);
    }

    /**
     给定一个二叉树，找出其最大深度。
     二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     说明: 叶子节点是指没有子节点的节点。
     示例：
     给定二叉树 [3,9,20,null,null,15,7]，
     3
     / \
     9  20
     /  \
     15   7
     返回它的最大深度 3 。
     */
    public static int maxDepth(TreeNode root) {
        if (root==null) return 0;//避免后面递归时的判断
        int l = 0,r = 0;
        l = maxDepth(root.left);
        r = maxDepth(root.right);
        return Math.max(l,r)+1;
    }

    // 二叉树是否平衡
    public static boolean isBalanced(TreeNode root) {
        if (root==null) return true;//避免后面递归时的判断
        if (root.left==null && root.right==null) return true;
        boolean l = false,r = false;
        int ll=0,rr=0;
        l = isBalanced(root.left);
        ll = maxDepth(root.left);
        r = isBalanced(root.right);
        rr = maxDepth(root.right);
        return l && r && (Math.abs(ll-rr)<=1);
    }

    // 给定一个二叉树，返回所有从根节点到叶子节点的路径。
    // 思路:用前序遍历遍历一遍树便可以，主要是怎么把遍历到的节点拼接成字符串
    // 关键就是这个字符串不用回溯，要一直保持，而那个栈是需要回溯的
    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> stringList = new LinkedList<>();
        if(root==null)
            return stringList;
        binaryTreePaths(root,stringList,root.val+"");
        return stringList;
    }

    public static void binaryTreePaths(TreeNode root, List<String> res, String val) {
        if (root!=null && root.left==null && root.right==null) {//已经没有子节点了
            res.add(val);
            return;
        }
        if (root.left!=null){
            binaryTreePaths(root.left, res, val + "->" + root.left.val);
        }
        if (root.right!=null) {
            binaryTreePaths(root.right, res, val + "->" + root.right.val);
        }
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        List<String> list = binaryTreePaths(root);
        for (String s : list) {
            String[] s1 = s.split("->");
            int tmp,sumtmp=0;
            for (String s2 : s1) {
                tmp = Integer.parseInt(s2);
                sumtmp+=tmp;
            }
            if (sumtmp==sum) return true;
        }
        return  false;
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> lists = new LinkedList<>();
        List<String> list = binaryTreePaths(root);
        for (String s : list) {
            String[] s1 = s.split("->");
            int tmp,sumtmp=0;
            for (String s2 : s1) {
                tmp = Integer.parseInt(s2);
                sumtmp+=tmp;
            }
            if (sumtmp==sum) {
                List<Integer> list1 = new LinkedList<>();
                for (String s2 : s1) {
                    tmp = Integer.parseInt(s2);
                    list1.add(tmp);
                }
                lists.add(list1);
            }
        }
        return  lists;
    }

    //找到根节点的位置,用start和end是为了更好的限制范围，避免数组拷贝
    private static int findPosition(int[] array, int start, int end, int key) {
        for (int i = start; i <= end; i++) {
            if (array[i] == key)  return i;
        }
        return -1;
    }

    //1、首先根据前序遍历序列的第一个数字创建根结点（前序遍历序列的第一个数字就是根结点）
    //2、然后在中序遍历序列中找到根结点所在的位置，这样就能确定左、右子树结点的数量，这样也就分别找到了左、右子树的前序遍历序列和中序遍历序列。
    //3、然后用递归的方法去构建左、右子树，直到叶子结点。
    private TreeNode myBuildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if (inStart > inEnd) {
            return null;
        }
        //前序遍历序列的第一个数字就是根结点，创建根结点root
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);
        //在中序遍历序列中找到根结点的位置
        int position = findPosition(inorder, inStart, inEnd, rootVal);
        //递归构建左子树
        root.left = myBuildTree(preorder, preStart + 1, preStart + (position - inStart), inorder, inStart, position - 1);
        //递归构建右子树
        root.right = myBuildTree(preorder, preStart + (position - inStart) + 1, preEnd, inorder, position + 1, inEnd);
        return root;
    }

    /**
     根据一棵树的前序遍历与中序遍历构造二叉树。
     注意:
     你可以假设树中没有重复的元素。
     例如，给出
     前序遍历 preorder = [3,9,20,15,7]
     中序遍历 inorder = [9,3,15,20,7]
     返回如下的二叉树：
     3
     / \
     9  20
     /  \
     15   7
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int len1 = preorder.length,len2 = inorder.length;
        if (len1 <1 || len1 != len2) {//前序遍历序列与中序遍历序列长度不等
            return null;
        }
        return myBuildTree(preorder, 0, len1 - 1, inorder, 0, len2 - 1);
    }

    //1、首先根据后序遍历序列的最后一个数字创建根结点
    //2、然后在中序遍历序列中找到根结点所在的位置，这样就能确定左、右子树结点的数量，这样也就分别找到了左、右子树的后序遍历序列和中序遍历序列。
    //3、然后用递归的方法去构建左、右子树，直到叶子结点。
//    注意：：：在序列中划分左、右子树时，确定数组下标一定要仔细，否则很容易出现数组越界异常，且得不到正确的二叉树。
    private static TreeNode myBuildTree1(int[] inorder, int inStart, int inEnd,int[] postorder, int postStart, int postEnd) {
        if (inStart > inEnd || postStart > postEnd) {
            return null;
        }
        int rootVal = postorder[postEnd];
        TreeNode root = new TreeNode(rootVal);
        //在中序遍历序列中找到根结点的位置
        int position = findPosition(inorder, inStart, inEnd, rootVal);
        //递归构建左子树
        root.left = myBuildTree1( inorder, inStart, position - 1, postorder,postStart , postStart + (position - inStart-1));
        //递归构建右子树
        root.right = myBuildTree1( inorder, position + 1, inEnd, postorder,postStart + (position - inStart), postEnd-1);
        return root;
    }

    /**
     根据一棵树的中序遍历与后序遍历构造二叉树。
     注意:
     你可以假设树中没有重复的元素。
     例如，给出
     中序遍历 inorder = [9,3,15,20,7]
     后序遍历 postorder = [9,15,7,20,3]
     返回如下的二叉树：
     3
     / \
     9  20
     /  \
     15   7
     */
    public static TreeNode buildTree1(int[] inorder, int[] postorder) {
        int len1 = inorder.length,len2 = postorder.length;
        if (len1 <1 || len1 != len2) {
            return null;
        }
        return myBuildTree1(inorder, 0, len1 - 1, postorder, 0, len2 - 1);
    }

    /**
     给定一个二叉树，检查它是否是镜像对称的。

     例如，二叉树 [1,2,2,3,4,4,3] 是对称的。

     1
     / \
     2   2
     / \ / \
     3  4 4  3
     但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:

     1
     / \
     2   2
     \   \
     3    3
     说明:

     如果你可以运用递归和迭代两种方法解决这个问题，会很加分。

     迭代就是层序遍历，然后看每一层是否是回文
     递归就是 转化为求两个二叉树是否是镜像二叉树
     */
    public static boolean isSymmetric(TreeNode root) {
        return root == null || isSymmetric(root.left, root.right);
    }

    //以root1为根的二叉树与以root2为根的二叉树是否是镜像二叉树。
    private static boolean isSymmetric(TreeNode root1,TreeNode root2){
        if(root1==null && root2==null){
            return true;
        }
        if(root1==null || root2==null || root1.val!=root2.val){
            return false;
        }
        return isSymmetric(root1.left, root2.right) && isSymmetric(root1.right, root2.left);
    }

    /**
     将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
     本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
     示例:
     给定有序数组: [-10,-3,0,5,9],
     一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
     0
     / \
     -3   9
     /   /
     -10  5
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums == null || nums.length == 0)
            return null;
        return getTree(nums,0,nums.length - 1);
    }
    //采用二分法来创建平衡二叉树，根结点刚好为数组中间的节点，根节点的左子树的根是数组左边部分的中间节点，
    // 根节点的右子树是数据右边部分的中间节点
    public TreeNode getTree(int []nums,int l,int r){
        if(l <= r){
            int mid = (l+r)/2;
            TreeNode node = new TreeNode(nums[mid]);
            node.left = getTree(nums,l,mid-1);
            node.right = getTree(nums,mid+1,r);
            return node;
        }else{
            return null;
        }
    }

    /**

     给定一个二叉树，找出其最小深度。
     最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     说明: 叶子节点是指没有子节点的节点。
     示例:
     给定二叉树 [3,9,20,null,null,15,7],
         3
        / \
       9  20
         /  \
        15   7
     返回它的最小深度  2.

     这种属于最短路径，也就是要用广度优先，对应二叉树的层序遍历
     深度优先看 JumpGame
     */
    public int minDepth(TreeNode root,int level) {
        if (root == null) return 0;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
//            if (size<Math.pow(2,level)) return level;//当前层 节点不满 那么上一层必然有叶子节点// 这属于理解错误，因为不满也可以分散，比如上一层每个节点都只有一个子节点
            level++;
            for (int i = 0; i < size; ++i) {
                TreeNode node = q.poll();
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
                if(node.left==null && node.right==null) return level;//找到叶子节点了。
            }
        }
        return level;
    }

    public  int minDepth(TreeNode root){
        return minDepth(root,0);
    }

    /**
     给定一个二叉树，原地将它展开为链表。
     例如，给定二叉树
         1
        / \
       2   5
      / \   \
     3   4   6
     将其展开为：

     1
         \
             2
                 \
                     3
                         \
                             4
                                 \
                                     5
                                         \
                                            6
     */
    // 这道题要求把二叉树展开成链表，根据展开后形成的链表的顺序分析出是使用先序遍历，那么只要是树的遍历就有递归和非递归的两种方法来求解，
    // 这里我们也用两种方法来求解。
    // 首先来看递归版本的，思路是先利用DFS的思路找到最左子节点，然后回到其父节点，把其父节点和右子节点断开，
    // 将原左子结点连上父节点的右子节点上，然后再把原右子节点连到新右子节点的右子节点上，然后再回到上一父节点做相同操作。

    public void flatten(TreeNode root) {
        if (root==null) return;
        if (root.left!=null) flatten(root.left);
        if (root.right!=null) flatten(root.right);
        TreeNode tmp = root.right;
        root.right = root.left;
        root.left = null;
        while (root.right!=null) root = root.right;
        root.right = tmp;
    }

    //下面我们再来看非迭代版本的实现，这个方法是从根节点开始出发，先检测其左子结点是否存在，
    // 如存在则将根节点和其右子节点断开，将左子结点及其后面所有结构一起连到原右子节点的位置，
    // 把原右子节点连到原左子结点最后面的右子节点之后。

//    此题还可以延伸到用中序，后序，层序的遍历顺序来展开原二叉树，分别又有其对应的递归和非递归的方法
    public void  flatten1(TreeNode root){
        TreeNode cur = root;
        while (cur!=null) {
            if (cur.left!=null) {
                TreeNode p = cur.left;
                while (p.right!=null) p = p.right;
                p.right = cur.right;
                cur.right = cur.left;
                cur.left = null;
            }
            cur = cur.right;
        }
    }

    /**
     给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
     本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
     示例:
     给定的有序链表： [-10, -3, 0, 5, 9],
     一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：

     0
     / \
     -3   9
     /   /
     -10  5

     可以老老实实按套路来，就是a指针每次走一步，b指针每次走两步
     但是也可以直接把链表转换为数组
     */
    public TreeNode sortedListToBST(DeleteKNode.ListNode head) {
        List<Integer> listNodes = new ArrayList<>();
        while (head!=null){
            listNodes.add(head.val);
            head = head.next;
        }
//        Integer[] nums = new  Integer[listNodes.size()];// list 转数组，但是不能是基本类型
//        listNodes.toArray(nums);
//        return sortedArrayToBST(nums);

        int[] nums = new int[listNodes.size()];
        for (int i = 0; i < listNodes.size(); i++) {
            nums[i] = listNodes.get(i);
        }
        return sortedArrayToBST(nums);
    }

    /**
     二叉树中任意两个节点的最近公共祖先
     思路：从根节点开始遍历，如果node1和node2中的任一个和root匹配，那么root就是最低公共祖先。
     如果都不匹配，则分别递归左、右子树，如果有一个 节点出现在左子树，并且另一个节点出现在右子树，则root就是最低公共祖先.
     如果两个节点都出现在左子树，则说明最低公共祖先在左子树中，否则在右子树。
     感觉很奇妙。引申的问题
     如果给定的不是二叉树，而是二叉搜索树呢？会比较简单一点，如果是带有指向父节点的指针的树，可以转化为两个链表求交汇点的问题。
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //发现目标节点则通过返回值标记该子树发现了某个目标结点
        if(root == null || root == p || root == q) return root;
        //查看左子树中是否有目标结点，没有为null
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        //查看右子树是否有目标节点，没有为null
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        //都不为空，说明做右子树都有目标结点，则公共祖先就是本身
        if(left!=null&&right!=null) return root;
        //如果发现了目标节点，则继续向上标记为该目标节点
        return left == null ? right : left;
    }


    // 感受：
    public static void main (String ...args){
//        out.println(null==null);// true

        TreeNode a = new TreeNode(3);
        TreeNode a1 = new TreeNode(1);
        TreeNode a2 = new TreeNode(5);
        TreeNode a3 = new TreeNode(0);
        TreeNode a4 = new TreeNode(2);
        TreeNode a5 = new TreeNode(4);
        TreeNode a6 = new TreeNode(6);
        a.left = a1;a.right = a2;
        a1.left = a3;a1.right = a4;
        a2.left = a5;a2.right=a6;
//        out.println(levelOrder5(a));
//        out.println(levelOrder6(a));
//        out.println(maxDepth(a));
//        out.println(preorderTraversal2(a));
        out.println(binaryTreePaths(a));
    }
}

 class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }

     @Override
     public String toString() {
         return ""+val;
     }
 }
