package Model.Utils;

public class MyHeap<TKey, TElem> extends MyDictionary<TKey, TElem> {
    public int position = 1;

    public MyHeap() {
        super();
    }

    private int setPosition() {
        return this.position + 1;
    }

    public int getFreePosition() {
        int freePosition = position;
        position = setPosition();
        return freePosition;
    }

    @Override
    public void clear() {
        super.clear();
        position = 1;
    }
}
