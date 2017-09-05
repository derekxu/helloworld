package facebook;

import java.util.*;

/**
http://www.1point3acres.com/bbs/thread-158818-1-1.html

给定N个2D坐标（可以设想为餐厅的位置），要求输入任意坐标，可以返回方圆d距离内的所有餐厅
 */
public class ResterauntsInARange {

	public static void main(String[] args) {
		Point[] points = {new Point(1,1), new Point(2,1)};
		Point loc = new Point(0,1);
		int dist = 2;
		List<Point> res = new ResterauntsInARange().nearResteraunts(points, dist, loc);
		for (Point p : res) {
			System.out.printf("(%d,%d)", p.x, p.y);
		}
	}

	public List<Point> nearResteraunts(Point[] points, int dist, Point loc) {
		int minX = loc.x - dist;
		int maxX = loc.x + dist;
		int minY = loc.y - dist;
		int maxY = loc.y + dist;
		List<Point> res = new ArrayList<>();
		for (Point p : points) {
			if (p.x >= minX && p.x <= maxX &&
					p.y >= minY && p.y <= maxY &&
					Math.abs(p.x*p.x+p.y*p.y-loc.x*loc.x-loc.y*loc.y) <= dist) {
				res.add(p);
			}
		}
		return res;
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
