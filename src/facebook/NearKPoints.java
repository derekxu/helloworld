package facebook;

import java.util.*;

/**
给2D平面上的N个点，求离原点最近的K个点
 */

public class NearKPoints {

	public static void main(String[] args) {
		Point[] points = {new Point(1,2), new Point(2,1), new Point(2,0), new Point(1, 1)};
		int k = 2;
		List<Point> res = new NearKPoints().findKNearest2(points, k);
		for (Point p : res) {
			System.out.printf("(%d,%d),", p.x, p.y);
		}
		System.out.println();
	}

	public List<Point> findKNearest(Point[] points, int k) {
		PriorityQueue<Point> heap = new PriorityQueue<>(k, new Comparator<Point>() {
			@Override
			public int compare(Point a, Point b) {
				return b.x*b.x + b.y*b.y - a.x*a.x - a.y*a.y;
			}
		});
		for (Point point : points) {
			if (heap.size() < k) {
				heap.add(point);
			} else if (isCloser(point, heap.peek())) {
				heap.poll();
				heap.add(point);
			}
		}
		List<Point> res = new ArrayList<>();
		while (!heap.isEmpty()) {
			res.add(heap.poll());
		}
		return res;
	}
	private boolean isCloser(Point a, Point b) {
		return a.x*a.x + a.y*a.y - b.x*b.x - b.y*b.y < 0;
	}

	public List<Point> findKNearest2(Point[] points, int k) {
		int l = 0, r = points.length - 1;
		List<Point> res = new ArrayList<>();
		while (l <= r) {
			int i = partition(points, l, r);
			if (i == k) {
				for (int j = 0; j < k; j++) {
					res.add(points[j]);
				}
				return res;
			}
			// Here needs to add test cases for logic
			if (i > k) {
				r = i - 1;
			} else {
				l = i + 1;
			}
		}
		for (int j = 0; j < k; j++) {
			res.add(points[j]);
		}
		return res;
	}

	private int partition(Point[] points, int l, int r) {
		int i = l;
		while (i <= r) {
			if (isFurther(points[i], points[l])) {
				swap(points, i, r);
				r--;
			} else {
				i++;
			}
		}
		i--;
		swap(points, l, i);
		return i;
	}
	private boolean isFurther(Point a, Point b) {
		return a.x*a.x + a.y*a.y - b.x*b.x - b.y*b.y > 0;
	}

	private void swap(Point[] points, int i, int j) {
		Point tmp = points[j];
		points[j] = points[i];
		points[i] = tmp;
	}

	static class Point {
		int x;
		int y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
