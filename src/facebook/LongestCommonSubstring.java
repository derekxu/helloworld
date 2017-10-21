package facebook;

import java.util.*;

/**
找出两个给出两个string, leetcode, codyabc和一个数字k = 3,
问两个string里面存不存在连续的common substring大于等于k.比如这个例子，两个string 都有cod,所以返回true
 */
public class LongestCommonSubstring {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "leetcode";
		String t = "codspring";
		int k = 4;
		System.out.println(new LongestCommonSubstring().hasCommonSubstring(s, t, k));
		System.out.println(new LongestCommonSubstring().hasCommonSubString2(s, t, k));
	}

	public boolean hasCommonSubstring(String s, String t, int k) {
		int m = s.length(), n = t.length();
		if (m == 0 || n == 0) return k == 0;
		int[][] dp = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (s.charAt(i) == t.charAt(j)) {
					dp[i][j] = 1;
					if (i > 0 && j > 0) {
						dp[i][j] += dp[i-1][j-1];
					}
					if (dp[i][j] >= k) return true; 
				} else {
					dp[i][j] = 0;
				}
			}
		}
		return false;
	}

	public boolean hasCommonSubString2(String s, String t, int k) {
		int m  = s.length(), n = t.length();
		if (m == 0 || n == 0) return k == 0;
		Set<String> set = new HashSet<>();
		for (int i = 0; i <= m-k; i++) {
			set.add(s.substring(i, i+k));
		}
		for (int i = 0; i <= n-k; i++) {
			if (set.contains(t.substring(i, i+k))) {
				return true;
			}
		}
		return false;
	}
}
