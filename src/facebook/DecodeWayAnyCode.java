package facebook;

/**
第二题decode ways,出了个小bug，该i+1的写成了i..然后followup是如果给的value不连续，比如a:78, b:539, …, 怎么办。。

链接: https://instant.1point3acres.com/thread/198514
 *
 */
public class DecodeWayAnyCode {

	public static void main(String[] args) {
		String[] codes = "78,539,25".split(",");
		String nums = "7853925";
		int res = new DecodeWayAnyCode().decodeWays(nums, codes);
		System.out.println(res);
	}

	public int decodeWays(String nums, String[] codes) {
		int n = nums.length();
		if (n == 0) return 1;
		int[] dp = new int[n+1];
		dp[0] = 1;
		for (int i = 0; i < n; i++) {
			for (String code : codes) {
				int len = code.length();
				if (code.charAt(code.length()-1) == nums.charAt(i) &&
						len <= i+1 && code.equals(nums.substring(i+1-len, i+1))) {
					dp[i+1] += dp[i+1-len];
				}
			}
		}
		return dp[n];
	}
}
