import java.util.*;
/*
 *Author Jason
 Really important 前提
 树是按1～N的排列，所以节点不会重复，而且不会超过这个范围
 那么只要给一个k在1～N范围，构建这颗树就是1～k的key值
 * */
public class sameBST{
    public static final int MAX=10;

    protected class Node{
        int val=0;
        boolean flag;//default value
        Node left;
        Node right;
        public Node(int x){
            this.val=x;
            this.left=null;
            this.right=null;
            this.flag=false;
        }
    }

    /**/
    public void preOrderTraversal(Node root){
        if(root!=null){
            System.out.println(root.val);
            preOrderTraversal(root.left);
            preOrderTraversal(root.right);
        }
    }
    public Node Insert(Node root,int x){
        if(root==null){
            root=new Node(x);

        }else{
            if(root.val<x){
                root.right=Insert(root.right,x);
            }else if(root.val>x){
                root.left=Insert(root.left,x);
            }
        }
        return root;
    }
//read a sequence and builid a tree
    public Node createTree(int times){
       Scanner s=new Scanner(System.in);
       Node root=null;
       for(int i=0;i<times;i++){
          int number=s.nextInt();
            if(root==null){
                root=new Node(number);
            }else{
                root=Insert(root,number);
            }
       }
       return root;
    }

    public void processSeq(Node root,int times){
        boolean flag=true;
        Scanner s1=new Scanner(System.in);
        int tmp=s1.nextInt();
        if(tmp==root.val)
            root.flag=true;
        else
            flag=false;
        for(int i=1;i<times;i++){//if one is not equal then all fail!
            tmp=s1.nextInt();
            if(!judge(root,tmp))
                flag=false;
        }
        if(flag)
            System.out.println("Yes");
        else
            System.out.println("No");
    }

    public boolean judge(Node root,int key){
        Node p=root;
        boolean flag=true;
        //default value of p.flag==true
        while(p.flag){
            if(key>p.val){
                p=p.right;
            }
            if(key<p.val){
                p=p.left;
            }
        }
       if(p.val!=key){
            flag=false;
        }else{
            p.flag=true;//here means coresponding position is true
        }
        return flag;
    }

//reset all nodes!
    public void reset_flag(Node root){
        if(root.left!=null){
            reset_flag(root.left);
        }
        if(root.right!=null){
            reset_flag(root.right);
        }
        root.flag=false;
    }

    public static void main(String[] args){
       Scanner s1=new Scanner(System.in);
       sameBST r=new sameBST();
       int N=s1.nextInt();
       if(N<0||N>10){
           System.out.println("error input!");
           return;
       }
       int L=s1.nextInt();//test sequence
        Node root=r.createTree(N);
      //  r.preOrderTraversal(root);
        for(int i=0;i<L;i++){
            r.processSeq(root,N);
            r.reset_flag(root);
        }
//       for(int i=0;i<L;i++)

    }
}
