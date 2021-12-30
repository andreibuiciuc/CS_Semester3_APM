package Model.Utils;

import java.util.List;

public interface MyIList<TElem> {
    int getSize();

    void clear();

    boolean isEmpty();

    void addToLastPosition(TElem value);

    TElem removeFirst() throws Exception;

    List<TElem> getData();
}
