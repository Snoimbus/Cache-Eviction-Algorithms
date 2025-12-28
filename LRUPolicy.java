import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Map;

//LRU models on recency

public class LRUPolicy implements EvictionPolicy {

    private Queue<String> queue; //stores keys in order of access

    public LRUPolicy(){
        this.queue = new ArrayDeque<>();
    }

    @Override
    public void onInsert(String key){ //called when new entry is added to cache
        queue.add(key);
    }

    @Override
    public void onAccess(String key){ //called when something is read
        //move accessed key to the end of the queue (most recently used)
        queue.remove(key);
        queue.add(key);
    }

    @Override
    public String chooseEvictionKey(
        Map<String, CacheEntry> entries,
        Metrics metrics
    ){
        return queue.poll(); //removes and returns the least recently used key
    }

}
