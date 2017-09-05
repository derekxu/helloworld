package facebook;

import java.util.Arrays;

/**
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=216744&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
一个array, [1,3,5,7,4,8,2], 一个 target k, 找出这个array里所有子集的个数，满足：子集里最小和最大的数相加小于等于k.
 */
public class SubarrayMinMaxSum {

	public static void main(String[] args) {
		int[] nums = {1,1,3,5};
		int target = 8;
		System.out.println(new SubarrayMinMaxSum().numOfSubarrays(nums, target));

	}

	public int numOfSubarrays(int[] nums, int t) {
		if (nums.length == 0) return 0;
		Arrays.sort(nums);
		int res = 0;
		for (int i = 0; i < nums.length && nums[i] <= t; i++) {
			if (i > 0 && nums[i-1] == nums[i]) continue;
			for (int j = getInit(nums, t, i); j >= i; j--) {
				if (nums[i] + nums[j] <= t) {
					res += getNumSubsets(nums, i+1, j-1);
				}
			}
		}
		return res;
	}
	private int getInit(int[] nums, int target, int i) {
		int l = i, r = nums.length - 1;
		int t = target - nums[i];
		while (l <= r) {
			int m = l + (r - l)/2;
			if (nums[m] >= t) {
				r = m - 1;
			} else {
				l = m + 1;
			}
		}
		while (l < nums.length - 1) {
			if (nums[l+1] == nums[l]) {
				l++;
			} else {
				break;
			}
		}
		if (l >= nums.length) l = nums.length-1;
		return l;
	}
	private int getNumSubsets(int[] nums, int i, int j) {
		if (i > j) return 1;
		int k = i;
		int res = 1;
		while (k <= j) {
			if (k == i || nums[k-1] != nums[k]) {
				res *= 2;
				k++;
			} else {
				res /= 2;
				int reps = 1;
				while (k <= j && nums[k-1] == nums[k]) {
					k++;
					reps++;
				}
				res *= (reps + 1);
			}
		}
		return res;
	}
}
