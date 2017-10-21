package facebook;

import java.util.*;

/**
Similar to 4Sum, but define a k to have k nums sum to a target.
*/

public class KSum {
    public List<List<Integer>> kSum(int[] nums, int target, int k) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        helper(nums, 0, k, target, new ArrayList<Integer>(), res);
        return res;
    }
    private void helper(int[] nums, int idx, int k, int t, List<Integer> arr, List<List<Integer>> res) {
        if (idx == nums.length-k+1) return;
        for (int i = idx; i < nums.length; i++) {
            if (i > idx && nums[i-1] == nums[i]) continue;
            arr.add(nums[i]);
            if (k == 3) {
                twoSum(nums, i+1, nums.length-1, t-nums[i], arr, res);
            } else {
            	helper(nums, i+1, k-1, t-nums[i], arr, res);
            }
            arr.remove(arr.size()-1);
        }
    }
    private void twoSum(int[] nums, int l, int r, int t, List<Integer> arr, List<List<Integer>> res) {
        while (l < r) {
            if (nums[l] + nums[r] == t) {
                arr.add(nums[l]);
                arr.add(nums[r]);
                res.add(new ArrayList<Integer>(arr));
                arr.remove(arr.size()-1);
                arr.remove(arr.size()-1);
                l++;
                while (l < r && nums[l-1] == nums[l]) l++; 
                r--;
                while (l < r && nums[r+1] == nums[r]) r--;
            } else if (nums[l] + nums[r] < t) {
                l++;
                while (l < r && nums[l-1] == nums[l]) l++; 
            } else {
                r--;
                while (l < r && nums[r+1] == nums[r]) r--;
            }
        }
    }
  
	public static void main(String[] args) {
		int[] nums = {2,0,-1,-1,-1,3,5,2,1,1};
		int target = 0;
		int k = 5;
		System.out.println(new KSum().kSum(nums, target, k));
	}
}
