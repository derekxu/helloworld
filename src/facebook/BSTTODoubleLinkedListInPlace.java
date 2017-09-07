package facebook;

import java.util.*;

/**
http://www.1point3acres.com/bbs/thread-16830-1-1.html

Convert a binary search tree to a sorted, circular, doubly-linked list, in place (using the tree nodes as the new list nodes).

use leftChild as "prev"

use rightChild as "next"
 */
public class BSTTODoubleLinkedListInPlace {

	public TreeNode udpate(TreeNode root) {
		if (root == null) return null;
		TreeNode curr = root;
		TreeNode head = null, last = null;
		Stack<TreeNode> stack = new Stack<>();
		while (curr != null || !stack.isEmpty()) {
			if (curr != null) {
				stack.push(curr);
				curr = curr.left;
			} else {
				curr = stack.pop();
				if (head == null) {
					head = curr;
				}
				if (last != null) {
					last.right = curr;
					curr.left = last;
				}
				last = curr;
				curr = curr.right;
			}
		}
		last.right = head;
		head.left = last;
		return head;
	}

	public TreeNode udpate2(TreeNode root) {
		if (root == null) return null;
		TreeNode curr = root;
		TreeNode head = null, last = null;
		while (curr != null) {
			while (curr.left != null) {
				TreeNode node = curr.left;
				while (node.right != null) {
					node = node.right;
				}
				node.right = curr;
				TreeNode next = curr.left;
				curr.left = node;
				curr = next;
			}
			if (head == null) {
				head = curr;
			}
			if (last != null) {
				last.right = curr;
				curr.left = last;
			}
			while (last == null ||
					(curr != null && last.right == curr && curr.left == last)) {
				last = curr;
				curr= curr.right;
			}
		}
		last.right = head;
		head.left = last;
		return head;
	}

	public static void main(String[] args) {
		String input = "5,2,8,1,4,7,null,null,null,3";
		//String input = "5,2,7,1,3,6,8";
		//String input = "1";
		TreeNode root = ConstructBinaryTree.constructFromString(input);
		TreeNode head = new BSTTODoubleLinkedListInPlace().udpate(root);
		TreeNode node = head;
		System.out.print(node.val + ",");
		node = node.right;
		while (node != head) {
			System.out.print(node.val + ",");
			node = node.right;
		}
		System.out.println();
		TreeNode root2 = ConstructBinaryTree.constructFromString(input);
		TreeNode head2 = new BSTTODoubleLinkedListInPlace().udpate2(root2);
		node = head2;
		System.out.print(node.val + ",");
		node = node.right;
		while (node != head2) {
			System.out.print(node.val + ",");
			node = node.right;
		}
	}

}
