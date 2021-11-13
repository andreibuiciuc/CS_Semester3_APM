package View;

import Controller.Controller;
import Model.Expressions.*;
import Model.ProgramState;
import Model.Statements.*;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.ReferenceType;
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
        MyIDictionary<Integer, Value> heap1 = new MyHeap<>();

        IStatement ex1 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
        new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
        new PrintStatement(new VariableExpression("v"))));
        ProgramState programState1 = new ProgramState(exeStack1, symTable1, out1, ex1, fileTable1, heap1);

        IRepository repository1 = new Repository("log1.txt");
        Controller controller1 = new Controller(repository1);
        controller1.addProgramState(programState1);

        menu.addCommand(new RunExampleCommand("1", ex1.toString(), controller1));

        // Example 2
        MyIStack<IStatement> exeStack2 = new MyStack<>();
        MyIDictionary<String, Value> symTable2 = new MyDictionary<>();
        MyIList<Value> out2 = new MyList<>();
        MyIDictionary<StringValue, BufferedReader> fileTable2 = new MyDictionary<>();
        MyIDictionary<Integer, Value> heap2 = new MyHeap<>();

        IStatement ex2 = new CompoundStatement(new VariableDeclarationStatement("a", new IntType()),
        new CompoundStatement(new VariableDeclarationStatement("b", new IntType()),
        new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression('+', new ValueExpression(new IntValue(2)),
        new ArithmeticExpression('*', new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
        new CompoundStatement(new AssignmentStatement("b", new ArithmeticExpression('+', new VariableExpression("a"),
        new ValueExpression(new IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));
        ProgramState programState2 = new ProgramState(exeStack2, symTable2, out2, ex2, fileTable2, heap2);

        IRepository repository2 = new Repository("log2.txt");
        Controller controller2 = new Controller(repository2);
        controller2.addProgramState(programState2);

        menu.addCommand(new RunExampleCommand("2", ex2.toString(), controller2));

        // Example 3
        MyIStack<IStatement> exeStack3 = new MyStack<>();
        MyIDictionary<String, Value> symTable3 = new MyDictionary<>();
        MyIList<Value> out3 = new MyList<>();
        MyIDictionary<StringValue, BufferedReader> fileTable3 = new MyDictionary<>();
        MyIDictionary<Integer, Value> heap3 = new MyHeap<>();

        IStatement ex3 = new CompoundStatement(new VariableDeclarationStatement("a",new BoolType()),
        new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
        new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
        new CompoundStatement(new IfStatement(new VariableExpression("a"),
        new AssignmentStatement("v",new ValueExpression(new IntValue(2))),
        new AssignmentStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new VariableExpression("v"))))));
        ProgramState programState3 = new ProgramState(exeStack3, symTable3, out3, ex3, fileTable3, heap3);

        IRepository repository3 = new Repository("log3.txt");
        Controller controller3 = new Controller(repository3);
        controller3.addProgramState(programState3);

        menu.addCommand(new RunExampleCommand("3", ex3.toString(), controller3));

        
        // Example 4
        MyIStack<IStatement> exeStack4 = new MyStack<>();
        MyIDictionary<String, Value> symTable4 = new MyDictionary<>();
        MyIList<Value> out4 = new MyList<>();
        MyIDictionary<StringValue, BufferedReader> fileTable4 = new MyDictionary<>();
        MyIDictionary<Integer, Value> heap4 = new MyHeap<>();

        Expression fileNameExample = new ValueExpression(new StringValue("test.in"));
        IStatement ex4 =
        new CompoundStatement(new OpenReadFileStatement(fileNameExample),
        new CompoundStatement(new VariableDeclarationStatement("varc", new IntType()),
        new CompoundStatement(new ReadFileStatement(fileNameExample, "varc"),
        new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
        new CompoundStatement(new ReadFileStatement(fileNameExample, "varc"),
        new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
        new CloseReadFileStatement(fileNameExample)))))));

        ProgramState programState4 = new ProgramState(exeStack4, symTable4, out4, ex4, fileTable4, heap4);
        IRepository repository4 = new Repository("log4.txt");
        Controller controller4 = new Controller(repository4);
        controller4.addProgramState(programState4);

        menu.addCommand(new RunExampleCommand("4", ex4.toString(), controller4));

        // Example 5
        MyIStack<IStatement> exeStack5 = new MyStack<>();
        MyIDictionary<String, Value> symTable5 = new MyDictionary<>();
        MyIList<Value> out5 = new MyList<>();
        MyIDictionary<StringValue, BufferedReader> fileTable5 = new MyDictionary<>();
        MyIDictionary<Integer, Value> heap5 = new MyHeap<>();

        IStatement ex5 =
        new CompoundStatement(new VariableDeclarationStatement("a",new IntType()),
        new CompoundStatement(new VariableDeclarationStatement("b", new IntType()),
        new CompoundStatement(new AssignmentStatement("b", new ValueExpression(new IntValue(-2))),
        new IfStatement(new RelationalExpression(new VariableExpression("a"),
        new VariableExpression("b"), ">"), new PrintStatement(new VariableExpression("a")),
        new PrintStatement(new VariableExpression("b"))))));

        ProgramState programState5 = new ProgramState(exeStack5, symTable5, out5, ex5, fileTable5, heap5);
        IRepository repository5 = new Repository("log5.txt");
        Controller controller5 = new Controller(repository5);
        controller5.addProgramState(programState5);

        menu.addCommand(new RunExampleCommand("5", ex5.toString(), controller5));

        // Example 6
        // Ref int v;new(v,20);Ref Ref int a; new(a,v);print(v);print(a)
        MyIStack<IStatement> exeStack6 = new MyStack<>();
        MyIDictionary<String, Value> symTable6 = new MyDictionary<>();
        MyIList<Value> out6 = new MyList<>();
        MyIDictionary<StringValue, BufferedReader> fileTable6 = new MyDictionary<>();
        MyIDictionary<Integer, Value> heap6 = new MyHeap<>();

        IStatement ex6 =
        new CompoundStatement(new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
        new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
        new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
        new CompoundStatement(new HeapAllocationStatement("a", new VariableExpression("v")),
        new CompoundStatement(new PrintStatement(new VariableExpression("v")),
        new PrintStatement(new VariableExpression("a")))))));

        ProgramState programState6 = new ProgramState(exeStack6, symTable6, out6, ex6, fileTable6, heap6);
        IRepository repository6 = new Repository("log6.txt");
        Controller controller6 = new Controller(repository6);
        controller6.addProgramState(programState6);

        menu.addCommand(new RunExampleCommand("6", ex6.toString(), controller6));

        // Example 7
        MyIStack<IStatement> exeStack7 = new MyStack<>();
        MyIDictionary<String, Value> symTable7 = new MyDictionary<>();
        MyIList<Value> out7 = new MyList<>();
        MyIDictionary<StringValue, BufferedReader> fileTable7 = new MyDictionary<>();
        MyIDictionary<Integer, Value> heap7 = new MyHeap<>();

        IStatement ex7 =
        new CompoundStatement(new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
        new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
        new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
        new CompoundStatement(new HeapAllocationStatement("a", new VariableExpression("v")),
        new CompoundStatement(new PrintStatement(new HeapReadingExpression(new VariableExpression("v"))),
        new PrintStatement(new ArithmeticExpression('+', new HeapReadingExpression(new HeapReadingExpression(
        new VariableExpression("a"))), new ValueExpression(new IntValue(5)))))))));

        ProgramState programState7 = new ProgramState(exeStack7, symTable7, out7, ex7, fileTable7, heap7);
        IRepository repository7 = new Repository("log7.txt");
        Controller controller7 = new Controller(repository7);
        controller7.addProgramState(programState7);

        menu.addCommand(new RunExampleCommand("7", ex7.toString(), controller7));

        // Example 8
        MyIStack<IStatement> exeStack8 = new MyStack<>();
        MyIDictionary<String, Value> symTable8 = new MyDictionary<>();
        MyIList<Value> out8 = new MyList<>();
        MyIDictionary<StringValue, BufferedReader> fileTable8 = new MyDictionary<>();
        MyIDictionary<Integer, Value> heap8 = new MyHeap<>();

        IStatement ex8 =
        new CompoundStatement(new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
        new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
        new CompoundStatement(new PrintStatement(new HeapReadingExpression(new VariableExpression("v"))),
        new CompoundStatement(new HeapWritingStatement("v", new ValueExpression(new IntValue(30))),
        new PrintStatement(new ArithmeticExpression('+', new HeapReadingExpression(new VariableExpression("v")),
        new ValueExpression(new IntValue(5))))))));

        ProgramState programState8 = new ProgramState(exeStack8, symTable8, out8, ex8, fileTable8, heap8);
        IRepository repository8 = new Repository("log8.txt");
        Controller controller8 = new Controller(repository8);
        controller8.addProgramState(programState8);

        menu.addCommand(new RunExampleCommand("8", ex8.toString(), controller8));

        // Example 9
        // Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30);print(rH(rH(a)))
        MyIStack<IStatement> exeStack9 = new MyStack<>();
        MyIDictionary<String, Value> symTable9 = new MyDictionary<>();
        MyIList<Value> out9 = new MyList<>();
        MyIDictionary<StringValue, BufferedReader> fileTable9 = new MyDictionary<>();
        MyIDictionary<Integer, Value> heap9 = new MyHeap<>();

        IStatement ex9 =
        new CompoundStatement(new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
        new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
        new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
        new CompoundStatement(new HeapAllocationStatement("a", new VariableExpression("v")),
        new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntValue(30))),
        new PrintStatement(new HeapReadingExpression(new HeapReadingExpression(new VariableExpression("a")))))))));

        ProgramState programState9 = new ProgramState(exeStack9, symTable9, out9, ex9, fileTable9, heap9);
        IRepository repository9 = new Repository("log9.txt");
        Controller controller9 = new Controller(repository9);
        controller9.addProgramState(programState9);

        menu.addCommand(new RunExampleCommand("9", ex9.toString(), controller9));


        // Example 10
        MyIStack<IStatement> exeStack10 = new MyStack<>();
        MyIDictionary<String, Value> symTable10 = new MyDictionary<>();
        MyIList<Value> out10 = new MyList<>();
        MyIDictionary<StringValue, BufferedReader> fileTable10 = new MyDictionary<>();
        MyIDictionary<Integer, Value> heap10 = new MyHeap<>();

        IStatement ex10 =
        new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
        new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(4))),
        new CompoundStatement(new WhileStatement(new RelationalExpression(new VariableExpression("v"),
        new ValueExpression(new IntValue()), ">"), new CompoundStatement(
        new PrintStatement(new VariableExpression("v")), new AssignmentStatement("v",
        new ArithmeticExpression('-', new VariableExpression("v"), new ValueExpression(new IntValue(1)))))),
        new PrintStatement(new VariableExpression("v")))));


        ProgramState programState10 = new ProgramState(exeStack10, symTable10, out10, ex10, fileTable10, heap10);
        IRepository repository10 = new Repository("log10.txt");
        Controller controller10 = new Controller(repository10);
        controller10.addProgramState(programState10);

        menu.addCommand(new RunExampleCommand("10", ex10.toString(), controller10));

        // Show menu
        menu.show();

    }
}
