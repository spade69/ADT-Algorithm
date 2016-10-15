#include<iostream>
#define MaxVertexNum 100
#define INFINITY 999999
//
using namespace std;

enum GraphType{DG,UG,DN,UN};
bool visited[MaxVertexNum]={0};

typedef struct node{//边表 结点（边结点）
    int AdjV;   //邻接点域,一般是表示节点的下标
    struct node *Next;//指向下一个邻接点的指针域
}EdgeNode;

typedef char VertexType;

typedef struct Vnode{//顶点表结点
    VertexType Vertex; //顶点域，一般是顶点的信息
    EdgeNode *FirstEdge; //Edge 表头指针
}VertexNode;

typedef VertexNode AdjList[MaxVertexNum];//AdjList是邻接表类型
typedef struct{
    AdjList adjlist;//顶点数组！
    int n,e;
    enum GraphType GType;
}ALGraph;

EdgeNode* InsertSort(EdgeNode* head);

void CreateALGraph(ALGraph *G){
    EdgeNode *edge,*edgex;
    G->GType=DG;
    cout<<"Please input  vexnum and edgenum format:vexnum edgenum"<<endl;
    cin>>G->n; cin>>G->e;
    cout<<"please input vextex info"<<endl;
    for(int i=0;i<G->n;i++){
        cin>>G->adjlist[i].Vertex;//Build n Vexnode's table, read the vertex info
        G->adjlist[i].FirstEdge=NULL;//All initialize to NULL
    }
    cout<<"Input edge info,format: i j"<<endl;
    //intial
    for(int k=0;k<G->e;k++){//input k groups data-pair
        int i,j;
        cin>>i;cin>>j;
        edge=new EdgeNode;
        edge->AdjV=j;//邻接点序号为j
        edge->Next=G->adjlist[i].FirstEdge;
        //将新的边表点edge插入到顶点Vi的边表头部。
        G->adjlist[i].FirstEdge=edge;

        /*Undirected Graph add on, actually add one more edgenode!*/
        edgex=new EdgeNode;
        edgex->AdjV=i;//
        edgex->Next=G->adjlist[j].FirstEdge;
        G->adjlist[j].FirstEdge=edgex;
    }

    for(int i=0;i<G->n;i++){
        EdgeNode *tmp=G->adjlist[i].FirstEdge;
        if(tmp!=NULL)
        G->adjlist[i].FirstEdge=InsertSort(tmp);
    }
}


//DFS on one node
void DFS(ALGraph *G,int i){
    //Vi为出发点对邻接表存储的图G进行DFS搜索
    EdgeNode *W;
    int min=INFINITY;
    cout<<"visit vertex:"<<G->adjlist[i].Vertex<<endl;
    //i th  node.
    visited[i]=1;
    /***Find the min ***
    for(W=G->adjlist[i].FirstEdge;W;W=W->Next){
        if(min>W->AdjV)
            min=W->AdjV;
    }*/

    for(W=G->adjlist[i].FirstEdge;W;W=W->Next)
        if(!visited[W->AdjV])
            DFS(G,W->AdjV);

}

//DFS traversal
void DFSTraverse(ALGraph *G){
    int i;
    for(int i=0;i<G->n;i++)
        visited[i]=0;
    for(int i=0;i<G->n;i++)
    {
        if(!visited[i])
        {
            cout<<"connected"<<endl;
            DFS(G,i);

        }
    }
}

void BFS(ALGraph *G){
   EdgeNode *W;

}

//sort the linked list
EdgeNode* InsertSort(EdgeNode* head){
    EdgeNode* first;
    EdgeNode* t;//temporary variable points to first
    EdgeNode* p;//
    EdgeNode* q;//points to head

    first=head->Next;//first represent the list that not in order
    head->Next=NULL;//只有一个节点的链表的有序链表

    while(first!=NULL){
        //在无序链表中查找插入到有序链表的位置
        for(t=first,q=head;(q!=NULL)&&(q->AdjV<t->AdjV);p=q,q=q->Next);
        //退出for循环就找到了s
        first=first->Next;//无序链表下一个节点。
        if(q==head){//q一直比t的adjx要大
            head=t;//插到前面,替换之前的head
       }
        else{//p=q~
            p->Next=t;
        }
        t->Next=q;
    }
    return head;
}



int main(){
    ALGraph *G=new ALGraph;
    CreateALGraph(G);
    EdgeNode *result=new EdgeNode;
   // cout<<result->AdjV<<endl;
    DFSTraverse(G);
    return 0;
}




