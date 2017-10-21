package facebook;

import java.util.*;

/**
Previous Permutation，7531->7513.
 */
public class LastPermutation {

	public static void main(String[] args) {
		//String num = "7138";
		String num = "7358";
		//String num = "1234";
		System.out.println(new LastPermutation().next(num));
	}

	public String next(String num) {
		num = num.trim();
		if (num.length() == 0) return "";
		int[] arr = new int[num.length()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (num.charAt(i) - '0');
		}
		int i = arr.length - 2;
		int j = i+1;
		while ( i >= 0) {
			if (arr[i+1] < arr[i]) {
				j = arr.length - 1;
				while (j >= i+1 && arr[j] >= arr[i]) {
					j--;
				}
				break;
			}
			i--;
		}
		if (i >= 0) {
			swap(arr, i, j);
		}
		sort(arr, i+1);
		StringBuilder res = new StringBuilder();
		for (int x : arr) {
			res.append(x);
		}
		return res.toString();
	}
	private void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	private void sort(int[] arr, int idx) {
		if (idx < 0 || idx >= arr.length) return;
		List<Integer> nums = new ArrayList<>();
		for (int i = idx; i < arr.length; i++) {
			nums.add(arr[i]);
		}
		Collections.sort(nums, Collections.reverseOrder());
		for (int i = 0; i < nums.size(); i++) {
			arr[i+idx] = nums.get(i);
		}
	}
}
