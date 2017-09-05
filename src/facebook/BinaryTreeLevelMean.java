package facebook;

import java.util.*;

/**
Return a list of means of each level of a binary tree.
 */
public class BinaryTreeLevelMean {

	public static void main(String[] args) {
		String input = "1,2,3,6,4,null,5,null,null,7,8";
		//String input = "";
		TreeNode root = ConstructBinaryTree.constructFromString(input);
		List<Double> res = new BinaryTreeLevelMean().levelMean(root);
		for (double num : res) {
			System.out.printf("%f, ", num);
		}
	}

	public List<Double> levelMean(TreeNode root) {
		List<Double> res = new ArrayList<>();
		if (root == null) return res;
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		while(!q.isEmpty()) {
			int n = q.size();
			double sum = 0;
			for (int i = 0; i < n; i++) {
				TreeNode node = q.poll();
				sum += (double) node.val;
				if (node.left != null) {
					q.add(node.left);
				}
				if (node.right != null) {
					q.add(node.right);
				}
			}
			res.add((double) (sum / n));
		}
		return res;
	}
}
