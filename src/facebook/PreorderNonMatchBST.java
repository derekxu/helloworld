package facebook;

import java.util.*;

public class PreorderNonMatchBST {
	public List<Integer> getFirstNonMatchLeaves(int[] arr1, int[] arr2) {
		return helper(arr1, 0, arr1.length-1, arr2, 0, arr2.length-1);
	}
	// Use l1, r1, l2, r2...
	private List<Integer> helper(int[] arr1, int l1, int r1, int[] arr2, int l2, int r2) {
		if (l1 == r1 || l2 == r2) {
			if ( l1 == r1 && l2 == r2) {
				if (arr1[l1] != arr2[l2]) {
					return Arrays.asList(arr1[l1], arr2[l2]);
				}
				return null;
			} else if (l1 == r1) {
				// Need to specify
				return Arrays.asList(arr1[l1], arr2[l2]);
			} else {
				return Arrays.asList(arr1[l1], arr2[l2]);
			}
		}
		if (l1 > r1 || l2 > r2) return null;
		int i1 = l1;
		for (; i1 <= r1; i1++) {
			if (arr1[i1] > arr1[l1]) {
				break;
			}
		}
		int i2 = l2;
		for (; i2 <= r2; i2++) {
			if (arr2[i2] > arr2[l2]) {
				break;
			}
		}
		List<Integer> left = helper(arr1, l1+1, i1-1, arr2, l2+1, i2-1);
		if (left != null) return left;
		List<Integer> right = helper(arr1, i1, r1, arr2, i2, r2);
		if (right != null) return right;
		return null;
	}

	public static void main(String[] args) {
	}
}
