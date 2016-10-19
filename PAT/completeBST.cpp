#include<iostream>
#include<algorithm>
#include<cmath>
#define MAX 1001
using namespace std;
/*
struct Node{
    int val;
    struct Node* left;
    struct Node* right;
    Node(int x):
        val(x),left(NULL),right(NULL){}
};*/
int Arr[MAX];
int levelOrder[MAX];
int pos;
//Calculate the left subtree's nodes
int getLength(int n){
    int L;

    return 0;
}
//qsort from small to large
int compare(const void *a,const void *b){
    return *(int*)a-*(int*)b;
}
//recursive store
void Solve(int Aleft,int Aright,int root){
    int n=Aright-Aleft+1;//n means the length of current sequence
    if(n==0)
        return;
    int L=getLength(n);
    levelOrder[root]=Arr[Aleft+L];
    int lRoot=2*root+1;
    int rRoot=lRoot+1;
    Solve(Aleft,Aleft+L-1,lRoot);
    Solve(Aleft+L+1,Aright,rRoot);
}

//只要按中序来做，只有完全二叉树符合，左右孩子分别为2i 2i+1,按这个性质构造出来的
//树就肯定是一颗完全二叉树了
void build(int root,int n){//normally root is 1
    if(root>n)//递归直到2*i大于n，
        return;
    int lson=root<<1;
    int rson=(root<<1)+1;
    build(lson,n);//InOrder traversal, 按中序的顺序把节点存进新数组
    levelOrder[root]=Arr[pos++];//levelorder 0 是初始化的值，应该从1开始计数
    build(rson,n);
}

int main(){
    //N<=1000 key<=2000
    int N;
    pos=0;
    cin>>N;
    if(N>1000||N<=0)
    {
        cout<<"Error"<<endl;
        return -1;
    }
    for(int i=0;i<N;i++){
        cin>>Arr[i];
    }

    qsort(Arr,N,sizeof(int),compare);
    //cout<<Arr[0]<<endl;
    //root 从1开始递归，1 2 4 8 16...
    build(1,N);
    cout<<levelOrder[1];
    for(int i=2;i<=N;i++)
        cout<<" "<<levelOrder[i];
    cout<<endl;
    return 0;
}
