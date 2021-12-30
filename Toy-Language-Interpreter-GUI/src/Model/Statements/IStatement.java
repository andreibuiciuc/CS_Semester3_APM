package Model.Statements;

import Model.ProgramState;
import Model.Types.Type;
import Model.Utils.MyIDictionary;

public interface IStatement {
    ProgramState execute(ProgramState state) throws Exception;

    MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnvironment) throws Exception;

}
