package binaryTree;
import java.util.*;

class TreeNode{
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int val){
		this.val=val;
	}
}

public class Str2Bin {
	//# 表示这个节点为空  ！表示一个值的结束  这是先序order 的序列化
	public String Serialize(TreeNode root){	
		if(root==null){
			return "#!";
		}
		String res=root.val+"!";
		res+=Serialize(root.left);
		res+=Serialize(root.right);
		return res;
	}
	//This function only split转成字符串数组and store push into a queue
	public TreeNode reconByPreString(String preStr){
		String[] values=preStr.split("!");//字符串数组
		Queue<String> queue=new LinkedList<String>();
		for(int i=0;i<values.length;i++){
			queue.add(values[i]);
		}
		return reconPreOrder(queue);
	}
	//
	public TreeNode reconPreOrder(Queue<String> queue){
		String value=queue.remove();
		if(value.equals("#")){
			return null;
		}
		//valueof 取出字符串的值
		TreeNode head=new TreeNode(Integer.valueOf(value));
		head.left=reconPreOrder(queue);
		head.right=reconPreOrder(queue);
		return head;
	}
	
	public static void main(String[] args){
		Str2Bin str=new Str2Bin();
		
	}
}
