package Model.Expressions;

import Model.Utils.MyIDictionary;
import Model.Values.Value;

public final class ValueExpression implements Expression {
    private final Value value;

    public ValueExpression(Value value) {
        this.value = value;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> table){
        return value;
    }

    @Override
    public String toString() {
        return "" + value.toString();
    }
}
