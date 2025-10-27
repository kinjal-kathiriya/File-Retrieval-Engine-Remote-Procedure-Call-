package csc435.app;

import csc435.app.FileRetrievalEngineGrpc.FileRetrievalEngineBlockingStub;
import csc435.app.IndexReq;
import csc435.app.SearchReq;
import csc435.app.SearchRep;

import io.grpc.Grpc;
import io.grpc.InsecureChannelCredentials;
import io.grpc.ManagedChannel;
import java.util.concurrent.TimeUnit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ClientSideEngine {
    // Connection tracking
    ManagedChannel channel;
    FileRetrievalEngineBlockingStub stub;

    public ClientSideEngine() {
        // Constructor implementation
    }

    public void indexFiles(String directoryPath) throws IOException {
        long startTime = System.nanoTime();  // Start time measurement
        File directory = new File(directoryPath);
        if (!directory.exists() || !directory.isDirectory()) {
            throw new IOException("Invalid directory path");
        }

        Map<String, Map<String, Long>> fileWordFrequencyMap = new HashMap<>();

        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (file.isFile() && file.getName().endsWith(".txt")) {
                Map<String, Long> wordFrequency = processFile(file);
                fileWordFrequencyMap.put(file.getAbsolutePath(), wordFrequency);
            }
        }

        for (Map.Entry<String, Map<String, Long>> entry : fileWordFrequencyMap.entrySet()) {
            String filePath = entry.getKey();
            Map<String, Long> wordFrequency = entry.getValue();
            IndexReq.Builder requestBuilder = IndexReq.newBuilder()
                .setDocumentPath(filePath)
                .putAllWordFrequencies(wordFrequency);
            
            stub.indexFile(requestBuilder.build());
        }

        long endTime = System.nanoTime();  // End time measurement
        System.out.println("Indexing time: " + (endTime - startTime) / 1_000_000_000.0 + " seconds");
    }

    private Map<String, Long> processFile(File file) throws IOException {
        Map<String, Long> wordFrequency = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\W+");
                for (String word : words) {
                    if (!word.isEmpty()) {
                        wordFrequency.put(word, wordFrequency.getOrDefault(word, 0L) + 1);
                    }
                }
            }
        }
        return wordFrequency;
    }




     

public void searchFiles(String query) {
    long startTime = System.nanoTime();  // Start time measurement

    String[] terms = query.split("\\s+AND\\s+");
    Map<String, Long> combinedResults = new HashMap<>();

    for (String term : terms) {
        SearchReq request = SearchReq.newBuilder().setTerm(term).build();
        SearchRep response = stub.searchFiles(request);

        for (Map.Entry<String, Long> entry : response.getDocumentFrequenciesMap().entrySet()) {
            combinedResults.merge(entry.getKey(), entry.getValue(), Long::sum);
        }
    }

    long endTime = System.nanoTime();  // End time measurement
    double searchTime = (endTime - startTime) / 1_000_000_000.0;

    System.out.println("Search completed in " + searchTime + " seconds");
    System.out.println("Search results (top 10):");
    combinedResults.entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .limit(10)
            .forEach(entry -> System.out.println("* " + entry.getKey() + " " + entry.getValue()));
}
   


    public void openConnection(String host, int port) {
        channel = Grpc.newChannelBuilderForAddress(host, port, InsecureChannelCredentials.create()).build();
        stub = FileRetrievalEngineGrpc.newBlockingStub(channel);
    }

    public void closeConnection() {
        if (channel != null) {
            channel.shutdown();
            try {
                channel.awaitTermination(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
