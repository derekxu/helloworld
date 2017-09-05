package facebook;

/**
void putInteger( Int x) {} 这个就是打印一个整数 ，必须用下面的程序。
using putc( char ); 这个function就是打印char， 输入是什么char打印就是什么char
 */
public class PrintInteger {

	public static void main(String[] args) {
		int num = 1102241245;
		new PrintInteger().printInteger(num);
	}

	public void printInteger(int x) {
		if (x == Integer.MAX_VALUE) {
			for (char c : "-2147483648".toCharArray())
				printChar(c);
			return;
		}
		if (x == 0) printChar('0');
		boolean isNeg = false;
		if (x < 0) {
			isNeg = true;
		}
		x = Math.abs(x);
		StringBuilder sb = new StringBuilder();
		while (x > 0) {
			sb.append(x%10);
			x /= 10;
		}
		if (isNeg) sb.append('-');
		for (char c : sb.reverse().toString().toCharArray()) {
			printChar(c);
		}
	}

	private void printChar(char c) {
		System.out.printf("%s", c);
	}
}
