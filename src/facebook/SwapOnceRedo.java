package facebook;

import java.util.*;

/**
给你一个数组int[] nums，只swap一次，让你maximize这个数组能组成的数，假设数组里所有的数都是一位数

O(n)
 */
public class SwapOnceRedo {
	public void swapOnce(int[] nums) {
		int n = nums.length;
		if (n == 0) return;
		int i = 0;
		while (i < n-1 && nums[i] >= nums[i+1]) {
			i++;
		}
		if (i == n) return;
		int j = i+1;
		int max = nums[j];
		int k = i+2;
		while (k < n) {
			if (nums[k] >= max) {
				max = nums[k];
				j = k;
			}
			k++;
		}
		for (i = 0; i < n; i++) {
			if (nums[i] < max) {
				swap(nums, i, j);
				return;
			}
		}
	}
	private void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}

	public static void main(String[] args) {
		//int[] nums = {4,2,3,3,1};
		int[] nums = {4,2,6,1,6};
		new SwapOnceRedo().swapOnce(nums);
		for (int num : nums) {
			System.out.printf("%d,", num);
		}
	}
}
