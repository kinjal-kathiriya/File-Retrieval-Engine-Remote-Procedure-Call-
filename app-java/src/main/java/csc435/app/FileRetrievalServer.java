package csc435.app;

public class FileRetrievalServer
{
    public static void main( String[] args )
    {
       // int serverPort = 12346;// default port

        if (args.length < 1) {
            System.out.println("Usage: java FileRetrievalServer <port>");
            return;
        }
        // TO-DO initialize the number of worker threads from args[0]

        int serverPort = Integer.parseInt(args[0]);

        IndexStore store = new IndexStore();
        ServerSideEngine engine = new ServerSideEngine(store);
        ServerAppInterface appInterface = new ServerAppInterface(engine);
        
        // create a thread that creates and server TCP/IP socket and listenes to connections
        engine.startgRPCServer(serverPort);

        // read commands from the user
        appInterface.readCommands();
    }

}