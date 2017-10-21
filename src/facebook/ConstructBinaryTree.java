package facebook;

import java.util.*;

/**
NOT an interview question
 */
public class ConstructBinaryTree {
	public static void main(String[] args) {
		// No ignore of null until end of the bottom level.
		String input = "1,2,3,6,4,null,5,null,null,7,8";
		//String input = "";
		TreeNode root = constructFromString(input);
		printTreeInOrder(root);
	}

	/**
	 * 
	 * @param str array like serialized TreeNode input. e.g. "1,2,3,null,4,5",
	 * no ignore of null until end of the bottom level.
	 * @return
	 */
	public static TreeNode constructFromString(String str) {
		str = str.trim();
		String[] arr = str.split(",");
		if (str.length() == 0 || "null".equals(arr[0]))
			return null;
		TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
		boolean isLeft = true;
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		TreeNode cur = null;
		for (int i = 1; i < arr.length; i++) {
			if (isLeft) {
				cur = q.poll();
				if (!"null".equals(arr[i])) {
					cur.left = new TreeNode(Integer.parseInt(arr[i]));
				}
				q.add(cur.left);
			} else {
				if (!"null".equals(arr[i])) {
					cur.right = new TreeNode(Integer.parseInt(arr[i]));
				}
				q.add(cur.right);
			}
			isLeft = !isLeft;
		}
		return root;
	}

	public static void printTreeInOrder(TreeNode root) {
		dfsPrintTree(root);
		System.out.println();
	}

	private static void dfsPrintTree(TreeNode root) {
		if (root == null) {
			System.out.printf("null,");
			return;
		}
		if (root.left != null) {
			dfsPrintTree(root.left);
		}
		System.out.printf(root.val + ",");
		if (root.right != null) {
			dfsPrintTree(root.right);
		}
	}
}
