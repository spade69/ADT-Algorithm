#include<iostream>
#define MaxData 1000
using namespace std;

typedef struct HeapStruct *MaxHeap;
struct HeapStruct{
    int *Ele;//array that store the heap
    int Size;
    int Capacity;
};

MaxHeap Create(int MaxSize){
    MaxHeap H=new HeapStruct;
    H->Ele=new int[MaxSize+1];
    H->Size=0;
    H->Capacity=MaxSize;
    H->Ele[0]=MaxData;//哨兵
    return H;
}

bool IsFull(MaxHeap H){
    return (H->Size==H->Capacity);
}

bool IsEmpty(MaxHeap H){
    return (H->Size==0);
}

//O(lgn)
void Insert(MaxHeap H,int item){
    int i;
    if(IsFull(H)){
        cout<<"already FUll!"<<endl;
        return;
    }
    i=++H->Size;
    //H->Ele[0]是哨兵元素，它不小于堆的最大元素，控制循环结束
    for(;H->Ele[i/2]<item;i/=2)//
        H->Ele[i]=H->Ele[i/2];
    H->Ele[i]=item;
}


//如果 2i  2i+1的值大于size了，就说明已经超出这个堆的范围了
//表示没有左儿子或右儿子
//取出根节点元素，同时删除堆的一个节点。
//根节点拿出来，然后用最后的一个元素来替代根的位置，显然不行，要一
//一步一步比较挪动到儿子
int DeleteMax(MaxHeap H){
   int Parent,Child;
   int MaxItem,temp;
   if(IsEmpty(H)){
        cout<<"最大堆已空"<<endl;
        return -1;
   }
   MaxItem=H->Ele[1];//取出根节点
   temp=H->Ele[H->Size--];//用最大堆中最后一个元素从根节点开始向下过滤
   //如果Parent*2超出了范围，就表示没有左儿子
   for(Parent=1;Parent*2<=H->Size;Parent=Child){
       Child=Parent*2;
       if((Child!=H->Size)&&(H->Ele[Child]<H->Ele[Child+1]))
           Child++;//取大的儿子
       if(temp>=H->Ele[Child]) break;//比最大的儿子的值还要大
       else{
           H->Ele[Parent]=H->Ele[Child];
       }

   }
    H->Ele[Parent]=temp;
    return MaxItem;
}

int main(){
    MaxHeap H=Create(5);
    cout<<H->Ele[0]<<endl;
    cout<<H->Ele[1]<<endl;
    cout<<H->Size<<" "<<H->Capacity<<endl;

    return 0;
}
