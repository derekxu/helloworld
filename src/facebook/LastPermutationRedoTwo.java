package facebook;

import java.util.Arrays;
import java.util.Collections;

public class LastPermutationRedoTwo {
	public Integer[] lastPermute(Integer[] nums) {
		int n = nums.length;
		if (n == 0) return new Integer[0];
		int i = n-1;
		while (i > 0) {
			if (nums[i-1] > nums[i]) break;
			i--;
		}
		if (i == 0) {
			Arrays.sort(nums, Collections.reverseOrder());
			return nums;
		}
		for (int j = n-1; j >= i+1; j--) {
			if (nums[i] < nums[j]) {
				swap(nums, i, j);
				break;
			}
		}
		return nums;
	}

	private void swap(Integer[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}

	public static void main(String[] args) {
		//String num = "7138";
		Integer[] nums = new Integer[]{7,1,3,8};
		//String num = "1234";
		Integer[] res = new LastPermutationRedoTwo().lastPermute(nums);
		for (int num : res) System.out.print(num + ",");
	}

}
