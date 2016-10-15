#include<iostream>
using namespace std;

typedef struct TreeNode *HuffmanTree;
struct TreeNode{
    int Weight;
    HuffmanTree Left,Right;
}

//O(NlgN)
HuffmanTree Huffman(MinHeap H){
    //假设H->Size个权值已经存在 H->Ele[i]->Weight里面
    int i; HuffmanTree T;
    BuildMinHeap(H);//建立一个堆！
    for(int i=1;i<H->Size;i++){
        T=new TreeNode;
        T->Left=DeleteMin(H);//取出两个最小的
        T->Right=DeleteMin(H);
        //并把权值加起来赋给T的weight
        T->Weight=T->Left->Weight+T->Right->Weight;
        Insert(H,T);//将新的T插入最小堆
    }
    T=DeleteMin(H);
    return T;
}
