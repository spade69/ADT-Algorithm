#include<iostream>
#include<cmath>
#define MAXVERTEX 1000
#define YES 1
#define NO 0
using namespace std;
bool visited[MAXVERTEX];

typedef struct node{
    int x;
    int y;
}Node;

//Node adjlist[MAXVERTEX];


bool firstJump(Node v,Node adjlist[],int N,int D){
//find Node v the coordinate of James the next node he can jump,
//so you shoule calculate D and w(x,y) each node in this map
   for(int i=0;i<N;i++){
      int tmp;
      tmp=sqrt((v.x-adjlist[i].x)*(v.x-adjlist[i].x)+(v.y-adjlist[i].y)*(v.y-adjlist[i].y));
      if(tmp>0&&tmp<=D)
          return true;

   }
   return false;
}

//Not necesarily to store the graph?
bool IsSafe(Node v,int D){
    if(abs(v.x)-50<=D||abs(v.y)-50<=D)
        return true;
    return false;
}

//DFS,After firstJump,so we already choose our first step,first node
//Then we consider left nodes,if they are reachable, and recursive
//
int DFS(Node adjlist[],int i,int N,int D){
    int answer=NO;
   visited[i]=true;
   if(IsSafe(adjlist[i],D))
       answer=YES;
   else{
        for(int j=0;j<N&&i!=j;j++){//i!=j, and must reachable,
            //In fact, we just need to figure out one way,we don't need to
            //figure out all kind of roads,so out search is not global-search
            if(!visited[j]&&firstJump(adjlist[j],adjlist,N,D)){
                answer=DFS(adjlist,j,N,D);
                if(answer==YES)
                    break;
            }
        }//for each j ,node except adjlist[i]
   }//else
   return answer;
}


void James007(Node adjlist[],int N,int D){
    int result;
   //every node judge if it's safe?
    for(int i=0;i<N;i++){
        //filter several node that can reach
        //We consider every node as starting point of James Bound
        //So Here we DFS N times.Because of N starting point.
        if(!visited[i]&&firstJump(adjlist[i],adjlist,N,D)){
            result=DFS(adjlist,i,N,D);
        }
    }
    if(result==YES)
        cout<<"YES"<<endl;
    else
        cout<<"NO"<<endl;
}

int main(){
    int N,D;
    Node adjlist[MAXVERTEX];
    for(int i=0;i<MAXVERTEX;i++)
        visited[i]=false;
    cin>>N;
    if(N<=0||N>100)
        return -1;
    cin>>D;
    cout<<N<<endl;
    cout<<D<<endl;
    //create array
    for(int k=0;k<N;k++){
        cin>>adjlist[k].x;
        cin>>adjlist[k].y;
    }
    James007(adjlist,N,D);
    return 0;
}
