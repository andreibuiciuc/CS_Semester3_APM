package Model.Expressions;

import Exceptions.VariableDefinitionException;
import Model.Types.Type;
import Model.Utils.MyIDictionary;
import Model.Values.Value;

public final class VariableExpression implements Expression {
    private final String id;

    public VariableExpression(String id) {
        this.id = id;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> symTable, MyIDictionary<Integer, Value> heap) throws Exception {
        if(!symTable.isDefined(id)) {
            throw new VariableDefinitionException("Variable " + id + " is not defined.");
        }
        return symTable.lookup(id);
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnvironment) throws Exception {
        return typeEnvironment.lookup(id);
    }

    @Override
    public String toString() {
        return id;
    }
}
