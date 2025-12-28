import java.util.Map;

// this is just a placeholder implementation for testing purposes, policy does nothing

public class DummyPolicy implements EvictionPolicy{
    @Override
    public void onAccess(String key){
    }

    @Override
    public void onInsert(String key){
    }

    @Override
    public String chooseEvictionKey(
        Map<String, CacheEntry> entries,
        Metrics metrics
    ){
        return null;
    }
}
