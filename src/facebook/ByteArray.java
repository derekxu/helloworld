package facebook;

/**
字符串是以[5, 'a', 'b', 'c', 'd', 'e']的形式存储，现在要你用C++将这样两个东西连接起来。。。比如[3,'a',b','c']和[2,'x', 'y']变成[5, 'a', b', 'c', 'x', 'y']
 */
public class ByteArray {

	public static void main(String[] args) {
		byte[] arr1 = {3, 'a', 'b', 'c'};
		byte[] arr2 = {2, 'x', 'y'};
		byte[] res = new ByteArray().merge(arr1, arr2);
		System.out.printf("%d,", res[0]);
		for (int i = 1; i < res.length; i++) {
			System.out.printf("%s,", (char)res[i]);
		}
	}

	public byte[] merge(byte[] arr1, byte[] arr2) {
		if (arr1.length == 0) return arr2;
		if (arr2.length == 0) return arr1;
		int n1 = (int) arr1[0];
		int n2 = (int) arr2[0];
		if (n1 + n2 > 255) {
			throw new RuntimeException();
		}
		byte[] res = new byte[n1+n2+1];
		res[0] = (byte)(n1+n2);
		for (int i = 0; i < n1; i++) {
			res[i+1] = arr1[i+1];
		}
		for (int i = 0; i < n2; i++) {
			res[i+n1+1] = arr2[i+1];
		}
		return res;
	}
}
