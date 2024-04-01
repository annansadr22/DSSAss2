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
public class QueryOneServiceGrpc {

  private QueryOneServiceGrpc() {}

  public static final String SERVICE_NAME = "com.example.QueryOneService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("This feature is experimental and subject to change.")
  public static final MethodDescriptor<com.example.Query.QueryOneRequest,
  com.example.Query.QueryOneResponse> METHOD_GET_COST =
  MethodDescriptor.<com.example.Query.QueryOneRequest, com.example.Query.QueryOneResponse>newBuilder()
          .setType(MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName("com.example.QueryOneService", "GetCost"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(com.example.Query.QueryOneRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(com.example.Query.QueryOneResponse.getDefaultInstance()))
          .build();

  public static QueryOneServiceStub newStub(io.grpc.Channel channel) {
    return new QueryOneServiceStub(channel);
  }

  public static QueryOneServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new QueryOneServiceBlockingStub(channel);
  }

  public static QueryOneServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new QueryOneServiceFutureStub(channel);
  }

  public static interface QueryOneService {

    public void getCost(com.example.Query.QueryOneRequest request,
        io.grpc.stub.StreamObserver<com.example.Query.QueryOneResponse> responseObserver);
  }

  public static interface QueryOneServiceBlockingClient {

    public com.example.Query.QueryOneResponse getCost(com.example.Query.QueryOneRequest request);
  }

  public static interface QueryOneServiceFutureClient {

    public com.google.common.util.concurrent.ListenableFuture<com.example.Query.QueryOneResponse> getCost(
        com.example.Query.QueryOneRequest request);
  }

  public static class QueryOneServiceStub extends io.grpc.stub.AbstractStub<QueryOneServiceStub>
      implements QueryOneService {
    private QueryOneServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private QueryOneServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryOneServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new QueryOneServiceStub(channel, callOptions);
    }

    @java.lang.Override
    public void getCost(com.example.Query.QueryOneRequest request,
        io.grpc.stub.StreamObserver<com.example.Query.QueryOneResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_COST, getCallOptions()), request, responseObserver);
    }
  }

  public static class QueryOneServiceBlockingStub extends io.grpc.stub.AbstractStub<QueryOneServiceBlockingStub>
      implements QueryOneServiceBlockingClient {
    private QueryOneServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private QueryOneServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryOneServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new QueryOneServiceBlockingStub(channel, callOptions);
    }

    @java.lang.Override
    public com.example.Query.QueryOneResponse getCost(com.example.Query.QueryOneRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_GET_COST, getCallOptions()), request);
    }
  }

  public static class QueryOneServiceFutureStub extends io.grpc.stub.AbstractStub<QueryOneServiceFutureStub>
      implements QueryOneServiceFutureClient {
    private QueryOneServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private QueryOneServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryOneServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new QueryOneServiceFutureStub(channel, callOptions);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.example.Query.QueryOneResponse> getCost(
        com.example.Query.QueryOneRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_COST, getCallOptions()), request);
    }
  }

  public static io.grpc.ServerServiceDefinition bindService(
      final QueryOneService serviceImpl) {
    return io.grpc.ServerServiceDefinition.builder(SERVICE_NAME)
      .addMethod(
        METHOD_GET_COST,
        asyncUnaryCall(
          new io.grpc.stub.ServerCalls.UnaryMethod<
              com.example.Query.QueryOneRequest,
              com.example.Query.QueryOneResponse>() {
            @java.lang.Override
            public void invoke(
                com.example.Query.QueryOneRequest request,
                io.grpc.stub.StreamObserver<com.example.Query.QueryOneResponse> responseObserver) {
              serviceImpl.getCost(request, responseObserver);
            }
          })).build();
  }
}
