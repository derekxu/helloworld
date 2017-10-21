package facebook;

/**
http://www.1point3acres.com/bbs/thread-209156-1-1.html

Palindrome 修改, e.g. acbbc 去掉a

LC516. Longest Palindromic Subsequence
 */
public class MinEditToPalin {

	public int minNumEdits(String str) {
		if (str.length() == 0) return 0;
		int n = str.length();
		// dp: longest palin subsequence
		int[][] dp = new int[n][n];
		for (int i = n-1; i >= 0; i--) {
			dp[i][i] = 1;
			for (int j = i+1; j < n; j++) {
				dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
				if (str.charAt(i) == str.charAt(j)) {
					if (i+1 == j) {
						dp[i][j] = 2;
					} else {
						dp[i][j] = Math.max(dp[i][j], 2 + dp[i+1][j-1]);
					}
				}
			}
		}
		return n - dp[0][n-1];
	}

	public int minNumEdits2(String s) {
		if (s.length() == 0) return 0;
		int n = s.length();
		int[][] dp = new int[n][n];
		for (int i = n-1; i >= 0; i--) {
			for (int j = i+1; j < n; j++) {
				if (s.charAt(i) == s.charAt(j)) {
					if (j-i < 2) {
						dp[i][j] = 0;
					} else {
						dp[i][j] = dp[i+1][j-1];
					}
				} else {
					dp[i][j] = Math.min(dp[i+1][j], dp[i][j-1]) + 1;
				}
			}
		}
		return dp[0][n-1];
	}

	public static void main(String[] args) {
		String input = "aaacbca";
		System.out.println(new MinEditToPalin().minNumEdits(input));
		System.out.println(new MinEditToPalin().minNumEdits2(input));
	}
}
