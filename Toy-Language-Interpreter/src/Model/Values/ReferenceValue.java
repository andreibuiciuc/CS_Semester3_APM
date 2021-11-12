package Model.Values;

import Model.Types.ReferenceType;
import Model.Types.Type;

import java.util.Objects;

public class ReferenceValue implements Value {
    int heapAddress;
    Type locationType;

    public ReferenceValue(int address, Type locationType) {
        this.heapAddress = address;
        this.locationType = locationType;
    }

    public ReferenceValue(Type locationType) {
        this.heapAddress = defaultHeapAddress;
        this.locationType = locationType;
    }

    public int getHeapAddress() {
        return heapAddress;
    }

    public Type getLocationType() {
        return locationType;
    }

    @Override
    public Type getType() {
        return new ReferenceType(locationType);
    }

    @Override
    public boolean equals(Object another) {
        return another instanceof ReferenceValue && ((ReferenceValue) another).getHeapAddress() == heapAddress;
    }

    @Override
    public int hashCode() {
        return Objects.hash(heapAddress, locationType);
    }

    @Override
    public String toString() {
        return "(" + heapAddress + ", " + locationType.toString() + ")";
    }
}
