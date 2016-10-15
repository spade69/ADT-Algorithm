#include<iostream>
#define MaxSize 10000
using namespace std;

typedef struct{
    int Data;
    int Parent;
}SetType;

//set -find
int Find(SetType S[],int X)
{//在数组S中查找值为X的元素所属集合
    int i=0;
    for(i=0;i<MaxSize&&S[i].Data!=X;i++);
    if(i>=MaxSize) return -1;
        //parent=-1 为根节点
    for(;S[i].Parent>=0;i=S[i].Parent);
        return i;

}
//union means combine two set.
void Union(SetType S[],int X1,int X2){
    int Root1,Root2;
    Root1=Find(S,X1);
    Root2=Find(S,X2);
    if(Root1!=Root2)
        S[Root2].Parent=Root1;
}


int main(){
    return 0;
}
