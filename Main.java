import java.util.Random;

public class Main {
public static void main(String[] args){
    EvictionPolicy policy = new GreedyPolicy(); // choose eviction policy, in this case, Greedy
    Cache cache = new Cache(5, policy); // create cache with capacity 5

    String[] queries = { // sample queries
        "users", "orders", "products", "reviews", "inventory", "sessions"
    };

    Random rand = new Random();

    for (int i = 0; i < 1000; i++){ // simulates 1000 query accesses
        String key = queries[rand.nextInt(queries.length)];

        if (rand.nextDouble() < 0.7){ // favor read operations to reflect cache heavy workloads
            cache.get(key);
        }
    }

 
    Metrics m = cache.getMetrics();
    System.out.println("Hits: " + m.getHits());
    System.out.println("Misses: " + m.getMisses());
    System.out.println("Evictions: " + m.getEvictions());
    System.out.println("Hit Rate: " + m.getHitRate());
}
}



