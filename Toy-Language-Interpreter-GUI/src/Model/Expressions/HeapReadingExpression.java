package Model.Expressions;

import Exceptions.InvalidTypeException;
import Exceptions.VariableDefinitionException;
import Model.Types.ReferenceType;
import Model.Types.Type;
import Model.Utils.MyIDictionary;
import Model.Values.ReferenceValue;
import Model.Values.Value;

public class HeapReadingExpression implements Expression {
    private final Expression expression;

    public HeapReadingExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> symTable, MyIDictionary<Integer, Value> heap) throws Exception {
        Value value = expression.eval(symTable, heap);

        // Check if the expression is evaluated to a Reference Value.
        if (!(value instanceof ReferenceValue)) {
            throw new Exception("Expression is not evaluated as a reference.");
        }

        // Take the address component.
        int heapAddress = ((ReferenceValue) value).getHeapAddress();

        // Check if the address is in the Heap Table.
        if (!heap.isDefined(heapAddress)) {
            throw new VariableDefinitionException("Address " + heapAddress + " holds an undefined variable.");
        }

        // Get the value from the respective address.
        return heap.lookup(heapAddress);
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnvironment) throws Exception {
        Type type = expression.typeCheck(typeEnvironment);

        if (!(type instanceof ReferenceType)) {
            throw new InvalidTypeException("Argument of Heap Reading is not a Reference Type.");
        }

        ReferenceType referenceType = (ReferenceType) type;
        return referenceType.getInnerType();
    }

    @Override
    public String toString() {
        return "rH(" + expression.toString() + ")";
    }
}
