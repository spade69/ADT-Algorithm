package Sort;
import java.util.*;
//O(NlogN) demerits: need extra space O(n) or more(System stack)
public class MergeSort {
	//L 左边起始位置   R 右边起始位置  rightEnd右边终点位置
	public void Merge(int[] A,int[] tmpA,int L,int R,int rightEnd){
		int leftEnd=R-1;  //左边终点位置。假设左右两列挨着
		int tmp=L; //存放结果的数组的初始位置
		int numElements=rightEnd-L+1;
		while(L<=leftEnd&&R<=rightEnd){
			if(A[L]<=A[R])
				tmpA[tmp++]=A[L++];
			else 
				tmpA[tmp++]=A[R++];
		}
		while(L<=leftEnd)//直接复制左边剩下的
			tmpA[tmp++]=A[L++];
		while(R<=rightEnd)
			tmpA[tmp++]=A[R++];
		for(int i=0;i<numElements;i++,rightEnd--)
			A[rightEnd]=tmpA[rightEnd];
	}
	
	public int[] mergesort(int[] A,int[] tmpA,int low,int high){
		int mid=(low+high)/2;
		if(low<high){
			mergesort(A,tmpA,low,mid);
			mergesort(A,tmpA,mid+1,high);
			Merge(A,tmpA,low,mid+1,high);
		}
		return A;
	}
	
	public static void main(String[] args){
		int[] A={2,7,3,1,6,8,12,4};
		int[] tmpA=new int[A.length];
		MergeSort s=new MergeSort();
		A=s.mergesort(A,tmpA,0,7);
		for(int i=0;i<8;i++)
		System.out.println(A[i]);
	}
}
