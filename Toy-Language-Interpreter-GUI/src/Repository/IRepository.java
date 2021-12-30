package Repository;

import Model.ProgramState;

import java.util.List;

public interface IRepository {
    void addProgramState(ProgramState programState);

    void logProgramStateExecution(ProgramState programState) throws Exception;

    void clearLog() throws Exception;

    // ProgramState getCurrentProgramState();

    List<ProgramState> getProgramsList();

    void setProgramsList(List<ProgramState> programsList);
}
