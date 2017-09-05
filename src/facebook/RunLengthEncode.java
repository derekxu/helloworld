package facebook;

public class RunLengthEncode {

	public static void main(String[] args) {
		String code = "AA1111111077FF";
		System.out.println(new RunLengthEncode().rle(code));
	}

	public String rle (String code) {
		if (code.length() == 0) return "";
		Character last = null;
		int count = 0;
		StringBuilder res = new StringBuilder();
		for (char c : code.toCharArray()) {
			if (last == null) {
				count = 1;
			} else if (last == c) {
				count++;
			} else {
				res.append(count);
				res.append(last);
				count = 1;
			}
			last = c;
		}
		res.append(count);
		res.append(last);
		return res.toString();
	}
}
