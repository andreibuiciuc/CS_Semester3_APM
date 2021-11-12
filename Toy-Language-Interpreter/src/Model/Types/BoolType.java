package Model.Types;

import Model.Values.BoolValue;
import Model.Values.Value;

public final class BoolType implements Type {
    public BoolType() {}

    @Override
    public boolean equals(Object another) {
        return another instanceof BoolType;
    }

    public Value getDefaultValue() {
        return new BoolValue();
    }

    @Override
    public String toString() {
        return "bool";
    }
}
