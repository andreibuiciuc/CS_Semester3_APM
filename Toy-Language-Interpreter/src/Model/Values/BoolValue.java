package Model.Values;

import Model.Types.BoolType;
import Model.Types.Type;

public final class BoolValue implements Value {
    private final boolean value;

    public BoolValue() {
        value = defaultBooleanValue;
    }

    public BoolValue(boolean value) {
        this.value = value;
    }

    public boolean getVal() {
        return value;
    }

    @Override
    public Type getType() {
        return new BoolType();
    }

    @Override
    public String toString() {
        return "" + value;
    }
}
