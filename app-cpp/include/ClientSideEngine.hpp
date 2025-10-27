#ifndef CLIENT_SIDE_ENGINE_H
#define CLIENT_SIDE_ENGINE_H

#include <grpc/grpc.h>
#include <grpcpp/channel.h>
#include <grpcpp/client_context.h>
#include <grpcpp/create_channel.h>
#include <grpcpp/security/credentials.h>

#include "proto/file_retrieval_engine.grpc.pb.h"

class ClientSideEngine {
    // TO-DO keep track of the connection
    std::shared_ptr<grpc::Channel> channel;
    std::unique_ptr<fre::FileRetrievalEngine::Stub> stub;

    public:
        // constructor
        ClientSideEngine();

        // default virtual destructor
        virtual ~ClientSideEngine() = default;

        // TO-DO re-declare index files and search files methods
        void indexFiles();
        void searchFiles();
        
        // TO-DO re-declare connect to the server
        void openConnection();
};

#endif