package facebook;

import java.util.*;

/**
在象棋棋盘上给你两个点A，B，问一个Knight(大哥说，就是骑马那个哈)最少要几步从A跳到B。露珠从来没玩过国际象棋，于 是问Knight咋走。
Turns out只要走任意朝向的L形就好。具体来说，如coordinate是(x,y) 那么在这里的一只knight可以跳到八个position中任何一个: (x- 2,y-1); (x-2,y+1);(x+2,y-1);(x+2,y+1);(x-1,y+2);(x-1,y-2);(x+1,y-2);(x+1,y+2).
 */
public class KnightPath {

	public static void main(String[] args) {
        int[] start = {1, 1};
        int[] end = {-4, -9};
        List<int[]> blocks = new ArrayList<>();
        blocks.add(new int[]{1,2});
        int res = new KnightPath().findPath(start, end, blocks, 10);
        System.out.println(res);
	}

	public int findPath(int[] start, int[] end, List<int[]> obs, int maxStep) {
		if (start[0] == end[0] && start[1] == end[1]) return 0;
		Set<String> blocks = new HashSet<>();
		for (int[] pos : obs) {
			blocks.add(posToStr(pos));
		}
		Queue<int[]> srcQ = new LinkedList<>();
		srcQ.add(start);
		Map<String, Integer> visitedSrc = new HashMap<>();
		visitedSrc.put(posToStr(start), 0);
		Queue<int[]> dstQ = new LinkedList<>();
		dstQ.add(end);
		Map<String, Integer> visitedDst = new HashMap<>();
		visitedDst.put(posToStr(end), 0);
		int lv = 0;
		int res = -1;
		while ((!srcQ.isEmpty() || !dstQ.isEmpty()) && lv * 2 <= maxStep) {
			res = bfs(srcQ, visitedSrc, visitedDst, lv, blocks);
			if (res > -1) return res;
			res = bfs(dstQ, visitedDst, visitedSrc, lv, blocks);
			if (res > -1) return res;
			lv++;
		}
		return -1;
	}
	private int bfs(Queue<int[]> q, Map<String, Integer> visited, Map<String, Integer> otherSide, int lv, Set<String> blocks) {
		int n = q.size();
		int[] dx = {1,1,-1,-1,2,2,-2,-2};
		int[] dy = {2,-2,2,-2,1,-1,1,-1};
		for (int k = 0; k < n; k++) {
			int[] cur = q.poll();		
			for (int i = 0; i < 8; i++) {
				int[] pos = new int[2];
				pos[0] = cur[0] + dx[i];
				pos[1] = cur[1] + dy[i];
				String posStr = posToStr(pos);
				if (visited.containsKey(posStr) || blocks.contains(posStr)) continue;
				if (otherSide.containsKey(posStr)) {
					return lv + 1 + otherSide.get(posStr);
				}
				visited.put(posStr, lv+1);
				q.add(pos);
			}
		}
		return -1;
	}
	private String posToStr(int[] pos) {
		return "x" + pos[0] + "y" + pos[1];
	}
}
