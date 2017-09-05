package facebook;

/**
balance parentheses in a string
例子:
"(a)()" -> "(a)()"
"((bc)" -> "(bc)"
")))a((" -> "a"
"(a(b)" ->"(ab)" or "a(b)"
 */
public class BalanceParanthesis {

	public static void main(String[] args) {
		String str = ")))a((";
		System.out.println(new BalanceParanthesis().balance(str));
	}

	public String balance(String str) {
		return helper(helper(str, "()".toCharArray()), ")(".toCharArray());
	}

	public String helper(String str, char[] pair) {
		int stack = 0;
		char[] arr = str.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == pair[1] && stack == 0)
				continue;
			if (arr[i] == pair[0]) stack++;
			if (arr[i] == pair[1]) stack--;
			sb.append(arr[i]);
		}
		return sb.reverse().toString();
	}
}
