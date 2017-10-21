package facebook;

import java.util.*;

/**
在象棋棋盘上给你两个点A，B，问一个Knight(大哥说，就是骑马那个哈)最少要几步从A跳到B。露珠从来没玩过国际象棋，于 是问Knight咋走。
Turns out只要走任意朝向的L形就好。具体来说，如coordinate是(x,y) 那么在这里的一只knight可以跳到八个position中任何一个: (x-2,y-1); (x-2,y+1);(x+2,y-1);(x+2,y+1);(x-1,y+2);(x-1,y-2);(x+1,y-2);(x+1,y+2).
 */
public class KnightPathRedo {
	public int getPath(List<int[]> obs, int[] start, int[] end) {
		if (isEqual(start, end)) return 0;
		Queue<int[]> q = new LinkedList<>();
		q.add(start);
		Set<String> used = new HashSet<>();
		used.add(toHash(start));
		Set<String> blocks = new HashSet<>();
		for (int[] ob : obs) {
			blocks.add(toHash(ob));
		}
		int step = 0;
		int[] dr = new int[]{1,1,-1,-1,2,2,-2,-2};
		int[] dc = new int[]{2,-2,2,-2,1,-1,1,-1};
		while (!q.isEmpty()) {
			int n = q.size();
			for (int k = 0; k < n; k++) {
				int[] pos = q.poll();
				for (int i = 0; i < 8; i++) {
					int r = pos[0]+dr[i];
					int c = pos[1]+dc[i];
					String h = toHash(r, c);
					if (used.contains(h) || blocks.contains(h))
						continue;
					if (h.equals(toHash(end))) return step+1;
					q.add(new int[]{r,c});
					used.add(h);
				}
			}
			step++;
		}
		return -1;
	}
	private String toHash(int[] pos) {
		return "x" + String.valueOf(pos[0]) + "y" + String.valueOf(pos[1]);
	}
	private String toHash(int r, int c) {
		return "x" + String.valueOf(r) + "y" + String.valueOf(c);
	}
	private boolean isEqual(int[] a, int[] b) {
		return a[0] == b[0] && a[1] == b[1];
	}

	public static void main(String[] args) {
        int[] start = {1, 1};
        int[] end = {-4, -9};
        List<int[]> blocks = new ArrayList<>();
        blocks.add(new int[]{1,2});
        int res = new KnightPathRedo().getPath(blocks, start, end);
        System.out.println(res);
	}

}
