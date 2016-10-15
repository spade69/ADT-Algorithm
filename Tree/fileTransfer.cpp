#include<iostream>
#include<cstdio>
#define MAXSIZE 10000
using namespace std;

typedef struct{
    int Data;
    int Parent;//index not data!
}SetType;

    //Find root of the SET!
int Find(SetType S[],int x){
    int i;
    for(i=0;i<MAXSIZE&&S[i].Data!=x;i++);
    if(i>=MAXSIZE) return -1;//-1 means
    for(;S[i].Parent>=0;i=S[i].Parent);
    return i;
}

bool check(SetType S[],int x1,int x2){
    int root1,root2;
    root1=Find(S,x1);
    root2=Find(S,x2);
    if(root1==-1&&root2==-1) return false;
    return (root1==root2)?true:false;
}

int countConnected(SetType S[],int N){
   int index=0,count=0;
   int* root=new int[N];
   int* flag=new int[N];
   for(int i=0;i<N;i++){
       if(Find(S,i)!=-1){
           root[index]=Find(S,i);
           flag[root[index]]=1;//record
           index++;
       }
   }
   if(index==0)
       return 0;
   for(int i=0;i<index;i++){
       if(flag[i]==1)
           count++;
   }

   return count;
}

int main(){
    int N;//number of nodes in a network,less than MAXSIZE
    SetType S[MAXSIZE];
    cin>>N;
    if(N>MAXSIZE||N<2){
        cout<<"Number out of range!"<<endl;
        return -1;
    }
    for(int i=0;i<N;i++){
        S[i].Data=i+1;
    }
    for(int i=0;i<MAXSIZE;i++)
        S[i].Parent=-1;

    char ch='a';
    int count=0;
    while(1){
        cin>>ch;
        if(ch=='S')
            break;
        int m,n;
        cin>>m;
        cin>>n;
        //cout<<ch;

        if(ch=='I')
        {
            S[m-1].Parent=n-1;//store the index not data!
        }
        else if(ch=='C'){
            bool result=check(S,m,n);
            if(result)
                cout<<"yes"<<endl;
            else
                cout<<"no"<<endl;
        }
    }//while
    int k=countConnected(S,N);
    cout<<"k is: "<<k<<endl;
    return 0;
}


