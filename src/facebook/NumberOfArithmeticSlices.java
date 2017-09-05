package facebook;

import java.util.*;

/**
Given an array of integers, how many of subarrays of arithmetic.

https://www.jiuzhang.com/solution/arithmetic-slices-ii-subsequence/
 */
public class NumberOfArithmeticSlices {

	public static void main(String[] args) {
		//int[] nums = {2,4,6,8,10};
		//int[] nums = {6,6,6,6,6};
		int[] nums = {2,4,6,8,6,6,10};
		System.out.println(new NumberOfArithmeticSlices().numOfArithmetic(nums));
	}

	public int numOfArithmetic(int[] nums) {
		if (nums.length < 3) return 0;
		int n = nums.length;
		Arrays.sort(nums);
		/*int[] lens = new int[n];
		Map<Integer, Integer> dup = new HashMap<>();
		for (int num : nums) {
			if (!dup.containsKey(num)) {
				dup.put(num, 1);
			} else {
				dup.put(num, dup.get(num)+1);
			}
		}*/
		int res = 0;
		Map<Integer, Integer>[] maps = new Map[n];
		for (int i = 0; i < n; i++) {
			//if (i == 0 || nums[i-1] == nums[i]) continue;
			maps[i] = new HashMap<>();
			for (int j = 0; j < i; j++) {
				//if (nums[j] == nums[j+1]) continue;
				int diff = nums[i] - nums[j];
				int numI = maps[i].getOrDefault(diff, 0);
				int numJ = maps[j].getOrDefault(diff, 0);
				numI += numJ + 1;
				maps[i].put(diff, numI);
				res += numJ;
			}
		}
		return res;
	}
}
