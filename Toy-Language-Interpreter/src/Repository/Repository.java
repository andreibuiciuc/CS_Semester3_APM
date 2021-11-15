package Repository;

import Model.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public final class Repository implements IRepository{
    private final List<ProgramState> programsList;
    private final String logFilePath;

    public Repository(String logFilePath) {
        this.logFilePath = logFilePath;
        programsList = new ArrayList<>();
    }

    @Override
    public void addProgramState(ProgramState programState) {
        programsList.add(programState);
    }

    @Override
    public ProgramState getCurrentProgramState() {
        return programsList.get(programsList.size() - 1);
    }

    @Override
    public void logProgramStateExecution() throws Exception {
        PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));

        for(ProgramState programState: this.programsList) {
            logFile.append(programState.toString());
        }

        logFile.close();
    }

    @Override
    public void clearLog() throws Exception {
        // Want to clear all the data from the log file after the execution
        // for future executions.
        PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, false)));
        logFile.write("");
        logFile.close();
    }
}
