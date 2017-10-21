package facebook;

/**
Interview Question.
 *
 */

public class ConstructBSTFromPreOrder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String input = "6,3,2,4,9,8";
		String[] inputs = input.split(",");
		int[] arr = new int[inputs.length];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(inputs[i]);
		}
		TreeNode root = new ConstructBSTFromPreOrder().constructBSTFromPreOrder(arr);
		ConstructBinaryTree.printTreeInOrder(root);
	}

	public TreeNode constructBSTFromPreOrder(int[] preorder) {
		return toBST(preorder, 0, preorder.length-1);
	}
	private TreeNode toBST(int[] arr, int l, int r) {
		if (l < 0 || l > r || r >= arr.length) return null;
		TreeNode root = new TreeNode(arr[l]);
		int i = l + 1;
		while (i <= r) {
			if (arr[i] > arr[l]) {
				break;
			}
			i++;
		}
		root.left = toBST(arr, l+1, i-1);
		root.right = toBST(arr, i, r);
		return root;
	}

}
