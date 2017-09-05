package facebook;

import java.util.*;

/**
给一个matrix, all elements are positive，问有没有个sub rectangle加起来和等于target。return true/false。
 */

public class SubmatrixSum {

	public static void main(String[] args) {
		int[][] matrix = {
				{1,2,3},
				{4,5,6},
				{7,8,9},
		};
		int target = 16;
		if (new SubmatrixSum().hasSum(matrix, target)) {
			System.out.println("Has sub matrix");
		} else {
			System.out.println("No sub matrix");
		}

	}

	public boolean hasSum(int[][] matrix, int target) {
		if(matrix.length == 0 || matrix[0].length == 0)
			return false;
		
		int m = matrix.length, n = matrix[0].length;
		int[][] sums = new int[m+1][n+1];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				sums[i+1][j+1] = matrix[i][j] + sums[i][j+1] + sums[i+1][j] - sums[i][j];
			}
		}
		for (int i = 0; i < m; i++) {
			for (int j = i+1; j <= m; j++) {
				Set<Integer> set = new HashSet<>();
				for (int k = 0; k <= n; k++) {
					int tmp = sums[i][k] - sums[j][k];
					if (set.contains(tmp)) {
						return true;
					}
					set.add(tmp-target);
				}
			}
		}
		return false;
	}
}
