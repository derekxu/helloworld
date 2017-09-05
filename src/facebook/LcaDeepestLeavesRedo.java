package facebook;

import java.util.*;

class TreePair {
	TreeNode node;
	int depth;
	TreePair(TreeNode node, int depth) {
		this.node = node;
		this.depth = depth;
	}
}
class Item {
	TreeNode node;
	Item parent;
	List<TreePair> subs;
	Item(TreeNode node, Item parent) {
		this.node = node;
		this.parent = parent;
		subs = new ArrayList<>();
	}
}
public class LcaDeepestLeavesRedo {

	public static void main(String[] args) {
		//String input = "1,2,3,null,4,null";
		String input = "1,2,3,6,4,null,5";
		TreeNode root = ConstructBinaryTree.constructFromString(input);
		LcaDeepestLeavesRedo instance = new LcaDeepestLeavesRedo();
		TreeNode lca = instance.LcaDeepestRecursion(root);
		System.out.printf("Recursive: ");
		if (lca == null) {
			System.out.println("null");
		} else {
			System.out.println(lca.val);
		}
		lca = instance.LcaDeepestIter(root);
		System.out.printf("Iterative: ");
		if (lca == null) {
			System.out.println("null");
		} else {
			System.out.println(lca.val);
		}
	}

	public TreeNode LcaDeepestRecursion(TreeNode root) {
		TreePair res = helper(root);
		return res.node;
	}
	private TreePair helper(TreeNode root) {
		if (root == null) return new TreePair(null, 0);
		if (root.left == null && root.right == null)
			return new TreePair(root, 1);
		
		TreePair left = helper(root.left);
		left.depth++;
		TreePair right = helper(root.right);
		right.depth++;
		if (left.depth == right.depth) {
			return new TreePair(root, left.depth);
		}
		if (left.depth > right.depth) {
			return left;
		} else {
			return right;
		}
	}

	public TreeNode LcaDeepestIter(TreeNode root) {
		if (root == null) return null;
		Item res = new Item(null, null);
		Stack<Item> stack = new Stack<>();
		stack.push(new Item(root, res));
		while (!stack.isEmpty()) {
			Item top = stack.peek();
			TreeNode node = top.node;
			if (node == null) {
				top.parent.subs.add(new TreePair(null, 0));
				stack.pop();
			} else if (top.subs.isEmpty()) {
				stack.push(new Item(node.right, top));
				stack.push(new Item(node.left, top));
			} else {
				TreePair left = top.subs.get(0);
				left.depth++;
				TreePair right = top.subs.get(1);
				right.depth++;
				if (left.depth == right.depth) {
					top.parent.subs.add(new TreePair(node, left.depth));
				} else if (left.depth > right.depth) {
					top.parent.subs.add(new TreePair(left.node, left.depth));
				} else {
					top.parent.subs.add(new TreePair(right.node, right.depth));
				}
				stack.pop();
			}
		}
		return res.subs.get(0).node;
	}
}
