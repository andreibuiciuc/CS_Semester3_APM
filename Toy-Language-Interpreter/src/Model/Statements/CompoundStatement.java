package Model.Statements;

import Model.Utils.MyIStack;
import Model.ProgramState;

public final class CompoundStatement implements IStatement {
    private final IStatement firstStatement;
    private final IStatement secondStatement;

    public CompoundStatement(IStatement firstStatement, IStatement secondStatement) {
        this.firstStatement = firstStatement;
        this.secondStatement = secondStatement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        MyIStack<IStatement> exeStack = state.getExeStack();
        exeStack.push(secondStatement);
        exeStack.push(firstStatement);

        return state;
    }

    @Override
    public String toString() {
        return "("+ firstStatement.toString() + " ; " + secondStatement.toString() + ")";
    }
}
