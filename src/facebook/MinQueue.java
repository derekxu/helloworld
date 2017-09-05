package facebook;

import java.util.*;

/**
http://www.1point3acres.com/bbs/thread-193703-1-1.html
Min Queue, 跟Min Stack类似， 实现一个Queue， 然后O（1）复杂度获得这个Queue里最小的元素。
 */
public class MinQueue {
	Stack<Integer> s1;
	Stack<Integer> minS1;
	Stack<Integer> s2;
	Stack<Integer> minS2;

	MinQueue() {
		s1 = new Stack<>();
		minS1 = new Stack<>();
		s2 = new Stack<>();
		minS2 = new Stack<>();
	}

	public static void main(String[] args) {
		MinQueue q = new MinQueue();
		q.add(2);
		q.add(5);
		System.out.println("getMin: " + q.getMin());
		q.poll();
		System.out.println("getMin: " + q.getMin());
		q.poll();
		q.add(8);
		System.out.println("getMin: " + q.getMin());
		q.add(7);
		System.out.println("getMin: " + q.getMin());

	}

	public void add(int val) {
		if (minS1.isEmpty() || val <= minS1.peek()) {
			minS1.push(val);
		}
		s1.push(val);
	}

	public Integer poll() {
		if (isEmpty()) {
			return null;
		}
		if (s2.isEmpty()) {
			while (!s1.isEmpty()) {
				s2.push(s1.pop());
				if (minS2.isEmpty() || s2.peek() <= minS2.peek()) {
					minS2.push(s2.peek());
				}
				if (!minS1.isEmpty()) minS1.pop();
			}
		}
		if (s2.peek() == minS2.peek()) minS2.pop();
		return s2.pop();
	}

	public boolean isEmpty() {
		return s1.isEmpty() && s2.isEmpty();
	}

	public Integer getMin() {
		if (isEmpty()) return null;
		int min1 = Integer.MAX_VALUE;
		int min2 = Integer.MAX_VALUE;
		if (!minS1.isEmpty()) min1 = minS1.peek();
		if (!minS2.isEmpty()) min2 = minS2.peek();
		return Math.min(min1, min2);
	}
}
