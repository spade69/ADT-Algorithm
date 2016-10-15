import java.util.*;

public class Traversal{
    public class TreeNode{
        TreeNode left=null;
        TreeNode right=null;
        int val=0;
        TreeNode(int val){
            this.val=val;
        }
    }
    //中序遍历 递归实现，先序和后序就是语句交换次序
    public void InOrderTraversal(TreeNode BT){
        if(BT!=null){
            InOrderTraversal(BT.left);
            System.out.println(BT.val);
            InOrderTraversal(BT.right);
        }
    }

    //stack implementation非递归实现遍历
    public void InOrderTraversalST(TreeNode root){
        Stack<TreeNode> s1=new Stack<TreeNode>();
        while(root!=null||!s1.empty()){
            while(root!=null){
                s1.push(root);
                root=root.left;
            }
            if(!s1.empty()){
                root=s1.pop();
                System.out.println(root.val);
                root=root.right;
            }
        }
    }

 //print all the leaves node
    public void traversalPrintLeaves(TreeNode BT){
        if(BT!=null){
            traversalPrintLeaves(BT.left);
            if(BT.left==null&&BT.right==null)
            System.out.println(BT.val);
            traversalPrintLeaves(BT.right);
        }
    }


//queue implementation 层次遍历
    public void levelOrderTraversal(TreeNode root){
        LinkedList<TreeNode> l1=new LinkedList<TreeNode>();
        if(root==null) return;
        l1.add(root);
        //root如果为空了，就会一直remove（）直到队列为空。
        //不需要再次判断root是否为空
        while(l1.size()!=0){
            root=l1.remove();
            System.out.println(root.val);
            if(root.left!=null) l1.add(root.left);
            if(root.right!=null) l1.add(root.right);

        }
    }

    public static void main(String[] args){
        Traversal a=new Traversal();
        //两种方法创建内部类对象
        //TreeNode b=new Traversal().new TreeNode(1);
        TreeNode c=a.new TreeNode(2);
        TreeNode d=a.new TreeNode(3);
        TreeNode e=a.new TreeNode(4);
        c.left=d;c.right=e;
        //a.InOrderTraversal(c);
        Stack<Integer> s1=new Stack<Integer>();
        s1.push(3);

        LinkedList<Integer> l1=new LinkedList<Integer>();
        l1.add(3);l1.add(4);l1.add(5);
        System.out.println(l1);
        //l1.remove();l1.remove();l1.remove();
        System.out.println(l1);
        System.out.println(l1.size());

        a.levelOrderTraversal(c);
    }
}
