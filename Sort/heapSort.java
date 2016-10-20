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
		for(tmp=A[i];(2*i+1)<N;i=child){//i=child 下沉到child
			child=2*i+1;
			if(child!=N-1&&A[child+1]>A[child])
				child++;
			if(tmp>=A[child]) //比最大的儿子还要大,不需要交换
				break;
			else//tmp 是小的，因为是大顶堆所以需要把tmp下移到合适的位置然后把child上移
				A[i]=A[child]; //把最大的child上移
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
		for(int i=N-1;i>0;i--){//A[0]是最大的元素，A[i]是当前堆的最后一个元素
			swap(A,0,i);
			//产生新的大顶堆，结束后堆的规模减1, i means the length of heap 
			percDown(A,0,i);//交换完之后重新调整一下0 和 i之间的节点，把A[0]下移到合适的位置
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
