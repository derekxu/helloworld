package facebook;

import java.util.*;

public class MonthIntervalMerge {
	String[] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
	Map<String, Integer> moMap;

	MonthIntervalMerge() {
		moMap = new HashMap<>();
		for (int i = 0; i < 12; i++) {
			moMap.put(months[i], i);
		}
	}

	public static void main(String[] args) {
		String[] data = {"Apr 2010 - Dec 2010", "Aug 2010 - Dec 2010", "Jan 2011 - Mar 2011"};
		System.out.println(new MonthIntervalMerge().totalCoverMonth(data));
	}

	public int totalCoverMonth(String[] data) {
		if (data.length == 0) return 0;
		int res = 0;
		List<List<Integer>> seq = new ArrayList<>();
		for (String str : data) {
			seq.add(parse(str));
		}
		Collections.sort(seq, new Comparator<List<Integer>> () {
			@Override
			public int compare(List<Integer> a, List<Integer> b) {
				return Integer.compare(a.get(0), b.get(0));
			}
		});
		List<Integer> last = null;
		for (List<Integer> cur : seq) {
			if (last != null) {
				if (last.get(1) >= cur.get(0)) {
					last.set(1, Math.max(last.get(1), cur.get(1)));
				} else {
					res += last.get(1) - last.get(0);
					last = cur;
				}
			} else {
				last = cur;
			}
		}
		res += last.get(1) - last.get(0);
		return res;
	}
	private List<Integer> parse(String data) {
		String[] range = data.trim().split("-");
		List<Integer> res = new ArrayList<>();
		for (String str : range) {
			String[] ts = str.trim().split(" ");
			res.add(Integer.parseInt(ts[1]) * 12 + moMap.get(ts[0]));
		}
		return res;
	}
}
