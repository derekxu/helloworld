package facebook;

import java.util.*;

/**
http://www.geeksforgeeks.org/convert-a-binary-tree-to-a-circular-doubly-link-list/

convert a binary tree to a circle, double linked list...就是double linked list然后头尾连起来
 */
public class BinaryTreeToDoubleLinkedList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	@SuppressWarnings("unused")
	public DoubleListNode convert(TreeNode root) {
		TreeNode node = root;
		Stack<TreeNode> stack = new Stack<>();
		DoubleListNode last = null, head = null;
		while (node != null && !stack.isEmpty()) {
			if (node != null) {
				stack.push(node);
				node = node.left;
			} else {
				node = stack.pop();
				if (last != null) {
					last.next = new DoubleListNode(node.val);
					last.next.prev = last;
					last = last.next;
				} else {
					last = new DoubleListNode(node.val);
					head = last;
				}
				node = node.right;
			}
		}
		if (head != null && last != null) {
			head.prev = last;
			last.next = head;
		}
		return head;
	}

	class DoubleListNode {
		int val;
		DoubleListNode prev, next;
		DoubleListNode(int val) {
			this.val = val;
			prev = null;
			next = null;
		}
	}
}
