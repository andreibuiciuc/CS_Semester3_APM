package GUI;

import Controller.Controller;
import Model.ProgramState;
import Model.Statements.IStatement;
import Model.Statements.IfStatement;
import Model.Utils.MyIDictionary;
import Model.Values.Value;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.*;
import java.util.stream.Collectors;

public class InterpreterWindowController {
    private Controller controller;
    private ProgramState program;

    @FXML
    private TextField programStatesNumber = new TextField();

    @FXML
    private TableView<HeapEntry> heapTableView = new TableView<>();

    @FXML
    private TableColumn<HeapEntry, Integer> heapAddressColumn = new TableColumn<>();

    @FXML
    private TableColumn<HeapEntry, Value> heapValueColumn = new TableColumn<>();

    @FXML
    private ListView<String> outListView = new ListView<>();

    @FXML
    private ListView<String> fileListView = new ListView<>();

    @FXML
    private ListView<Integer> programStatesView = new ListView<>();

    @FXML
    private TableView<SymbolTableEntry> symbolTableView = new TableView<>();

    @FXML
    private TableColumn<Map.Entry<String, String>, String> variableNameColumn = new TableColumn<>();

    @FXML
    private TableColumn<Map.Entry<String, String>, String> variableValueColumn = new TableColumn<>();

    @FXML
    private ListView<String> exeStackView = new ListView<>();

    public InterpreterWindowController() {}

    public void init(Controller controller) {
        this.controller = controller;
        program = controller.getRepository().getProgramsList().get(0);
        loadProgramData();
    }

    private void loadProgramData() {
        if (program != null) {
            // Set the Text Field denoting the number of program states.
            programStatesNumber.setText(Integer.toString(controller.getRepository().getProgramsList().size()));

            // Set the Heap Table (Table View).
            loadHeapTableView();

            // Set the Output List (List View).
            loadOutListView();

            // Set the File Table (List View).
            loadFileView();

            // Set the Program States (List View).
            loadProgramsStatesView();

            // Set the Symbol Table (Table View).
            loadSymbolTableView();

            // Set the Execution Stack (List View).
            loadExeStackView();

        }
    }

    private void loadHeapTableView() {
        heapAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        heapValueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));


        ObservableList<HeapEntry> data = FXCollections.observableArrayList();
        Map<Integer, Value> heap = program.getHeap().getContent();

        for (Map.Entry<Integer, Value> entry: heap.entrySet()) {
            data.add(new HeapEntry(entry.getKey(), entry.getValue()));
        }

        heapTableView.setItems(data);
        heapTableView.refresh();
    }

    private void loadOutListView() {
        outListView.getItems().setAll(String.valueOf(FXCollections.observableArrayList(program.getOut().getData())));
    }

    private void loadFileView() {
        fileListView.getItems().setAll(String.valueOf(FXCollections.observableArrayList(program.getFileTable().getContent().keySet())));
    }

    private void loadProgramsStatesView() {
        programStatesView.getItems().setAll(FXCollections.observableArrayList(controller.getRepository().getProgramsList()
                .stream().map(ProgramState::getThreadID).collect(Collectors.toList())));
    }

    private void loadSymbolTableView() {
        variableNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        variableValueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        ObservableList<SymbolTableEntry> data = FXCollections.observableArrayList();
        Map<String, Value> symbolTable = program.getSymTable().getContent();

        for (Map.Entry<String, Value> entry: symbolTable.entrySet()) {
            data.add(new SymbolTableEntry(entry.getKey(), entry.getValue()));
        }

        symbolTableView.setItems(data);
        symbolTableView.refresh();
    }

    private void loadExeStackView() {
        exeStackView.getItems().setAll(String.valueOf(FXCollections.observableArrayList(program.getExeStack().getStack())));
    }

    @FXML
    private void setSelectedProgram() {
        int index = programStatesView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            program = controller.getRepository().getProgramsList().get(index);
            loadProgramData();
        }
    }

    @FXML
    private void runOneStepButtonAction() {

        Alert warningAlert = new Alert(Alert.AlertType.WARNING);
        warningAlert.setTitle("Warning");
        warningAlert.setContentText("No program selected.");

        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setTitle("Information");
        infoAlert.setHeaderText("Program over.");
        infoAlert.setContentText("Choose another program.");

        if (controller == null) {
            warningAlert.showAndWait();
        }

        if (program.getExeStack().isEmpty()) {
            infoAlert.showAndWait();
        }


        controller.oneStepAtATimeExecution();
        loadProgramData();

    }
}
