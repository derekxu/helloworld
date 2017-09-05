package facebook;

import java.util.*;

/**
salbring tree 跟https://leetcode.com/problems/po ... rs-in-each-node-ii/有点像，不过没有next指针， 你要用原来的left，right指针

比如：
          1
     2        3
4           5   6
. more info on 1point3acres.com
变成：
1
｜
2 ——3
｜
4——5——6
 */
public class SalbringTree {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.right.left = new TreeNode(4);
		TreeNode res = new SalbringTree().convert(root);
		TreeNode cur = res;
		TreeNode nxt = null;
		while (cur != null) {
			nxt = cur.left;
			while (cur != null) {
				System.out.printf("%d,", cur.val);
				cur = cur.right;
			}
			System.out.println("");
			cur = nxt;
		}
	}

	public TreeNode convert(TreeNode root) {
		if (root == null) return null;
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		while (!q.isEmpty()) {
			int n = q.size();
			TreeNode beginNode = q.peek();
			for (int k = 0; k < n; k++) {
				TreeNode node = q.poll();
				if (node.left != null) {
					q.add(node.left);
				}
				if (node.right != null) {
					q.add(node.right);
				}
				node.left = null;
				if (k == n-1) {
					node.right = null;
				} else {
					node.right = q.peek();
				}
			}
			beginNode.left = q.peek();
		}
		return root;
	}
}
