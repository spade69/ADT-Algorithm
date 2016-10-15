import java.util.*;
import java.math.*;
/*
 *Author Jason
  AVL Tree!
  需要得到每个结点的高度，才能求出平衡因子，所以不仅仅是求树的高度。
  高度，而是所有节点的高度
 * */

public class rootAVL{
    public static final int MAX=20;
    protected class Node{
        int val=0;
        Node left;
        Node right;
        int height;
        public Node(int val){
            this.val=val;
            this.left=null;
            this.right=null;
            this.height=0;
        }
    }
    public int getNodeHeight(Node root){
        if(root==null)
        {
            return 0;
        }
        else
            return root.height;
    }

    public int Max(int h1,int h2){
        return (h1>h2)?h1:h2;
    }
    public boolean isBalance(Node root){
        return Math.abs(getNodeHeight(root.left)-getNodeHeight(root.right))<2;
    }

    //LL 单旋转
    public Node singleLeftRotation(Node A){
        Node B=A.left;
        A.left=B.right;
        B.right=A;
        A.height=Max(getNodeHeight(A.left),getNodeHeight(A.right))+1;
        B.height=Max(getNodeHeight(B.left),A.height)+1;
        return B;//return root,because root is B
    }
    //RR 单旋转
    public Node singleRightRotation(Node A){
        Node B=A.right;
        A.right=B.left;
        B.left=A;
        A.height=Max(getNodeHeight(A.left),getNodeHeight(A.right))+1;
        B.height=Max(getNodeHeight(B.right),A.height)+1;
        return B;
    }
    //LR 就是左子树的右子树引起了不平衡，需要先RR再LL！
    public Node doubleLRrotation(Node A){
        A.left=singleRightRotation(A.left);
        return singleLeftRotation(A);
    }
    //RL 右子树的左子树引起了不平横
    public Node doubleRLrotation(Node A){
        A.right=singleLeftRotation(A.right);
        return singleRightRotation(A);
    }

    public Node AVLInsetion(int nodeVal,Node root){
        if(root==null){
            root=new Node(nodeVal);
            root.height=0;
        }
        else if(nodeVal<root.val){
            root.left=AVLInsetion(nodeVal,root.left);
            if(!isBalance(root))
            {
                if(nodeVal<root.left.val){
                    root=singleLeftRotation(root);

                }else{
                    root=doubleLRrotation(root);
                }
            }
        }
        else if(nodeVal>root.val){
            root.right=AVLInsetion(nodeVal,root.right);
             if(!isBalance(root))
            {
                if(nodeVal>root.right.val)
                    root=singleRightRotation(root);
                else
                    root=doubleRLrotation(root);
            }
        }
        root.height=Max(getNodeHeight(root.left),getNodeHeight(root.right))+1;
        return root;
    }

    public static void main(String[] args){
        Scanner s1=new Scanner(System.in);
        rootAVL r=new rootAVL();
        int N=s1.nextInt();
        Node root=null;
        Node result;
        if(N<=0||N>20){
            System.out.println("Error input!");
            return;
        }
        for(int i=0;i<N;i++){
            int tmp=s1.nextInt();
            root=r.AVLInsetion(tmp,root);
       //     System.out.println(root.height);
        }
        System.out.println(root.val);
    }
}
