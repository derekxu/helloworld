package facebook;

import java.util.*;

/**
有一个无限流的interval输入，要找出cover range
 */
public class IntervalStream {
	TreeSet<Interval> sets;

	IntervalStream() {
		this.sets = new TreeSet<>(new Comparator<Interval>() {
			@Override
			public int compare(Interval o1, Interval o2) {
				return Integer.compare(o1.start, o2.start);
			}
		});
	}

	public static void main(String[] args) {
		List<Interval> input = new ArrayList<>();
		input.add(new Interval(0,1));
		input.add(new Interval(5,6));
		input.add(new Interval(4,4));
		input.add(new Interval(1,2));
		input.add(new Interval(4,5));
		input.add(new Interval(10,11));
		Iterator<Interval> stream = input.iterator();
		IntervalStream instance = new IntervalStream();
		for (int i = 0; i < input.size();i++) {
			instance.printIntervals(stream);
		}
	}

	public void printIntervals(Iterator<Interval> stream) {
		Interval cur = stream.next();
		if (sets.contains(cur)) {
			cur.end = Math.max(cur.end, sets.floor(cur).end);
			sets.remove(sets.floor(cur));
		}
		Interval l = sets.lower(cur);
		Interval r = sets.higher(cur);
		Interval newInt = null;
		if (l == null && r == null) {
			sets.add(cur);
		} else if (l == null) {
			newInt = new Interval(cur.start, cur.end);
			if (newInt.end >= r.start) {
				newInt.end = Math.max(newInt.end, r.end);
				sets.remove(r);
			}
			sets.add(newInt);
		} else if (r == null) {
			newInt = new Interval(l.start, l.end);
			if (newInt.end >= cur.start) {
				newInt.end = Math.max(newInt.end, cur.end);
				sets.remove(l);
			}
			sets.add(cur);
		} else {
			if (l.end >= cur.start && cur.end >= r.start) {
				newInt = new Interval(l.start, Math.max(cur.end, r.end));
				sets.remove(l);
				sets.remove(r);
			} else if (l.end >= cur.start) {
				newInt = new Interval(l.start, Math.max(l.end, cur.end));
				sets.remove(l);
			} else if (cur.end >= r.start) {
				newInt = new Interval(cur.start, Math.max(cur.end, r.end));
				sets.remove(r);
			} else {
				newInt = cur;
			}
			sets.add(newInt);
		}
		System.out.printf("Stream added [%d,%d]: ", cur.start, cur.end);
		for (Interval interval : sets) {
			System.out.printf("[%d,%d],", interval.start, interval.end);
		}
		System.out.println("");
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
