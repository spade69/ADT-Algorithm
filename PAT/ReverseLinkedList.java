import java.util.*;

public class ReverseLinkedList {
    public static final int MAX=100000;
    public Node[] list;
    public int headAddress,rhead;
    public int N,K;
    public class Node{
        int data=0;
        int next=0;
        int address=0;
        public Node(int address,int data,int next){
            this.address=address;
            this.data=data;
            this.next=next;
        }
    }


    public void createPoly(){
        int nodeAddress,i=0;
        int data,next;
        Scanner s=new Scanner(System.in);
        headAddress=s.nextInt();
        N=s.nextInt();
        K=s.nextInt();

        if(headAddress<0||headAddress>99999||N>100000||N<0||K<0)
        {
            System.out.println("Input Error");
            return;
        }
        //variable can be declared but not initilize,
        //here we initialize the list!
        list=new Node[MAX];
        while(i<N){
            //Actually,it is not needed to use getLine.
           /* String str=s.nextLine();
            String[] arr=str.split(" ");
            int[] arrx=new int[arr.length];
            for(int k=0;k<arr.length;k++){
                 arrx[k]=Integer.parseInt(arr[k]);
            }*/
            nodeAddress=s.nextInt();
            data=s.nextInt();
            next=s.nextInt();
            list[nodeAddress]=new Node(nodeAddress,data,next);

            i++;
        }
    }

    public void printList(Node[] xlist,int xheadAddress){
        while(xlist[xheadAddress].next!=-1){
            System.out.printf("%05d %d %05d\n",xheadAddress,xlist[xheadAddress].data,xlist[xheadAddress].next);
            xheadAddress=xlist[xheadAddress].next;
        }
        System.out.printf("%05d %d %05d\n",xheadAddress,xlist[xheadAddress].data,xlist[xheadAddress].next);

    }

    public Node[] reverse(){
        //list
        Stack<Node> s1=new Stack<Node>();
        Node[] rlist=new Node[MAX];
        int rheadAddress=0,rear=0,Last=0;
        if(N<K)
            return null;
        int count=N/K;

        while(count>0){
            for(int i=0;i<K;i++){
                s1.push(list[headAddress]);
                rheadAddress=headAddress;
                headAddress=list[headAddress].next;
            }
            if(count==N/K)
            {
                rhead=s1.peek().address;
            }
            count--;
            for(int j=0;j<K;j++){
                //if N%K==0 , it will pop
                if(!s1.empty()){
                    //这里花费了一晚上，对于内存管理的不了解！
                    Node xtmp=s1.pop();
                    Node tmp=new Node(xtmp.address,xtmp.data,xtmp.next);
                    rlist[rheadAddress]=tmp;
                    if(count==0&&j==0){
                        Last=tmp.address;
                    }
                    //using top()!!!!!!!
                    if(s1.empty()){
                        if(count==0)
                            rear=tmp.address;
                        break;
                    }
                    rlist[rheadAddress].next=s1.peek().address;
                    rheadAddress=rlist[rheadAddress].next;
                }
            }
        }
        System.out.printf("%d %d\n",Last,rear);
        if(N%K!=0){
            int num=N%K+1;//Start from the node "1", the node rear.
            //So including rear there shall add 1.
            while(num>0){
                Node tmp=new Node(rear,list[rear].data,list[Last].next);
                rlist[rear]=tmp;
                //rlist[rear].next=list[Last].next;
                Last=list[Last].next;
                rear=Last;
                num--;
            }

        }
        return rlist;

    }


    public static void main(String[] args){
        ReverseLinkedList r=new ReverseLinkedList();
        Scanner s=new Scanner(System.in);
      /*  int[] arrx=new int[4];
        String str=s.nextLine();
        String[] arr=str.split(" ");
        for(int i=0;i<arr.length;i++){
            arrx[i]=Integer.parseInt(arr[i]);
        }
        System.out.println(arrx[0]);
        System.out.println(arrx[1]);
*/
        Node[] rlist;
        r.createPoly();
        r.printList(r.list,r.headAddress);
        rlist=r.reverse();

        System.out.println(rlist[r.rhead].data);
        int rhead=r.rhead;
       r.printList(rlist,rhead);
    }
}
