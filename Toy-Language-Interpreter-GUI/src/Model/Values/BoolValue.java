package Model.Values;

import Model.Types.BoolType;
import Model.Types.Type;

import java.util.Objects;

public final class BoolValue implements Value {
    private final boolean value;

    public BoolValue() {
        value = defaultBooleanValue;
    }

    public BoolValue(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public Type getType() {
        return new BoolType();
    }

    @Override
    public boolean equals(Object another) {
        return another instanceof BoolValue && ((BoolValue) another).getValue() == value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "" + value;
    }
}
