import java.util.*;
/*
 * 二分查找！
 * Return the index
 *NotFound : -1
 * */
public class BinSearch{
    public int BinarySearch(int[] Table,int k){
        int left,right,mid;
        left=0;
        right=Table.length-1;
        while(left<=right){
            mid=(left+right)/2;
            if(Table[mid]>k)
                right=mid-1;
            else if(Table[mid]<k)
                left=mid+1;
            else
                return mid;
        }
        return -1;
    }
    public static void main(String[] args){
        BinSearch bin=new BinSearch();
        int result;
        int[] arrx={1,2,3,4,5,6};
        //ArrayList arr=new ArrayList();
        result=bin.BinarySearch(arrx,4);
        System.out.println(result);
    }

}
