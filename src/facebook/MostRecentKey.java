package facebook;

import java.util.*;

/**
设计一个class，每个key 有一个value，实现以下4个功能: 
set key's value
get key's value
erase key
get mostrecentkey
 */
public class MostRecentKey {
	Map<String, ListNode> map;
	ListNode head;

	MostRecentKey() {
		map = new HashMap<>();
		head = null;
	}

	public static void main(String[] args) {
		MostRecentKey instance = new MostRecentKey();
		instance.put("1", 1);
		instance.put("2", 2);
		instance.put("3", 3);
		System.out.println("MostRecent: " + instance.getMostRecentKey());
		instance.remove("3");
		System.out.println("MostRecent: " + instance.getMostRecentKey());
		System.out.printf("get %s: %d\n", "1", instance.get("1"));
		System.out.println("MostRecent: " + instance.getMostRecentKey());
	}

	public void put(String key, int val) {
		if (!map.containsKey(key)) {
			map.put(key, new ListNode(key, val));
		} else {
			removeNode(map.get(key));
		}
		setAsHead(map.get(key));
	}

	public Integer get(String key) {
		if (!map.containsKey(key))
			return null;
		
		removeNode(map.get(key));
		setAsHead(map.get(key));
		return map.get(key).val;
	}

	public void remove(String key) {
		if (!map.containsKey(key))
			return;
		if (head == map.get(key))
			head = map.get(key).next;
		
		removeNode(map.get(key));
		map.remove(key);
	}

	public String getMostRecentKey() {
		return head.key;
	}

	private void removeNode(ListNode node) {
		if (head == node) head = node.next;
		if (node.prev != null)
			node.prev.next = node.next;
		if (node.next != null)
			node.next.prev = node.prev;

		node.next = null;
		node.prev = null;
	}
	
	private void setAsHead(ListNode node) {
		node.next = head;
		node.prev = null;
		if (head != null)
			head.prev = node;
		head = node;
	}

	class ListNode {
		String key;
		int val;
		ListNode next, prev;
		ListNode(String key, int val) {
			this.key = key;
			this.val = val;
			next = null;
			prev = null;
		}
	}
}
