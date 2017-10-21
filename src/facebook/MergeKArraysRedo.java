package facebook;

import java.util.*;

public class MergeKArraysRedo {
	public int[] merge(List<int[]> arrs) {
		PriorityQueue<T> q = new PriorityQueue<>(10, new Comparator<T>() {
			@Override
			public int compare(T a, T b) {
				return a.val - b.val;
			}
		});
		int total = 0;
		for (int i = 0; i < arrs.size(); i++) {
			total += arrs.get(i).length;
			if (arrs.get(i).length == 0) continue;
			q.add(new T(i, 0, arrs.get(i)[0]));
		}
		int[] res = new int[total];
		int i = 0;
		while (!q.isEmpty()) {
			T t = q.poll();
			res[i++] = t.val;
			if (t.aid+1 < arrs.get(t.lid).length) {
				q.add(new T(t.lid, t.aid+1, arrs.get(t.lid)[t.aid+1]));
			}
		}
		return res;
	}

	class T {
		int lid;
		int aid;
		int val;
		T(int lid, int aid, int val) {
			this.lid = lid;
			this.aid = aid;
			this.val = val;
		}
	}

	public static void main(String[] args) {
		List<int[]> arrs = new ArrayList<>();
		arrs.add(new int[]{1,3,5,7});
		arrs.add(new int[]{2,4,6,8});
		int[] res = new MergeKArraysRedo().merge(arrs);
		for (int num : res) System.out.print(num+",");
	}

}
