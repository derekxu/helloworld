package facebook;

import java.util.*;

/**
一个完全树。node有parent指针。
每个node的值为 0或 1
每个parent的值为两个子node的 “and” 结果
现在把一个leaf翻牌子（0变1或者1变0）
把树修正一遍
 */
public class AndTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AndTreeNode root = new AndTreeNode(true);
		root.left = new AndTreeNode(true);
		root.right = new AndTreeNode(true);
		root.left.parent = root;
		root.right.parent = root;
		AndTreeNode res = new AndTree().update(root, root.right);
	}

	public AndTreeNode update(AndTreeNode root, AndTreeNode toModify) {
		toModify.val = !toModify.val;
		AndTreeNode n1 = toModify;
		AndTreeNode n2 = null;
		AndTreeNode parent = null;
		while (n1 != root) {
			parent = n1.parent;
			if (parent.left == n1) n2 = parent.right;
			if (parent.right == n1) n2 = parent.left;
			if (n2.val == false) {
				break;
			}
			parent.val = !parent.val;
			n1 = parent;
		}
		return root;
	}

	private AndTreeNode helper(AndTreeNode node) {
		if (node == null) return null;
		if (node.left == null && node.right == null)
			return node.parent == null ? node : node.parent;
		if (node.left == null) {
			node.val = node.right.val;
		} else if (node.right == null) {
			node.val = node.left.val;
		} else {
			node.val = node.left.val && node.right.val;
		}
		return node.parent == null ? node : node.parent;
	}

	static class AndTreeNode {
		boolean val;
		AndTreeNode left;
		AndTreeNode right;
		AndTreeNode parent;
		AndTreeNode(boolean val) {
			this.val = val;
		}
	}
}
