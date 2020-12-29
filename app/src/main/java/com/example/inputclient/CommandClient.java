package com.example.inputclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CommandClient {
    private Socket connectionSocket;
    private OutputStream outputStream;
    // PrintWriter for writing the commands
    private PrintWriter printWriter;
    // IP for making connections
    private String ip;
    // Port for making connections
    private int port;
    // Logger for tracking errors
    private boolean running;
    Logger logger;

    public static int INPUT_PORT = 42033;
    /**
     * Constructor to open a server
     * @param ip
     * @param port
     */
    public CommandClient(String ip, int port) {
        try {
            // Init logger
            logger = Logger.getLogger(CommandClient.class.getName());
            running = true;
            // Create connections and get the streams
            connectionSocket = new Socket(ip, port);
            outputStream = connectionSocket.getOutputStream();
            printWriter = new PrintWriter(outputStream);
        } catch (IOException ioException) {
            logger.log(Level.SEVERE, "Connection to Input Server failed.");
            System.out.println("Connection to Input Server failed.");
        }
    }

   public void sendCommand(String command) {
        CommandExecutor commandExecutor = new CommandExecutor(printWriter, command);
        commandExecutor.start();
   }


    /**
     * Close connection and terminate
     */
    public synchronized void close() {
        printWriter.close();
        try {
            connectionSocket.close();
        } catch (IOException ioException) {
            logger.log(Level.SEVERE, "Error upon closing connection to input server");
            System.out.println("Connection to Input Server failed.");
        }
    }

    public static void main(String[] args) {
        // Establish connection to server
        CommandClient commandClient = new CommandClient("127.0.0.1", INPUT_PORT);
        // Create a new scanner to get input from user
        Scanner inputScanner = new Scanner(System.in);
        String command_input = "";
        while (!command_input.equals("quit")) {
            command_input = inputScanner.nextLine();
            // Quit when the command is input to quit
            if (command_input.equals("quit")) {
                break;
            }
            commandClient.sendCommand(command_input);
        }
    }
}
