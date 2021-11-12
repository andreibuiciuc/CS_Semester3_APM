package Model.Types;

import Model.Values.IntValue;
import Model.Values.Value;

public final class IntType implements Type {
    public IntType() {}

    @Override
    public boolean equals(Object another) {
        return another instanceof IntType;
    }


    public Value getDefaultValue() {
        return new IntValue();
    }

    @Override
    public String toString() {
        return "int";
    }
}
