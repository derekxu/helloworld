package facebook;

import java.util.*;

/**
http://www.1point3acres.com/bbs/thread-172203-1-1.html

顺时针的print binary tree boundary, 就是从根开始，先打右边界，再打叶子，最后打左边界。
 */
public class PrintBinaryTree {

	public static void main(String[] args) {
		String input = "1,2,3,4,5,6,null,null,1,7,9,null,8";
		//String input = "";
		TreeNode root = ConstructBinaryTree.constructFromString(input);
		new PrintBinaryTree().printBoundary(root);
	}

	public void printBoundary(TreeNode root) {
		if (root == null) return;
		System.out.print(root.val + ",");
		if (root.right != null) {
			printRight(root.right);
		} else if (root.left != null) {
			printRight(root.left);
		}
		printLeaves(root);
		if (root.left != null) {
			printLeft(root.left);
		} else if (root.right != null) {
			printLeft(root.right);
		}
		System.out.println();
	}

	private void printLeaves(TreeNode root) {
		if (root == null) return;
		if (root.left == null && root.right == null) {
			System.out.print(root.val + ",");
			return;
		}
		printLeaves(root.right);
		printLeaves(root.left);
	}

	private void printRight(TreeNode root) {
		if (root == null) return;
		if (root.left == null && root.right == null)
			return;
		
		System.out.print(root.val + ",");
		if (root.right != null) {
			printRight(root.right);
		} else {
			printRight(root.left);
		}
	}

	private void printLeft(TreeNode root) {
		if (root == null) return;
		if (root.left == null && root.right == null)
			return;
		if (root.left != null){
			printLeft(root.left);
		} else {
			printLeft(root.right);
		}
		System.out.print(root.val + ",");
	}
}
