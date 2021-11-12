package Model.Statements;

import Exceptions.InvalidTypeException;
import Exceptions.VariableDefinitionException;
import Model.Expressions.Expression;
import Model.ProgramState;
import Model.Types.StringType;
import Model.Utils.MyIDictionary;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;

public class CloseReadFileStatement implements IStatement {
    private final Expression filePath;

    public CloseReadFileStatement(Expression filePath) {
        this.filePath = filePath;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
        MyIDictionary<Integer, Value> heap = state.getHeap();

        Value filePathValue = filePath.eval(symTable, heap);

        if(!filePathValue.getType().equals(new StringType())) {
            throw new InvalidTypeException("Variable is not a string.");
        }

        if(!fileTable.isDefined((StringValue) filePathValue)) {
            throw new VariableDefinitionException("File path is not defined in the File Table.");
        }

        BufferedReader fileBufferReader = fileTable.lookup((StringValue) filePathValue);
        fileBufferReader.close();
        fileTable.remove((StringValue) filePathValue);

        return state;

    }

    @Override
    public String toString() {
        return "closeRead (" + filePath + ")";
    }
}
