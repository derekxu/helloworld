package facebook;

import java.util.*;

public class NumArithmeticSlicesRedo {
	public int getNumArithmetic(int[] nums) {
		int n = nums.length;
		if (n == 0) return 0;
		Arrays.sort(nums);
		Map<Integer, Integer>[] maps = new Map[n];
		for (int i = 0; i < n; i++) {
			maps[i] = new HashMap<>();
		}
		int res = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < i; j++) {
				int diff = nums[i] - nums[j];
				int numI = maps[i].getOrDefault(diff, 0);
				int numJ = maps[j].getOrDefault(diff, 0);
				numI += numJ + 1;
				res += numJ;
				maps[i].put(diff, numI);
			}
		}
		return res;
	}

	public static void main(String[] args) {
		int[] nums = {2,4,6,8,6,6,10};
		System.out.println(new NumArithmeticSlicesRedo().getNumArithmetic(nums));
	}

}
