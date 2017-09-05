package facebook;

import java.util.*;

/**
Given two pre-order traversal arrays of two binary search tree respectively, find first pair of non-matching leaves. 
Follow Up: If they are general binary trees instead of BSTs, could you solve it? give out your reason.



补充内容 (2016-12-19 10:55)
还有一个相关题目：
Given two (binary) trees, return the first pair of non-matching leaves 
Tree 1: A, B, C, D, E, null, null 
Tree 2: A, D, B . 
Output: (E,B)

 */
public class PreorderNonMatchBinaryTree {

	public static void main(String[] args) {
		String str1 = "1,2,3,4,5,#,#";
		String str2 = "1,3,4,#,#";
		List<Integer> res = new PreorderNonMatchBinaryTree().findFirstNonMatch(str1, str2);
		for (int num : res) {
			System.out.println(num);
		}

	}

	public List<Integer> findFirstNonMatch(String str1, String str2) {
		String[] arr1 = str1.split(",");
		String[] arr2 = str2.split(",");
		int m = arr1.length;
		int n = arr2.length;
		int i = 0, j = 0;
		while (i < m && j < n) {
			while (i < m) {
				if (isLeaf(arr1, i)) {
					break;
				}
				i++;
			}
			while (j < n) {
				if (isLeaf(arr2, j)) {
					break;
				}
				j++;
			}
			if (!arr1[i].equals(arr2[j])) {
				List<Integer> res = new ArrayList<>();
				res.add(Integer.parseInt(arr1[i]));
				res.add(Integer.parseInt(arr2[j]));
				return res;
			}
			i++;
			j++;
		}
		if (i < m) {
			while (i < m) {
				if (isLeaf(arr1, i)) {
					break;
				}
				i++;
			}
			List<Integer> res = new ArrayList<>();
			res.add(Integer.parseInt(arr1[i]));
			return res;
		}
		if (j < n) {
			while (j < n) {
				if (isLeaf(arr2, j)) {
					break;
				}
				j++;
			}
			List<Integer> res = new ArrayList<>();
			res.add(Integer.parseInt(arr2[j]));
			return res;
		}
		return new ArrayList<Integer>();
	}
	private boolean isLeaf(String[] arr, int i) {
		if (i >= arr.length - 3 && !"#".equals(arr[i])) return true;
		if (!"#".equals(arr[i]) && "#".equals(arr[i+1]) && "#".equals(arr[i+2])) {
			return true;
		}
		return false;
	}
}
