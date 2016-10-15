import java.util.*;

public class issomorphism{
    public int size=0;
    public static final int MAX=10;
    public Node[] T1,T2;
    protected class Node{
        String data="-";
        int left;
        int right;
        public Node(String data,int left,int right){
            this.data=data;
            this.left=left;
            this.right=right;
        }
    }

    public int createTree(Node[] T1){
        int size=T1.length;
        String cdata,cl,cr;
        int[] check=new int[size];
        for(int i=0;i<size;i++) check[i]=0;//clear
        Scanner s1=new Scanner(System.in);
        int root=-1;
        for(int i=0;i<size;i++){
            cdata=s1.next();
            cl=s1.next();
            cr=s1.next();
            Node tmp=new Node(cdata,-1,-1);
            if(!cl.equals("-")){
                //String to Int   Integer.parseInt()!
                tmp.left=Integer.parseInt(cl);
                check[tmp.left]=1;
            }else{
                tmp.left=-1;
            }
            if(!cr.equals("-")){
                tmp.right=Integer.parseInt(cr);
                check[tmp.right]=1;
            }else{
                tmp.right=-1;
            }
            T1[i]=tmp;
        }
        for(int j=0;j<size;j++){
            if(check[j]==0){
                root=j;
                break;//The node has not be marked is root
            }
        }

        return root;
    }

    public boolean Issomorphic(int R1,int R2){
        if(R1==-1&&R2==-1)
            return true;
        if((R1==-1&&R2!=-1)||(R1!=-1&&R2==-1))
            return false;
        if(!T1[R1].data.equals(T2[R2].data))//roots are different
            return false;
        if((T1[R1].left==-1)&&(T2[R2].left==-1))
            return Issomorphic(T1[R1].right,T2[R2].right);
        if((T1[R1].left!=-1)&&(T2[R2].left!=-1)&&
                (T1[T1[R1].left].data.equals(T2[T2[R2].left].data)))
            return (Issomorphic(T1[R1].left,T2[R2].left)&&
                    Issomorphic(T1[R1].right,T2[R2].right));
        else//need to swap left and right sub tree
            return (Issomorphic(T1[R1].left,T2[R2].right)&&
                    Issomorphic(T1[R1].right,T2[R2].left));
    }

    public static void main(String[] args){
        Scanner s1=new Scanner(System.in);
        issomorphism iss=new issomorphism();
        int R1,R2;
        boolean result;
        int N=s1.nextInt();
        iss.T1=new Node[N];
        R1=iss.createTree(iss.T1);
        int M=s1.nextInt();
        iss.T2=new Node[M];
        R2=iss.createTree(iss.T2);

        result=iss.Issomorphic(R1,R2);
        // System.out.printf("R1 is %d R2 :%d",R1,R2);
        if(result)
            System.out.println("Yes");
        else
            System.out.println("No");
    }
}
