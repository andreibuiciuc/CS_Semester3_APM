package Model.Types;

import Model.Values.ReferenceValue;
import Model.Values.Value;

public class ReferenceType implements Type{
    Type innerType;

    public ReferenceType(Type innerType) {
        this.innerType = innerType;
    }

    Type getInnerType() {
        return innerType;
    }

    public boolean equals(Object another) {
        if(another instanceof ReferenceType) {
            return innerType.equals(((ReferenceType) another).getInnerType());
        }
        else {
            return false;
        }
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
