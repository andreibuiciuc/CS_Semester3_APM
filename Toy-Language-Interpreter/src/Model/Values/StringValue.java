package Model.Values;

import Model.Types.StringType;
import Model.Types.Type;

import java.util.Objects;

public class StringValue implements Value{
    private final String value;

    public StringValue() {
        this.value = defaultStringValues;
    }

    public StringValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object another) {
        return another instanceof StringValue && Objects.equals(((StringValue) another).getValue(), value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public Type getType() {
        return new StringType();
    }

    @Override
    public String toString() {
        return value;
    }
}
