package Model.Statements;

import Model.Expressions.Expression;
import Model.Types.Type;
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
        MyIDictionary<Integer, Value> heap = state.getHeap();

        Value expressionValue = expression.eval(symTable, heap);
        out.addToLastPosition(expressionValue);

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnvironment) throws Exception {
        expression.typeCheck(typeEnvironment);
        return typeEnvironment;
    }

    @Override
    public String toString() {
        return "print(" + expression.toString() + ")";
    }
}
