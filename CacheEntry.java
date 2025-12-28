public class CacheEntry {
    private String query; // original query string
    private String result; // cached result
    private long insertionTime; // time of insertion
    private long lastAccessTime; // time of last access
    private int accessCount; // number of accesses

    public CacheEntry(String query, String result){ // constructor
        this.query = query;
        this.result = result;
        this.insertionTime = System.currentTimeMillis();
        this.lastAccessTime = this.insertionTime;
        this.accessCount = 0;
    }
    public String getQuery(){ // get original query string
        return query;
    }

    public String getResult(){ // get cached result
        return result;
    }

    public long getInsertionTime(){ // get insertion time
        return insertionTime;
    }

    public long getLastAccessTime(){ // get last access time
        return lastAccessTime;
    }

    public int getAccessCount(){ // get access count
        return accessCount;
    }

    public void recordAccess(){ // record an access
        accessCount++; 
        lastAccessTime = System.currentTimeMillis(); // update last access time
    }
}