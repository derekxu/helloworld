package facebook;

import java.util.*;

import facebook.MaxTimesInIntervals.Interval;

/**
interval [startTime, stoptime) ----integral time stamps 给这样的一串区间 I1, I2......In
找出 一个 time stamp 出现在interval的次数最多。

startTime <= t< stopTime 代表这个数在区间里面出现过。 example: [1,3), [2, 7), [4, 8), [5, 9) 5和6各出现了三次， 所以答案返回5，6。 (Hard)
 */
public class MaxTimesIntervalRedo {
	public List<Integer> maxTimesLoc(Interval[] intervals) {
		List<Line> lines = new ArrayList<>();
		for (Interval interval : intervals) {
			lines.add(new Line(interval.start, 1));
			lines.add(new Line(interval.end, -1));
		}
		Collections.sort(lines, new Comparator<Line>(){
			@Override
			public int compare(Line a, Line b) {
				if (a.x == b.x) {
					return Integer.compare(a.count, b.count);
				}
				return Integer.compare(a.x, b.x);
			}
		});
		int max = 0;
		int times = 0;
		int startX = Integer.MIN_VALUE;
		List<Interval> intersects = new ArrayList<>();
		for (Line line : lines) {
			if (line.count == 1) {
				times++;
				startX = line.x;
				if (times > max) {
					max = times;
					intersects = new ArrayList<>();
				}
			} else {
				if (times == max) {
					intersects.add(new Interval(startX, line.x));
				}
				times--;
				startX = line.x;
			}
		}
		List<Integer> res = new ArrayList<>();
		for (Interval interval : intersects) {
			for (int x = interval.start; x < interval.end; x++) {
				res.add(x);
			}
		}
		return res;
	}

	public static void main(String[] args) {
		Interval[] intervals = {
				new Interval(1,3),
				new Interval(2,7),
				new Interval(4,8),
				new Interval(5,9),
		};
		List<Integer> res = new MaxTimesIntervalRedo().maxTimesLoc(intervals);
		for (int num : res) {
			System.out.printf("%d,", num);
		}
		System.out.println();
	}

	class Line {
		int x;
		int count;
		Line(int x, int count) {
			this.x = x;
			this.count = count;
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
