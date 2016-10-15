import java.util.*;

public class queue{
    //Only using N-1 space
    protected int front=0;
    protected int rear=0;
    public static final int N=101;
    private int[] arr=new int[N];

    public boolean isEmpty(){
        return(front==rear);
    }
    public boolean isFull(){
        if((rear+1)%N==front)
            return true;
        return false;
    }
    public void enqueue(int v){
        if(!isFull()){
           rear=(rear+1)%N;
           arr[rear]=v;
        }
        else
        System.out.println("The queue is full!");
    }
    public int dequeue(){
        int tmp;
        if(!isEmpty()){
            front=(front+1)%N;
            tmp=arr[front];//front points before the data!it's empty!
            return tmp;
        }
        else{
            System.out.println("The queue is empty!");
            return -1;
        }
    }

    public static void main(String[] args){
        queue B=new queue();
        int tmp;
        B.enqueue(2);
        B.enqueue(3);
        B.enqueue(4);
        tmp=B.dequeue();
        System.out.println(tmp);
    }
}
