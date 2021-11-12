package Model.Expressions;

import Exceptions.DivisionByZero;
import Exceptions.InvalidTypeException;
import Model.Utils.MyIDictionary;
import Model.Types.IntType;
import Model.Values.IntValue;
import Model.Values.Value;

public final class ArithmeticExpression implements Expression {
    private final Expression expression1;
    private final Expression expression2;
    private final char operation;

    public ArithmeticExpression(char operation, Expression expression1, Expression expression2) {
        this.operation = operation;
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> symTable, MyIDictionary<Integer, Value> heap) throws Exception{
        Value value1 = expression1.eval(symTable, heap);

        if(value1.getType().equals(new IntType())) {
            Value value2 = expression2.eval(symTable, heap);
            if(value2.getType().equals(new IntType())) {
                IntValue integerValue1 = (IntValue) value1;
                IntValue integerValue2 = (IntValue) value2;
                int int1 = integerValue1.getValue();
                int int2 = integerValue2.getValue();

                switch (operation) {
                    case '+': return new IntValue(int1 + int2);
                    case '-': return new IntValue(int1 - int2);
                    case '*': return new IntValue(int1 * int2);
                    case '/': {
                        if(int2 == 0) {
                            throw new DivisionByZero("Division by zero encountered.");
                        }
                        else {
                            return new IntValue(int1 / int2);
                        }
                    }
                    default: return null;
                }

            }
            else {
                throw new InvalidTypeException("Second operand is not an integer.");
            }
        }
        else {
            throw new InvalidTypeException("First operand is not an integer.");
        }

    }

    @Override
    public String toString() {
        return expression1 + " " + operation + " " + expression2;
    }
}
