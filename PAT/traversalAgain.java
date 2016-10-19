import java.util.*;

public class traversalAgain{
    //push的顺序都一样，只是pop出来的时机不一样！
    //前中后序的访问路线都是一样的。
    //中序的pop顺序： 左孩子，自身，右孩子
    //后序的pop顺序：左孩子，右孩子，自身
    // 1.入栈入到底，然后为NULL的时候pop，此时是左孩子
    //1.入栈顺序是先序序列，出栈顺序为中序序列
    //2.即利用先序和中序把后序推出来！
    //3.先序第一个为根元素，根据中序序列得到root的位置,同时得到左边子树和右边子树
    //各有多少个节点
    //4 在后序序列中，0～L为左子树，L+1～L+R为右子树，最后一个为root
    //5.递归左右数组重复上述过程
    public static final int MAX=30;
    public int[] preOrder;
    public int[] inOrder;
    public int[] postOrder;
    public void createOrder(int[] preOrder,int[] inOrder){
        int len=preOrder.length;
        if(len==1)
        {
            preOrder[0]=inOrder[0]=1;
        }
        if(len<1)
            return;
        Scanner s=new Scanner(System.in);
       Stack<Integer> stack=new Stack<Integer>();
        int i=0;//no tree
        int pindex=0,index=0;
        boolean flag=true;
        while(i<2*len){
            String action=s.nextLine();
            String[] tmp=action.split(" ");
            if(tmp.length>1){
                if(tmp[0].equals("Push"))
                {
                    int val=Integer.parseInt(tmp[1]);
                    stack.push(val);
                    preOrder[pindex++]=val;
                }
            else{
                    System.out.println("Input error");
                    return;
                }
            }else{
                if(tmp[0].equals("Pop")){
                    if(stack.size()!=0)
                    {
                        int valp=stack.pop();
                        inOrder[index++]=valp;
                    }
                }
                else{
                    System.out.println("Input error");
                    return;
                }
            }
            i++;
        }
    }
    public void postSequence(int preL,int inL,int postL,int n){
        if(n==0)//n current array length,number of node
            return;
        if(n==1)
        {
            postOrder[postL]=preOrder[preL];
            return;
        }
        int root=preOrder[preL];
        postOrder[postL+n-1]=root;//last one insert root`
        int L,R;
        for(int i=0;i<n;i++){
            if(inOrder[inL+i]==root)
                break;
        }
        L=i;//number of left node
        R=n-L-1;
        postSequence(preL+1,inL,postL,L);
        postSequence(preL+L+1,inL+L+1,postL+L,R);

    }
    public static void main(String[] agrs){
        Scanner s1=new Scanner(System.in);
        traversalAgain tr=new traversalAgain();
        int N=s1.nextInt();
        if(N<0||N>30){
            System.out.println("Error input");
            return;
        }
        tr.preOrder=new int[N];
        tr.inOrder=new int[N];
        tr.postOrder=new int[N];
        tr.createOrder(tr.preOrder,tr.inOrder);
        for(int i=0;i<N;i++){
            System.out.println(tr.inOrder[i]);
        }
        tr.postSequence(0,)
    }
}
