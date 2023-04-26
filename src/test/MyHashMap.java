package test;

public class MyHashMap<K, V> {

    class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

    }

    final int DEFAULT_CAPACITY = 16;
    final float LOAD_FACTOR = 0.75f;
    private int size;
    Node<K, V>[] buckets;

    public MyHashMap() {
        buckets = new Node[DEFAULT_CAPACITY];
        size = 0;
    }

    public MyHashMap(int capacity) {
        buckets = new Node[capacity];
        size = 0;
    }

    private int getIndex(K key, int length) {
        int hashCode = key.hashCode();
        int index = hashCode % length;
        return Math.abs(index);
    }

    public void put(K key, V value) {
        //判断是否需要进行扩容
        if (size > buckets.length * LOAD_FACTOR) resize();
        putVal(key, value, buckets);
    }

    private void putVal(K key, V value, Node<K, V>[] table) {
        //获取位置
        int index = getIndex(key, table.length);
        Node<K, V> node = table[index];
        //插入的位置为空
        if (node == null) {
            table[index] = new Node<>(key, value);
            size++;
            return;
        }
        //插入位置不为空，说明发生冲突，使用链地址法,遍历链表
        while (node != null) {
            //如果key相同，就覆盖掉
            if ((node.key.hashCode() == key.hashCode()) && (node.key == key || node.key.equals(key))) {
                node.value = value;
                return;

            }
            node = node.next;
        }
        //当前key不在链表中，插入链表头部
        Node<K, V> newNode = new Node<>(key, value, table[index]);
        table[index] = newNode;
        size++;
    }

    private void resize() {
        Node<K, V>[] newBuckets = new Node[buckets.length * 2];
        rehash(newBuckets);
        buckets = newBuckets;
    }

    private void rehash(Node<K, V>[] newBuckets) {
        size = 0;
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] == null) {
                continue;
            }
            Node<K, V> node = buckets[i];
            while (node != null) {
                putVal(node.key, node.value, newBuckets);
                node = node.next;
            }
        }
    }

    public V get(K key) {
        int index = getIndex(key, buckets.length);
        if (buckets[index] == null) return null;
        Node<K, V> node = buckets[index];
        while (node != null) {
            if ((node.key.hashCode() == key.hashCode())
                    && (node.key == key || node.key.equals(key))) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    public int size() {
        return size;
    }

}
