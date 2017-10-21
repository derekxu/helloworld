package facebook;

import java.util.*;

/**
http://www.1point3acres.com/bbs/thread-166129-1-1.html

几何算法问题。如果给你一堆的矩形， 求重合矩形重合最多的坐标位置。我上过一个算法课，大概思路就是做一个二维的meeting room II.
 */
/**
Another solution: 
1. Horizontal scan first, save rectangles to each segment (line object store rect id).
2. Vertical scan each segment, iterate rectangles of each seg.
https://photos.app.goo.gl/uoDypBtdDlRekX113
 */
public class OverlapRectangles {

	public static void main(String[] args) {
		//Rectangle[] rects = {new Rectangle(0,7,4,5), new Rectangle(2,6,6,4), new Rectangle(3,3,7,0)};
		//Rectangle[] rects = {new Rectangle(0,7,4,5), new Rectangle(2,6,6,4), new Rectangle(3,6,7,4)};
		Rectangle[] rects = {new Rectangle(0,7,4,5), new Rectangle(2,6,6,4), new Rectangle(3,5,7,4)}; // Edge case
		List<Point> res = new OverlapRectangles().mostOverlapPoints(rects);
		for (Point p : res) {
			System.out.printf("(%d,%d),", p.x, p.y);
		}
	}

	// Some edge cases cannot pass. Next time: save add points on each scan line,
	// horizontally replicate the points at next lines
	public List<Point> mostOverlapPoints(Rectangle[] rects) {
		int n = rects.length;
		Line[] vertLines = new Line[n*2];
		for (int i = 0; i < n; i++) {
			vertLines[i*2] = new Line(rects[i].ul[0], 1);
			vertLines[i*2+1] = new Line(rects[i].lr[0], -1);
			Line horiLine1 = new Line(rects[i].lr[1], 1);
			Line horiLine2 = new Line(rects[i].ul[1], -1);
			vertLines[i*2].horizons.add(horiLine1);
			vertLines[i*2].horizons.add(horiLine2);
			vertLines[i*2+1].horizons.add(horiLine1);
			vertLines[i*2+1].horizons.add(horiLine2);
		}
		Arrays.sort(vertLines, new LineComparator());
		List<Line> horiLines = new ArrayList<>();
		int max = 0;
		List<Rectangle> overlaps = new ArrayList<>();
		List<Integer> lrx = new ArrayList<>();
		for (Line vline : vertLines) {
			if (vline.count == 1) {
				int count = 0;
				horiLines.addAll(vline.horizons);
				int minLoc = vline.horizons.get(0).loc;
				int maxLoc = vline.horizons.get(1).loc;
				Collections.sort(horiLines, new LineComparator());
				for (Line hline : horiLines) {
					if (hline.loc > maxLoc) break;
					if (hline.count == 1) {
						count++;
						if (count == max && hline.loc >= minLoc && hline.loc <= maxLoc) {
							overlaps.add(new Rectangle(vline.loc, null, null, hline.loc));
						}
						if (count > max && hline.loc >= minLoc && hline.loc <= maxLoc) {
							max = count;
							overlaps = new ArrayList<>();
							overlaps.add(new Rectangle(vline.loc, null, null, hline.loc));
						}
					} else {
						if (count == max && hline.loc >= minLoc && hline.loc <= maxLoc) {
							overlaps.get(overlaps.size()-1).ul[1] = hline.loc;
						}
						count--;
					}
				}
			} else {
				int count = 0;
				Collections.sort(horiLines, new LineComparator());
				List<Integer> hLineIds = new ArrayList<>();
				for (int i = 0; i < horiLines.size(); i++) {
					Line hline = horiLines.get(i);
					if (hline.count == 1) {
						count++;
						if (count == max) {
							lrx.add(vline.loc);
						}
					} else {
						count--;
					}
					for (int j = 0; j < 2; j++) {
						if (vline.horizons.get(0).loc == hline.loc) {
							hLineIds.add(i);
							break;
						}
					}
					if (hLineIds.size() > 1) {
						remove(horiLines, hLineIds.get(1));
						remove(horiLines, hLineIds.get(0));
					}
				}
			}
		}
		List<Point> res = new ArrayList<>();
		for (int i = 0; i < overlaps.size(); i++) {
			Rectangle rect = overlaps.get(i);
			rect.lr[0] = lrx.get(i);
			res.addAll(getPoints(rect));
		}
		return res;
	}

	private List<Point> getPoints(Rectangle rect) {
		List<Point> res = new ArrayList<>();
		if (rect.ul[0] == null || rect.ul[1] == null
			|| rect.lr[0] == null || rect.lr[1] == null) {
			return res;
		}
		for (int x = rect.ul[0]; x <= rect.lr[0]; x++) {
			for (int y = rect.lr[1]; y <= rect.ul[1]; y++) {
				res.add(new Point(x, y));
			}
		}
		return res;
	}

	private void remove(List<Line> lines, int i) {
		lines.set(i, lines.get(lines.size()-1));
		lines.remove(lines.size()-1);
	}

	class LineComparator implements Comparator<Line> {
		@Override
		public int compare(Line a, Line b) {
			if (a.loc == b.loc) {
				return a.count - b.count;
			}
			return a.loc - b.loc;
		}
	}

	class Point {
		int x;
		int y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	class Line {
		int loc;
		int count;
		List<Line> horizons;
		Line(int loc, int count) {
			this.loc = loc;
			this.count = count;
			horizons = new ArrayList<>();
		}
	}

	static class Rectangle {
		Integer[] ul;
		Integer[] lr;
		Rectangle(Integer ulx, Integer uly, Integer lrx, Integer lry) {
			ul = new Integer[2];
			ul[0] = ulx;
			ul[1] = uly;
			lr = new Integer[2];
			lr[0] = lrx;
			lr[1] = lry;
		}
	}
}
