package Model.Expressions;

import Model.Types.Type;
import Model.Utils.MyIDictionary;
import Model.Values.Value;

public final class ValueExpression implements Expression {
    private final Value value;

    public ValueExpression(Value value) {
        this.value = value;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> table, MyIDictionary<Integer, Value> heap) {
        return value;
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnvironment) throws Exception {
        return value.getType();
    }

    @Override
    public String toString() {
        return "" + value.toString();
    }
}
