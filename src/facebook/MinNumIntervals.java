package facebook;

import java.util.*;

/**
给出一系列区间，比如{ [1,3], [3,5], [2,4], [4,7], [4,9], [7,12] }。问如何用最少数目的区间来覆盖目标区间（比如[2，9]）。
在这个例子中，答案应该是{[2,4], [4,9]}。这道题还要考虑无解的情况。LZ给出的解法是先排序，再扫描一遍出结果。
 */
public class MinNumIntervals {
	public int findMinNumIntervals(List<int[]> inters, int[] target) {
		int n = inters.size();
		if (n == 0) return 0;
		int t = target[0];
		int i = 0;
		int res = 0;
		Collections.sort(inters, new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				return a[0]-b[0];
			}
		});
		int max = Integer.MIN_VALUE;
		while (i < n && max < target[1]) {
			if (inters.get(i)[0] > t) {
				return 0;
			}
			while (i < n && inters.get(i)[0] <= t) {
				max = Math.max(max, inters.get(i)[1]);
				i++;
			}
			res++;
			t = max;
		}
		return max >= target[1] ? res : 0;
	}

	public static void main(String[] args) {
		List<int[]> inters = new ArrayList<>();
		inters.add(new int[]{1,3});
		inters.add(new int[]{3,5});
		inters.add(new int[]{2,4});
		inters.add(new int[]{4,9});
		System.out.println(new MinNumIntervals().findMinNumIntervals(inters, new int[]{2,9}));
	}
}
