package facebook;

/**
convert string to double -12.35e2 -> double。。这题可以assume给的string都是valid的
 */
public class ConvertStringToDouble {

	public static void main(String[] args) {
		String num = "-12.35e-2";
		System.out.println(new ConvertStringToDouble().convertToDouble(num));
	}

	public double convertToDouble(String num) {
		double integer = 0, postDot = 0;
		int postE = 0;
		boolean isNeg = false;
		num = num.trim();
		if (num.length() == 0) return 0;
		int i = 0;
		if (num.charAt(0) == '+' || num.charAt(0) == '-') {
			if (num.charAt(0) == '-') {
				isNeg = true;
			}
			i++;
		}
		boolean afterDot = false, afterExp = false;
		boolean isNegPostE = false;
		int postDotId = 10;
		for (; i < num.length(); i++) {
			char c = num.charAt(i);
			if (c >= '0' && c <= '9') {
				if (!afterDot && !afterExp){
					integer = (double) (integer*10 + c - '0');
				} else if (afterExp) {
					postE = (int) (postE*10 + c - '0');
				} else if (afterDot && !afterExp) {
					double tmp =  (double) (c - '0');
					postDot += tmp/postDotId;
					postDotId *= 10;
				}
			} else if (c == 'e' || c == 'E') {
				afterExp = true;
			} else if (c == '.') {
				afterDot = true;
			} else if (c == '-' && afterExp) {
				isNegPostE = true;
			}
		}
		double res = integer + postDot;
		for (int j = 0; j < postE; j++) {
			if (isNegPostE) {
				res /= 10;
			} else {
				res *= 10;
			}
		}
		return isNeg ? -res : res;
	}
}
