package Model.Statements;

import Exceptions.InvalidTypeException;
import Exceptions.VariableDefinitionException;
import Model.Expressions.Expression;
import Model.Utils.MyIDictionary;
import Model.ProgramState;
import Model.Types.Type;
import Model.Values.Value;

public final class AssignmentStatement implements IStatement {
    private final String id; // variable name
    private final Expression expression;

    public AssignmentStatement(String id, Expression expression) {
        this.id = id;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIDictionary<Integer, Value> heap = state.getHeap();

        if(symTable.isDefined(id)) {
            Value expressionValue = expression.eval(symTable, heap);
            Type type = symTable.lookup(id).getType();
            if(expressionValue.getType().equals(type)) {
                symTable.update(id, expressionValue);
            }
            else {
                throw new InvalidTypeException("Declared type of variable " + id + " " +
                        "and type of the assigned expression" + "do not match.");
            }
        }
        else {
            throw new VariableDefinitionException("The used variable " + id + " was not declared before.");
        }

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnvironment) throws Exception {
        Type typeVariable, typeExpression;
        typeVariable = typeEnvironment.lookup(id);
        typeExpression = expression.typeCheck(typeEnvironment);

        if(!typeVariable.equals(typeExpression)) {
            throw new InvalidTypeException("Right hand side and left hand side have different types.");
        }

        return typeEnvironment;
    }

    @Override
    public String toString() {
        return id + " = " + expression.toString();
    }
}
