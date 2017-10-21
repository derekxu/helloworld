package facebook;

import java.util.*;

/**
Given an array, return the number of longest possible arithmetic sequence (can reorder)
For example, [-1, 1, 3, 3, 3, 2, 1, 0}  return 5 because {-1, 0, 1, 2, 3} forms a arithmetic sequence. 
 */
public class LongestArchimetic {

	public static void main(String[] args) {
		//int[] nums = {-1, 1, 4, 3, 3, 3, 2, 1, 0};
		int[] nums = {-1, 1, 4, 3, 2, 3, 3, 2, 1, 0};
		//int[] nums = {1,2,2,2,3,5,6,7,9};
		//int[] nums = {2,2,2,2};
		//int[] nums = {1, 7, 10, 13, 14, 19};
		System.out.println("O(n^3): "+new LongestArchimetic().longestArchimetic1(nums));
		System.out.println("O(n^2): "+new LongestArchimetic().longestArchimetic2(nums));
		System.out.println("O(n^2): "+new LongestArchimetic().longestArithmetic3(nums));
		System.out.println("O(n^2): "+new LongestArchimetic().longestArithmetic4(nums));
	}
	public int longestArithmetic3(int[] nums) {
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

	public int longestArithmetic4(int[] nums) {
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
		@SuppressWarnings("unchecked")
		Map<Integer, Integer>[] maps = new Map[n];
		for (int i = 0; i < n; i++) {
			maps[i] = new HashMap<>();
		}
		// Sort seems unnecessary?, keep it, better to be safe in interview
		//Arrays.sort(nums);
		for (int i  = n-2; i >= 0; i--) {
			if (nums[i] == nums[i+1]) continue;
			for (int j = i+1; j < n; j++) {
				if (j < n-1 && nums[j] == nums[j+1]) continue;
				int diff = nums[j] - nums[i];
				if (diff == 0) continue;
				int len = 2;
				if (maps[j].containsKey(diff)) {
					len += maps[j].get(diff) - 1;
				}
				maps[i].put(diff, len);
				max = Math.max(max, len);
			}
		}
		return max;
	}
	public int longestArchimetic2(int[] nums) {
		if (nums.length == 0) return 0;
		Map<Integer, List<List<Integer>>> map = new HashMap<>();
		for (int i = 0; i < nums.length - 1; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				int diff = Math.abs(nums[j] - nums[i]);
				if (!map.containsKey(diff)) {
					map.put(diff, new ArrayList<List<Integer>>());
				}
				List<Integer> arr = new ArrayList<>();
				if (nums[i] <= nums[j]) {
					arr.add(i);
					arr.add(j);
				} else {
					arr.add(j);
					arr.add(i);
				}
				map.get(diff).add(arr);
			}
		}
		int res = 1;
		for (int diff : map.keySet()) {
			int[] lens = new int[nums.length];
			Arrays.fill(lens, 1);
			for (List<Integer> arr : map.get(diff)) {
				lens[arr.get(1)] = lens[arr.get(0)] + 1;
				res = Math.max(res, lens[arr.get(1)]);
			}
		}
		return res;
	}
	public int longestArchimetic1(int[] nums) {
		if (nums.length == 0) return 0;
		Arrays.sort(nums);
		Map<Integer, Integer> map = new HashMap<>();
		int res = 1;
		for (int num : nums) {
			if (!map.containsKey(num)) {
				map.put(num, 1);
			} else {
				map.put(num, map.get(num)+1);
			}
			res = Math.max(res, map.get(num));
		}
		for (int i = 0; i < nums.length - res + 1; i++) {
			for (int j = i+1; j < nums.length; j++) {
				if (nums[i] == nums[j]) continue;
				int gap = nums[j] - nums[i];
				int cur = nums[j];
				int len = 2;
				while (map.containsKey(cur+gap)) {
					len++;
					cur += gap;
				}
				res = Math.max(res, len);
			}
		}
		return res;
	}
}
