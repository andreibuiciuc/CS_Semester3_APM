package Model.Utils;

import java.util.HashMap;

public interface MyIDictionary<TKey, TElem> {
    int size();

    void clear();

    boolean isEmpty();

    void add(TKey key, TElem value);

    void remove(TKey key);

    void update(TKey key, TElem newValue);

    boolean isDefined(TKey id);

    TElem lookup(TKey id);

    void setContent(HashMap<TKey, TElem> content);

    HashMap<TKey, TElem> getContent();
}
