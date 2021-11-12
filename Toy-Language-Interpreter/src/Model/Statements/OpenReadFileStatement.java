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
import java.io.FileReader;

public class OpenReadFileStatement implements IStatement {
    private final Expression filePath;

    public OpenReadFileStatement(Expression filePath) {
        this.filePath = filePath;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
        MyIDictionary<Integer, Value> heap = state.getHeap();

        Value filePathValue = filePath.eval(symTable, heap);

        if (filePathValue.getType().equals(new StringType())) {
            // Check if the string values is not already in the FileTable.
            // cast to StringValue

            if (fileTable.isDefined((StringValue) filePathValue)) {
                throw new VariableDefinitionException("File path already in the File Table.");
            }

            // Get the String file path for Buffered Reader.
            String filePathString = ((StringValue) filePathValue).getValue();
            BufferedReader fileBufferReader = new BufferedReader(new FileReader(filePathString));
            fileTable.add((StringValue) filePathValue, fileBufferReader);

        } else {
            throw new InvalidTypeException("Value is not a String Type.");
        }

        return state;
    }

    @Override
    public String toString() {
        return "openRead (" + filePath + ")";
    }
}
