package facebook;

import java.util.*;

/**
merge K arrays.
 */
public class MergeKArrays {

	public static void main(String[] args) {
		List<int[]> arrs = new ArrayList<>();
		arrs.add(new int[]{1,3,5,7});
		arrs.add(new int[]{2,4,6,8});
		int[] res = new MergeKArrays().merge(arrs);
		for (int num : res) {
			System.out.printf("%d,", num);
		}
		System.out.println("");
	}

	public int[] merge(List<int[]> arrs) {
		if (arrs.size() == 0) return new int[0];
		int k = arrs.size();
		int[] ids = new int[k];
		PriorityQueue<Pair> q = new PriorityQueue<>(k, new Comparator<Pair>() {
			@Override
			public int compare(Pair a, Pair b) {
				return a.val - b.val;
			}
		});
		List<Integer> resLst = new ArrayList<>();
		for (int i = 0; i < k; i++) {
			if (ids[i] >= arrs.get(i).length) continue;
			q.add(new Pair(i, arrs.get(i)[ids[i]]));
			ids[i]++;
		}
		while (!q.isEmpty()) {
			Pair cur = q.poll();
			resLst.add(cur.val);
			int i = cur.id;
			if (ids[i] >= arrs.get(i).length) continue;
			q.add(new Pair(i, arrs.get(i)[ids[i]]));
			ids[i]++;
		}
		int[] res = new int[resLst.size()];
		for (int i = 0; i < res.length; i++) {
			res[i] = resLst.get(i);
		}
		return res;
	}

	class Pair {
		int id;
		int val;
		Pair(int id, int val) {
			this.id = id;
			this.val = val;
		}
	}
}
