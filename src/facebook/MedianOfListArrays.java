package facebook;

import java.util.*;

/**
list of sorted integer arrays，要求找所有的数的median. e.g. [1,3,6,7,9], [2,4, 8], [5], return 5
 */
public class MedianOfListArrays {

	public static void main(String[] args) {
		List<int[]> arrs = new ArrayList<>();
		arrs.add(new int[]{2,3,5,8});
		arrs.add(new int[]{6,9,10});
		arrs.add(new int[]{7});
		System.out.println(new MedianOfListArrays().findMedian(arrs));
	}

	public Integer findMedian(List<int[]> arrs) {
		if (arrs.size() == 0) return null;
		int k = arrs.size();
		PriorityQueue<Elem> q = new PriorityQueue<>(k, new Comparator<Elem>(){
			@Override
			public int compare (Elem a, Elem b) {
				return Integer.compare(a.val, b.val);
			}
		});
		int total = 0;
		for (int i = 0; i < k; i++) {
			total += arrs.get(i).length;
			if (arrs.get(i).length > 0) {
				q.add(new Elem(i, 0, arrs.get(i)[0]));
			}
		}
		int median = total/2;
		int count = 0;
		if (total%2 == 1) median++;
		while (count <= median && !q.isEmpty()) {
			Elem e = q.poll();
			count++;
			if (count == median) return e.val;
			if (e.aid == arrs.get(e.lid).length-1) continue;
			q.add(new Elem(e.lid, e.aid+1, arrs.get(e.lid)[e.aid+1]));
		}
		return null;
	}

	class Elem {
		int lid;
		int aid;
		int val;
		Elem (int lid, int aid, int val) {
			this.lid = lid;
			this.aid = aid;
			this.val = val;
		}
	}
}
