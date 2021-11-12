package Model.Statements;

import Exceptions.InvalidTypeException;
import Model.Expressions.Expression;
import Model.Types.Type;
import Model.Utils.MyIDictionary;
import Model.Utils.MyIStack;
import Model.ProgramState;
import Model.Types.BoolType;
import Model.Values.BoolValue;
import Model.Values.Value;

public final class IfStatement implements IStatement {
    private final Expression expression;
    private final IStatement thenStatement;
    private final IStatement elseStatement;

    public IfStatement(Expression expression, IStatement thenStatement, IStatement elseStatement){
        this.expression = expression;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        MyIStack<IStatement> exeStack = state.getExeStack();
        MyIDictionary<String, Value> symTable = state.getSymTable();

        Value expressionValue = expression.eval(symTable);
        Type valueType = expressionValue.getType();

        if(!(valueType instanceof BoolType)) {
            throw new InvalidTypeException("Conditional expression not a boolean");
        }
        else {
        if(((BoolValue) expressionValue).getVal()) {
            exeStack.push(thenStatement);
        }
        else {
            exeStack.push(elseStatement);
        }
        }

        return state;
    }

    @Override
    public String toString() {
        return "if(" + expression.toString() + ") then (" + thenStatement.toString() + ") else (" +
                elseStatement.toString() + "))";
    }
}
