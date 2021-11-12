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

    int getHeapAddress() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReferenceValue that = (ReferenceValue) o;
        return heapAddress == that.heapAddress && Objects.equals(locationType, that.locationType);
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
