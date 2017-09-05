package facebook;

import java.util.*;

/**
Given an array, return the number of longest possible arithmetic sequence
For example, [-1, 1, 3, 3, 3, 2, 1, 0}  return 5 because {-1, 0, 1, 2, 3} forms a arithmetic sequence. 
 */
public class LongestArchimetic {

	public static void main(String[] args) {
		int[] nums = {-1, 1, 4, 3, 3, 3, 2, 1, 0};
		//int[] nums = {1,2,2,2,3,5,6,7,9};
		//int[] nums = {1, 7, 10, 13, 14, 19};
		System.out.println("O(n^3): "+new LongestArchimetic().longestArchimetic1(nums));
		System.out.println("O(n^2): "+new LongestArchimetic().longestArchimetic2(nums));
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
