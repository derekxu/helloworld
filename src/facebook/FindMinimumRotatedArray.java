package facebook;

public class FindMinimumRotatedArray {

	public static void main(String[] args) {
		int[] nums = {1};
		int res = new FindMinimumRotatedArray().findMinimumRotatedArray(nums);
		System.out.println(res);
	}

	public int findMinimumRotatedArray(int[] nums) {
		if (nums.length == 0) return -1;
		int l = 0, r = nums.length - 1;
		while (l <= r) {
			while (l < r && nums[l+1] == nums[l]) l++;
			while (l < r && nums[r-1] == nums[r]) r--;
			if (r - l <= 1) {
				return Math.min(nums[l], nums[r]);
			}
			int m = l + (r - l)/2;
			if (nums[m] <= nums[r]) {
				r = m;
			} else {
				l = m;
			}
		}
		return nums[l];
	}
}
