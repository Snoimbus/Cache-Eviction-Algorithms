import java.util.Map;
import java.util.HashMap;

public class Cache {
    private int capacity; // max amount of entries in the cache
    private EvictionPolicy evictionPolicy; // eviction policy to use
    private Metrics metrics; // cache performance metrics
    private Map<String, CacheEntry> entries; // map of cache entries


public Cache(int capacity, EvictionPolicy evictionPolicy){ // constructor
    this.capacity = capacity;
    this.evictionPolicy = evictionPolicy;
    this.metrics = new Metrics();
    this.entries = new HashMap<>();
}

public String get(String query){ // retrieve result from cache
    if (entries.containsKey(query)){
        CacheEntry entry = entries.get(query);
        entry.recordAccess();
        evictionPolicy.onAccess(query);
        metrics.recordHit();
        return entry.getResult();
    }

    metrics.recordMiss(); // record cache miss


    if (entries.size() >= capacity){ // evict an entry if at capacity
        String keyToEvict = evictionPolicy.chooseEvictionKey(
            entries,
            metrics
        );
        
        if (keyToEvict != null){
            entries.remove(keyToEvict);
            metrics.recordEviction();
        }
    }

    String result = "Result for [" + query + "]"; // simulate query execution
    CacheEntry newEntry = new CacheEntry(query, result); // creates new cache entry
    entries.put(query, newEntry); //adds new cache entry
    evictionPolicy.onInsert(query); // notify eviction policy of insertion

    return result;
}

public Metrics getMetrics(){
    return metrics; // return cache performance metrics
}
}