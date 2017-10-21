package facebook;

/**
 * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=217746&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
 * 
flatten linked list: a listNode has a next pointer, and data;data could be either a normal data such as int val, 
or a pointer point to another linked list node

允许，node的data要么存具体的数据，要么指向另外一个node。面试官没有给出具体的数据结构，需要自己定义。可以理解为一个listNode, 
但是有两个next pointer和一个数据，不过要自己定义一个funciton 返回这个node是否含有数据还是第二个next pointer也指向两外一个node。
在flatten的过程中不包含具体数据的node要删除掉。我当时是用递归的方法解的。

REDO use isInteger interface
 */
public class FlattenNestedLinkedList {

	public static void main(String[] args) {
		DoubleListNode root = new DoubleListNode(1);
		/*root.right = new DoubleListNode(2);
		root.right.right = new DoubleListNode(4);
		root.down = new DoubleListNode(3);
		root.down.down = new DoubleListNode(4);*/
		DoubleListNode res = new FlattenNestedLinkedList().flatten(root);
		while (res != null) {
			System.out.printf("%d,", res.val);
			res = res.right;
		}
		System.out.println("");
	}

	public DoubleListNode flatten(DoubleListNode root) {
		if (root == null) return root;
		DoubleListNode node = root;
		while (node != null) {
			if (node.down == null) {
				node = node.right;
			} else if (node.right == null) {
				node.right = node.down;
				node = node.right;
			} else {
				if (node.down.val <= node.right.val) {
					DoubleListNode tmp = node.right;
					node.right = node.down;
					node.down = null;
					node.right.right = mergeRights(node.right.right, tmp);
					node = node.right;
				} else {
					node.right.down = mergeDowns(node.right.down, node.down);
					node.down = null;
					node = node.right;
				}
			}
		}
		return root;
	}

	private DoubleListNode mergeRights(DoubleListNode node1, DoubleListNode node2) {
		DoubleListNode dummy = new DoubleListNode(-1);
		DoubleListNode pre = dummy;
		while (node1 != null && node2 != null) {
			if (node1.val <= node2.val) {
				pre.right = node1;
				node1 = node1.right;
			} else {
				pre.right = node2;
				node2 = node2.right;
			}
			pre = pre.right;
		}
		if (node1 != null) {
			pre.right = node1;
		}
		if (node2 != null) {
			pre.right = node2;
		}
		return dummy.right;
	}

	private DoubleListNode mergeDowns(DoubleListNode node1, DoubleListNode node2) {
		DoubleListNode dummy = new DoubleListNode(-1);
		DoubleListNode pre = dummy;
		while (node1 != null && node2 != null) {
			if (node1.val <= node2.val) {
				pre.down = node1;
				node1 = node1.down;
			} else {
				pre.down = node2;
				node2 = node2.down;
			}
			pre = pre.down;
		}
		if (node1 != null) {
			pre.down = node1;
		}
		if (node2 != null) {
			pre.down = node2;
		}
		return dummy.down;
	}

	static class DoubleListNode {
		int val;
		DoubleListNode right, down;
		DoubleListNode(int val) {
			this.val = val;
		}
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
