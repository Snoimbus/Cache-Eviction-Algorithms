import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Map;

//FIFO models on age

public class FIFOPolicy implements EvictionPolicy {

    private Queue<String> queue; //stores keys in order they were added

    public FIFOPolicy(){
        this.queue = new ArrayDeque<>();
    }

    @Override
    public void onInsert(String key){ //called when new entry is added to cache
        queue.add(key);
    }

    @Override
    public void onAccess(String key){ //called when something is read
        //FIFO doesnt care about access...
    }

    @Override
    public String chooseEvictionKey(
        Map<String, CacheEntry> entries,
        Metrics metrics
    ){ //called when cache is full
        return queue.poll();
    }
}


