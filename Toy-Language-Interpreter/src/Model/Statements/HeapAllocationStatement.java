package Model.Statements;

import Exceptions.InvalidTypeException;
import Exceptions.VariableDefinitionException;
import Model.Expressions.Expression;
import Model.ProgramState;
import Model.Types.Type;
import Model.Utils.MyHeap;
import Model.Utils.MyIDictionary;
import Model.Values.ReferenceValue;
import Model.Values.Value;

public class HeapAllocationStatement implements IStatement {
    private final String variableName;
    private final Expression expression;

    public HeapAllocationStatement(String variableName, Expression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIDictionary<Integer, Value> heap = state.getHeap();

        if(!symTable.isDefined(variableName)) {
            throw new VariableDefinitionException(variableName + " is not defined in symbol table.");
        }

        Value variableValue = symTable.lookup(variableName);
        Value expressionValue = expression.eval(symTable, heap);

        Type referencedType = ((ReferenceValue)variableValue).getLocationType();
        Type expressionType = expressionValue.getType();

        if(!referencedType.equals(expressionType)) {
            throw new InvalidTypeException("Referenced type and expression type do not match.");
        }

        int freePosition = ((MyHeap<Integer, Value>)heap).getFreePosition();
        heap.add(freePosition, expressionValue);

        symTable.update(variableName, new ReferenceValue(freePosition, referencedType));

        return state;
    }

    @Override
    public String toString() {
        return "new(" + variableName + "," + expression.toString() + ")";
    }
}
