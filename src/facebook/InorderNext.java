package facebook;

import java.util.*;

/**
binary tree的node加一个ptr next，point到inorder traversal的下一个node
 */
public class InorderNext {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(2);
		root.right = new TreeNode(9);
		root.left.left = new TreeNode(1);
		root.right.left = new TreeNode(8);
		TreeNode res = new InorderNext().link(root);
		while (res.left != null) {
			res = res.left;
		}
		while (res != null) {
			System.out.print(res.val + ",");
			res = res.next;
		}
	}

	public TreeNode link(TreeNode root) {
		TreeNode node = root;
		TreeNode last = null;
		Stack<TreeNode> stack = new Stack<>();
		while (node != null || !stack.isEmpty()) {
			if (node != null) {
				stack.push(node);
				node = node.left;
			} else {
				node = stack.pop();
				if (last != null) {
					last.next = node;
				}
				last = node;
				node = node.right;
			}
		}
		return root;
	}

	static class TreeNode {
		int val;
		TreeNode left, right, next;
		TreeNode(int val) {
			this.val = val;
		}
	}
}
