package View;

import Controller.Controller;
import Model.Expressions.*;
import Model.ProgramState;
import Model.Statements.*;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Utils.*;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Model.Values.Value;
import Repository.IRepository;
import Repository.Repository;

import java.io.BufferedReader;

public class Interpreter {

    public static void main(String[] args) {
        TextMenu menu = new TextMenu();

        menu.addCommand(new ExitCommand("0", "Exit"));

        // Example 1
        MyIStack<IStatement> exeStack1 = new MyStack<>();
        MyIDictionary<String, Value> symTable1 = new MyDictionary<>();
        MyIList<Value> out1 = new MyList<>();
        MyIDictionary<StringValue, BufferedReader> fileTable1 = new MyDictionary<>();

        IStatement ex1 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))));
        ProgramState programState1 = new ProgramState(exeStack1, symTable1, out1, ex1, fileTable1);

        IRepository repository1 = new Repository("log1.txt");
        Controller controller1 = new Controller(repository1);
        controller1.addProgramState(programState1);

        menu.addCommand(new RunExampleCommand("1", ex1.toString(), controller1));

        // Example 2
        MyIStack<IStatement> exeStack2 = new MyStack<>();
        MyIDictionary<String, Value> symTable2 = new MyDictionary<>();
        MyIList<Value> out2 = new MyList<>();
        MyIDictionary<StringValue, BufferedReader> fileTable2 = new MyDictionary<>();

        IStatement ex2 = new CompoundStatement(new VariableDeclarationStatement("a", new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("b", new IntType()),
                        new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression('+', new ValueExpression(new IntValue(2)),
                                new ArithmeticExpression('*', new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                                new CompoundStatement(new AssignmentStatement("b", new ArithmeticExpression('+', new VariableExpression("a"),
                                        new ValueExpression(new IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));
        ProgramState programState2 = new ProgramState(exeStack2, symTable2, out2, ex2, fileTable2);

        IRepository repository2 = new Repository("log2.txt");
        Controller controller2 = new Controller(repository2);
        controller2.addProgramState(programState2);

        menu.addCommand(new RunExampleCommand("2", ex2.toString(), controller2));

        // Example 3
        MyIStack<IStatement> exeStack3 = new MyStack<>();
        MyIDictionary<String, Value> symTable3 = new MyDictionary<>();
        MyIList<Value> out3 = new MyList<>();
        MyIDictionary<StringValue, BufferedReader> fileTable3 = new MyDictionary<>();

        IStatement ex3 = new CompoundStatement(new VariableDeclarationStatement("a",new BoolType()),
                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                        new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompoundStatement(new IfStatement(new VariableExpression("a"),
                                        new AssignmentStatement("v",new ValueExpression(new IntValue(2))),
                                        new AssignmentStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new VariableExpression("v"))))));
        ProgramState programState3 = new ProgramState(exeStack3, symTable3, out3, ex3, fileTable3);

        IRepository repository3 = new Repository("log3.txt");
        Controller controller3 = new Controller(repository3);
        controller3.addProgramState(programState3);

        menu.addCommand(new RunExampleCommand("3", ex3.toString(), controller3));

        // Example 4
        MyIStack<IStatement> exeStack4 = new MyStack<>();
        MyIDictionary<String, Value> symTable4 = new MyDictionary<>();
        MyIList<Value> out4 = new MyList<>();
        MyIDictionary<StringValue, BufferedReader> fileTable4 = new MyDictionary<>();

        Expression fileNameExample = new ValueExpression(new StringValue("test.in"));
        IStatement ex4 =
                new CompoundStatement(new OpenReadFileStatement(fileNameExample),
                        new CompoundStatement(new VariableDeclarationStatement("varc", new IntType()),
                                new CompoundStatement(new ReadFileStatement(fileNameExample, "varc"),
                                        new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                new CompoundStatement(new ReadFileStatement(fileNameExample, "varc"),
                                                        new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                                new CloseReadFileStatement(fileNameExample)))))));

        ProgramState programState4 = new ProgramState(exeStack4, symTable4, out4, ex4, fileTable4);
        IRepository repository4 = new Repository("log4.txt");
        Controller controller4 = new Controller(repository4);
        controller4.addProgramState(programState4);

        menu.addCommand(new RunExampleCommand("4", ex4.toString(), controller4));

        // Example 5
        MyIStack<IStatement> exeStack5 = new MyStack<>();
        MyIDictionary<String, Value> symTable5 = new MyDictionary<>();
        MyIList<Value> out5 = new MyList<>();
        MyIDictionary<StringValue, BufferedReader> fileTable5 = new MyDictionary<>();

        IStatement ex5 =
                new CompoundStatement(new VariableDeclarationStatement("a",new IntType()),
                        new CompoundStatement(new VariableDeclarationStatement("b", new IntType()),
                                new CompoundStatement(new AssignmentStatement("b", new ValueExpression(new IntValue(-2))),
                                        new IfStatement(new RelationalExpression(new VariableExpression("a"),
                                                new VariableExpression("b"), ">"), new PrintStatement(new VariableExpression("a")),
                                                new PrintStatement(new VariableExpression("b"))))));

        ProgramState programState5 = new ProgramState(exeStack5, symTable5, out5, ex5, fileTable5);
        IRepository repository5 = new Repository("log5.txt");
        Controller controller5 = new Controller(repository5);
        controller5.addProgramState(programState5);

        menu.addCommand(new RunExampleCommand("5", ex5.toString(), controller5));

        // Show menu
        menu.show();
    }
}
