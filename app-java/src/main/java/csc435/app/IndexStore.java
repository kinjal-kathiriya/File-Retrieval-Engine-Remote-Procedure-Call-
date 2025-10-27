package csc435.app;

import java.util.HashMap;
import java.util.Map;

public class IndexStore {
    private final Map<String, Map<String, Long>> mainIndex;

    public IndexStore() {
        mainIndex = new HashMap<>();
    }

    public synchronized void insertWord(String filePath, String word, long frequency) {
        mainIndex.computeIfAbsent(word, k -> new HashMap<>()).put(filePath, frequency);
    }

    public Map<String, Long> lookupWord(String word) {
        return mainIndex.getOrDefault(word, new HashMap<>());
    }
}
