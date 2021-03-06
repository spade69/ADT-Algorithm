import java.util.*;

public class ReverseListTrue {
    public static final int MAX=100000;
    public Node[] list;
    public int headAddress,rhead;
    protected int N,K;
    protected class Node{
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

    public void reverse(){
        //Node[] rlist=new Node[MAX];
        //curr represent the new reverse list's head
        //old represent the old list's head
        //tmp save old.next;
        int nodeAddress=headAddress,curr=headAddress;
        int old=list[curr].next;
        if(N<K)
            return;
        int count=N/K;

        while(count>0){

            for(int j=1;j<K;j++){ //start from 1
                int tmp=list[old].next;
                list[old].next=curr;//reverse
                curr=old;
                old=tmp;

            }
            if(count==N/K)
                headAddress=curr;
            list[nodeAddress].next=old;
            nodeAddress=old;
            curr=nodeAddress;
            old=list[old].next;
            count--;
        }
        if(N%K!=0){
            list[nodeAddress].next=old;
        }

    }


    public static void main(String[] args){

        ReverseListTrue r=new ReverseListTrue();
        //Scanner s=new Scanner(System.in);
        r.createPoly();
        r.reverse();

        r.printList(r.list,r.headAddress);
    }
}
