package Model;

import Model.Utils.*;
import Model.Statements.IStatement;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;

public class ProgramState {
    private MyIStack<IStatement> exeStack;
    private MyIDictionary<String, Value> symTable;
    private MyIList<Value> out;
    private MyIDictionary<StringValue, BufferedReader> fileTable;
    private MyIDictionary<Integer, Value> heap;
    // IStatement originalProgram;

    public ProgramState(MyIStack<IStatement> exeStack, MyIDictionary<String, Value> symTable,
                        MyIList<Value> out, IStatement originalProgram,
                        MyIDictionary<StringValue, BufferedReader> fileTable,
                        MyIDictionary<Integer, Value> heap) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.fileTable = fileTable;
        this.heap = heap;
        //this.originalProgram = originalProgram;
        this.exeStack.push(originalProgram);
    }

    public MyIStack<IStatement> getExeStack() {
        return exeStack;
    }

    public void setExeStack(MyIStack<IStatement> exeStack) {
        this.exeStack = exeStack;
    }

    public MyIDictionary<String, Value> getSymTable() {
        return symTable;
    }

    public void setSymTable(MyIDictionary<String, Value> symTable) {
        this.symTable = symTable;
    }

    public MyIList<Value> getOut() {
        return out;
    }

    public void setOut(MyIList<Value> out) {
        this.out = out;
    }

    public MyIDictionary<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }

    public void setFileTable(MyIDictionary<StringValue, BufferedReader> fileTable) {
        this.fileTable = fileTable;
    }

    public MyIDictionary<Integer, Value> getHeap() {
        return heap;
    }

    public void setHeap(MyIDictionary<Integer, Value> heap) {
        this.heap = heap;
    }

    @Override
    public String toString() {
        return  "\nExecution stack:\n" + exeStack + "\n" +
                "Symbols table:\n" + symTable + "\n" +
                "Out list:\n" + out + " \n" +
                "File table:\n" + fileTable + "\n" +
                "Heap: \n" + heap + "\n";
    }

}
