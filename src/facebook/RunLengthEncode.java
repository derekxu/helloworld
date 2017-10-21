package facebook;

/**
要求就是写个function实现run length encoding
然后input是binary的
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=218482&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
 */
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
