#include<iostream>
#define MaxVertexNum 100 //最大顶点数
#define INFINITY 65535  //设为双字节无符号整数的最大值65535
using namespace std;
typedef char VertexType; //顶点类型设为字符型
typedef int EdgeType;  //边的权值
enum GraphType{DG,UG,DN,UN}; //枚举，有向图，无向图，有向网图，无向网图。

typedef struct {
    VertexType Vertices[MaxVertexNum];//顶点表
    EdgeType Edges[MaxVertexNum][MaxVertexNum];//邻接矩阵，边表
    int n,e;
    enum GraphType GType;//图的4种类型
}MGraph;

void CreateMGraph(MGraph *G){
    int i,j,k,w;
    G->GType=UN;//undirected Nextwork
    cout<<"Please input number of Vertex: n and Number of edges: e"<<endl;
    cin>>G->n;cin>>G->e;
    cout<<"Input the info of Vertex"<<endl;
    for(int i=0;i<G->n;i++){
        cin>>G->Vertices[i];//输入顶点信息，建立顶点表
    }
    for(int i=0;i<G->n;i++){
        for(int j=0;j<G->n;j++)
            G->Edges[i][j]=INFINITY;//初始化矩阵
    }
    cout<<"Please input the weight and number format: i, j, w:\n"<<endl;
}



