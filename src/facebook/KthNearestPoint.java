package facebook;

import java.util.*;

/**
找出平面上离原点最近的k nearest points
 */
public class KthNearestPoint {

	public static void main(String[] args) {
		Point[] points = {new Point(1,0), new Point(1,1)};
		int k = 0;
		Point res = new KthNearestPoint().findKthNearest(points, k);
		System.out.printf(res.x + ",");
		System.out.println(res.y);
	}

	public Point findKthNearest(Point[] points, int k) {
		int l = 0, r = points.length-1;
		while (l <= r) {
			int m = partition(points, l, r);
			if (m == k) {
				/*for(Point p : points) {
					System.out.printf("[%d,%d],", p.x, p.y);
				}
				System.out.println("");*/
				return points[m];
			}
			if (m < k) {
				l = m + 1;
			} else {
				r = m - 1;
			}
		}
		return null;
	}
	private int partition(Point[] points, int p, int r) {
		int l = p;
		int i = l;
		while (i <= r && l <= i) {
			if (isSmallerOrEqual(points[i], points[p])) {
				if (l == i) {
					l++;
					i++;
				} else {
					swap(points, l, i);
					l++;
				}
			} else {
				swap(points, i, r);
				r--;
			}
		}
		l--;
		swap(points, p, l);
		return l;
	}
	private void swap(Point[] points, int i, int j) {
		Point tmp = points[i];
		points[i] = points[j];
		points[j] = tmp;
	}
	private boolean isSmallerOrEqual(Point a, Point b) {
		return a.x * a.x + a.y * a.y - b.x * b.x - b.y * b.y <= 0;
	}
	static class Point{
		int x;
		int y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
