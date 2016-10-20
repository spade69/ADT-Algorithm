package binaryTree;

public class uniqueSearchTrees {
	public int numTrees(int n){
		if(n<=1)
			return 1;
		int uniqueBST=0;
		for(int i=1;i<=n;i++){
			uniqueBST+=numTrees(i-1)*numTrees(n-i);
		}
		return uniqueBST;
	}
	public static void main(String[] args){
		uniqueSearchTrees tr=new uniqueSearchTrees();
		int bstNum=tr.numTrees(4);
		System.out.println(bstNum);
	}
}
