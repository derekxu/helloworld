package facebook;

/**
 * 
给一个数，问是第几个Fibonacci, 不是Fibonacci 就是的话就是前一个index,
 */
public class FibIndex {

	public static void main(String[] args) {
		int num = 6;
		int res = new FibIndex().fibIndex(num);
		System.out.println(res);
	}

	public int fibIndex(int num) {
		int[] fib = new int[2];
		if (num < 0) return -1;
		if (num < 2) return num;
		fib[0] = 0;
		fib[1] = 1;
		int i = 1;
		while (fib[i%2] <= num) {
			if (fib[i%2] == num) return i+1;
			fib[(i+1)%2] = fib[i%2] + fib[(i-1)%2];
			i++;
		}
		return i;
	}
}
