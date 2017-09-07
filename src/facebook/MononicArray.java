package facebook;

/**
判断一个int[] 是否是monotonous的
 */
public class MononicArray {

	public static void main(String[] args) {
		int[] nums = {1,2,2,3};
		boolean res = new MononicArray().isMononic(nums);
		if (res) {
			System.out.println("Mononic");
		} else {
			System.out.println("NOT");
		}

	}
	public boolean isMononic(int[] nums) {
		if (nums.length < 3) return true;
		boolean hasAesc = false;
		boolean hasDesc = false;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] > nums[i-1]) {
				hasAesc = true;
				if (hasDesc) {
					return false;
				}
			} else if (nums[i] < nums[i-1]) {
				hasDesc = true;
				if (hasAesc) {
					return false;
				}
			}
		}
		return true;
	}
}
