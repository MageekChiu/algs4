package edu.princeton.cs.other;


import java.util.*;

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
    public void levelOrder(TreeNode Node) {
        if (Node == null) {
            return;
        }
        int depth = depth(Node);
        for (int i = 1; i <= depth; i++) {
            levelOrder(Node, i);
        }
    }
    private void levelOrder(TreeNode Node, int level) {
        if (Node == null || level < 1) {
            return;
        }
        if (level == 1) {
            System.out.print(Node.val + "  ");
            return;
        }
        // 左子树
        levelOrder(Node.left, level - 1);
        // 右子树
        levelOrder(Node.right, level - 1);
    }
    public int depth(TreeNode Node) {
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
    public List<Integer> preorderTraversal2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new LinkedList<>();
        while(root != null || !stack.empty()) {
            while(root != null) {
//                System.out.print(root.val + "   ");
                res.add(root.val);
                stack.push(root);
                root = root.left;
            }
            if(!stack.empty()) {
                root = stack.pop();
                root = root.right;
            }
        }
        return  res;
    }

    private void inorderTraversal2(TreeNode Node, List<Integer> list){
        Stack<TreeNode> stack = new Stack<>();
        while(Node != null || !stack.empty()){
            while (Node != null)
            {
                stack.push(Node);
                Node = Node.left;
            }
            if(!stack.empty())
            {
                Node = stack.pop();
                System.out.print(Node.val + "   ");
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

    public void levelOrder2(TreeNode Node) {
        if (Node == null) {
            return;
        }
        TreeNode binaryNode;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(Node);

        while (queue.size() != 0) {
            binaryNode = queue.poll();
            System.out.print(binaryNode.val + "  ");

            if (binaryNode.left != null) {
                queue.offer(binaryNode.left);
            }
            if (binaryNode.right != null) {
                queue.offer(binaryNode.right);
            }
        }
    }



        // 感受：
    public static void main (String ...args){

    }
}

 class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
 }
