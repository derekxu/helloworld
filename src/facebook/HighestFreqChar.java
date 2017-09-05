package facebook;

import java.util.*;

/**
面试官说先从一个简单的问题开始，给一个string，找出string中出现次数最多的字母，快速的写了个map count的算 法;之后
follow up: 如果不算空格呢?不算其他字符呢?大写小写字母都算相同字母呢?算法的时间空间复杂度?O(n) 的时间怎么优化?
这个问题纠结了很久，开始说如果变成O(logn)需要用bianry search，但是需要sorted string，最后说 这个方法不行。那么面试官提示说不然少读一半的字母，或者少读一个字母也算优化。。。。最后想了和出现频率最高 和第二高的字母相关，频率相减如果大于剩下的字母就可以直接return了，最后讨论是大于还是大于等于，仔细思考一 下大于等于也可以。。。。
 */
public class HighestFreqChar {

	public static void main(String[] args) {
		String str = "aaabadfdGGGGGGGg";
		char res = new HighestFreqChar().highest(str);
		System.out.printf("Highest freq character: %s\n", res);
	}

	public char highest(String str) {
		Map<Character, Integer> map = new HashMap<>();
		if (str.length() == 0) throw new RuntimeException();
		int max1 = 1, max2 = 1;
		char maxChar1 = str.charAt(0);
		for (int i = 0; i <= str.length()-1-max1+max2; i++) {
			char c = str.charAt(i);
			if (!isValid(c)) continue;
			if (c >= 'A' && c <= 'Z') {
				c = (char) ('a' + c - 'A');
			}
			if (map.containsKey(c)) {
				map.put(c, map.get(c)+1);
			} else {
				map.put(c, 1);
			}
			if (map.get(c) >= max1) {
				max2 = max1;
				max1 = map.get(c);
				maxChar1 = c;
			} else if (map.get(c) >= max2) {
				max2 = map.get(c);
			}
		}
		return maxChar1;
	}
	private boolean isValid(char c) {
		return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
	}
}
