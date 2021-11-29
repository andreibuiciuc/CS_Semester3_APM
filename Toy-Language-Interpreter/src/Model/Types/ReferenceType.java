package Model.Types;

import Model.Values.ReferenceValue;
import Model.Values.Value;

public class ReferenceType implements Type{
    Type innerType;

    public ReferenceType(Type innerType) {
        this.innerType = innerType;
    }

    public Type getInnerType() {
        return innerType;
    }

    public boolean equals(Object another) {
        return another instanceof ReferenceType && innerType.equals(((ReferenceType) another).getInnerType());
    }

    @Override
    public Value getDefaultValue() {
        return new ReferenceValue(innerType);
    }

    @Override
    public String toString() {
        return "Ref " + innerType.toString();
    }
}
