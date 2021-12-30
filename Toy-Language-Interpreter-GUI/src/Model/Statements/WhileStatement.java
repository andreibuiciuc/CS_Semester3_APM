package Model.Statements;

import Exceptions.InvalidTypeException;
import Model.Expressions.Expression;
import Model.ProgramState;
import Model.Types.BoolType;
import Model.Types.Type;
import Model.Utils.MyIDictionary;
import Model.Utils.MyIStack;
import Model.Values.BoolValue;
import Model.Values.Value;

public class WhileStatement implements IStatement {
    private final Expression expression;
    private final IStatement statement;

    public WhileStatement(Expression expression, IStatement statement) {
        this.expression = expression;
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        MyIStack<IStatement> exeStack = state.getExeStack();
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIDictionary<Integer, Value> heap = state.getHeap();

        Value value = expression.eval(symTable, heap);
        if (!(value instanceof BoolValue)) {
            throw new InvalidTypeException("Condition in while statement not a boolean.");
        }

        if (((BoolValue) value).getValue()) {
            exeStack.push(this);
            exeStack.push(statement);
        }

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnvironment) throws Exception {
        Type typeExpression = expression.typeCheck(typeEnvironment);

        if (!typeExpression.equals(new BoolType())) {
            throw new InvalidTypeException("Condition in while statement not a boolean");
        }

        statement.typeCheck(typeEnvironment.clone());
        return typeEnvironment;
    }

    @Override
    public String toString() {
        return "while(" + expression.toString() + ")" + " {" + statement.toString() + " }";
    }
}


