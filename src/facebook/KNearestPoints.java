package facebook;

/**
找出平面上离原点最近的k nearest points
 */
public class KNearestPoints {
	public Point[] findKNearestPoints(Point[] points, int k) {
		if (points.length == 0 || points.length < k)
			return new Point[0];
		int l = 0, r = points.length-1;
		Point[] res = new Point[k];
		int len = k;
		k--;
		while (l <= r) {
			int m = partition(points, l, r);
			if (m == k) {
				for (int i = 0; i < len; i++) {
					res[i] = points[i];
				}
				return res;
			}
			if (m < k) {
				l = m+1;
			} else {
				r = m-1;
			}
		}
		return res;
	}
	private int partition(Point[] points, int p, int r) {
		int l = p, i = l;
		while (i <= r && i >= l) {
			if (isSmallOrEqual(points[i], points[p])) {
				if (l == i) {
					l++;
					i++;
				} else {
					swap(points, l++, i);
				}
			} else {
				swap(points, r--, i);
			}
		}
		l--;
		swap(points, l, p);
		return l;
	}
	private boolean isSmallOrEqual(Point a, Point b) {
		int distA = a.x*a.x+a.y*a.y;
		int distB = b.x*b.x+b.y*b.y;
		return distA <= distB;
	}
	private void swap(Point[] points, int i, int j) {
		Point tmp = points[i];
		points[i] = points[j];
		points[j] = tmp;
	}
	static class Point {
		int x;
		int y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public void print() {
			System.out.printf("%d,%d; ", x, y);
		}
	}

	public static void main(String[] args) {
		Point[] points = {new Point(1,0), new Point(1,1), new Point(5,6), new Point(7,2), new Point(3,2)};
		int k = 3;
		Point[] res = new KNearestPoints().findKNearestPoints(points, k);
		for (Point p : res) p.print();
	}

}
