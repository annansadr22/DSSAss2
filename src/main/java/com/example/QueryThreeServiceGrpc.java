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
public class QueryThreeServiceGrpc {

  private QueryThreeServiceGrpc() {}

  public static final String SERVICE_NAME = "com.example.QueryThreeService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("This feature is experimental and subject to change.")
  public static final MethodDescriptor<com.example.Query.QueryThreeRequest,
  com.example.Query.QueryThreeResponse> METHOD_GET_TOP_ECONOMIC_STATES =
  MethodDescriptor.<com.example.Query.QueryThreeRequest, com.example.Query.QueryThreeResponse>newBuilder()
          .setType(MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName("com.example.QueryThreeService", "GetTopEconomicStates"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(com.example.Query.QueryThreeRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(com.example.Query.QueryThreeResponse.getDefaultInstance()))
          .build();

  public static QueryThreeServiceStub newStub(io.grpc.Channel channel) {
    return new QueryThreeServiceStub(channel);
  }

  public static QueryThreeServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new QueryThreeServiceBlockingStub(channel);
  }

  public static QueryThreeServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new QueryThreeServiceFutureStub(channel);
  }

  public static interface QueryThreeService {

    public void getTopEconomicStates(com.example.Query.QueryThreeRequest request,
        io.grpc.stub.StreamObserver<com.example.Query.QueryThreeResponse> responseObserver);
  }

  public static interface QueryThreeServiceBlockingClient {

    public com.example.Query.QueryThreeResponse getTopEconomicStates(com.example.Query.QueryThreeRequest request);
  }

  public static interface QueryThreeServiceFutureClient {

    public com.google.common.util.concurrent.ListenableFuture<com.example.Query.QueryThreeResponse> getTopEconomicStates(
        com.example.Query.QueryThreeRequest request);
  }

  public static class QueryThreeServiceStub extends io.grpc.stub.AbstractStub<QueryThreeServiceStub>
      implements QueryThreeService {
    private QueryThreeServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private QueryThreeServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryThreeServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new QueryThreeServiceStub(channel, callOptions);
    }

    @java.lang.Override
    public void getTopEconomicStates(com.example.Query.QueryThreeRequest request,
        io.grpc.stub.StreamObserver<com.example.Query.QueryThreeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_TOP_ECONOMIC_STATES, getCallOptions()), request, responseObserver);
    }
  }

  public static class QueryThreeServiceBlockingStub extends io.grpc.stub.AbstractStub<QueryThreeServiceBlockingStub>
      implements QueryThreeServiceBlockingClient {
    private QueryThreeServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private QueryThreeServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryThreeServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new QueryThreeServiceBlockingStub(channel, callOptions);
    }

    @java.lang.Override
    public com.example.Query.QueryThreeResponse getTopEconomicStates(com.example.Query.QueryThreeRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_GET_TOP_ECONOMIC_STATES, getCallOptions()), request);
    }
  }

  public static class QueryThreeServiceFutureStub extends io.grpc.stub.AbstractStub<QueryThreeServiceFutureStub>
      implements QueryThreeServiceFutureClient {
    private QueryThreeServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private QueryThreeServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryThreeServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new QueryThreeServiceFutureStub(channel, callOptions);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.example.Query.QueryThreeResponse> getTopEconomicStates(
        com.example.Query.QueryThreeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_TOP_ECONOMIC_STATES, getCallOptions()), request);
    }
  }

  public static io.grpc.ServerServiceDefinition bindService(
      final QueryThreeService serviceImpl) {
    return io.grpc.ServerServiceDefinition.builder(SERVICE_NAME)
      .addMethod(
        METHOD_GET_TOP_ECONOMIC_STATES,
        asyncUnaryCall(
          new io.grpc.stub.ServerCalls.UnaryMethod<
              com.example.Query.QueryThreeRequest,
              com.example.Query.QueryThreeResponse>() {
            @java.lang.Override
            public void invoke(
                com.example.Query.QueryThreeRequest request,
                io.grpc.stub.StreamObserver<com.example.Query.QueryThreeResponse> responseObserver) {
              serviceImpl.getTopEconomicStates(request, responseObserver);
            }
          })).build();
  }
}
