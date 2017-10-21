package facebook;

import java.util.*;

/**
给2D平面上的N个点，求离原点最近的K个点
 */
public class NearKPointsRedo {

	public List<Point> kNearest(Point[] points, int k) {
		int l = 0, r = points.length-1;
		List<Point> res = new ArrayList<>();
		k = k-1;
		while (l <= r) {
			int m = partition(points, l, r);
			if (m == k) {
				for (int i = 0; i <= k; i++) {
					res.add(points[i]);
				}
				return res;
			} else if (m < k) {
				l = m + 1;
			} else {
				r = m - 1;
			}
		}
		return res;
	}

	private int partition(Point[] points, int l, int r) {
		int i = l;
		int dist = points[l].getDist();
		while (i <= r) {
			if (points[i].getDist() <= dist) {
				i++;
			} else {
				swap(points, i, r);
				r--;
			}
		}
		i--;
		swap(points, l, i);
		return i;
	}

	private void swap(Point[] points, int i, int j) {
		Point tmp = points[i];
		points[i] = points[j];
		points[j] = tmp;
	}

	public static void main(String[] args) {
		Point[] points = {new Point(1,2), new Point(2,1), new Point(-2,1), new Point(1, -2)};
		int k = 2;
		List<Point> res = new NearKPointsRedo().kNearest(points, k);
		for (Point p : res) {
			System.out.printf("(%d,%d),", p.x, p.y);
		}
		System.out.println();
	}

	static class Point {
		int x;
		int y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getDist() {
			return x*x + y*y;
		}
	}
}
