package Controller;

import Exceptions.ADTException;
import Model.Utils.MyIStack;
import Model.ProgramState;
import Model.Statements.IStatement;
import Model.Values.ReferenceValue;
import Model.Values.Value;
import Repository.IRepository;

import java.sql.Ref;
import java.text.CollationElementIterator;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public final class Controller {
    private final IRepository repository;

    public Controller(IRepository repository) {
        this.repository = repository;
    }

    public void addProgramState(ProgramState programState) {
        repository.addProgramState(programState);
    }

    List<Integer> getAddressesFromSymbolTable(Collection<Value> symTableValues, Collection<Value> heapValues) {
       return Stream.concat(
               // get the addresses from the symbol table.
               symTableValues.stream()
                       .filter(value -> value instanceof ReferenceValue)
                       .map(value -> {ReferenceValue value1 = (ReferenceValue)value; return value1.getHeapAddress(); }),

               // get the addresses from the heap table
               heapValues.stream()
                       .filter(value -> value instanceof ReferenceValue)
                       .map(value -> {ReferenceValue value1 = (ReferenceValue)value; return value1.getHeapAddress();})
       ).collect(Collectors.toList());

    }

    // Safe implementation of Garbage Collector
    // Eliminates heap entries whose keys (addresses) do not occur in the
    // address fields of the Reference Values from the Symbol Table
    private Map<Integer, Value> garbageCollector(List<Integer> symTableAddresses, Map<Integer, Value> heap) {
        return heap.entrySet().stream()
                .filter(value -> symTableAddresses.contains(value.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private ProgramState oneStep(ProgramState state) throws Exception {
        MyIStack<IStatement> exeStack = state.getExeStack();
        if(exeStack.isEmpty()) {
            throw new ADTException("Program Execution stack is empty.");
        }
        IStatement currentStatement = exeStack.pop();
        return currentStatement.execute(state);
    }

    public ProgramState allStep() throws Exception {
        ProgramState programState = repository.getCurrentProgramState();

        // System.out.println(programState);
        repository.logProgramStateExecution();

        while(!programState.getExeStack().isEmpty()) {
            oneStep(programState);

            // System.out.println(programState);
            repository.logProgramStateExecution();

            programState.getHeap().setContent((HashMap<Integer, Value>)
                    garbageCollector(getAddressesFromSymbolTable(
                        programState.getSymTable().getContent().values(),
                        programState.getHeap().getContent().values()
            ), programState.getHeap().getContent()));
        }

        return programState;
    }
}
