package facebook;

public class NumPalindrome {

	public static void main(String[] args) {
		String str = "aaaba";
		int res = new NumPalindrome().numPalindrome(str);
		System.out.println(res);
	}
	public int numPalindrome(String str) {
		if (str.length() == 0) return 1;
		int n = str.length();
		int res = 0;
		boolean[][] dp = new boolean[n][n];
		for (int i = n-1; i >= 0; i--) {
			dp[i][i] = true;
			res++;
			for (int j = i+1; j < n; j++) {
				if (str.charAt(i) == str.charAt(j)) {
					if (i+1>j-1 || dp[i+1][j-1]) {
						dp[i][j] = true;
						res++;
					}
				}
			}
		}
		return res;
	}

}
