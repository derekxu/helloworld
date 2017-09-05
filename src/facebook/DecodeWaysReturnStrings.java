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

public class DecodeWaysReturnStrings {
	public static void main(String[] args) {
		String input = "12234";
		DecodeWaysReturnStrings instance = new DecodeWaysReturnStrings();
		for (String str : instance.decodeWays(input)) {
			System.out.println(str);
		}
	}

	public List<String> decodeWays(String nums) {
		if (nums.length() == 0) return new ArrayList<String>();
		Node[] dp  = new Node[2];
		dp[0] = new Node(1, "");
		dp[1] = ways(nums.charAt(0));
		for (int i = 1; i < nums.length(); i++) {
			Node n1 = union(dp[i%2], ways(nums.charAt(i)));
			Node n2 = union(dp[(i-1)%2], ways(nums.charAt(i-1), nums.charAt(i)));
			if (n1 == null) {
				dp[(i+1)%2] = n2;
			} else if (n2 == null) {
				dp[(i+1)%2] = n1;
			} else {
				dp[(i+1)%2] = new Node(n1.val+n2.val, n1.strs);
				dp[(i+1)%2].strs.addAll(n2.strs);
			}
		}
		if (dp[nums.length()%2] == null)
			return new ArrayList<String>();
		return dp[nums.length()%2].strs;
	}
	private void print(int i, List<String> strs) {
		System.out.printf(i + ": ");
		for (String str : strs) {
			System.out.printf(str + ",");
		}
		System.out.println();
	}
	private Node ways(char ch) {
		if (ch == '0') return null;
		return new Node(1, String.valueOf((char)('A' + ch - '1')));
	}
	private Node ways(char ch1, char ch2) {
		if (ch1 == '0' || ch1 > '2') return null;
		int num = Integer.parseInt(String.valueOf(ch1) + String.valueOf(ch2));
		String str = String.valueOf((char)('A' + num - 1));
		if (ch1 == '1') {
			return new Node(1, str);
		} else {
			if (ch2 > '6')
				return null;

			return new Node(1, str);
		}
	}
	private Node union(Node n1, Node n2) {
		if (n1 == null || n2 == null) return null;
		Node res = new Node(n1.val * n2.val);
		for (String str1 : n1.strs) {
			for (String str2 : n2.strs) {
				res.strs.add(str1+str2);
			}
		}
		return res;
	}
}

class Node {
	long val;
	List<String> strs;
	Node(long val) {
		this.val = val;
		strs = new ArrayList<>();
	}
	Node(long val, List<String> strs) {
		this.val = val;
		this.strs = new ArrayList<String>(strs);
	}
	Node(long val, String str) {
		this.val = val;
		this.strs = new ArrayList<String>();
		strs.add(str);
	}
}
