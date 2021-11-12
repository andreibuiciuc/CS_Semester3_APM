package Model.Types;

import Model.Values.StringValue;
import Model.Values.Value;

public class StringType implements Type{
    public StringType() { }

    @Override
    public boolean equals(Object object) {
        return object instanceof StringType;
    }

    @Override
    public Value getDefaultValue() {
        return new StringValue();
    }

    @Override
    public String toString() {
        return "string";
    }
}
