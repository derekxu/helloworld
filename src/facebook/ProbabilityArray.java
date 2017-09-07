package facebook;

import java.util.*;

/**
给一个数组，每个元素有一个概率，写一个函数按照每个元素的概率每次返回一个元素。
比如1：0.2，2：0.3，3：0.5    返回1的概率是0.2，返回3的概率是0.5
 */
public class ProbabilityArray {
	int[] nums;
	double[] probs;
	Random rand;

	ProbabilityArray(int[] nums, double[] probs) {
		this.nums = nums;
		this.probs = new double[probs.length];
		rand = new Random();
		if (probs.length == 0) return;
		this.probs[0] = probs[0];
		for (int i = 1; i < probs.length; i++) {
			this.probs[i] = this.probs[i-1] + probs[i];
		}
	}
	
	public Integer get() {
		double prob = rand.nextDouble();
		int l = 0, r = nums.length-1;
		while (l <= r) {
			int m = l + (r-l)/2;
			if (r < l + 2) {
				if (isValid(l, prob))
					return nums[l];
				else if (isValid(r, prob))
					return nums[r];
				
				return null;
			}
			if (isValid(m, prob))
				return nums[m];
			if (this.probs[m] > prob) {
				r = m - 1;
			} else {
				l = m + 1;
			}
		}
		return null;
	}

	private boolean isValid(int i, double t) {
		return t < this.probs[i] && (i == 0 || t >= this.probs[i-1]);
	}

	public static void main(String[] args) {
		int[] nums = {0,1,2};
		double[] probs = {0.2,0.3,0.5};
		ProbabilityArray arr = new ProbabilityArray(nums, probs);
		int[] counts = new int[nums.length];
		int times = 1000;
		for (int i = 0; i < times; i++)
			counts[arr.get()]++;
		
		for (int i = 0; i < counts.length; i++) {
			System.out.println(i + ": " + counts[i]);
		}
	}
}
