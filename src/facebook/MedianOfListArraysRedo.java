package facebook;

import java.util.*;

/**
list of sorted integer arrays，要求找所有的数的median. e.g. [1,3,6,7,9], [2,4, 8], [5], return 5
 */
public class MedianOfListArraysRedo {

	public double getMedian(List<int[]> arrList) {
		if (arrList.size() == 0) return -1;
		PriorityQueue<Elem> q = new PriorityQueue<>(arrList.size(), new Comparator<Elem>(){
			@Override
			public int compare(Elem a, Elem b) {
				return Integer.compare(a.val, b.val);
			}
		});
		int total = 0;
		for (int i = 0; i < arrList.size(); i++) {
			int[] arr = arrList.get(i);
			total += arr.length;
			if (arr.length > 0) {
				q.add(new Elem(i, 0, arr[0]));
			}
		}
		int[] lens = new int[2];
		lens[0] = (total+1)/2;
		lens[1] = (total+2)/2;
		double[] medians = {0.0, 0.0};
		while (!q.isEmpty() && (lens[0] > 0 || lens[1] > 0)) {
			Elem top = q.poll();
			for (int i = 0; i < 2; i++) {
				lens[i]--;
				if (lens[i] == 0) {
					medians[i] = (double) top.val;
				}
			}
			if (arrList.get(top.lid).length > top.aid+1) {
				q.add(new Elem(top.lid, top.aid+1, arrList.get(top.lid)[top.aid+1]));
			}
		}
		return (medians[0]+medians[1])/2.0;
	}

	public static void main(String[] args) {
		List<int[]> arrs = new ArrayList<>();
		arrs.add(new int[]{2,3,5,8});
		arrs.add(new int[]{6,9,10});
		arrs.add(new int[]{7});
		System.out.println(new MedianOfListArraysRedo().getMedian(arrs));
	}

	class Elem {
		int lid;
		int aid;
		int val;
		Elem(int lid, int aid, int val) {
			this.lid = lid;
			this.aid = aid;
			this.val = val;
		}
	}
}
