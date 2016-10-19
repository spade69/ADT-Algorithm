/**
 * Created by root on 9/9/16.
 */
import java.util.*;
public class popSequence {
    public static final int Max=1000;
    public String judgeSeq(int[] arr,int M){
        int len=arr.length;
        int index=0;
        Stack<Integer> s1=new Stack<Integer>();
        for(int i=0;i<len;++i){
            //need to push
            if(arr[i]>index){
                for(int j=index+1;j<=arr[i];j++){
                    s1.push(j);
                    //if overflow
                    if(s1.size()>M){
                        return "NO";
                    }
                }
                index=arr[i];
            }
            //exception when pop
            if(s1.empty()){
                return "NO";
            }
            //Normal pop
            int val=s1.pop();
            if(val!=arr[i]){
                //System.out.println("This");
                return "NO";
            }
        }
        return "YES";
    }

    public static void main(String[] args){
        int i=0;
        popSequence PS=new popSequence();
        Scanner s=new Scanner(System.in);
        int M=s.nextInt();//maxium capacity of the stack
        int N=s.nextInt();//length of push sequence
        int K=s.nextInt();//the number of pop sequence
        if(M<0||N<0||K<0||M>1000||N>1000||K>1000)
        {
            //System.out.println("Input Error");
            System.out.println("NO");
            return;
        }
        int[][] popArr=new int[K][N];
        int[] testArr={1,2,3,4,5,6,7};
        while(i<K){
            //Actually,it is not needed to use getLine.
            for(int j=0;j<N;j++)
                popArr[i][j]=s.nextInt();
            i++;
        }
        int count=0;
        while(count<K){
        String result;
        result=PS.judgeSeq(popArr[count],M);
        System.out.println(result);
        count++;
        }
    }
}
