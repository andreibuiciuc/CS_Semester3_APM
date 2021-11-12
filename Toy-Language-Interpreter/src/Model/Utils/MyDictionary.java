package Model.Utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MyDictionary<TKey, TElem> implements MyIDictionary<TKey, TElem>{
    private final Map<TKey, TElem> dictionary;

    public MyDictionary() { this.dictionary = new HashMap<>(); }

    @Override
    public int size() {
        return dictionary.size();
    }

    @Override
    public void clear() {
        dictionary.clear();
    }

    @Override
    public boolean isEmpty() {
        return dictionary.isEmpty();
    }

    @Override
    public void add(TKey key, TElem value) {
        // In a Java HashMap, when an element with the same key is added,
        // it overwrites the element mapped to that particular key
        dictionary.put(key, value);
    }

    @Override
    public TElem remove(TKey key) {
        return dictionary.remove(key);
    }

    @Override
    public void update(TKey key, TElem newValue){
        dictionary.replace(key, newValue);
    }

    @Override
    public TElem lookup(TKey id) {
        return dictionary.get(id);
    }

    public boolean isDefined(TKey id){
        return dictionary.containsKey(id);
    }

    @Override
    public String toString() {
        if(dictionary.isEmpty()) {
            return "{}";
        }

        String result = "{ ";
        Collection<TKey> keys = dictionary.keySet();

        for(TKey key: keys) {
            result = result + key.toString() + " -> " + dictionary.get(key).toString() + ", ";
        }
        result = result.substring(0, result.length() - 2);
        result += " }";
        return result;
    }

}
