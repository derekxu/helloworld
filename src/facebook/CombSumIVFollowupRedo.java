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
		int maxLen = 4;
		System.out.println(new CombSumIVFollowupRedo().numOfSums(nums, target, maxLen));
	}

	public int numOfSums(int[] nums, int target, int maxLen) {
		return helper(nums, target, maxLen, new HashMap<Integer, Map<Integer, Integer>>());
	}
	// map - counts of target at maxLen
	private int helper(int[] nums, int t, int maxLen, Map<Integer, Map<Integer,Integer>> map) {
		if (maxLen < 0) return 0;
		int count = 0;
		if (t == 0) count++;
		for (int num : nums) {
			if (map.containsKey(t - num) && map.get(t-num).containsKey(maxLen-1)) {
				count += map.get(t-num).get(maxLen-1);
			} else {
				count += helper(nums, t-num, maxLen-1, map);
			}
		}
		if (!map.containsKey(t)) {
			map.put(t, new HashMap<Integer, Integer>());
		}
		map.get(t).put(maxLen, count);
		return count;
	}
}
