package facebook;

import java.util.*;

/**
给一个matrix, all elements are positive，问有没有个sub rectangle加起来和等于target。return true/false。
 */
public class SubmatrixSumRedo {

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
			for (int j = i+1; j < m+1; j++) {
				Set<Integer> set = new HashSet<>();
				for (int k = 0; k < n+1; k++) {
					int num = sums[i][k] - sums[j][k];
					if (set.contains(num)) {
						return true;
					}
					set.add(num - target);
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		int[][] matrix = {
				{1,2,3},
				{4,5,6},
				{7,8,9},
		};
		int target = 16;
		if (new SubmatrixSumRedo().hasSum(matrix, target)) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}

}
