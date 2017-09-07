package facebook;

import java.util.*;

/**
http://www.1point3acres.com/bbs/thread-209156-1-1.html

Shorest Path between 2 nodes in Graph
 */
public class ShortestPathToNodes {

	public String shortestPath(GraphNode start, GraphNode end) {
		if (start == null || end == null) return "";
		if (start == end) return String.format("%d", start.val);
		Queue<Elem> q = new LinkedList<>();
		q.add(new Elem(start, String.format("%d", start.val)));
		Set<GraphNode> visited = new HashSet<>();
		visited.add(start);
		while (!q.isEmpty()) {
			int n = q.size();
			for (int i = 0; i < n; i++) {
				Elem top = q.poll();
				for (GraphNode sub : top.node.subs) {
					if (sub == end) {
						return top.path + "," + sub.val;
					}
					if (!visited.contains(sub)) {
						visited.add(sub);
						q.add(new Elem(sub, top.path + "," + sub.val));
					}
				}
			}
		}
		return "";
	}

	public static void main(String[] args) {
		int n = 5;
		GraphNode[] nodes = new GraphNode[n];
		for (int i = 0; i < n; i++) {
			nodes[i] = new GraphNode(i);
		}
		nodes[0].subs.add(nodes[1]);
		nodes[0].subs.add(nodes[2]);
		nodes[1].subs.add(nodes[3]);
		nodes[3].subs.add(nodes[4]);
		nodes[2].subs.add(nodes[4]);
		System.out.println(new ShortestPathToNodes().shortestPath(nodes[0], nodes[n-1]));
	}

	class Elem {
		GraphNode node;
		String path;
		Elem(GraphNode node, String path) {
			this.node = node;
			this.path = path;
		}
	}

	static class GraphNode {
		int val;
		List<GraphNode> subs;
		GraphNode(int val) {
			this.val = val;
			subs = new ArrayList<>();
		}
	}
}
