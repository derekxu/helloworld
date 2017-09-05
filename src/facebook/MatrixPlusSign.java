package facebook;

import java.util.Arrays;

/**
Matrix中有0和1， 求1组成的最大的plus （+）形状的长度
就是以每个不为1的cell为中心，向上下左右四个方向扩展，找到四个长度中最小的那个就是以这个Cell为中心的plus.
例如，下面的。 以（2,2）为中心的plus长度为1. （Plus的外围是一个正方形）

0 0 1 0 0 1 0
1 0 1 0 1 0 1
1 1 1 1 1 1 1
0 0 1 0 0 0 0
0 0 0 0 0 0 0
 */
public class MatrixPlusSign {

	public static void main(String[] args) {
		char[][] box = {
				"01111".toCharArray(),
				"01111".toCharArray(),
				"11111".toCharArray(),
				"01111".toCharArray(),
				"11100".toCharArray(),
				};
		System.out.println(new MatrixPlusSign().maxLen(box));
	}

	public int maxLen(char[][] box) {
		if (box.length == 0 || box[0].length == 0)
			return 0;
		int m = box.length, n = box[0].length;
		int[] h = new int[n];
		int[] left = new int[n];
		int[][] topDown = new int[n][n];
		for (int i = 0; i < m; i++) {
			int lenSoFar = 0;
			for (int j = 0; j < n; j++) {
				left[j] = lenSoFar;
				if (box[i][j] == '0') {
					h[j] = 0;
					lenSoFar = 0;
				} else {
					h[j]++;
					lenSoFar++;
				}
				
			}
			lenSoFar = 0;
			for (int j = n-1; j >= 0; j--) {
				if (h[j] == 0) {
					lenSoFar = 0;
				} else {
					topDown[i][j] = Math.min(h[j]-1, Math.min(left[j], lenSoFar));
					lenSoFar++;
				}
			}
		}
		Arrays.fill(h, 0);
		int res = 0;
		for (int i = m-1; i >= 0; i--) {
			for (int j = 0; j < n; j++) {
				if (box[i][j] == '0') {
					h[j] = 0;
				} else {
					res = Math.max(res, Math.min(h[j], topDown[i][j]));
					h[j]++;
				}
			}
		}
		return res;
	}
}
