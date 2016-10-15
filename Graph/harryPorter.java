import java.util.*;

public class harryPorter{
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
    public harryPorter(int n,int e){
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
        for(int i=0;i<n;i++)
            vertice[i]=i+1;//i from 1 to n coding
        for(int i=0;i<n;i++){
            dist[i]=INFINITY;//-1 not suitable for Dij
            path[i]=-1;
        }
        //initialize
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
                edges[i][j]=INFINITY;//

        for(int k=0;k<e;k++){
            int m,n,weight;
            //
            m=s.nextInt()-1;n=s.nextInt()-1;
            weight=s.nextInt();
            if(weight>100||weight<=0){
                System.out.println("Error");
                return;
            }
            try{
             edges[m][n]=weight;
             edges[n][m]=weight;//undirectd!
            }catch(Exception e){
                e.printStackTrace();
                return;
            }

        }
    }


//Floyd or run Dijsktra on every node in Graph
//Floyd calculate every Shortest Path between arbitary two node
    public void floyd(){
        int[][] D=new int[n][n];
        int[][] path=new int[n][n];
       for(int i=0;i<n;i++){
           for(int j=0;j<n;j++){
               D[i][j]=edges[i][j];
               path[i][j]=-1;
            }
        }

       for(int k=0;k<n;k++){
           for(int i=0;i<n;i++){
               for(int j=0;j<n;j++){
                   if(D[i][k]+D[k][j]<D[i][j])
                       D[i][j]=D[i][k]+D[k][j];
                   //k 0-> n-1, then we found the best k
                   //but the path[i][j]=k only one ,you should recursive figure out i..k,k..j
               //        path[i][j]=k;
               }
            }
       }

        int flag=0;
        int num=INFINITY;
        int mindis=INFINITY;//99999
       //output data

       for(int i=0;i<n;i++){
            int maxdis=-1;
            for(int j=0;j<n;j++){
                if(j==i)
                    continue;
                if(D[i][j]==INFINITY) break;

                    maxdis=(D[i][j]>maxdis)?D[i][j]:maxdis;
                    System.out.println("maxdis :");
                    System.out.println(maxdis);
            }
            if(flag==1) break;
            if(mindis>maxdis){
                mindis=maxdis;
                num=i+1;//find the min  MOST difficult to find animal
            }

       }
       if(flag==1){
           System.out.println(0);
       }
       else{
           System.out.printf("%d %d",num,mindis);

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

       //反方向变化的魔咒就是简单的反向念，所以是一个无向图。只要能从
    //A-B， B-》A也必然成立，而且权值完全相同
    public static void main(String[] args){
        Scanner s=new Scanner(System.in);
        LinkedList l=new LinkedList();
        int n=s.nextInt();//N
        int e=s.nextInt();//M means edges , because it's undirected graph
        if(n<=0||n>100||e<=0){
            System.out.println("Out of range");
            return;
        }
        if(n<=1)
        {
            n=0;
            System.out.println(n);
            return;
        }
        harryPorter g=new harryPorter(n,e);
        g.createGraph();
        g.floyd();
    }

}
