package facebook;

import java.util.*;

/**
自然string comparator。不知道的搜下。就是string 比较的时候考虑里面数字的大小，比如 abc9 < abc123 abc > ab9 因为char比digit重要。
 *
 */
public class NatrualStringComparator implements Comparator<String> {

	public static void main(String[] args) {
		String a = "ab9";
		String b = "abc123";
		int res = new NatrualStringComparator().compare(a, b);
		System.out.println(res);
	}

	@Override
	public int compare (String a, String b) {
		int n = Math.min(a.length(), b.length());
		for (int i = 0; i < n; i++) {
			int cmp = compare(a.charAt(i), b.charAt(i));
			if (cmp != 0) {
				return cmp;
			}
		}
		return Integer.compare(a.length(), b.length());
	}

	private int compare (char a, char b) {
		if ((a >= '0' && a <= '9') && (b >= '0' && b <= '9')) {
			return Character.compare(a, b);
		}
		if ((a >= '0' && a <= '9')) return -1;
		if ((b >= '0' && b <= '9')) return 1;
		
		return (int) a - b;
	}
}
