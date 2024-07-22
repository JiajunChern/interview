package org.interview.algorithm._0146;

import java.util.HashMap;
import java.util.Map;

/**
 * 一、偷懒法： 1.继承LinkedHashMap，注意泛型
 * 2.初始化capacity
 * 3.get，put方法直接调父类的
 * 4.最重要的一步是大于容量后需要删除最后一个元素，直接用父类的
 * 二、手写LRUCache
 * 1.创建双向链表，节点是key=value
 * 2.记住参数：capacity，size，虚拟头尾节点，缓存Map
 * 3.初始化参数，双向链表初始化（头尾互指）
 * 4.get先移出，在加到头
 * 5.put不存在，创建节点加入并移动到头部，缓存key移除 加入时需要注意容量，超出容量则移除最后一个。
 * 6.主要理解移除节点和插入到头节点
 */
class LRUCache {

    class Node {
        int key, value;
        Node prev, next;

        public Node(int k, int v) {
            key = k;
            value = v;
        }
    }

    int capacity;
    Node head, tail;
    Map<Integer, Node> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(2);
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (map.isEmpty()) {
            return -1;
        }
        if (!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.value;
    }

    private void addToHead(Node node) {
        Node oldHead = head.next;
        oldHead.prev = node;
        node.next = oldHead;
        head.next = node;
        node.prev = head;
    }

    private void removeNode(Node node) {
        // 用伪节点
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void put(int key, int value) {
        Node node = new Node(key, value);
        if(map.containsKey(key)){
            Node remove = map.get(key);
            removeNode(remove);
        }
        map.put(key, node);
        addToHead(node);
        if (map.size() > capacity) {
            Node remove = map.get(tail.prev.key);
            map.remove(tail.prev.key);
            removeNode(remove);
        }
    }
}


public class Solution {
    public static void main(String[] args) {
        LRUCache lru = new LRUCache(2);
        lru.put(1, 1);
        lru.put(2, 1);
        lru.get(1);
        lru.put(3, 3);
        lru.get(2);
        lru.put(4, 4);
    }
}
