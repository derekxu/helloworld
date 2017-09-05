package facebook;

import java.util.*;

public class RandomPop {

	public static void main(String[] args) {
		int[] nums = {1,2,3,4,5};
		System.out.print("Random output: ");
		int[] res = new RandomPop().getSorted(new RandomArray(nums));
		System.out.println();
		System.out.print("Result: ");
		for (int num : res) {
			System.out.print(num + ",");
		}
	}

	public int[] getSorted(RandomArray arr) {
		int n = arr.size();
		int[] res = new int[n];
		int l = 0, r = n-1;
		while (!arr.isEmpty()) {
			int num = arr.pop();
			System.out.print(num + ",");
			if (r == n - 1) {
				res[r--] = num;
			} else {
				if (num > res[r+1]) {
					res[l] = res[r+1];
					l++;
					res[r+1] = num;
				} else {
					res[r--] = num;
				}
			}
		}
		return res;
	}

	static class RandomArray {
		int l, r;
		int[] nums;
		Random rand;
		RandomArray(int[] nums) {
			this.nums = nums;
			l = 0;
			r = nums.length-1;
			rand = new Random();
		}

		Integer pop() {
			if (l > r) return null;
			if (rand.nextInt(2) == 0) {
				return nums[l++];
			} else {
				return nums[r--];
			}
		}

		boolean isEmpty() {
			return l > r;
		}

		int size() {
			return r - l + 1;
		}
	}

}
