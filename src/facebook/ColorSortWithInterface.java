package facebook;

/**
给三个funtions: is_low(), is_mid(), is_high(). 
让给一个数组排序, low的放在最前面, mid的放在中间, high的放在最后面. Color sort: think about when there are K colors
 */
public class ColorSortWithInterface {

	int th1 = 3;
	int th2 = 6;

	public static void main(String[] args) {
		int[] arr = {2,5,6,7,3,8,2,4};
		int[] res = new ColorSortWithInterface().sort(arr);
		for (int num : res) {
			System.out.print(num + ",");
		}
	}

	public int[] sort(int[] arr) {
		int l = 0, r = arr.length - 1;
		int i = l;
		while (i <= r) {
			if (isLow(arr[i])) {
				if (i > l) {
					swap(arr, l, i);
					l++;
				} else {
					l++;
					i++;
				}
			} else if (isHigh(arr[i])) {
				swap(arr, r, i);
				r--;
			} else {
				i++;
			}
		}
		return arr;
	}

	private void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	public boolean isLow(int x) {
		return x <= th1;
	}

	public boolean isMid(int x) {
		return x > th1 && x <= th2;
	}

	public boolean isHigh(int x) {
		return x > th2;
	}
}
