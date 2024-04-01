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
public class QueryTwoServiceGrpc {

  private QueryTwoServiceGrpc() {}

  public static final String SERVICE_NAME = "com.example.QueryTwoService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("This feature is experimental and subject to change.")
  public static final MethodDescriptor<com.example.Query.QueryTwoRequest,
  com.example.Query.QueryTwoResponse> METHOD_GET_TOP_STATES =
  MethodDescriptor.<com.example.Query.QueryTwoRequest, com.example.Query.QueryTwoResponse>newBuilder()
          .setType(MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName("com.example.QueryTwoService", "GetTopStates"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(com.example.Query.QueryTwoRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(com.example.Query.QueryTwoResponse.getDefaultInstance()))
          .build();

  public static QueryTwoServiceStub newStub(io.grpc.Channel channel) {
    return new QueryTwoServiceStub(channel);
  }

  public static QueryTwoServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new QueryTwoServiceBlockingStub(channel);
  }

  public static QueryTwoServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new QueryTwoServiceFutureStub(channel);
  }

  public static interface QueryTwoService {

    public void getTopStates(com.example.Query.QueryTwoRequest request,
        io.grpc.stub.StreamObserver<com.example.Query.QueryTwoResponse> responseObserver);
  }

  public static interface QueryTwoServiceBlockingClient {

    public com.example.Query.QueryTwoResponse getTopStates(com.example.Query.QueryTwoRequest request);
  }

  public static interface QueryTwoServiceFutureClient {

    public com.google.common.util.concurrent.ListenableFuture<com.example.Query.QueryTwoResponse> getTopStates(
        com.example.Query.QueryTwoRequest request);
  }

  public static class QueryTwoServiceStub extends io.grpc.stub.AbstractStub<QueryTwoServiceStub>
      implements QueryTwoService {
    private QueryTwoServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private QueryTwoServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryTwoServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new QueryTwoServiceStub(channel, callOptions);
    }

    @java.lang.Override
    public void getTopStates(com.example.Query.QueryTwoRequest request,
        io.grpc.stub.StreamObserver<com.example.Query.QueryTwoResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_TOP_STATES, getCallOptions()), request, responseObserver);
    }
  }

  public static class QueryTwoServiceBlockingStub extends io.grpc.stub.AbstractStub<QueryTwoServiceBlockingStub>
      implements QueryTwoServiceBlockingClient {
    private QueryTwoServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private QueryTwoServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryTwoServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new QueryTwoServiceBlockingStub(channel, callOptions);
    }

    @java.lang.Override
    public com.example.Query.QueryTwoResponse getTopStates(com.example.Query.QueryTwoRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_GET_TOP_STATES, getCallOptions()), request);
    }
  }

  public static class QueryTwoServiceFutureStub extends io.grpc.stub.AbstractStub<QueryTwoServiceFutureStub>
      implements QueryTwoServiceFutureClient {
    private QueryTwoServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private QueryTwoServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryTwoServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new QueryTwoServiceFutureStub(channel, callOptions);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.example.Query.QueryTwoResponse> getTopStates(
        com.example.Query.QueryTwoRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_TOP_STATES, getCallOptions()), request);
    }
  }

  public static io.grpc.ServerServiceDefinition bindService(
      final QueryTwoService serviceImpl) {
    return io.grpc.ServerServiceDefinition.builder(SERVICE_NAME)
      .addMethod(
        METHOD_GET_TOP_STATES,
        asyncUnaryCall(
          new io.grpc.stub.ServerCalls.UnaryMethod<
              com.example.Query.QueryTwoRequest,
              com.example.Query.QueryTwoResponse>() {
            @java.lang.Override
            public void invoke(
                com.example.Query.QueryTwoRequest request,
                io.grpc.stub.StreamObserver<com.example.Query.QueryTwoResponse> responseObserver) {
              serviceImpl.getTopStates(request, responseObserver);
            }
          })).build();
  }
}
