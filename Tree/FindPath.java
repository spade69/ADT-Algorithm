package binaryTree;
import java.util.*;



public class FindPath {
	private ArrayList<ArrayList<Integer>> listAll=new ArrayList<ArrayList<Integer>>();
	private ArrayList<Integer> list =new ArrayList<Integer>();
	public ArrayList<ArrayList<Integer>> findpath(TreeNode root,int target){
		if(root==null) return listAll;
		list.add(root.val);
		target-=root.val;
		if(target==0&&root.left==null&&root.right==null)
			listAll.add(new ArrayList<Integer>(list));
		findpath(root.left,target);
		findpath(root.right,target);
		list.remove(list.size()-1);
		return listAll;
	}
}
