package GUI;

import Controller.Controller;
import Model.Expressions.*;
import Model.ProgramState;
import Model.Statements.*;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.ReferenceType;
import Model.Types.Type;
import Model.Utils.*;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Model.Values.Value;
import Repository.IRepository;
import Repository.Repository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProgramsViewController {

    private final List<IStatement> programsList = new ArrayList<>();

    private final List<Controller> programsControllers = new ArrayList<>();

    @FXML
    private Button runButton;

    @FXML
    private ListView<String> listView = new ListView<>();

    @FXML
    private Label title;

    @FXML
    private Label text;

    public ProgramsViewController() {}

    public void init() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Type checker Error");

        // Example 1
        MyIStack<IStatement> exeStack1 = new MyStack<>();
        MyIDictionary<String, Value> symTable1 = new MyDictionary<>();
        MyIList<Value> out1 = new MyList<>();
        MyIDictionary<StringValue, BufferedReader> fileTable1 = new MyDictionary<>();
        MyIDictionary<Integer, Value> heap1 = new MyHeap<>();

        IStatement ex1 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new BoolValue(true))),
                        new PrintStatement(new VariableExpression("v"))));

        try {
            MyIDictionary<String, Type> typeEnvironment = new MyDictionary<>();
            ex1.typeCheck(typeEnvironment);

            ProgramState programState1 = new ProgramState(exeStack1, symTable1, out1, ex1, fileTable1, heap1);
            IRepository repository1 = new Repository("log1.txt");
            Controller controller1 = new Controller(repository1);
            controller1.addProgramState(programState1);

            programsList.add(ex1);
            programsControllers.add(controller1);

        } catch (Exception e) {
            alert.setHeaderText("Error in Example 1.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }


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

        try {
            MyIDictionary<String, Type> typeEnvironment = new MyDictionary<>();
            ex2.typeCheck(typeEnvironment);

            ProgramState programState2 = new ProgramState(exeStack2, symTable2, out2, ex2, fileTable2, heap2);
            IRepository repository2 = new Repository("log2.txt");
            Controller controller2 = new Controller(repository2);
            controller2.addProgramState(programState2);

            programsList.add(ex2);
            programsControllers.add(controller2);

        } catch (Exception e) {
            alert.setHeaderText("Error in Example 2.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }


        // Example 3
        MyIStack<IStatement> exeStack3 = new MyStack<>();
        MyIDictionary<String, Value> symTable3 = new MyDictionary<>();
        MyIList<Value> out3 = new MyList<>();
        MyIDictionary<StringValue, BufferedReader> fileTable3 = new MyDictionary<>();
        MyIDictionary<Integer, Value> heap3 = new MyHeap<>();

        IStatement ex3 = new CompoundStatement(new VariableDeclarationStatement("a", new BoolType()),
                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                        new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompoundStatement(new IfStatement(new VariableExpression("a"),
                                        new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                                        new AssignmentStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new VariableExpression("v"))))));

        try {
            MyIDictionary<String, Type> typeEnvironment = new MyDictionary<>();
            ex3.typeCheck(typeEnvironment);

            ProgramState programState3 = new ProgramState(exeStack3, symTable3, out3, ex3, fileTable3, heap3);
            IRepository repository3 = new Repository("log3.txt");
            Controller controller3 = new Controller(repository3);
            controller3.addProgramState(programState3);

            programsList.add(ex3);
            programsControllers.add(controller3);

        } catch (Exception e) {
            alert.setHeaderText("Error in Example 3.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }


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

        try {
            MyIDictionary<String, Type> typeEnvironment = new MyDictionary<>();
            ex4.typeCheck(typeEnvironment);

            ProgramState programState4 = new ProgramState(exeStack4, symTable4, out4, ex4, fileTable4, heap4);
            IRepository repository4 = new Repository("log4.txt");
            Controller controller4 = new Controller(repository4);
            controller4.addProgramState(programState4);

            programsList.add(ex4);
            programsControllers.add(controller4);

        } catch (Exception e) {
            alert.setHeaderText("Error in Example 4.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }


        // Example 5
        MyIStack<IStatement> exeStack5 = new MyStack<>();
        MyIDictionary<String, Value> symTable5 = new MyDictionary<>();
        MyIList<Value> out5 = new MyList<>();
        MyIDictionary<StringValue, BufferedReader> fileTable5 = new MyDictionary<>();
        MyIDictionary<Integer, Value> heap5 = new MyHeap<>();

        IStatement ex5 =
                new CompoundStatement(new VariableDeclarationStatement("a", new IntType()),
                        new CompoundStatement(new VariableDeclarationStatement("b", new IntType()),
                                new CompoundStatement(new AssignmentStatement("b", new ValueExpression(new IntValue(-2))),
                                        new IfStatement(new RelationalExpression(new VariableExpression("a"),
                                                new VariableExpression("b"), ">"), new PrintStatement(new VariableExpression("a")),
                                                new PrintStatement(new VariableExpression("b"))))));

        try {
            MyIDictionary<String, Type> typeEnvironment = new MyDictionary<>();
            ex5.typeCheck(typeEnvironment);

            ProgramState programState5 = new ProgramState(exeStack5, symTable5, out5, ex5, fileTable5, heap5);
            IRepository repository5 = new Repository("log5.txt");
            Controller controller5 = new Controller(repository5);
            controller5.addProgramState(programState5);

            programsList.add(ex5);
            programsControllers.add(controller5);

        } catch (Exception e) {
            alert.setHeaderText("Error in Example 5.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }


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

        try {
            MyIDictionary<String, Type> typeEnvironment = new MyDictionary<>();
            ex6.typeCheck(typeEnvironment);

            ProgramState programState6 = new ProgramState(exeStack6, symTable6, out6, ex6, fileTable6, heap6);
            IRepository repository6 = new Repository("log6.txt");
            Controller controller6 = new Controller(repository6);
            controller6.addProgramState(programState6);

            programsList.add(ex6);
            programsControllers.add(controller6);

        } catch (Exception e) {
            alert.setHeaderText("Error in Example 6.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }


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

        try {
            MyIDictionary<String, Type> typeEnvironment = new MyDictionary<>();
            ex7.typeCheck(typeEnvironment);

            ProgramState programState7 = new ProgramState(exeStack7, symTable7, out7, ex7, fileTable7, heap7);
            IRepository repository7 = new Repository("log7.txt");
            Controller controller7 = new Controller(repository7);
            controller7.addProgramState(programState7);

            programsList.add(ex7);
            programsControllers.add(controller7);

        } catch (Exception e) {
            alert.setHeaderText("Error in Example 7.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }


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

        try {
            MyIDictionary<String, Type> typeEnvironment = new MyDictionary<>();
            ex8.typeCheck(typeEnvironment);

            ProgramState programState8 = new ProgramState(exeStack8, symTable8, out8, ex8, fileTable8, heap8);
            IRepository repository8 = new Repository("log8.txt");
            Controller controller8 = new Controller(repository8);
            controller8.addProgramState(programState8);

            programsList.add(ex8);
            programsControllers.add(controller8);

        } catch (Exception e) {
            alert.setHeaderText("Error in Example 8.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }


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

        try {
            MyIDictionary<String, Type> typeEnvironment = new MyDictionary<>();
            ex9.typeCheck(typeEnvironment);

            ProgramState programState9 = new ProgramState(exeStack9, symTable9, out9, ex9, fileTable9, heap9);
            IRepository repository9 = new Repository("log9.txt");
            Controller controller9 = new Controller(repository9);
            controller9.addProgramState(programState9);

            programsList.add(ex9);
            programsControllers.add(controller9);

        } catch (Exception e) {
            alert.setHeaderText("Error in Example 9.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }


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

        try {
            MyIDictionary<String, Type> typeEnvironment = new MyDictionary<>();
            ex10.typeCheck(typeEnvironment);

            ProgramState programState10 = new ProgramState(exeStack10, symTable10, out10, ex10, fileTable10, heap10);
            IRepository repository10 = new Repository("log10.txt");
            Controller controller10 = new Controller(repository10);
            controller10.addProgramState(programState10);

            programsList.add(ex10);
            programsControllers.add(controller10);
        } catch (Exception e) {
            alert.setHeaderText("Error in Example 10.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }


        // Example 11
        MyIStack<IStatement> exeStack11 = new MyStack<>();
        MyIDictionary<String, Value> symTable11 = new MyDictionary<>();
        MyIList<Value> out11 = new MyList<>();
        MyIDictionary<StringValue, BufferedReader> fileTable11 = new MyDictionary<>();
        MyIDictionary<Integer, Value> heap11 = new MyHeap<>();

        IStatement ex11 =
                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                        new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new IntType())),
                                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(10))),
                                        new CompoundStatement(new HeapAllocationStatement("a", new ValueExpression(new IntValue(22))),
                                                new CompoundStatement(new ForkStatement(new CompoundStatement(new HeapWritingStatement("a", new ValueExpression(new IntValue(30))),
                                                        new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(32))),
                                                                new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                                        new PrintStatement(new HeapReadingExpression(new VariableExpression("a"))))))),
                                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                                new PrintStatement(new HeapReadingExpression(new VariableExpression("a")))))))));

        try {
            MyIDictionary<String, Type> typeEnvironment = new MyDictionary<>();
            ex11.typeCheck(typeEnvironment);

            ProgramState programState11 = new ProgramState(exeStack11, symTable11, out11, ex11, fileTable11, heap11);
            IRepository repository11 = new Repository("log11.txt");
            Controller controller11 = new Controller(repository11);
            controller11.addProgramState(programState11);

            programsList.add(ex11);
            programsControllers.add(controller11);

        } catch (Exception e) {
            System.out.println();
        }

        // Example 12
        // int v; fork( v=2; print(v) ); fork( v=1; v=v-1; print(v) )
        MyIStack<IStatement> exeStack12 = new MyStack<>();
        MyIDictionary<String, Value> symTable12 = new MyDictionary<>();
        MyIList<Value> out12 = new MyList<>();
        MyIDictionary<StringValue, BufferedReader> fileTable12 = new MyDictionary<>();
        MyIDictionary<Integer, Value> heap12 = new MyHeap<>();

        IStatement ex12 =
                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                        new CompoundStatement(new ForkStatement(new CompoundStatement(new AssignmentStatement("v",
                                new ValueExpression(new IntValue(2))), new PrintStatement(new VariableExpression("v")))),
                                new ForkStatement(new CompoundStatement(new AssignmentStatement("v", new ValueExpression(
                                        new IntValue(1))), new CompoundStatement(new AssignmentStatement("v", new ArithmeticExpression('-',
                                        new VariableExpression("v"), new ValueExpression(new IntValue(1)))),
                                        new PrintStatement(new VariableExpression("v")))))));

        try {
            MyIDictionary<String, Type> typeEnvironment = new MyDictionary<>();
            ex12.typeCheck(typeEnvironment);

            ProgramState programState12 = new ProgramState(exeStack12, symTable12, out12, ex12, fileTable12, heap12);
            IRepository repository12 = new Repository("log12.txt");
            Controller controller12 = new Controller(repository12);
            controller12.addProgramState(programState12);

            programsList.add(ex12);
            programsControllers.add(controller12);

        } catch (Exception e) {
            alert.setHeaderText("Error in Example 12.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

        // Example 13
        // Ref int a; new(a, 0), fork( wH(a, 10), print(rH(a)), fork( print(rH(a))) print(rH(a) )
        MyIStack<IStatement> exeStack13 = new MyStack<>();
        MyIDictionary<String, Value> symTable13 = new MyDictionary<>();
        MyIList<Value> out13 = new MyList<>();
        MyIDictionary<StringValue, BufferedReader> fileTable13 = new MyDictionary<>();
        MyIDictionary<Integer, Value> heap13 = new MyHeap<>();

        IStatement ex13 =
                new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new IntType())),
                        new CompoundStatement(new HeapAllocationStatement("a", new ValueExpression(new IntValue())),
                                new CompoundStatement(new ForkStatement(new CompoundStatement(new HeapWritingStatement("a",
                                        new ValueExpression(new IntValue(10))), new CompoundStatement(new PrintStatement(
                                        new HeapReadingExpression(new VariableExpression("a"))), new ForkStatement(
                                        new PrintStatement(new HeapReadingExpression(new VariableExpression("a"))))))),
                                        new PrintStatement(new HeapReadingExpression(new VariableExpression("a"))))));

        try {
            MyIDictionary<String, Type> typeEnvironment = new MyDictionary<>();
            ex13.typeCheck(typeEnvironment);

            ProgramState programState13 = new ProgramState(exeStack13, symTable13, out13, ex13, fileTable13, heap13);
            IRepository repository13 = new Repository("log13.txt");
            Controller controller13 = new Controller(repository13);
            controller13.addProgramState(programState13);

            programsList.add(ex13);
            programsControllers.add(controller13);

        } catch (Exception e) {
            alert.setHeaderText("Error in Example 13.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

        // Create a list of programs string representation.
        List<String> programsStringList = new ArrayList<>();
        programsList.forEach(program -> programsStringList.add(program.toString()));

        ObservableList<String> programsObservableList = FXCollections.observableArrayList(programsStringList);
        listView.setItems(programsObservableList);
    }

    @FXML
    private void runProgramAction() throws Exception {
        if (listView.getSelectionModel().getSelectedItem() != null) {
            // Program is selected from the List View.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterpreterWindow.fxml"));
            AnchorPane interpreterWindow = loader.load();

            InterpreterWindowController controller = loader.getController();
            controller.init(programsControllers.get(listView.getSelectionModel().getSelectedIndex()));

            Stage stage = new Stage();
            stage.setTitle("Toy-Language-Interpreter");
            stage.setScene(new Scene(interpreterWindow));
            stage.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Selection Warning");
            alert.setContentText("No program selected.");
            alert.showAndWait();
        }
    }

}
