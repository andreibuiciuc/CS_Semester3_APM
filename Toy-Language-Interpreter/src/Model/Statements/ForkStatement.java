package Model.Statements;

import Model.ProgramState;
import Model.Types.Type;
import Model.Utils.MyIDictionary;
import Model.Utils.MyIStack;
import Model.Utils.MyStack;

public class ForkStatement implements IStatement {
    private final IStatement statement;

    public ForkStatement(IStatement statement) {
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        if(statement == null) {
            return null;
        }

        return new ProgramState(new MyStack<>(), state.getSymTable().clone(), state.getOut(),
               statement, state.getFileTable(), state.getHeap());
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnvironment) throws Exception {
        return statement.typeCheck(typeEnvironment);
    }

    @Override
    public String toString() {
        return "Fork(" + statement.toString() + ")";
    }
}
