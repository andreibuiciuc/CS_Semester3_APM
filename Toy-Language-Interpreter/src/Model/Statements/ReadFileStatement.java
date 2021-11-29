package Model.Statements;

import Exceptions.InvalidTypeException;
import Exceptions.VariableDefinitionException;
import Model.Expressions.Expression;
import Model.ProgramState;
import Model.Types.IntType;
import Model.Types.StringType;
import Model.Types.Type;
import Model.Utils.MyIDictionary;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;

public class ReadFileStatement implements IStatement{
    private final Expression filePath;
    private final String variableName;

    public ReadFileStatement(Expression filePath, String variableName) {
        this.filePath = filePath;
        this.variableName = variableName;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
        MyIDictionary<Integer, Value> heap = state.getHeap();

        // Check if the variable is defined in the Symbol Table.
        if(!symTable.isDefined(variableName)) {
            throw new VariableDefinitionException("Variable not defined.");
        }

        // Check if the variable is an integer.
        if(!symTable.lookup(variableName).getType().equals(new IntType())) {
            throw new InvalidTypeException("Variable not an integer.");
        }

        // Check if the filePath provided is a string.
        Value filePathValue = filePath.eval(symTable, heap);
        if(!filePathValue.getType().equals(new StringType())) {
            throw new InvalidTypeException("File path provided not a string.");
        }

        // Check if filePath is in the File Table.
        if(!fileTable.isDefined((StringValue) filePathValue)) {
            throw new VariableDefinitionException("File path not in the File Table.");
        }

        // Get the file descriptor from the File Table
        BufferedReader fileBufferReader = fileTable.lookup((StringValue) filePathValue);

        String line = fileBufferReader.readLine();
        if(line == null) {
            symTable.update(variableName, new IntValue());
        }
        else {
            symTable.update(variableName, new IntValue(Integer.parseInt(line)));
        }

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnvironment) throws Exception {
        Type typeVariable, typeExpression;
        typeVariable = typeEnvironment.lookup(variableName);
        typeExpression = filePath.typeCheck(typeEnvironment);

        if(!typeVariable.equals(new IntType())) {
            throw new InvalidTypeException("Variable not an integer.");
        }

        if(!typeExpression.equals(new StringType())) {
            throw new InvalidTypeException("File path not a string.");
        }

        return typeEnvironment;
    }

    @Override
    public String toString() {
        return "readFile (" + filePath + ")";
    }
}
