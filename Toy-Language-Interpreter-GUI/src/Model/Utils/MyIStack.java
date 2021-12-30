package Model.Utils;

import java.util.Deque;

public interface MyIStack<TElem> {
    int getSize();

    void clear();

    boolean isEmpty();

    void push(TElem value);

    TElem pop() throws Exception;

    Deque<TElem> getStack();
}
