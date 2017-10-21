package facebook;

import java.util.*;

/**
Given an array, return the number of longest possible arithmetic sequence
For example, [-1, 1, 3, 3, 3, 2, 1, 0}  return 5 because {-1, 0, 1, 2, 3} forms a arithmetic sequence. 
 */
public class LongestArithmeticRedo {
	public int longestArithmetic(int[] nums) {
		int n = nums.length;
		if (n < 3) return 0; 
		int max = 0;
		Map<Integer, Integer> counts = new HashMap<>();
		for (int num : nums) {
			if (!counts.containsKey(num)) {
				counts.put(num, 0);
			}
			counts.put(num, counts.get(num)+1);
			if (counts.get(num) > 2)
				max = Math.max(counts.get(num), max);
		}
		Map<Integer, Map<Integer, Integer>> diffToPairs = new HashMap<>();
		for (int i = 0; i < n-1; i++) {
			for (int j = i+1; j < n; j++) {
				int diff = nums[j] - nums[i];
				if (diff > 0) {
					if (!diffToPairs.containsKey(diff))
						diffToPairs.put(diff, new HashMap<Integer, Integer>());
					if (!diffToPairs.get(diff).containsKey(nums[i]))
						diffToPairs.get(diff).put(nums[i], nums[j]);
				} else if (diff < 0) {
					if (!diffToPairs.containsKey(-diff))
						diffToPairs.put(-diff, new HashMap<Integer, Integer>());
					if (!diffToPairs.get(-diff).containsKey(nums[j]))
						diffToPairs.get(-diff).put(nums[j], nums[i]);
				}
			}
		}
		for (int diff : diffToPairs.keySet()) {
			Map<Integer, Integer> visited = new HashMap<>();
			Map<Integer, Integer> pairs  = diffToPairs.get(diff);
			for (int i : pairs.keySet()) {
				int len = 2;
				int nxt = pairs.get(i);
				while (pairs.containsKey(nxt)) {
					if (visited.containsKey(nxt)) {
						len += visited.get(nxt);
						break;
					} else {
						len++;
						nxt = pairs.get(nxt);
					}
				}
				visited.put(i, len);
				max = Math.max(max, len);
			}
		}
		return max;
	}
	public int longestArithmetic2(int[] nums) {
		int n = nums.length;
		if (n < 3) return 0; 
		int max = 0;
		Map<Integer, Integer> counts = new HashMap<>();
		for (int num : nums) {
			counts.put(num, counts.getOrDefault(num, 0)+1);
			if (counts.get(num) > 2)
				max = Math.max(counts.get(num), max);
		}
		Map<Integer, Map<Integer, Integer>> diffToPairs = new HashMap<>();
		for (int i = 0; i < n-1; i++) {
			for (int j = i+1; j < n; j++) {
				int diff = nums[j] - nums[i];
				if (diff > 0) {
					if (!diffToPairs.containsKey(diff))
						diffToPairs.put(diff, new HashMap<Integer, Integer>());
					if (!diffToPairs.get(diff).containsKey(nums[i]))
						diffToPairs.get(diff).put(nums[i], nums[j]);
				} else if (diff < 0) {
					if (!diffToPairs.containsKey(-diff))
						diffToPairs.put(-diff, new HashMap<Integer, Integer>());
					if (!diffToPairs.get(-diff).containsKey(nums[j]))
						diffToPairs.get(-diff).put(nums[j], nums[i]);
				}
			}
		}
		for (int diff : diffToPairs.keySet()) {
			Map<Integer, Integer> visited = new HashMap<>();
			Map<Integer, Integer> pairs  = diffToPairs.get(diff);
			for (int i : pairs.keySet()) {
				int len = 1;
				len += helper(i, pairs, visited);
				max = Math.max(max, len);
			}
		}
		return max;
	}
	private int helper(int cur, Map<Integer, Integer> pairs, Map<Integer, Integer> visited) {
		if (visited.containsKey(cur)) return visited.get(cur);
		int res = 0;
		if (pairs.containsKey(cur)) {
			res++;
			res += helper(pairs.get(cur), pairs, visited);
		}
		visited.put(cur, res);
		return res;
	}

	public static void main(String[] args) {
		int[] nums = {-1, 1, 4, 3, 3, 3, 2, 1, 0};
		//int[] nums = {1,2,2,2,3,5,6,7,9};
		//int[] nums = {1, 7, 10, 13, 14, 19};
		System.out.println("O(n^2): "+new LongestArithmeticRedo().longestArithmetic(nums));
		System.out.println("O(n^2): "+new LongestArithmeticRedo().longestArithmetic2(nums));

	}
}
