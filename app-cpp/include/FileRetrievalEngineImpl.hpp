#ifndef MATH_FORMULA_H
#define MATH_FORMULA_H

#include <grpc/grpc.h>
#include <grpcpp/server_context.h>

#include "proto/file_retrieval_engine.grpc.pb.h"

#include "IndexStore.hpp"

class FileRetrievalEngineImpl final : public fre::FileRetrievalEngine::Service
{
    // TO-DO keep track of the index store
    std::shared_ptr<IndexStore> store;

    public:
        explicit FileRetrievalEngineImpl(std::shared_ptr<IndexStore> store);
        grpc::Status IndexFile(grpc::ServerContext* context, const fre::IndexReq* request, fre::IndexRep* reply);
        grpc::Status SearchFiles(grpc::ServerContext* context, const fre::SearchReq* request, fre::SearchRep* reply);
};

#endif