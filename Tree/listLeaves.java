import java.util.*;

public class listLeaves{
    public static final int MAX=10;
    public Node[] List;
    protected class Node{
        int data=0;
        int left;
        int right;
        public Node(int left,int right){
            this.left=left;
            this.right=right;
        }
    }
    public int CreateTree(Node[] l1){
        int len=l1.length;
        if(len<1||len>10)
        {
            System.out.println("Illegal Input");
            return -1;
        }
        if(len==1)
            return 0;
        int[] checked=new int[len];
        for(int i=0;i<len;i++) checked[i]=0;
        int root=-1;
        Scanner s1=new Scanner(System.in);
        String cl,cr;
        for(int k=0;k<len;k++){
           Node tmp=new Node(-1,-1);
           cl=s1.next();
           cr=s1.next();
           if(!cl.equals("-")){
                tmp.left=Integer.parseInt(cl);
                checked[tmp.left]=1;
           }
           if(!cr.equals("-")){
               tmp.right=Integer.parseInt(cr);
               checked[tmp.right]=1;
           }
           l1[k]=tmp;
        }
        for(int i=0;i<len;i++){
            if(checked[i]!=1)
            {  root=i;
                break;
            }
        }
        return root;
    }
    //travser
    public void printLeaves(Node[] l1,int root){
        //directly using
        int len=l1.length;
        LinkedList<Integer> myQueue=new LinkedList<Integer>();
        if(root==-1)
            return;
        if(root==0)
        {
            System.out.println(root);
            return;
        }
        myQueue.add(root);
        while(myQueue.size()!=0){
            int tmp=myQueue.remove();
            if((l1[tmp].left==-1)&&(l1[tmp].right==-1))
                System.out.printf("%d ",tmp);
            if(l1[tmp].left!=-1) myQueue.add(l1[tmp].left);
            if(l1[tmp].right!=-1) myQueue.add(l1[tmp].right);
        }
    }

    public static void main(String[] args){
        Scanner s1=new Scanner(System.in);
        listLeaves list=new listLeaves();
        int N=s1.nextInt();
        int Root;
        list.List=new Node[N];
        Root=list.CreateTree(list.List);
        //System.out.println(Root);
        list.printLeaves(list.List,Root);
    }
}
