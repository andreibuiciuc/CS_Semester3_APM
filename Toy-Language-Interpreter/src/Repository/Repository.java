package Repository;

import Model.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public final class Repository implements IRepository{
    private final List<ProgramState> programThreads;
    private final String logFilePath;

    public Repository(String logFilePath) {
        this.logFilePath = logFilePath;
        programThreads = new ArrayList<>();
    }

    @Override
    public void addProgramState(ProgramState programState) {
        programThreads.add(programState);
    }

    @Override
    public ProgramState getCurrentProgramState() {
        return programThreads.get(programThreads.size() - 1);
    }

    @Override
    public void logProgramStateExecution() throws Exception {
        PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));

        for(ProgramState programState: this.programThreads) {
            logFile.append(programState.toString());

        }

        logFile.close();
    }
}
