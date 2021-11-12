package Repository;

import Model.ProgramState;

public interface IRepository {
    public void addProgramState(ProgramState programState);

    public void logProgramStateExecution() throws Exception;

    ProgramState getCurrentProgramState();
}
