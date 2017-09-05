package facebook;

public class CircularBuffer {
	Integer[] buff;
	CircularBuffer(int n) {
		buff = new Integer[n];
	}

	public static void main(String[] args) {
		int n = 10;
		CircularBuffer buffer = new CircularBuffer(n);
		buffer.set(21, 11);
		try {
			buffer.get(55);
		} catch (RuntimeException err) {
			System.out.println("55 not initialized");
		}
		int id = 51;
		System.out.printf("Get %d, result: %d\n", id, buffer.get(id));
		
	}

	public int get(int i) {
		Integer res = buff[i%buff.length];
		if (res == null) throw new RuntimeException();
		return res;
	}

	public void set(int i, int val) {
		buff[i%buff.length] = val;
	}
}
