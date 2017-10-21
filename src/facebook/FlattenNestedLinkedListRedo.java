package facebook;

import java.util.*;

/**
flatten linked list: a listNode has a next pointer, and data; data could be either a normal data such as int val, 
or a pointer point to another linked list node

允许，node的data要么存具体的数据，要么指向另外一个node。面试官没有给出具体的数据结构，需要自己定义。可以理解为一个listNode, 
但是有两个next pointer和一个数据，不过要自己定义一个function 返回这个node是否含有数据还是第二个next pointer也指向两外一个node。
在flatten的过程中不包含具体数据的node要删除掉。我当时是用递归的方法解的。

REDO use isInteger interface
 */
// Use stack for traversal.
public class FlattenNestedLinkedListRedo {

	public List<Integer> flatten(NestedNode root) {
		List<Integer> res = new ArrayList<>();
		if (root == null) return res;
		helper(root, res);
		return res;
	}

	private void helper(NestedNode root, List<Integer> res) {
		if (root == null) return;
		Stack<NestedNode> stack = new Stack<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			NestedNode node = stack.pop();
			if (node.next != null) stack.push(node.next);
			if (node.isInteger()) {
				res.add(node.data);
			} else {
				stack.push(node.point);
			}
		}
	}

	public static void main(String[] args) {
		NestedNode root = new NestedNode(0);
		NestedNode n1 = new NestedNode(1);
		NestedNode n2 = new NestedNode(2);
		root.next = new NestedNode(new NestedNode(new NestedNode(n1)));
		root.next.next = new NestedNode(new NestedNode(n2));
		System.out.println(new FlattenNestedLinkedListRedo().flatten(root));
	}

	static class NestedNode {
		Integer data;
		NestedNode next, point;
		NestedNode(int data) {
			this.data = data;
			next = null;
			point = null;
		}

		NestedNode(NestedNode data) {
			this.data = null;
			next = null;
			point = data;
		}
	
		boolean isInteger() {
			return point == null;
		}
	}
}
