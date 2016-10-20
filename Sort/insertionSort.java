package Sort;

import java.util.*;
//heap sort is the better version of InsertionSort!
public class insertionSort {
	public static final int INFINITY=99999;
	//合法的交换！
	void swap(int[] A,int a,int b){
		int tmp= A[a];
		A[a]=A[b];
		A[b]=tmp;
	}
	public void insertionsort(int[] A){
		int N=A.length;
		for(int i=0;i<N;i++){
			//MinPosition=ScanForMin(A[],i,N-1)
			int Min=INFINITY;
			int MinPos=-1;
			for(int k=i;k<N;k++){
				if(Min>A[k])
				{
					Min=A[k];
					MinPos=k;
				}
			}
			int tmp=A[i];
			A[i]=A[MinPos];
			A[MinPos]=tmp;
		}
	}
	public static void main(String[] args){
		int[] A={44,12,42,23,32,5,99,1};
		insertionSort h1=new insertionSort();
		h1.insertionsort(A);
		for(int i=0;i<A.length;i++)
			System.out.println(A[i]);
	}
}
