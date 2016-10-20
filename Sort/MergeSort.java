package Sort;
import java.util.*;
//O(NlogN) demerits: need extra space O(n) or more(System stack)
public class MergeSort {
	//L �����ʼλ��   R �ұ���ʼλ��  rightEnd�ұ��յ�λ��
	public void Merge(int[] A,int[] tmpA,int L,int R,int rightEnd){
		int leftEnd=R-1;  //����յ�λ�á������������а���
		int tmp=L; //��Ž��������ĳ�ʼλ��
		int numElements=rightEnd-L+1;
		while(L<=leftEnd&&R<=rightEnd){
			if(A[L]<=A[R])
				tmpA[tmp++]=A[L++];
			else 
				tmpA[tmp++]=A[R++];
		}
		while(L<=leftEnd)//ֱ�Ӹ������ʣ�µ�
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
