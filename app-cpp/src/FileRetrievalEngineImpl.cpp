#include "FileRetrievalEngineImpl.hpp"

FileRetrievalEngineImpl::FileRetrievalEngineImpl(std::shared_ptr<IndexStore> store) : store(store) {
    // TO-DO implement constructor
}

grpc::Status FileRetrievalEngineImpl::IndexFile(
        grpc::ServerContext* context,
        const fre::IndexReq* request,
        fre::IndexRep* reply)
{
    // TO-DO update global index with temporary index received from the request
    // TO-DO send an OK message as the reply

    return grpc::Status::OK;
}

grpc::Status FileRetrievalEngineImpl::SearchFiles(
        grpc::ServerContext* context,
        const fre::SearchReq* request,
        fre::SearchRep* reply) 
{
    // TO-DO do lookup over the global index given the search term from the request
    // TO-DO send the results as the reply message

    return grpc::Status::OK;
}