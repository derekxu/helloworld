package facebook;

/**
convert string to double -12.35e2 -> double。。这题可以assume给的string都是valid的
 */
public class ConvertStringToDoubleRedo {

	public double convert(String num) {
		int n = num.length();
		if (n == 0) return 0.0;
		int i = 0;
		boolean isNeg = false;
		if (num.charAt(i) == '+' || num.charAt(i) == '-') {
			if (num.charAt(i) == '-') {
				isNeg = true;
			}
			i++;
		}
		boolean isPostDot = false, isPostE = false;
		boolean isNegE = false;
		double integer = 0, floatNum = 0;
		int floatCount = 0;
		int expNum = 0;
		for (; i < n; i++) {
			char c = num.charAt(i);
			if (c >= '0' && c <= '9') {
				if (isPostE) {
					expNum = expNum*10 + (int) (c-'0');
				} else if (isPostDot) {
					floatNum = floatNum*10 + (double) (c-'0');
					floatCount++;
				} else {
					integer = integer*10 + (double)(c-'0');
				}
			} else if (c == '.') {
				isPostDot = true;
			} else if (c == 'e' || c == 'E') {
				isPostE = true;
			} else if (c == '-' && isPostE) {
				isNegE = true;
			}
		}
		if (isNegE) expNum = -expNum;
		double res = integer;
		res += floatNum/Math.pow(10, floatCount);
		res *= Math.pow(10, expNum);
		return isNeg ? -res : res;
	}

	public static void main(String[] args) {
		String num = "-12.35e-2";
		System.out.println(new ConvertStringToDoubleRedo().convert(num));
	}

}
