package Model.Values;

import Model.Types.Type;
import Model.Types.IntType;

public final class IntValue implements Value {
    private final int value;

    public IntValue() {
        value = defaultIntValue;
    }

    public IntValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public Type getType() {
        return new IntType();
    }

    @Override
    public String toString() {
        return "" + value;
    }
}
