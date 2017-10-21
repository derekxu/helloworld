package facebook;

/**
Find minimum in rotated sorted array
 */
public class FindMinimumInRotatedArray {

	public static void main(String[] args) {
		int[] nums = {9,10,1,2,3,5,7};
		int res = new FindMinimumInRotatedArray().findMinimumRotatedArray(nums);
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
