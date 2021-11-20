package Controller;

import Exceptions.ADTException;
import Model.Utils.MyIStack;
import Model.ProgramState;
import Model.Statements.IStatement;
import Model.Values.ReferenceValue;
import Model.Values.Value;
import Repository.IRepository;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public final class Controller {
    private final IRepository repository;
    private ExecutorService executor;

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

    public void oneStepForAllPrograms(List<ProgramState> programsList) throws Exception {
        programsList.forEach(programState -> {
            try {
                repository.logProgramStateExecution(programState);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Prepare the list of callables
        List<Callable<ProgramState>> callList = programsList.stream()
                .map( (ProgramState p) -> (Callable<ProgramState>)( () -> {return p.oneStep();}))
                .collect(Collectors.toList());

        // Start the execution of the callables.
        List<ProgramState> newProgramsList = executor.invokeAll(callList).stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        throw new RuntimeException(e.getMessage());
                    }

                })
                .filter(programState -> programState != null)
                .collect(Collectors.toList());

        programsList.addAll(newProgramsList);

        programsList.forEach(programState -> {
            try {
                repository.logProgramStateExecution(programState);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        repository.setProgramsList(programsList);
    }


    public void allStep() throws Exception {
        repository.clearLog();

        executor = Executors.newFixedThreadPool(2);

        List<ProgramState> programsList = removeCompletedProgram(repository.getProgramsList());

        while(programsList.size() > 0) {

            // System.out.println(programState);
            programsList.get(0).getHeap().setContent((HashMap<Integer, Value>)
                    garbageCollector(getAddressesFromSymbolTable(
                        programsList.get(0).getSymTable().getContent().values(),
                        programsList.get(0).getHeap().getContent().values()
            ), programsList.get(0).getHeap().getContent()));

            oneStepForAllPrograms(programsList);
            programsList = removeCompletedProgram(repository.getProgramsList());

        }

        executor.shutdownNow();

        repository.setProgramsList(programsList);

    }

    public List<ProgramState> removeCompletedProgram(List<ProgramState> inputPrograms) {
        return inputPrograms.stream()
                .filter(programState -> programState.isNotCompleted())
                .collect(Collectors.toList());
    }
}
