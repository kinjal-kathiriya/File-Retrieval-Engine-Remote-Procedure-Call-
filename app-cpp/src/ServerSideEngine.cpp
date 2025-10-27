#include "ServerSideEngine.hpp"

ServerSideEngine::ServerSideEngine(std::shared_ptr<IndexStore> store) : store(store) {
    // TO-DO implement constructor
    // TO-DO initialize FileRetrievalEngineImpl and gRPC Server objects
}

void ServerSideEngine::startgRPCServer(int serverPort) {
    // TO-DO build the gRPC Server
    // TO-DO register the FileRetrievalEngineImpl service with the gRPC Server
    // TO-DO start the gRPC Server
}

void ServerSideEngine::shutdown() {
    // TO-DO shutdown the gRPC Server
    // TO-DO wait for the gRPC Server to shutdown
}