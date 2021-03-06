package Repository;

import Model.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public final class Repository implements IRepository {
    private List<ProgramState> programsList;
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
    public void logProgramStateExecution(ProgramState programState) throws Exception {
        PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        // save the content of the given input into a text file
        logFile.append(programState.toString());
        logFile.close();
    }

    @Override
    public void clearLog() throws Exception {
        // Want to clear all the data from the log file for future execution.
        PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, false)));
        logFile.write("");
        logFile.close();
    }

    @Override
    public List<ProgramState> getProgramsList() {
        return programsList;
    }

    @Override
    public void setProgramsList(List<ProgramState> programsList) {
        this.programsList = programsList;
    }
}
