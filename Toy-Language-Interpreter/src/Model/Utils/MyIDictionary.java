package Model.Utils;

public interface MyIDictionary<TKey, TElem> {
    int size();

    void clear();

    boolean isEmpty();

    void add(TKey key, TElem value);

    TElem remove(TKey key);

    void update(TKey key, TElem newValue);

    boolean isDefined(TKey id);

    TElem lookup(TKey id);
}
