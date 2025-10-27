#include "ClientSideEngine.hpp"

ClientSideEngine::ClientSideEngine() {
    // TO-DO implement constructor
}

void ClientSideEngine::indexFiles() {
    // TO-DO crawl the files from the input folder
    // for each file read and count the words and send the counted words to the server via gRPC call
}

void ClientSideEngine::searchFiles() {
    // TO-DO extract the terms from the query
    // for each term contact the server to retrieve the list of documents that contain the word via gRPC call
    // combine the results of a multi-term query
    // return top 10 results
}

void ClientSideEngine::openConnection() {
    // TO-DO create communication channel with the gRPC Server
    // TO-DO create gRPC client stub
}
