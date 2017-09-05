package facebook;

public class KnightOnDigitPad {

	public static void main(String[] args) {
		int steps = 3;
		System.out.println(new KnightOnDigitPad().waysToMove(steps));
		System.out.println(new KnightOnDigitPad().waysToMove2(steps));
	}

	public int waysToMove(int steps) {
		int[][] jumps = {{4,6},{6,8},{7,9},{4,8},{3,9,0},{},{1,7,0},{2,6},{1,3},{2,4}};
		return helper(steps, 1, jumps);
	}
	private int helper(int s, int num, int[][] jumps) {
		if (s == 0) return 1;
		int res = 0;
		for (int x : jumps[num]) {
			res += helper(s-1, x, jumps);
		}
		return res;
	}

	public int waysToMove2(int steps) {
		int[][] jumps = {{4,6},{6,8},{7,9},{4,8},{3,9,0},{},{1,7,0},{2,6},{1,3},{2,4}};
		int[][] dp = new int[2][10];
		dp[0][1] = 1;
		for (int i = 0; i < steps; i++) {
			for (int j = 0; j < 10; j++) {
				dp[(i+1)%2][j] = 0;
			}
			for (int j = 0; j < 10; j++) {
				for (int k : jumps[j]) {
					dp[(i+1)%2][k] += dp[i%2][j];
				}
			}
		}
		int res = 0;
		for (int j = 0; j < 10; j++) {
			res += dp[steps%2][j];
		}
		return res;
	}
}
