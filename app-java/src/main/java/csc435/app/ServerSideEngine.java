package csc435.app;

import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.ServerBuilder;
import io.grpc.Server;
import java.io.IOException;

public class ServerSideEngine {
    // TO-DO keep track of the gRPC Server object
    private Server server;
    // TO-DO keep track of the index store
    private IndexStore store;

    public ServerSideEngine(IndexStore store) {
        this.store = store;
        // TO-DO implement constructor
    }

    // TO-DO create and start the gRPC Server
    public void startgRPCServer(int serverPort) {
    try {
        int maxMessageSize = 100 * 1024 * 1024;

        server = ServerBuilder.forPort(serverPort)
                              .addService(new FileRetrievalEngineService(store))
                              .maxInboundMessageSize(maxMessageSize)
                              .build()
                              .start();
                              
        System.out.println("Server started on port " + serverPort);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("Shutting down gRPC server since JVM is shutting down");
            ServerSideEngine.this.shutdown();
            System.err.println("Server shut down");
        }));
    } catch (IOException e) {
        System.err.println("IOException occurred while starting the server: " + e.getMessage());
        e.printStackTrace();
    }
}


    // TO-DO shutdown the gRPC Server
    public void shutdown() {
        if (server != null) {
            server.shutdown();
            try {
                server.awaitTermination();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}