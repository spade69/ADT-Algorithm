import java.util.*;

//arcNode
class ANode{
    int adjV;
    ANode nextArc;
    int weight;
}

//VertexNode
class VNode{
    int Vertex;//
    ANode firstArc;
}

//in one java file only can have one public class
public class ALGraph{
    VNode[] adjlist;
    private int n;
    private int e;
    public static int MAXVERTEX=1000;
    public static int INFINITY=999999;
    public static int MINDATA=(-100000);
    private int[] path;
    private int[] dist;
    private boolean[] visited;

    public ALGraph(int n,int e){
        this.n=n;
        this.e=e;
        path=new int[n+1];//
        dist=new int[n+1];//distance or sum of weight between s and destination
        adjlist=new VNode[n];
        for(int i=0;i<n;i++)//from 1 to n,not 0!
            adjlist[i]=new VNode();

    }

    public boolean createGraph(){
        Scanner s=new Scanner(System.in);
        visited=new boolean[MAXVERTEX];
        ANode edge,edgex;
       for(int i=0;i<n;i++){
            int tmp=s.nextInt();
            if(tmp>=1&&tmp<=10)
            adjlist[i].Vertex=tmp;//satisfation point
            else{
                System.out.println("error input,out of range");
                return false;
            }
       }
        for(int i=0;i<n;i++){
            dist[i]=INFINITY;//-1 not suitable for Dij
            adjlist[i].firstArc=null;
        }
        for(int k=0;k<e;k++){
            int m,d;
            m=s.nextInt()-1;d=s.nextInt()-1;//weight=s.nextInt()
            edge=new ANode();
            edge.adjV=d;
            edge.nextArc=adjlist[m].firstArc;
            adjlist[m].firstArc=edge;

            //undirected graph
            edgex=new ANode();
            edgex.adjV=m;
            edgex.nextArc=adjlist[d].firstArc;
            adjlist[d].firstArc=edgex;
        }
        return true;
    }

    public void printPath(){
        for(int i=0;i<n;i++){
            ANode edge=adjlist[i].firstArc;
            VNode verX=adjlist[i];
            while(edge!=null){
                System.out.printf("%d -->",verX.Vertex);
                verX=adjlist[edge.adjV];
                edge=edge.nextArc;
            }
            System.out.println(verX.Vertex);
        }
    }

    //DP solution of wireless routers, not minmal spanning tree
    //router works indepently means we calculate sum of satisfaction for each router!

    //
    public boolean isConnected(){
        for(int i=0;i<n;i++)
            if(visited[i]==false)
                return false;
        return true;
    }
    //Basic Max calculation func, from one router to
    /*
    public int subMax(int[] storage){
        //graph
        int max=MINDATA;
        int index=-1;
       for(int i=0;i<n;i++) {
            if(storage[i]>max&&!visited[i]){
                max=storage[i];
                index=i;
            }
       }
       visited[index]=true;
       return max;
    }*/

    public int subMaxEx(){
        int max=MINDATA;
        int index=-1;
        for(int i=0;i<n;i++) {
            ANode edge=adjlist[i].firstArc;
            if(visited[i])
                continue;//end i,because it already visied
            int sum=adjlist[i].Vertex;
            while(edge!=null){
                if(!visited[edge.adjV])
                {sum+=adjlist[edge.adjV].Vertex;}
                edge=edge.nextArc;
            }

            if(sum>max&&!visited[i]){
                max=sum;
                index=i;
            }

        }
        //only here we do the check
        visited[index]=true;
        ANode edgex=adjlist[index].firstArc;
        while(edgex!=null){
            visited[edgex.adjV]=true;
            edgex=edgex.nextArc;
        }
        return max;

    }

    public int findMaxSatisfaction(int routers){
        //initialize a table
        int[] storage=new int[n];
        int maxSatisfaction=0;
        int M=0;//the number that routers cover all rooms
        for(int i=0;i<n;i++){
            ANode edge=adjlist[i].firstArc;
            int sum=adjlist[i].Vertex;
            while(edge!=null){
                sum+=adjlist[edge.adjV].Vertex;
                edge=edge.nextArc;
            }
            storage[i]=sum;
        }
        //Begining DP
        if(routers==1)
           // maxSatisfaction=subMax(storage);
           maxSatisfaction=subMaxEx();
        else{
            for(int k=0;k<routers;k++){
                //visited[]
                if(isConnected())
                {
                    M=k;//the graph already cover in M rounters
                    break;
                }
                maxSatisfaction+=subMaxEx();
            }
        }

        if(routers>=M&&M!=0){
            int sumx=0;
            for(int i=0;i<n;i++)
                sumx+=adjlist[i].Vertex;
            maxSatisfaction=sumx;
        }

        return maxSatisfaction;
    }

    public static void main(String[] args){
        Scanner s=new Scanner(System.in);
        int n=s.nextInt();
        int router=s.nextInt();
        int maxSatisfaction;
        if(n<2||n>1000||router<0||router>n||router>100)
        {
            System.out.println("Out of range!");
            return;
        }
        int e=n-1;//n-1 lines
        ALGraph g=new ALGraph(n,e);
        if(!g.createGraph())
            return;
//        g.printPath();
        maxSatisfaction=g.findMaxSatisfaction(router);
        System.out.println(maxSatisfaction);
    }
}
