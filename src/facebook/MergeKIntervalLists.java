package facebook;

import java.util.*;

/**
给出N个序列，比如2个序列A,B,没个序列包含若干的区间，比如 A: [1,5], [10,14], [16,18]
B: [2,6], [8,10], [11,20]
Merge them all: [1,6], [8, 20].
 */
public class MergeKIntervalLists {

	public static void main(String[] args) {
		List<List<Interval>> lists = new ArrayList<>();
		lists.add(Arrays.asList(new Interval(1,5), new Interval(10,14), new Interval(16,18)));
		lists.add(Arrays.asList(new Interval(2,6), new Interval(8,10), new Interval(11,20)));
		List<Interval> res = new MergeKIntervalLists().merge(lists);
		for (Interval interval : res) {
			System.out.printf("[%d, %d], ", interval.start, interval.end);
		}
	}

	public List<Interval> merge(List<List<Interval>> lists) {
		int n = lists.size();
		PriorityQueue<Elem> heap = new PriorityQueue<>(n, new Comparator<Elem>(){
			@Override
			public int compare(Elem a, Elem b) {
				if (a.interval.start == b.interval.start) {
					return a.interval.end - b.interval.end;
				}
				return a.interval.start - b.interval.start;
			}
		});
		for (int i = 0; i < n; i++) {
			if (lists.get(i).size() > 0) {
				heap.add(new Elem(i, 0, lists.get(i).get(0)));
			}
		}
		List<Interval> res = new ArrayList<>();
		Interval last = null;
		while (!heap.isEmpty()) {
			Elem cur = heap.poll();
			if (cur.aid + 1 < lists.get(cur.lid).size()) {
				heap.add(new Elem(cur.lid, cur.aid+1, lists.get(cur.lid).get(cur.aid+1)));
			}
			if (last != null) {
				if (last.end >= cur.interval.start) {
					last.start = Math.min(last.start, cur.interval.start);
					last.end = Math.max(last.end, cur.interval.end);
				} else {
					res.add(new Interval(last.start, last.end));
					last = cur.interval;
				}
			} else {
				last = cur.interval;
			}
		}
		if (last != null) {
			res.add(new Interval(last.start, last.end));
		}
		return res;
	}

	class Elem {
		int lid;
		int aid;
		Interval interval;
		Elem(int lid, int aid, Interval interval) {
			this.lid = lid;
			this.aid = aid;
			this.interval = interval;
		}
	}

	static class Interval {
		int start;
		int end;
		Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
}
