package facebook;

import java.util.Arrays;

/**
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=216744&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
一个array, [1,3,5,7,4,8,2], 一个 target k, 找出这个array里所有子集 (subset / subsequence) 的个数，满足：子集里最小和最大的数相加小于等于k.
 */
public class SubArrayMinMaxSumRedo {

	public int numOfSubsets(int[] nums, int k) {
		int n = nums.length;
		if (n == 0) return 0;
		Arrays.sort(nums);
		int res = 0;
		for (int i = 0; i < n; i++) {
			if (i > 0 && nums[i] == nums[i-1]) continue;
			int t = k - nums[i];
			int j = Arrays.binarySearch(nums, i+1, n, t);
			if (j < 0) {
				j = -(j+1)-1;
			}
			if (j == i) {
				res++;
			} else {
				res += (1<<(j-i));
			}
		}
		return res;
	}

	public static void main(String[] args) {
		int[] nums = {1,1,3,5};
		int target = 8;
		System.out.println(new SubArrayMinMaxSumRedo().numOfSubsets(nums, target));
	}
}
