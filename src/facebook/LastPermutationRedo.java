package facebook;

import java.util.*;

/**
Previous Permutation，7531->7513.
 */
public class LastPermutationRedo {

	public String permute(String num) {
		num = num.trim();
		if (num.length() == 0) return "";
		int[] arr = new int[num.length()];
		for (int i = 0; i < num.length(); i++) {
			arr[i] = (int) (num.charAt(i) - '0');
		}
		int i = arr.length-1;
		for (; i > 0; i--) {
			if (arr[i-1] > arr[i]) {
				break;
			}
		}
		if (i == 0) {
			return new StringBuilder(num).reverse().toString();
		}
		for (int j = arr.length - 1; j >= i; j--) {
			if (arr[i-1] > arr[j]) {
				swap(arr, i-1, j);
				revSort(arr, i);
				break;
			}
		}
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

	private void revSort(int[] arr, int start) {
		int len = arr.length - start;
		int[] tmp = new int[len];
		for (int i = start; i < arr.length; i++) {
			tmp[i-start] = arr[i];
		}
		Arrays.sort(tmp);
		for (int i = 0; i < len; i++) {
			arr[i+start] = tmp[len-i-1];
		}
	}

	public static void main(String[] args) {
		//String num = "7138";
		String num = "7358";
		//String num = "1234";
		System.out.println(new LastPermutationRedo().permute(num));
	}
}
