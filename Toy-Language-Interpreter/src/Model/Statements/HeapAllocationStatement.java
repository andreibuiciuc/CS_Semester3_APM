package Model.Statements;

import Exceptions.InvalidTypeException;
import Exceptions.VariableDefinitionException;
import Model.Expressions.Expression;
import Model.ProgramState;
import Model.Types.ReferenceType;
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

        // Check if the provided variable is in the Symbol table.
        if(!symTable.isDefined(variableName)) {
            throw new VariableDefinitionException(variableName + " not defined in symbol table.");
        }

        Value variableValue = symTable.lookup(variableName);
        Value expressionValue = expression.eval(symTable, heap);

        // Check if the type of the variable is a Reference Type.
        if(!(variableValue.getType() instanceof ReferenceType)) {
            throw new InvalidTypeException(variableName + " not a Reference Type.");
        }

        Type referencedType = ((ReferenceValue)variableValue).getLocationType();
        Type expressionType = expressionValue.getType();

        // Check whether the types are equal.
        if(!referencedType.equals(expressionType)) {
            throw new InvalidTypeException("Referenced type and expression type do not match.");
        }

        int freePosition = ((MyHeap<Integer, Value>)heap).getFreePosition();
        heap.add(freePosition, expressionValue);

        symTable.update(variableName, new ReferenceValue(freePosition, referencedType));

        return null;
    }

    @Override
    public String toString() {
        return "new(" + variableName + "," + expression.toString() + ")";
    }
}
