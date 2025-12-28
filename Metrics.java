public class Metrics {
    private int hits;
    private int misses;
    private int evictions;
    private int totalRequests;

    public void recordHit(){ // record cache hits
        hits++;
        totalRequests++;
    }

    public void recordMiss(){ // record cache misses
        misses++;
        totalRequests++;
    }

    public void recordEviction(){ // record evictions
        evictions++;
    }

    public int getHits(){ // get number of hits
        return hits;
    }

    public int getMisses(){ // get number of misses
        return misses;
    }

    public int getEvictions(){ // get number of evictions
        return evictions;
    }

    public int getTotalRequests(){ // get total requests
        return totalRequests;
    }

    public double getHitRate(){ // get hit rate
        if(totalRequests == 0) return 0.0;
        return (double) hits / totalRequests;
    }
}
