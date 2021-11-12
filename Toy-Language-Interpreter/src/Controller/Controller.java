package Controller;

import Exceptions.ADTException;
import Model.Utils.MyIStack;
import Model.ProgramState;
import Model.Statements.IStatement;
import Repository.IRepository;


public final class Controller {
    private final IRepository repository;

    public Controller(IRepository repository) {
        this.repository = repository;
    }

    public void addProgramState(ProgramState programState) {
        repository.addProgramState(programState);
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
        }

        return programState;
    }
}
