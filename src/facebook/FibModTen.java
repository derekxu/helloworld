package facebook;

import java.util.*;

/**
n th Fibonacci number mod 10.
 */
public class FibModTen {
	List<Integer> arr;
	FibModTen() {
		arr = new ArrayList<>();
		arr.add(0);
		arr.add(1);
		// exit loop when repeats
		while (arr.get(arr.size()-1) != 1 || arr.get(arr.size()-2) != 9) {
			int len = arr.size();
			arr.add((arr.get(len-1) + arr.get(len-2))%10);
		}
	}

	public static void main(String[] args) {
		System.out.println(new FibModTen().fibModTen(10));
	}

	public int fibModTen(int n) {
		return arr.get(n%arr.size());
	}
}
