import java.util.Map;

//This policy models on a combination of factors from FIFO and LRU. When hits are frequent, it prioritizes recency > frequency > age.
//When hits are infrequent, it prioritizes frequency > recency > age.

public class GreedyPolicy implements EvictionPolicy {

    @Override
    public void onInsert(String key){
        // Greedy policy does not track insertions
    }

    @Override
    public void onAccess(String key){
        // Greedy policy does not track accesses
    }

    @Override
    public String chooseEvictionKey( //called when cache is full
        Map<String, CacheEntry> entries, 
        Metrics metrics
    ){
        double hitRate = metrics.getHitRate(); // get current hit rate
        long now = System.currentTimeMillis(); // current time

        long maxRecency = 1; 
        long maxAge = 1;
        int maxFrequency = 1;

         for (CacheEntry e : entries.values()){ // find max values for normalization
            maxRecency = Math.max(maxRecency, now - e.getLastAccessTime());
            maxAge = Math.max(maxAge, now - e.getInsertionTime());
            maxFrequency = Math.max(maxFrequency, e.getAccessCount());
        }

        double frequencyWeight; // weights based on hit rate
        double recencyWeight;
        double ageWeight;

        if (hitRate < 0.3){ // low hit rate
            frequencyWeight = 0.6;
            recencyWeight = 0.3;
            ageWeight = 0.1;
        } else if (hitRate >0.7){ // high hit rate
            frequencyWeight = 0.3;
            recencyWeight = 0.6;
            ageWeight = 0.1;
        } else { // medium hit rate
            frequencyWeight = 0.4;
            recencyWeight = 0.4;
            ageWeight = 0.2;
        }

        String worstKey = null; // key to evict
        double worstScore = Double.NEGATIVE_INFINITY; // worst score found

        for (Map.Entry<String, CacheEntry> entry : entries.entrySet()){
            CacheEntry e = entry.getValue();

            long recency = now - e.getLastAccessTime(); // time since last access
            long age = now - e.getInsertionTime(); // time since insertion
            int frequency = e.getAccessCount(); // number of accesses

            double normRecency = (double) recency / maxRecency; // normalize values
            double normAge = (double) age / maxAge; 
            double normFrequency = (double) frequency / maxFrequency;

            double score = recencyWeight * normRecency //calculate score according to weights
            + ageWeight * normAge
            - frequencyWeight * normFrequency;

            if (score > worstScore){ // if score is worse, update worst
                worstScore = score;
                worstKey = entry.getKey(); 
            }
        }

        return worstKey;

    }

}

