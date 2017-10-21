package facebook;

public class MoveZeros {

	// Min writes, no need to keep orders
	public void move3(int[] arr) {
		int l = 0, r = arr.length-1;
		while (l < r) {
			while (l < r && arr[l] != 0) l++;
			while (l < r && arr[r] == 0) r--;
			if (l < r) {
				swap(arr, l, r);
				l++;
				r--;
			}
		}
	}
	private void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	public static void main(String[] args) {
		int[] arr = {0,0,1,1,3,0,0};
		MoveZeros instance = new MoveZeros();
		instance.move3(arr);
		for (int num : arr) {
			System.out.print(num+",");
		}
		System.out.println();
	}

}
