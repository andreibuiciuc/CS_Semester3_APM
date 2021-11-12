package Model.Statements;

import Model.ProgramState;

public final class NopStatement implements IStatement {
    public NopStatement() {};

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        return state;
    }
}
