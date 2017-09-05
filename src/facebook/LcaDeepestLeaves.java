package facebook;

import java.util.*;

/**
 Lowest Common Ancestor of deepest leaves.
 */
public class LcaDeepestLeaves {

	public static void main(String[] args) {
		//String input = "1,2,3,null,4,null";
		String input = "1,2,3,6,4,null,5,7";
		TreeNode root = ConstructBinaryTree.constructFromString(input);
		LcaDeepestLeaves instance = new LcaDeepestLeaves();
		TreeNode lca = instance.lcaDeepestLeavesRecursion(root);
		System.out.printf("Recursive: ");
		if (lca == null) {
			System.out.println("null");
		} else {
			System.out.println(lca.val);
		}
		lca =  instance.lcaDeepestLeavesIter(root);
		System.out.printf("Iterative: ");
		if (lca == null) {
			System.out.println("null");
		} else {
			System.out.println(lca.val);
		}
	}

	public TreeNode lcaDeepestLeavesRecursion(TreeNode root) {
		Pair res = helper(root);
		return res.node;
	}
	private Pair helper(TreeNode root) {
		if (root == null) return new Pair(0, null);
		if (root.left == null && root.right == null) {
			return new Pair(1, root);
		}
		Pair left  = helper(root.left);
		left.depth++;
		Pair right = helper(root.right);
		right.depth++;
		if (left.depth == right.depth) {
			return new Pair(left.depth, root);
		}
		if (left.depth > right.depth) {
			return left;
		} else {
			return right;
		}
	}

	public TreeNode lcaDeepestLeavesIter(TreeNode root) {
		if (root == null) return null;
		TreeItem res = new TreeItem(null, null);
		Stack<TreeItem> stack = new Stack<>();
		stack.push(new TreeItem(root, res));
		while (!stack.isEmpty()) {
			TreeItem top = stack.peek(), parent = top.parent;
			TreeNode node = top.node;
			if (node == null) {
				parent.subs.add(new Pair(0, null));
				stack.pop();
			} else if (top.subs.isEmpty()) {
				stack.push(new TreeItem(node.right, top));
				stack.push(new TreeItem(node.left, top));
			} else {
				Pair left = top.subs.get(0);
				left.depth++;
				Pair right = top.subs.get(1);
				right.depth++;
				if (left.depth == right.depth) {
					parent.subs.add(new Pair(left.depth, node));
				} else if (left.depth > right.depth) {
					parent.subs.add(left);
				} else {
					parent.subs.add(right);
				}
				stack.pop();
			}
		}
		return res.subs.get(0).node;
	}

	class Pair {
		public int depth = 0;
		public TreeNode node = null;
		public Pair() {}
		public Pair(int depth, TreeNode node) {
			this.depth = depth;
			this.node = node;
		}
	}

	class TreeItem {
		TreeNode node;
		TreeItem parent;
		List<Pair> subs;
		TreeItem(TreeNode node, TreeItem parent) {
			this.node = node;
			this.parent = parent;
			subs = new ArrayList<>();
		}
	}
}
