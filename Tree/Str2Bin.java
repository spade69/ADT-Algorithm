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
	//# ��ʾ����ڵ�Ϊ��  ����ʾһ��ֵ�Ľ���  ��������order �����л�
	public String Serialize(TreeNode root){	
		if(root==null){
			return "#!";
		}
		String res=root.val+"!";
		res+=Serialize(root.left);
		res+=Serialize(root.right);
		return res;
	}
	//This function only splitת���ַ�������and store push into a queue
	public TreeNode reconByPreString(String preStr){
		String[] values=preStr.split("!");//�ַ�������
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
		//valueof ȡ���ַ�����ֵ
		TreeNode head=new TreeNode(Integer.valueOf(value));
		head.left=reconPreOrder(queue);
		head.right=reconPreOrder(queue);
		return head;
	}
	
	public static void main(String[] args){
		Str2Bin str=new Str2Bin();
		
	}
}
