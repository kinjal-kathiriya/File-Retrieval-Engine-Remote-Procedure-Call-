package csc435.app;

import java.lang.System;
import java.util.Scanner;

public class ClientAppInterface {
    private ClientSideEngine engine;

    public ClientAppInterface(ClientSideEngine engine) {
        this.engine = engine;

        // TO-DO implement constructor
        // keep track of the connection with the client
    }

    public void readCommands() {
        // TO-DO implement the read commands method
        Scanner sc = new Scanner(System.in);
        String command;
        
        while (true) {
            System.out.print("> ");
            
            // read from command line
            command = sc.nextLine().trim();
            System.out.println("Received command: '" + command + "'");


            // if the command is quit, terminate the program       
            if (command.equals("quit")) {
                System.out.println("Quitting...");
                engine.closeConnection();
                break;
            }

            else if (command.startsWith("connect")) {
                System.out.println("Parsing connect command...");
                String[] parts = command.split("\\s+");
                System.out.println("Command parts: " + java.util.Arrays.toString(parts));
                if (parts.length == 3) {
                    String host = parts[1];
                    int port;
                    try {
                        port = Integer.parseInt(parts[2]);
                        System.out.println("Host: " + host + ", Port: " + port);
                        engine.openConnection(host, port);
                        System.out.println("Connection successful!");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid port number.");
                    }
                } else {
                    System.out.println("Usage: connect <host> <port>");
                }
                continue;
            }

            
        
            
            // if the command begins with index, index the files from the specified directory
            else if (command.startsWith("index")) {
                String directoryPath = command.substring(6).trim();
                try {
                    engine.indexFiles(directoryPath);
                    System.out.println("Indexing completed!");
                } catch (Exception e) {
                    System.out.println("Failed to index files: " + e.getMessage());
                }
                continue;
            }

            // if the command begins with search, search for files that matches the query
            else if (command.startsWith("search")) {
            String query = command.substring(7).trim();
            try {
                engine.searchFiles(query);
                System.out.println("Search completed!");
            } catch (Exception e) {
                System.out.println("Failed to search files: " + e.getMessage());
            }
            continue;
        }
            else {System.out.println("unrecognized command!");}
            
        }

        sc.close();
    }
}