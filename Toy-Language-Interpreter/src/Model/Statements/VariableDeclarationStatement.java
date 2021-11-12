package Model.Statements;

import Exceptions.VariableDefinitionException;
import Model.ProgramState;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.Type;
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

        if(symTable.isDefined(name)) {
            throw new VariableDefinitionException("Variable already defined.");
        }
//        else if(type instanceof IntType) {
//            symTable.add(name, type.getDefaultValue());
//        }
//        else if(type instanceof BoolType) {
//            symTable.add(name, type.getDefaultValue());
//        }

        symTable.add(name, type.getDefaultValue());

        return state;
    }

    @Override
    public String toString() {
        return type + " " + name;
    }
}
