#include<iostream>
using namespace std;

typedef struct node{
    struct node *next;
    int Adjv;
}EdgeNode;

EdgeNode* InsertSort(EdgeNode* head){
    EdgeNode* first;
    EdgeNode* t;//temporary variable points to first
    EdgeNode* p;//
    EdgeNode* q;//points to head

    first=head->next;//first represent the list that not in order
    head->next=NULL;//只有一个节点的链表的有序链表

    while(first!=NULL){
        //在无序链表中查找插入到有序链表的位置
        for(t=first,q=head;(q!=NULL)&&(q->Adjv<t->Adjv);p=q,q=q->next);
        //退出for循环就找到了s
        first=first->next;//无序链表下一个节点。
        if(q==head){//q一直比t的adjx要大
            head=t;//插到前面,替换之前的head
       }
        else{//p=q~
            p->next=t;
        }
        t->next=q;
    }
    return head;
}

int main(){

    return 0;
}
