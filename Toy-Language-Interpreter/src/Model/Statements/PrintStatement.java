package Model.Statements;

import Model.Expressions.Expression;
import Model.Utils.MyIDictionary;
import Model.Utils.MyIList;
import Model.ProgramState;
import Model.Values.Value;

public final class PrintStatement implements IStatement {
    private final Expression expression;

    public PrintStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        MyIList<Value> out = state.getOut();
        MyIDictionary<String, Value> symTable = state.getSymTable();
        Value expressionValue = expression.eval(symTable);
        out.addToLastPosition(expressionValue);

        return state;
    }

    @Override
    public String toString() {
        return "print(" + expression.toString() + ")";
    }
}
