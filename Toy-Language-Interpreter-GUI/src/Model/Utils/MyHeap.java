package Model.Utils;

public class MyHeap<TKey, TElem> extends MyDictionary<TKey, TElem> {
    public int position = 1;

    public MyHeap() {
        super();
    }

    public int getFreePosition() {
        int freePosition = position;
        position = position + 1;
        return freePosition;
    }

    @Override
    public void clear() {
        super.clear();
        position = 1;
    }
}
