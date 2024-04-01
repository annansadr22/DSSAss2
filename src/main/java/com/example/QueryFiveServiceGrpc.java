package com.example;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import io.grpc.MethodDescriptor;


@javax.annotation.Generated("by gRPC proto compiler")
public class QueryFiveServiceGrpc {

  private QueryFiveServiceGrpc() {}

  public static final String SERVICE_NAME = "com.example.QueryFiveService";

  // Static method descriptors that strictly reflect the proto.
  
  @io.grpc.ExperimentalApi("This feature is experimental and subject to change.")
  public static final MethodDescriptor<com.example.Query.QueryFiveRequest,
  com.example.Query.QueryFiveResponse> METHOD_GET_REGION_AVERAGES =
  MethodDescriptor.<com.example.Query.QueryFiveRequest, com.example.Query.QueryFiveResponse>newBuilder()
          .setType(MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName("com.example.QueryFiveService", "GetRegionAverages"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(com.example.Query.QueryFiveRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(com.example.Query.QueryFiveResponse.getDefaultInstance()))
          .build();
  
  public static QueryFiveServiceStub newStub(io.grpc.Channel channel) {
    return new QueryFiveServiceStub(channel);
  }

  public static QueryFiveServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new QueryFiveServiceBlockingStub(channel);
  }

  public static QueryFiveServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new QueryFiveServiceFutureStub(channel);
  }

  public static interface QueryFiveService {

    public void getRegionAverages(com.example.Query.QueryFiveRequest request,
        io.grpc.stub.StreamObserver<com.example.Query.QueryFiveResponse> responseObserver);
  }

  public static interface QueryFiveServiceBlockingClient {

    public com.example.Query.QueryFiveResponse getRegionAverages(com.example.Query.QueryFiveRequest request);
  }

  public static interface QueryFiveServiceFutureClient {

    public com.google.common.util.concurrent.ListenableFuture<com.example.Query.QueryFiveResponse> getRegionAverages(
        com.example.Query.QueryFiveRequest request);
  }

  public static class QueryFiveServiceStub extends io.grpc.stub.AbstractStub<QueryFiveServiceStub>
      implements QueryFiveService {
    private QueryFiveServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private QueryFiveServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryFiveServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new QueryFiveServiceStub(channel, callOptions);
    }

    @java.lang.Override
    public void getRegionAverages(com.example.Query.QueryFiveRequest request,
        io.grpc.stub.StreamObserver<com.example.Query.QueryFiveResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_REGION_AVERAGES, getCallOptions()), request, responseObserver);
    }
  }

  public static class QueryFiveServiceBlockingStub extends io.grpc.stub.AbstractStub<QueryFiveServiceBlockingStub>
      implements QueryFiveServiceBlockingClient {
    private QueryFiveServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private QueryFiveServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryFiveServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new QueryFiveServiceBlockingStub(channel, callOptions);
    }

    @java.lang.Override
    public com.example.Query.QueryFiveResponse getRegionAverages(com.example.Query.QueryFiveRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_GET_REGION_AVERAGES, getCallOptions()), request);
    }
  }

  public static class QueryFiveServiceFutureStub extends io.grpc.stub.AbstractStub<QueryFiveServiceFutureStub>
      implements QueryFiveServiceFutureClient {
    private QueryFiveServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private QueryFiveServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryFiveServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new QueryFiveServiceFutureStub(channel, callOptions);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.example.Query.QueryFiveResponse> getRegionAverages(
        com.example.Query.QueryFiveRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_REGION_AVERAGES, getCallOptions()), request);
    }
  }

  public static io.grpc.ServerServiceDefinition bindService(
      final QueryFiveService serviceImpl) {
    return io.grpc.ServerServiceDefinition.builder(SERVICE_NAME)
      .addMethod(
        METHOD_GET_REGION_AVERAGES,
        asyncUnaryCall(
          new io.grpc.stub.ServerCalls.UnaryMethod<
              com.example.Query.QueryFiveRequest,
              com.example.Query.QueryFiveResponse>() {
            @java.lang.Override
            public void invoke(
                com.example.Query.QueryFiveRequest request,
                io.grpc.stub.StreamObserver<com.example.Query.QueryFiveResponse> responseObserver) {
              serviceImpl.getRegionAverages(request, responseObserver);
            }
          })).build();
  }
}
