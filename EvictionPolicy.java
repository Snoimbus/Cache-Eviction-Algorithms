import java.util.Map;

// EvictionPolicy interface for cache eviction policies

public interface EvictionPolicy {
    
    void onAccess(String key); // notify policy of access
    
    void onInsert(String key); // notify policy of insertion
    
    String chooseEvictionKey( // choose key to evict
        Map<String, CacheEntry> entries,
        Metrics metrics
    );
}
