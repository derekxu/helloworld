package facebook;

/**
http://www.1point3acres.com/bbs/thread-206743-1-1.html

k个二 制数相加
 */
public class AddKBinaryNumbers {

	public static void main(String[] args) {
		String[] nums = {"111","10","011","111"};
		System.out.println(new AddKBinaryNumbers().sum(nums));
	}

	public String sum(String[] nums) {
		if (nums.length == 0) return "";
		StringBuilder pre = new StringBuilder(nums[0]);
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < nums.length; i++) {
			String num = nums[i];
			sb = new StringBuilder();
			int car = 0;
			int j = num.length()-1, k = pre.length()-1;
			while (j >= 0 && k >= 0) {
				car += (int) (num.charAt(j)-'0');
				car += (int) (pre.charAt(k)-'0');
				j--;
				k--;
				sb.append(car%2);
				car /= 2;
			}
			while (j >= 0) {
				car += (int) (num.charAt(j)-'0');
				j--;
				sb.append(car%2);
				car /= 2;
			}
			while (k >= 0) {
				car += (int) (pre.charAt(k)-'0');
				k--;
				sb.append(car%2);
				car /= 2;
			}
			while (car > 0) {
				sb.append(car%2);
				car /= 2;
			}
			pre = sb.reverse();
		}
		return pre.toString();
	}
}
