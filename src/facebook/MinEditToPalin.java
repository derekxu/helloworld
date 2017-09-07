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

	public static void main(String[] args) {
		String input = "aaacbca";
		System.out.println(new MinEditToPalin().minNumEdits(input));
	}
}
