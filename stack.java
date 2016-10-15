import java.util.*;
/*
Data:2016
Author:Jason
Stack implemented In Java
*/

public class stack{
    public static final int MAX=1000;
    private int[] arr=new int[MAX];
    protected int top=-1;
    //private ArrayList<Integer> storage=new ArrayList<Integer>();
    public void push(int v){
        if(size()==MAX)
            System.out.println("Error is full!");
        arr[++top]=v;
    }

    public int size(){
        return top+1;
    }

    public boolean isEmpty(){
        return(top<0);
    }

    public int pop(){
        int ele;
        if(isEmpty())
            System.out.println("Stack is empty!");
        ele=arr[top];
        top--;//dereference arr[top] for garbage collection
        return ele;
    }

    public static void main(String[] args){
        //using包装类！ 而不是 int
        //Integer String 等都是引用类
/*        ArrayList<Integer> arr=new ArrayList<Integer>();
        arr.add(1);
        arr.add(2);
        System.out.println(arr.get(1));
        //arr[0] 不合法！ 必须是数组才合法
        int[] arrx=new int[10];
        arrx[0]=20;
*/
        stack A=new stack();
        A.push(2);
        A.push(3);
        A.push(10);
        A.pop();
        System.out.println(A.size());

    }
}

