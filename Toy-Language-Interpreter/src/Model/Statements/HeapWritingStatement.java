package Model.Statements;

import Exceptions.InvalidTypeException;
import Exceptions.VariableDefinitionException;
import Model.Expressions.Expression;
import Model.ProgramState;
import Model.Types.ReferenceType;
import Model.Types.Type;
import Model.Utils.MyIDictionary;
import Model.Values.ReferenceValue;
import Model.Values.Value;

public class HeapWritingStatement implements IStatement {
    private final String variableName;
    private final Expression expression;

    public HeapWritingStatement(String variableName, Expression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIDictionary<Integer, Value> heap = state.getHeap();

        // Check if the variable is defined in the symbol table.
        if(!symTable.isDefined(variableName)) {
            throw new VariableDefinitionException("Variable " + variableName + " not defined.");
        }

        Value variableValue = symTable.lookup(variableName);
        Type variableType = variableValue.getType();

        // Check if the type of the variable is a Reference Type.
        if(!(variableType instanceof ReferenceType)) {
            throw new InvalidTypeException("Variable " + variableName + " not a reference.");
        }

        // Check if the referenced value is in the Heap (by checking if the address is a key in the Heap).
        int positionHeap = ((ReferenceValue)variableValue).getHeapAddress();
        if(!heap.isDefined(positionHeap)) {
            throw new VariableDefinitionException("Variable undefined at address: " + positionHeap);
        }

        Value expressionValue = expression.eval(symTable, heap);
        Type expressionType= expressionValue.getType();

        Value referencedValue = heap.lookup(positionHeap);
        Type referencedType = referencedValue.getType();

        // Check if the types are equal.
        if(!expressionType.equals(referencedType)) {
            throw new InvalidTypeException("Referenced type and expression type do not match.");
        }

        heap.update(positionHeap, expressionValue);

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnvironment) throws Exception {
        Type typeVariable, typeExpression;
        typeVariable = typeEnvironment.lookup(variableName);
        typeExpression = expression.typeCheck(typeEnvironment);

        if(!typeVariable.equals(new ReferenceType(typeExpression))) {
            throw new InvalidTypeException("Right hand side and left hand side of heap writing statement have different types.");
        }

        return typeEnvironment;
    }

    @Override
    public String toString() {
        return "wH(" + variableName + ", " + expression.toString() + ")";
    }
}
