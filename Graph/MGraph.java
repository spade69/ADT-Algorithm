import java.util.*;

public class MGraph{
    public static int MAXVERTEX=1000;
    public static int INFINITY=999999;
    public static int MINDATA=(-100000);
    private int e,n;
    private int[] vertice;
    private int[][] edges;
    private int[] path;
    private int[] dist;
    private boolean[] visited;
    //heap
    private int heapSize;
    //initialize
    public MGraph(int n,int e){
        this.n=n;
        this.e=e;
        edges=new int[n][n];
        vertice=new int[n];
        path=new int[n];
        dist=new int[n];
    }

    public void createGraph(){
        Scanner s=new Scanner(System.in);
        visited=new boolean[MAXVERTEX];
        System.out.println("Input vertice value,Integer!");
        for(int i=0;i<n;i++)
            vertice[i]=s.nextInt();
        for(int i=0;i<n;i++){
            dist[i]=INFINITY;//-1 not suitable for Dij
            path[i]=-1;
        }
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
                edges[i][j]=0;

        for(int k=0;k<e;k++){
            int i,j,weight;
            System.out.println("Input edges info, format : i j weight.");
            i=s.nextInt();j=s.nextInt();
            weight=s.nextInt();
            try{
             edges[i][j]=weight;
            }catch(Exception e){
                e.printStackTrace();
                return;
            }

        }
    }

    public void BFSFull(){
       LinkedList<Integer> q=new LinkedList<Integer>();
       for(int i=0;i<n;i++){//initalize!
           visited[i]=false;
       }
       for(int i=0;i<n;i++){//This is to deal with the graph that is not connected!
           if(!visited[i]){
               visited[i]=true;
               System.out.printf("visit vertex: %d!\n",vertice[i]);
               BFS(i);
           }

      }
    }

    public void BFS(int s)//source
    {
        LinkedList<Integer> q=new LinkedList<Integer>();
        q.add(s);
        while(q.size()!=0){
           int v=q.remove();
           for(int w=0;w<n;w++){
               if(!visited[w]&&edges[v][w]!=0){
                    visited[w]=true;
                    System.out.println(vertice[w]);
                    q.add(w);
               }
           }
        }
    }

    //Get shortest path
    //dist[w] s-->w , dist[s]=0; path[w]:vertex between s-->w
    public void Unweighted(int s,int dist[],int path[]){
        LinkedList<Integer> q=new LinkedList<Integer>();
        q.add(s);
        while(q.size()!=0){
            int v=q.remove();
            for(int w=0;w<n;w++){//dist[w] all initialize to -1
                if(edges[v][w]!=0&&dist[w]==INFINITY){
                    dist[w]=dist[v]+1;
                    path[w]=v;
                    q.add(w);
                }
            }
        }
    }

//Floyd or run Dijsktra on every node in Graph
//Floyd calculate every Shortest Path between arbitary two node
    public void floyd(){
        int[][] D=new int[n][n];
        int[][] path=new int[n][n];
       for(int i=0;i<n;i++)
           for(int j=0;j<n;j++){
               D[i][j]=edges[i][j];
               path[i][j]=-1;
            }

       for(int k=0;k<n;k++)
           for(int i=0;i<n;i++)
               for(int j=0;j<n;j++){
                   if(D[i][k]+D[k][j]<D[i][j])
                       D[i][j]=D[i][k]+D[k][j];
                   //k 0-> n-1, then we found the best k
                   //but the path[i][j]=k only one ,you should recursive figure out i..k,k..j
                       path[i][j]=k;
               }
    }
    //w is dest node . path[w]
    public void printPath(int[] path,int s,int w){
        if(path[w]!=s){
            printPath(path,s,path[w]);
            System.out.println(path[w]);
            System.out.println("---->");
        }else{
            System.out.println(path[w]);
            System.out.println("--->");
        }
    }

   // public void createHeap(int[])

    public int extractMin(int[] heap){
       //store dist array in a heap, MinHeap
       int parent,child;
       int MinItem,temp;
       if(heap.length<1){
           System.out.println("Error! dist is empty");
           return -1;
       }
       int size=heap.length-1;//the number of vertex in graph!correct!
       MinItem=heap[1];//root
       temp=heap[size--];//last ele in MinHeap
       for(parent=1;parent*2<=size;parent=child){
           child=parent*2;
           if((child!=size)&&heap[child]>heap[child+1])
               child++;
           if(temp<=heap[child]) break;
           else{
               heap[parent]=heap[child];
           }
       }
       heap[parent]=temp;
       return MinItem;
    }

    public void Insert(int[] heap,int item){
        int i;
        //int size=heap.length;//if size=0,
        i=++heapSize;
        for(;heap[i/2]>item;i/=2)
            heap[i]=heap[i/2];
        heap[i]=item;
    }

    //Dijkstra 前驱 how to represent?
    public void Dijkstra(int s){
       //dist INFINITY
        boolean[] collected=new boolean[n];
        int[] heap=new int[n+1];
        dist[s]=0;
        path[s]=s;
        heap[0]=MINDATA;//哨兵
        for(int i=0;i<n;i++){//initialize s0's adjnode!
         collected[i]=false;//initialize
            if(edges[s][i]>0&&i!=s){
                dist[i]=edges[s][i];
                Insert(heap,dist[i]);
                path[i]=s;
            }
        }
        while(true){
          // heap store the index of correspongding weight array?
          // int v=extractMin(heap);//heap now store weights
           if(collected[v]==true)
                break;
            collected[v]=true;
            for(int w=0;w<n;w++){
                if(edges[v][w]!=0&&collected[w]==false){
                    if(dist[v]+edges[v][w]<dist[w]){
                        dist[w]=dist[v]+edges[v][w];
                        Insert(heap,dist[w]);
                        path[w]=v;// v is front node of w
                    }
                }
            }
        }
    }

    public static void main(String[] args){
        Scanner s=new Scanner(System.in);
        LinkedList l=new LinkedList();
        // n e
        System.out.println("Input n and e:");
        int n=s.nextInt();
        int e=s.nextInt();
        MGraph g=new MGraph(n,e);
        g.createGraph();
       // g.BFSFull();
       // g.Unweighted(0,g.dist,g.path);
       // g.printPath(g.path,0,3);//w=dest
       // int[] heap=new int[n+1];//heap[0] doesn't store!
       // heap[0]=MINDATA;//哨兵
        g.heapSize=0;
       /*
        g.Insert(heap,5);
        g.Insert(heap,3);
        g.Insert(heap,10);
        g.Insert(heap,2);
        g.Insert(heap,6);
        int result=g.extractMin(heap);
        System.out.println(result);*/
        g.Dijkstra(0);

    }
}
