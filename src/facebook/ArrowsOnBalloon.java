package facebook;

import java.util.*;

/**
https://mp.weixin.qq.com/s/m-nnVCjyD9svTQq6Ll35Ug

在二维空间中的一组气球，给出每个气球的横向直径的起始横坐标和结束横坐标，保证起始横坐标小于结束横坐标。

不需要考虑气球的纵坐标，因此横坐标区间可以相互重叠。气球最多有10000个。一支箭可以选定一个横坐标纵向射击。

 一个气球的横向直径两端横坐标为xbegin,xend，一支箭射击的横坐标为x，如果有xbegin<=x<=xend，则这支箭可以刺破该气球。没有箭的使用数量限制，并且一支箭可以刺破相应坐标上的所有气球。

求出刺破所有气球所需的最少的箭的数量。
 */
public class ArrowsOnBalloon {

	public static void main(String[] args) {
		int[][] balloons = {
				{1,6},
				{2,8},
				{10,16},
				{7,12},
		};
		System.out.println(new ArrowsOnBalloon().numOfArrows(balloons));
	}

	public int numOfArrows(int[][] balloons) {
		if (balloons.length == 0) return 0;
		Arrays.sort(balloons, new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				return a[1] - b[1];
			}
		});
		int res = 1;
		int lastEnd = balloons[0][1];
		for (int i = 1; i < balloons.length; i++) {
			if (lastEnd < balloons[i][0]) {
				res++;
				lastEnd = balloons[i][1];
			}
		}
		return res;
	}
}
