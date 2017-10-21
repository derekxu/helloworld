package facebook;

/**
给一个手机键盘(只有0-9，不考虑*#那两个位置)样式的棋盘，骑士初始在数字1的位置，问走了s步以后(每 步走日字)，有多少种可能的走法。
提示是可以hard code下一步的位置， 比如1->(6，8)。
 */
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
