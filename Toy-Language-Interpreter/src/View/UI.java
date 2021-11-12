package View;

import Controller.Controller;
import Model.Expressions.ArithmeticExpression;
import Model.Expressions.ValueExpression;
import Model.Expressions.VariableExpression;
import Model.ProgramState;
import Model.Statements.*;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Utils.*;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;
import java.util.Scanner;

public class UI {
    static IStatement ex1 = new CompoundStatement(new VariableDeclarationStatement("v",new IntType()),
            new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new IntValue(2))), new PrintStatement(new VariableExpression("v"))));

    static IStatement ex2 = new CompoundStatement( new VariableDeclarationStatement("a",new IntType()),
            new CompoundStatement(new VariableDeclarationStatement("b",new IntType()),
                    new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression('+',new ValueExpression(new IntValue(2)),
                            new ArithmeticExpression('*',new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                            new CompoundStatement(new AssignmentStatement("b",new ArithmeticExpression('+',new VariableExpression("a"),
                                    new ValueExpression(new IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));

    static IStatement ex3 = new CompoundStatement(new VariableDeclarationStatement("a",new BoolType()),
            new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                    new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                            new CompoundStatement(new IfStatement(new VariableExpression("a"),
                                    new AssignmentStatement("v",new ValueExpression(new IntValue(2))),
                                    new AssignmentStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new VariableExpression("v"))))));


    private final Controller controller;

    public UI(Controller controller) {
        this.controller = controller;
    }

    private void createExample(String example) throws Exception {
        MyIStack<IStatement> exeStack = new MyStack<>();
        MyIDictionary<String, Value> symTable = new MyDictionary<>();
        MyIList<Value> out = new MyList<>();
        MyIDictionary<StringValue, BufferedReader> fileTable = new MyDictionary<>();
        MyIDictionary<Integer, Value> heap = new MyHeap<>();

        ProgramState programState;

        switch (example) {
            case "1" -> programState = new ProgramState(exeStack, symTable, out, UI.ex1, fileTable, heap);
            case "2" -> programState = new ProgramState(exeStack, symTable, out, UI.ex2, fileTable, heap);
            case "3" -> programState = new ProgramState(exeStack, symTable, out, UI.ex3, fileTable, heap);
            default -> throw new Exception("Invalid option provided"); // program cannot reach this branch
        }

        controller.addProgramState(programState);
    }

    private void executeProgram() {
        try {
            ProgramState programState = this.controller.allStep();
            System.out.println("Output: ");
            for(int i = 0; i < programState.getOut().getSize(); i ++) {
                System.out.println(programState.getOut().getData().get(i));
            }
        } catch (Exception error) {
            System.out.println(error.toString());
        }

    }

    private void printMenuBegin() {
        System.out.println("1. Choose a program.");
        System.out.println("0. Exit.");
    }

    private void printMenu() {
        System.out.println("Choose a program: ");
        System.out.println("1. Program 1: int v; v=2; print(v)");
        System.out.println("2. Program 2: int a; int b; a=2+3*5; b=a+1; print(b)");
        System.out.println("3. Program 3: bool a; int v; a=true; (if a then v=2 else v=3); print(v)");
        System.out.println("4. Execute (and print output).");
        System.out.println("0. Exit.");

        Scanner scan = new Scanner(System.in);
        boolean done = false;
        String command;

        while(!done) {
            System.out.println("\nEnter a command: ");
            command = scan.nextLine();
            try {
                switch (command) {
                    case "1" -> createExample("1");
                    case "2" -> createExample("2");
                    case "3" -> createExample("3");
                    case "4" -> executeProgram();
                    case "0" -> done = true;
                    default -> System.out.println("Invalid command.");
                }
            } catch (Exception error) {
                System.out.println(error.toString());
            }
        }
    }

    public void start() {
        String command;
        boolean done = false;
        Scanner scan = new Scanner(System.in);

        while(!done) {
            printMenuBegin();
            System.out.println("\nEnter a command: ");
            command = scan.nextLine();

            try {
                switch (command) {
                    case "1" -> printMenu();
                    case "0" -> done = true;
                    default -> System.out.println("Invalid command.");
                }
            } catch (Exception exception) {
                System.out.println(exception.toString());
            }
        }
    }
}
