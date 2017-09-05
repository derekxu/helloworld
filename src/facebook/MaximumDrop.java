package facebook;

import java.util.*;

/**
Given a list of number, there is only one peak or one drop. Find the maximum drop. Exps:
1 -> 2 -> 3 -> 9 -> 3 -> 0 = 9;
10 -> 4 -> 3 -> 8 = 7 ;
 */
public class MaximumDrop {

	public static void main(String[] args) {
		int[] nums = {10,4,3,8};
		System.out.println(new MaximumDrop().maxDrop(nums));
	}

	public int maxDrop(int[] nums) {
		if (nums.length < 2) return 0;
		boolean isDrop = false;
		int i = 1;
		while (i < nums.length && nums[i-1] == nums[i])
			i++;
		if (i == nums.length) return 0;
		if (nums[i-1] > nums[i]) isDrop = true;
		if (isDrop) {
			int l = 0, r = nums.length - 1;
			while (l <= r) {
				int m = l + (r-l)/2;
				if (l + 2 >= r) {
					int min = nums[l];
					min = Math.min(nums[m], min);
					min = Math.min(min, nums[r]);
					return nums[0] - min;
				}
				if (nums[m-1] > nums[m]) {
					l = m;
				} else {
					r = m - 1;
				}
			}
			return -1;
		} else {
			int l = 0, r = nums.length - 1;
			while (l <= r) {
				int m = l + (r-l)/2;
				if (l + 2 >= r) {
					int max = nums[l];
					max = Math.max(max, nums[m]);
					max = Math.max(max, nums[r]);
					return max - nums[nums.length-1];
				}
				if (nums[m-1] < nums[m]) {
					l = m;
				} else {
					r = m - 1;
				}
			}
			return -1;
		}
	}
}
