package Model.Statements;

import Model.ProgramState;
import Model.Types.Type;
import Model.Utils.MyIDictionary;

public final class NopStatement implements IStatement {
    public NopStatement() {
    }

    ;

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnvironment) throws Exception {
        return typeEnvironment;
    }
}
