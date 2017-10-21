package facebook;

import java.util.*;

/**
Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.

Example:

nums = [1, 2, 3]
target = 4

The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)

Note that different sequences are counted as different combinations.

Therefore the output is 7.
Follow up:
What if negative numbers are allowed in the given array?
How does it change the problem?
What limitation we need to add to the question to allow negative numbers?
 */
public class CombSumIVFollowupRedo {

	public static void main(String[] args) {
		int[] nums = {-1,1,2};
		int target = 4;
		int maxLen = 3;
		System.out.println(new CombSumIVFollowupRedo().numOfSums(nums, target, maxLen));
	}

	public int numOfSums(int[] nums, int target, int maxLen) {
		Map<Integer, Map<Integer, Integer>> map = new HashMap<Integer, Map<Integer, Integer>>();
		return helper(nums, target, maxLen, map);
	}

	private int helper(int[] nums, int t, int len, Map<Integer, Map<Integer, Integer>> map) {
		if (len < 0) return 0;
		if (!map.containsKey(t)) map.put(t, new HashMap<Integer, Integer>());
		if (map.get(t).containsKey(len))
			return map.get(t).get(len);
		int res = 0;
		if (t == 0) {
			res++;
		}
		for (int num : nums) {
			res += helper(nums, t-num, len-1, map);
		}
		map.get(t).put(len, res);
		return res;
	}
}
