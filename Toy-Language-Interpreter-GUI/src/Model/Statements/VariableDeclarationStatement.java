package Model.Statements;

import Exceptions.InvalidTypeException;
import Exceptions.VariableDefinitionException;
import Model.ProgramState;
import Model.Types.*;
import Model.Utils.MyIDictionary;
import Model.Values.Value;

public final class VariableDeclarationStatement implements IStatement {
    private final String name;
    private final Type type;

    public VariableDeclarationStatement(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        MyIDictionary<String, Value> symTable = state.getSymTable();

        if (symTable.isDefined(name)) {
            throw new VariableDefinitionException("Variable already defined.");
        }

        if (type instanceof IntType || type instanceof BoolType ||
                type instanceof StringType || type instanceof ReferenceType) {
            symTable.add(name, type.getDefaultValue());
        } else {
            throw new InvalidTypeException("Invalid type.");
        }

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnvironment) throws Exception {
        typeEnvironment.add(name, type);
        return typeEnvironment;
    }

    @Override
    public String toString() {
        return type + " " + name;
    }
}
