#include<iostream>
#define MinData (-100000)
using namespace std;

typedef struct HeapStruct *MinHeap;
struct HeapStruct{
    int *Ele;//array that store the heap
    int Size;
    int Capacity;
};

MinHeap Create(int MinSize){
    MinHeap H=new HeapStruct;
    H->Ele=new int[MinSize+1];
    H->Size=0;
    H->Capacity=MinSize;
    H->Ele[0]=MinData;//哨兵
    return H;
}

bool IsFull(MinHeap H){
    return (H->Size==H->Capacity);
}

bool IsEmpty(MinHeap H){
    return (H->Size==0);
}

//O(lgn)
void Insert(MinHeap H,int item){
    int i;
    if(IsFull(H)){
        cout<<"already FUll!"<<endl;
        return;
    }
    i=++H->Size;//i从size+1开始
    //H->Ele[0]是哨兵元素，它不大于堆的最小元素，控制循环结束
    for(;H->Ele[i/2]>item;i/=2)//如果item比parent小，就把parent移下来
        H->Ele[i]=H->Ele[i/2];
    H->Ele[i]=item;//把item赋给parent，此时i可能已经上移了若干次
}


//如果 2i  2i+1的值大于size了，就说明已经超出这个堆的范围了
//表示没有左儿子或右儿子
//取出根节点元素，同时删除堆的一个节点。
//根节点拿出来，然后用最后的一个元素来替代根的位置，显然不行，
//一步一步比较挪动到儿子
int DeleteMin(MinHeap H){
   int Parent,Child;
   int MinItem,temp;
   if(IsEmpty(H)){
        cout<<"最大堆已空"<<endl;
        return -1;
   }
   MinItem=H->Ele[1];//取出根节点
   temp=H->Ele[H->Size--];//用最小堆中最后一个元素从根节点开始向下过滤
   //如果Parent*2超出了范围，就表示没有左儿子
   for(Parent=1;Parent*2<=H->Size;Parent=Child){
       Child=Parent*2;
       if((Child!=H->Size)&&(H->Ele[Child]>H->Ele[Child+1]))
           Child++;//取小的儿子
       if(temp<=H->Ele[Child]) break;//比最小的儿子的值还要小
       else{//将儿子上移到父亲节点处
           H->Ele[Parent]=H->Ele[Child];
       }

   }
    H->Ele[Parent]=temp;
    return MinItem;
}

void print2Root(MinHeap H,int index){
    int i=index>>1;
    cout<<H->Ele[index];
    while(i>0){
        cout<<" "<<H->Ele[i];
        i=i>>1;//i/=2
    }
    cout<<endl;
}

int main(){
    int N,M;
    cin>>N;cin>>M;
    if(N<=0||N>1000||M<=0||M>1000||M>N)
    {
        cout<<"Input Error"<<endl;
        return -1;
    }
    MinHeap H=Create(N);
    for(int k=0;k<N;k++){
        int X;
        cin>>X;
        if(X<-10000||X>10000){
            cout<<"Data's range [-10000,10000]"<<endl;
            return -1;
        }
        Insert(H,X);
    }
    for(int j=0;j<M;j++){
        int tmp;
        cin>>tmp;
        if(tmp<=0||tmp>N){
            cout<<"Erro "<<endl;
            return -1;
        }

        print2Root(H,tmp);
    }
    cout<<endl;
    return 0;
}
