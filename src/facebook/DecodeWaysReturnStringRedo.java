package facebook;

import java.util.*;

/*
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26

Return String messages of decode ways.
 */

public class DecodeWaysReturnStringRedo {
	public static void main(String[] args) {
		String input = "12234";
		DecodeWaysReturnStrings instance = new DecodeWaysReturnStrings();
		for (String str : instance.decodeWays(input)) {
			System.out.println(str);
		}
	}

	public List<String> decodeways(String nums) {
		if (nums.length() == 0) return new ArrayList<String>();
		Node[] dp = new Node[nums.length()+1];
		List<String> arr = new ArrayList<>();
		arr.add("");
		dp[0] = new Node(arr);
		char[] strs = nums.toCharArray();
		for (int i = 0; i < strs.length; i++) {
			Node n1 = ways(strs[i]);
			Node tmp = union(dp[i], n1);
			if (i > 0) {
				Node n2 = ways(strs[i-1], strs[i]);
				tmp = union(tmp, union(dp[i-1], n2));
			}
			dp[i+1] = new Node(tmp.strs);
		}
		return dp[strs.length].strs;
	}

	Node ways(char ch) {
		if (ch >= '1' && ch <= '9') {
			char code = (char)('A' + ch - '1');
			List<String> arr = new ArrayList<>();
			arr.add(new StringBuilder(code).toString());
			return new Node(arr);
		}
		return null;
	}

	Node ways(char ch1, char ch2) {
		int num = (ch1 - '0') * 10 + (ch2 - '0');
		char code = (char)('A' + num - 1);
		if (ch1 == '1') {
			if (ch2 >= '0' && ch2 <= '9') {
				List<String> arr = new ArrayList<>();
				arr.add(new StringBuilder(code).toString());
				return new Node(arr);
			}
		} else if (ch1 == '2') {
			if (ch2 >= '0' && ch2 <= '6') {
				List<String> arr = new ArrayList<>();
				arr.add(new StringBuilder(code).toString());
				return new Node(arr);
			}
		}
		return null;
	}

	private Node union(Node n1, Node n2) {
		if (n1 == null) return n2;
		if (n2 == null) return n1;
		List<String> res = new ArrayList<>();
		for (String str1 : n1.strs) {
			for (String str2 : n2.strs) {
				res.add(str1 + str2);
			}
		}
		return new Node(res);
	}

	class Node {
		List<String> strs;
		Node(List<String> strs) {
			this.strs = new ArrayList<>(strs);
		}
		int getWays() {
			return strs.size();
		}
	}
}
