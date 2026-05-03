import java.util.HashMap;

class LRUCache {
    public String displayCache() {
        StringBuilder sb = new StringBuilder();

        Node current = head.next;

        while (current != tail) {
            sb.append("[").append(current.key)
            .append(":").append(current.value)
            .append("] ");

        current = current.next;
       }

       return sb.toString();
    }


    private int capacity;
    private HashMap<Integer, Node> map;
    private Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();

        head = new Node(0, 0);
        tail = new Node(0, 0);

        head.next = tail;
        tail.prev = head;
    }

    private void addNode(Node node) {
        node.next = head.next;
        node.prev = head;

        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToFront(Node node) {
        removeNode(node);
        addNode(node);
    }

    private Node removeTail() {
        Node lastNode = tail.prev;
        removeNode(lastNode);
        return lastNode;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }

        Node node = map.get(key);
        moveToFront(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            moveToFront(node);
        } else {
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            addNode(newNode);

            if (map.size() > capacity) {
                Node tailNode = removeTail();
                map.remove(tailNode.key);
            }
        }
    }
}