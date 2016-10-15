import java.util.*;

public class BinaryTree{
    public class TreeNode{
        TreeNode left=null;
        TreeNode right=null;
        int val=0;
        TreeNode(int val){
            this.val=val;
        }
    }
    public void InOrderTraversal(TreeNode BT){
        if(BT!=null){
            InOrderTraversal(BT.left);
            System.out.println(BT.val);
            InOrderTraversal(BT.right);
        }
    }


    public int GetHeight(TreeNode root){
        int MaxL,MaxR,Height;
        if(root!=null){
            MaxL=GetHeight(root.left);
            MaxR=GetHeight(root.right);
            Height=(MaxL>MaxR)?MaxL:MaxR;
            return Height+1;//返回树的深度
        }else{
            return 0;//empty tree
        }
    }
   //Find specific node in Binary Tree
    public TreeNode Find(TreeNode root,int X){
        while(root!=null){
            if(root.val<X){
                root=root.right;
            }
            else if(root.val>X){
                root=root.left;
            }
            else{
                return root;
            }
        }
        return null;
    }
    //Find min
    public TreeNode FindMin(TreeNode root){
        if(root!=null){
            while(root.left!=null)
                root=root.left;
        }
        return root;
    }
    //Find Max
    public TreeNode FindMax(TreeNode root){
        if(root!=null){
            while(root.right!=null){
                root=root.right;
            }
        }
        return root;
    }
    //Insert
    public TreeNode Insert(TreeNode root,int X){
        if(root==null){
            //当root为null，就要为它创建新的
            root=new TreeNode(X);
            root.left=root.right=null;
        }else{
            if(root.val<X){//当right为null的时候可以直接插入
                //当不是null就继续递归下一个节点的直到为null
                root.right=Insert(root.right,X);
            }
            else if(root.val>X){
                root.left=Insert(root.left,X);
            }
            //else X 存在，什么都不做
        }
        return root;
    }
    //Delete , return new BinTree
    public TreeNode Delete(TreeNode root,int X){
        TreeNode tmp;
        if(root==null) System.out.println("Not found!");
        else if(root.val<X)
            root.right=Delete(root.right,X);
        else if(root.val>X)
            root.left=Delete(root.left,X);
        else{
            //找到了，删除有两个节点的情况
            if(root.left!=null&&root.right!=null){
                tmp=FindMin(root.right);
                root.val=tmp.val;
                root.right=Delete(root.right,X);
            }
            else{
                tmp=root;
                if(root.left==null){
                    root=root.right; }
                else if(root.right==null){
                    root=root.left;
                }
                //free?
                tmp=null;
            }

        }
        return root;
    }

    public static void main(String[] args){
        BinaryTree t1=new BinaryTree();
        TreeNode a=t1.new TreeNode(2);
        TreeNode root=t1.new TreeNode(3);
        TreeNode c=t1.new TreeNode(4);
        TreeNode d=t1.new TreeNode(5);
        root.left=a;root.right=c;c.right=d;
        int height=t1.GetHeight(root);//
        System.out.println("height:"+height);
        //Testing Find FindMax FindMin
        TreeNode result,Max,Min;
        result=t1.Find(root,5);
        System.out.println(result.val);
        Max=t1.FindMax(root);
        Min=t1.FindMin(root);
        System.out.println("Max: "+Max.val+"Min: "+Min.val);
        //Insert& Delete
        TreeNode rootx=t1.Insert(root,10);
        TreeNode resultx=t1.Delete(rootx,5);
        t1.InOrderTraversal(resultx);
    }

}
