package Model.Utils;

import Exceptions.ADTException;

import java.util.ArrayList;
import java.util.List;

public final class MyList<TElem> implements MyIList<TElem> {
    private final List<TElem> list;

    public MyList() {
        this.list = new ArrayList<TElem>();
    }

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void addToLastPosition(TElem value) {
        list.add(value);
    }

    @Override
    public TElem removeFirst()throws Exception {
        if(list.isEmpty()) {
            throw new ADTException("List is empty.");
        }
        return list.remove(0);
    }

    public List<TElem> getData() {
        return list;
    }

    @Override
    public String toString() {
        if(list.isEmpty()) {
            return "[]";
        }

        String result = "[ ";
        for(TElem element: this.list) {
            result = result + element.toString() + ", ";
        }
        result = result.substring(0, result.length() - 2);
        result = result + " ]";
        return result;
    }
}
