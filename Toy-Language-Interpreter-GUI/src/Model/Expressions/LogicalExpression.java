package Model.Expressions;

import Exceptions.InvalidTypeException;
import Model.Types.Type;
import Model.Utils.MyIDictionary;
import Model.Types.BoolType;
import Model.Values.BoolValue;
import Model.Values.Value;

public final class LogicalExpression implements Expression {
    private final Expression expression1;
    private final Expression expression2;
    private final int operation;

    public LogicalExpression(Expression expression1, Expression expression2, int operation) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.operation = operation;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> symTable, MyIDictionary<Integer, Value> heap) throws Exception {
        Value value1 = expression1.eval(symTable, heap);

        if (value1.getType().equals(new BoolType())) {
            Value value2 = expression2.eval(symTable, heap);
            if (value2.getType().equals(new BoolType())) {
                BoolValue boolValue1 = (BoolValue) value1;
                BoolValue boolValue2 = (BoolValue) value2;
                boolean bool1 = boolValue1.getValue();
                boolean bool2 = boolValue2.getValue();

                return switch (operation) {
                    case 1 -> new BoolValue(bool1 && bool2);
                    case 2 -> new BoolValue(bool1 || bool2);
                    default -> null;
                };
            } else {
                throw new InvalidTypeException("Second operand is not a boolean.");
            }
        } else {
            throw new InvalidTypeException("First operand is not a boolean.");
        }

    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnvironment) throws Exception {
        Type type1, type2;
        type1 = expression1.typeCheck(typeEnvironment);
        type2 = expression2.typeCheck(typeEnvironment);

        if (!type1.equals(new BoolType())) {
            throw new InvalidTypeException("First operand is not a boolean.");
        }

        if (!type2.equals(new BoolType())) {
            throw new InvalidTypeException("Second operand is not a boolean.");
        }

        return new BoolType();
    }

    @Override
    public String toString() {
        return expression1 + " " + operation + " " + expression2;
    }
}
