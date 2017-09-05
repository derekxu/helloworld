package facebook;

import java.util.*;

/**
interval [startTime, stoptime) ----integral time stamps 给这样的一串区间 I1, I2......In
找出 一个 time stamp 出现在interval的次数最多。

startTime <= t< stopTime 代表这个数在区间里面出现过。 example: [1,3), [2, 7), [4, 8), [5, 9) 5和6各出现了三次， 所以答案返回5，6。 (Hard)
 */
public class MaxTimesInIntervals {

	public static void main(String[] args) {
		Interval[] intervals = {
				new Interval(1,3),
				new Interval(2,7),
				new Interval(4,8),
				new Interval(5,9),
		};
		List<Integer> res = new MaxTimesInIntervals().maxTimes(intervals);
		for (int num : res) {
			System.out.printf("%d,", num);
		}
		System.out.println();
	}

	// Use sweep line redo.
	public List<Integer> maxTimes(Interval[] intervals) {
		PriorityQueue<IntElem> heap = new PriorityQueue<>(10, new Comparator<IntElem>(){
			@Override
			public int compare(IntElem a, IntElem b) {
				if (a.interval.start == b.interval.start) {
					return Integer.compare(a.interval.end, b.interval.end);
				}
				return Integer.compare(a.interval.start, b.interval.start);
			}
		});
		for (Interval inter : intervals) {
			heap.add(new IntElem(inter, 1));
		}
		List<IntElem> elems = new ArrayList<>();
		while (!heap.isEmpty()) {
			IntElem a = heap.poll();
			if (!heap.isEmpty()) {
				IntElem b = heap.poll();
				if (a.interval.end > b.interval.start) {
					Interval intOverlap = new Interval(Math.max(a.interval.start, b.interval.start),
							Math.min(a.interval.end, b.interval.end));
					IntElem overlap = new IntElem(intOverlap, a.times+b.times);
					if (a.interval.end > b.interval.end) {
						b.interval.end = a.interval.end;
						b.times = a.times;
					}
					a.interval.end = overlap.interval.start;
					b.interval.start = overlap.interval.end;
					if (a.interval.start < a.interval.end) heap.add(a);
					if (b.interval.start < b.interval.end) heap.add(b);
					if (overlap.interval.start < overlap.interval.end) heap.add(overlap);
				} else {
					heap.add(b);
				}
			}
			elems.add(a);
		}
		int maxTime = 0;
		List<Integer> res = new ArrayList<>();
		for (IntElem elem : elems) {
			if (elem.times == maxTime) {
				for (int i = elem.interval.start; i < elem.interval.end; i++) {
					res.add(i);
				}
			}
			if (elem.times > maxTime) {
				maxTime = elem.times;
				res = new ArrayList<>();
				for (int i = elem.interval.start; i < elem.interval.end; i++) {
					res.add(i);
				}
			}
		}
		return res;
	}

	class IntElem {
		Interval interval;
		int times;
		IntElem(Interval interval, int times) {
			this.interval = interval;
			this.times = times;
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
