package facebook;

import java.util.*;

/**
给你一个数组int[] nums，只swap一次，让你maximize这个数组能组成的数，假设数组里所有的数都是一位数
 */
public class SwapOnce {

	public static void main(String[] args) {
		int[] nums = {4,2,3,3,1};
		new SwapOnce().swapOnce3(nums);
		for (int num : nums) {
			System.out.printf("%d,", num);
		}
	}

	public void swapOnce(int[] nums) {
		int n = nums.length;
		Elem[] arr = new Elem[n];
		for (int i = 0; i < n; i++) {
			arr[i] = new Elem(i, nums[i]);
		}
		Arrays.sort(arr, new Comparator<Elem>() {
			@Override
			public int compare(Elem a, Elem b) {
				return Integer.compare(b.val, a.val);
			}
		});
		for (int i = 0; i < n; i++) {
			if (arr[i].val > nums[i]) {
				nums[arr[i].id] = nums[i];
				nums[i] = arr[i].val;
				return;
			}
		}
	}
	static class Elem {
		int id;
		int val;
		Elem(int id, int val) {
			this.id = id;
			this.val = val;
		}
	}
	public void swapOnce2(int[] nums) {
		int n = nums.length;
		int i = 0;
		while (i < n-1) {
			if (nums[i] >= nums[i+1]) {
				i++;
			} else {
				break;
			}
		}
		if (i == n-1) return;
		int j = i+1;
		int max = nums[j];
		for (int k = i+1; k < n; k++) {
			if (nums[k] >= max) {
				j = k;
				max = nums[k];
			}
		}
		for (int k = 0; k < n; k++) {
			if (nums[k] < max) {
				swap(nums, k, j);
				return;
			}
		}
	}

	public static void swapOnce3(int[] arr) {
		int i = 0;
		while(i < arr.length - 1) {
			if(arr[i] >= arr[i+1]) {
				i++;
			} else {
				break;
			}
		}
		int j = i + 1;
		int max = arr[j];
		for(int k = i + 2; k < arr.length; ++k) {
			if(arr[k] >= max) {
				max = arr[k];
				j = k;
			}
		}
		int k = 0;
		while(k <= i && arr[k] >= max) {
			k++;
		}
		if(k < j && k >= 0 && j < arr.length) {
			swap(arr, k, j);
		}
	}
	
	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
