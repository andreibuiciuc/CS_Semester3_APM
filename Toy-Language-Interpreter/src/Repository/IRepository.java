package Repository;

import Model.ProgramState;

public interface IRepository {
    void addProgramState(ProgramState programState);

    void logProgramStateExecution() throws Exception;

    void clearLog() throws Exception;

    ProgramState getCurrentProgramState();
}
