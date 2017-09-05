package facebook;

public class SortedArraySquare {

	public static void main(String[] args) {
		int[] nums = {-2,0,1,3};
		int[] res = new SortedArraySquare().square(nums);
		for (int x : res) {
			System.out.printf("%d,", x);
		}
		System.out.println("");
	}

	public int[] square(int[] nums) {
		int n = nums.length;
		if (n == 0) return nums;
		int[] negs = new int[n];
		int posId = n;
		for (int i = 0; i < n; i++) {
			if (nums[i] < 0) {
				negs[i] = nums[i]*nums[i];
			} else {
				nums[i] = nums[i]*nums[i];
				if (posId == n)
					posId = i;
			}
		}
		int j = posId - 1, k = posId;
		int i = 0;
		while (j >= 0 && k >= 0 && k < n && i < n) {
			if (negs[j] <= nums[k]) {
				nums[i] = negs[j];
				i++;
				j--;
			} else {
				nums[i] = nums[k];
				i++;
				k++;
			}
		}
		while (j >= 0 && i < n) {
			nums[i] = negs[j];
			i++;
			j--;
		}
		return nums;
	}
}
