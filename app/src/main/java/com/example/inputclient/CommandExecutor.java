package com.example.inputclient;

import java.io.PrintWriter;

public class CommandExecutor extends Thread {
    PrintWriter printWriter;
    String command;

    public CommandExecutor(PrintWriter printWriter_, String command_) {
        this.printWriter = printWriter_;
        this.command = command_;
    }

    @Override
    public void run() {
        printWriter.print(this.command);
        printWriter.flush();
    }
}
