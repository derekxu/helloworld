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
public class CombSumIVFollowup {

	public static void main(String[] args) {
		int[] nums = {1,2,3};
		int target = 4;
		int maxLen = 3;
		int res = new CombSumIVFollowup().combSum4(nums, target, maxLen);
		System.out.println("Results: " + res);
	}

	public int combSum4(int[] nums, int t, int maxLen) {
		if (nums.length == 0 && t == 0) return 1;
		if (nums.length == 0) return 0;
		Map<Integer, Map<Integer,Integer>> map = new HashMap<>();
		return helper(nums, maxLen, t, map);
	}

	// Target = t, maxLen = len, return number of sequences from array nums.
	private int helper(int[] nums, int len, int t, Map<Integer, Map<Integer,Integer>> map) {
		if (len < 0) return 0;
		if (map.containsKey(t) && map.get(t).containsKey(len))
			return map.get(t).get(len);
		int count  = 0;
		// Current sequence, no more operation fulfill the target, thus, count++;
		if (t == 0) count++;
		for (int num : nums) {
			count += helper(nums, len-1, t-num, map);
		}
		if (!map.containsKey(t)) {
			map.put(t, new HashMap<Integer, Integer>());
		}
		map.get(t).put(len, count);
		return count;
	}

}
