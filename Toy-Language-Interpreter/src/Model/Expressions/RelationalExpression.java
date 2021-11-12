package Model.Expressions;

import Exceptions.InvalidTypeException;
import Model.Types.IntType;
import Model.Utils.MyIDictionary;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.Value;

public class RelationalExpression implements Expression{
    private final Expression expression1;
    private final Expression expression2;
    private final String operator;

    public RelationalExpression(Expression expression1, Expression expression2, String operator) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.operator = operator;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> symTable, MyIDictionary<Integer, Value> heap) throws Exception {
        Value value1 = expression1.eval(symTable, heap);
        Value value2 = expression2.eval(symTable, heap);

        if(!value1.getType().equals(new IntType())) {
            throw new InvalidTypeException("First operand is not an integer.");
        }

        if(!value2.getType().equals(new IntType())) {
            throw new InvalidTypeException("Second operand is not an integer.");
        }

        int intValue1 = ((IntValue)value1).getValue();
        int intValue2 = ((IntValue)value2).getValue();

        return switch (operator) {
            case "<" -> new BoolValue(intValue1 < intValue2);
            case "<=" -> new BoolValue(intValue1 <= intValue2);
            case "==" -> new BoolValue(intValue1 == intValue2);
            case "!=" -> new BoolValue(intValue1 != intValue2);
            case ">" -> new BoolValue(intValue1 > intValue2);
            case ">=" -> new BoolValue(intValue1 >= intValue2);
            default -> null;
        };


    }

    @Override
    public String toString() {
        return expression1.toString() + " " + operator + " " + expression2.toString();
    }
}
