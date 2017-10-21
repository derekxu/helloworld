package facebook;

import java.util.*;

public class BstToDoubleLinkedListInPlaceRedo {
	
	public TreeNode update(TreeNode root) {
		if (root == null) return null;
		TreeNode cur = root;
		TreeNode head = null, last = null;
		Stack<TreeNode> stack = new Stack<>();
		while (cur != null || !stack.isEmpty()) {
			if (cur != null) {
				stack.push(cur);
				cur = cur.left;
			} else {
				cur = stack.pop();
				if (head == null) {
					head = cur;
				}
				if (last != null) {
					last.right = cur;
					cur.left = last;
				}
				last = cur;
				cur = cur.right;
			}
		}
		if (last != null) {
			last.right = head;
			head.left = last;
		}
		return head;
	}

	public TreeNode update2(TreeNode root) {
		if (root == null) return null;
		TreeNode cur = root;
		TreeNode head = null, last = null;
		while (cur != null) {
			while (cur.left != null) {
				TreeNode node = cur.left;
				while (node.right != null) {
					node = node.right;
				}
				node.right = cur;
				TreeNode next = cur.left;
				cur.left = node;
				cur = next;
			}
			if (head == null) head = cur;
			if (last != null) {
				last.right = cur;
				cur.left = last;
			}
			while (last == null || (cur != null && cur.left == last && last.right == cur)) {
				last = cur;
				cur = cur.right;
			}
		}
		if (last != null) {
			last.right = head;
			head.left = last;
		}
		return head;
	}

	public static void main(String[] args) {
		String input = "5,2,8,1,4,7,null,null,null,3";
		//String input = "5,2,7,1,3,6,8";
		//String input = "1";
		TreeNode root = ConstructBinaryTree.constructFromString(input);
		TreeNode head = new BstToDoubleLinkedListInPlaceRedo().update(root);
		TreeNode node = head;
		System.out.print(node.val + ",");
		node = node.right;
		while (node != head) {
			System.out.print(node.val + ",");
			node = node.right;
		}
		System.out.println();
		TreeNode root2 = ConstructBinaryTree.constructFromString(input);
		TreeNode h2 = new BstToDoubleLinkedListInPlaceRedo().update(root2);
		node = h2;
		System.out.print(node.val + ",");
		node = node.right;
		while (node != h2) {
			System.out.print(node.val + ",");
			node = node.right;
		}
	}

}
