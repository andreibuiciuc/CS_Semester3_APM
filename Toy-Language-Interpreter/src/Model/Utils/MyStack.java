package Model.Utils;

import Exceptions.ADTException;

import java.util.ArrayDeque;
import java.util.Deque;


public final class MyStack<TElem> implements MyIStack<TElem> {
    private final Deque<TElem> stack;

    public MyStack() {
        this.stack = new ArrayDeque<TElem>();
    }

    @Override
    public int getSize() {
        return stack.size();
    }

    @Override
    public void clear() {
        stack.clear();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public TElem pop() throws Exception {
        if(stack.isEmpty()) {
            throw new ADTException("Stack is empty.");
        }
        return stack.pop();
    }

    @Override
    public void push(TElem value) {
        stack.push(value);
    }

    @Override
    public String toString() {
        if(stack.isEmpty()) {
            return "{}";
        }

        String result = "{ ";
        for(TElem element: this.stack) {
            result += element.toString() + " | ";
        }
        result = result.substring(0, result.length() - 2);
        result += "}";
        return result;
    }
}
