package facebook;

/**
实现一个比较两道字符串的方法；前提是让字符串分block比较，相连的字母组成一个block，相连的数字组成一个block，比如“abcd12ef3”由4个block组成，
两个字符串变成两组block之后对应的block挨个比较，如果对应的block一个是字母一个是数字，那么字母小于数字；如果对应的block都是字母，
用的是String的标准的比较方法；如果对应的两个block都是数字，那么比较数字的绝对值大小。
比如“abc12”大于"abc9"（第一个block相等，第二个block 12>9）, "a"小于“1”（字母小于数字），“12abd”小于"12ab"
（数字block一样，后面的字母block后者大）。
这道题了解了之后很简单，就是需要沟通很久确定各种情况，而且写代码很麻烦，还有很多边界条件需要确定（比如大小写，数字block会不会overflow之类），
再加上当时脑袋有点卡壳，我用了java的String.compareTo，parseInt，substring等自带的方程，都写了半天。
 */
public class CompareStrings {
	public int compare(String s1, String s2) {
		if (s1.length() == 0 && s2.length() == 0)
			return 0;
		if (s1.length() == 0) return -1;
		if (s2.length() == 0) return 1;
		int i = 0, j = 0;
		StringBuilder num1 = new StringBuilder();
		StringBuilder num2 = new StringBuilder();
		while (i < s1.length() && j < s2.length()) {
			if (!isDigit(s1.charAt(i)) && !isDigit(s2.charAt(j))) {
				if (num1.length() > 0 && num2.length() > 0 ) {
					int n1 = Integer.parseInt(num1.toString());
					int n2 = Integer.parseInt(num2.toString());
					if (n1 != n2) {
						return n1 - n2;
					}
					num1 = new StringBuilder();
					num2 = new StringBuilder();
				} else if (num1.length() > 0) {
					return 1;
				} else if (num2.length() > 0) {
					return -1;
				}
				if (s1.charAt(i) != s2.charAt(j)) {
					return Character.compare(s1.charAt(i), s2.charAt(j));
				}
				i++;
				j++;
			} else {
				if (isDigit(s1.charAt(i))) {
					num1.append(s1.charAt(i));
					i++;
				}
				if (isDigit(s2.charAt(j))) {
					num2.append(s2.charAt(j));
					j++;
				}
			}
		}
		while (i < s1.length() && isDigit(s1.charAt(i))) {
			num1.append(s1.charAt(i++));
		}
		while (j < s2.length() && isDigit(s2.charAt(j))) {
			num2.append(s2.charAt(j++));
		}

		if (num1.length() > 0 && num2.length() > 0 ) {
			int n1 = Integer.parseInt(num1.toString());
			int n2 = Integer.parseInt(num2.toString());
			if (n1 != n2) {
				return n1 - n2;
			}
			num1 = new StringBuilder();
			num2 = new StringBuilder();
		} else if (num1.length() > 0) {
			return 1;
		} else if (num2.length() > 0) {
			return -1;
		}

		if (i < s1.length() || j < s2.length()) {
			return i < s1.length() ? 1 : -1;
		}
		return 0;
	}
	private boolean isDigit(char c) {
		return c >= '0' && c <= '9';
	}

	public static void main(String[] args) {
		System.out.println(new CompareStrings().compare("b22ab", "b22"));
	}
}
