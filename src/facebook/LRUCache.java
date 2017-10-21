package facebook;

import java.util.*;

class DoubleListNode {
    int key;
    int val;
    DoubleListNode prev;
    DoubleListNode next;
    DoubleListNode(int key, int val) {
        this.key = key;
        this.val = val;
        prev = null;
        next = null;
    }
}
public class LRUCache {
    Map<Integer, DoubleListNode> map;
    int capacity;
    DoubleListNode head;
    DoubleListNode tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = null;
        tail = null;
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        DoubleListNode node = map.get(key);
        if (tail == node) {
            return node.val;
        }
        remove(node);
        moveToTail(node);
        return node.val;
    }

    private void remove(DoubleListNode node) {
        if (node == null) return;
        if (head == node) head = node.next;
        if (tail == node) tail = node.prev;
        if (node.next != null) node.next.prev = node.prev;
        if (node.prev != null) node.prev.next = node.next;
        node.next = null;
        node.prev = null;
    }

    private void moveToTail(DoubleListNode node) {
        if (tail == node) return;
        if (tail != null) {
            tail.next = node;
            node.prev = tail;
        }
        tail = node;
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            get(key);
            tail.val = value;
        } else {
        	DoubleListNode node = new DoubleListNode(key, value);
            if (map.size() == capacity) {
                map.remove(head.key);
                if (head == tail) {
                    head = null;
                    tail = null;
                }
                remove(head);
            }
            if (head == null) {
                head = node;
            }
            moveToTail(node);
            map.put(key, node);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */