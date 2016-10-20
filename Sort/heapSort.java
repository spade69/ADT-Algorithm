package Sort;
import java.util.*;
//heap sort is the better version of InsertionSort!
public class heapSort {
	public static final int INFINITY=99999;
	void swap(int[] A,int a,int b){
		int tmp= A[a];
		A[a]=A[b];
		A[b]=tmp;
	}
	//processing for MAXHEAP!  here N is the size of heap
	public void percDown(int[] A,int i,int N){//i means parent!actually

		int child;
		int tmp;
		for(tmp=A[i];(2*i+1)<N;i=child){//i=child �³���child
			child=2*i+1;
			if(child!=N-1&&A[child+1]>A[child])
				child++;
			if(tmp>=A[child]) //�����Ķ��ӻ�Ҫ��,����Ҫ����
				break;
			else//tmp ��С�ģ���Ϊ�Ǵ󶥶�������Ҫ��tmp���Ƶ����ʵ�λ��Ȼ���child����
				A[i]=A[child]; //������child����
		}
		//tmp store the initial value.
		A[i]=tmp;//actually here i means child,notice i=child!
	}
	
	public void heapsort(int[] A){
		if(A==null)
			return;
		if(A.length<1)
			return;
		int N=A.length;
		//Build heap
		for(int i=A.length/2;i>=0;i--){
			percDown(A,i,A.length);
		}
		//swap
		for(int i=N-1;i>0;i--){//A[0]������Ԫ�أ�A[i]�ǵ�ǰ�ѵ����һ��Ԫ��
			swap(A,0,i);
			//�����µĴ󶥶ѣ�������ѵĹ�ģ��1, i means the length of heap 
			percDown(A,0,i);//������֮�����µ���һ��0 �� i֮��Ľڵ㣬��A[0]���Ƶ����ʵ�λ��
		}
	}
	
	public static void main(String[] args){
		int[] A={44,12,42,23,32,5,99,1};
		heapSort h1=new heapSort();
		h1.heapsort(A);
		//A[i] i from 0 to N-1 store the heap value
		//and heap lchild=2*parent+1 rchild=2*parent+2 
		for(int i=0;i<A.length;i++)
			System.out.println(A[i]);
	}
}
