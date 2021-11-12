package Model.Values;

import Model.Types.Type;

public interface Value {
    int defaultIntValue = 0;
    boolean defaultBooleanValue = false;
    String defaultStringValues = "";

    Type getType();
}
