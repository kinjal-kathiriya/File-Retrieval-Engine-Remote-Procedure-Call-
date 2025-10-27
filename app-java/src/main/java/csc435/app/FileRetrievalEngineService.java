package csc435.app;

import io.grpc.stub.StreamObserver;
import java.util.Map;

public class FileRetrievalEngineService extends FileRetrievalEngineGrpc.FileRetrievalEngineImplBase {
    private final IndexStore store;

    public FileRetrievalEngineService(IndexStore store) {
        this.store = store;
    }

    @Override
    public void indexFile(IndexReq request, StreamObserver<IndexRep> responseObserver) {
        String filePath = request.getDocumentPath();
        Map<String, Long> wordFrequencies = request.getWordFrequenciesMap();

        for (Map.Entry<String, Long> entry : wordFrequencies.entrySet()) {
            String word = entry.getKey();
            Long frequency = entry.getValue();
            store.insertWord(filePath, word, frequency);
        }

        IndexRep response = IndexRep.newBuilder().setAck("OK").build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void searchFiles(SearchReq request, StreamObserver<SearchRep> responseObserver) {
        String term = request.getTerm();
        Map<String, Long> results = store.lookupWord(term);

        SearchRep.Builder responseBuilder = SearchRep.newBuilder();
        responseBuilder.putAllDocumentFrequencies(results);

        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }

    private IndexRep doIndexFile(IndexReq request) {
        // TO-DO update global index with temporary index received from the request
        // TO-DO send an OK message as the reply

        return IndexRep.newBuilder().build();
    }

    private SearchRep doSearchFiles(SearchReq request) {
        // TO-DO do lookup over the global index given the search term from the request
        // TO-DO send the results as the reply message

        return SearchRep.newBuilder().build();
    }

}