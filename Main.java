public class Main {
    public static void main(String[] args) {

        LRUCache cache = new LRUCache(3);

        cache.put(1, 100);
        cache.put(2, 200);
        cache.put(3, 300);

        System.out.println(cache.get(1)); // 100

        cache.put(4, 400);

        System.out.println(cache.get(2)); // -1
        System.out.println(cache.get(3)); // 300
        System.out.println(cache.get(4)); // 400
    }
}