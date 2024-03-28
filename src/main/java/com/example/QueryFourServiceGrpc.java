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
public class QueryFourServiceGrpc {

  private QueryFourServiceGrpc() {}

  public static final String SERVICE_NAME = "com.example.QueryFourService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("This feature is experimental and subject to change.")
  public static final MethodDescriptor<com.example.Query.QueryFourRequest,
  com.example.Query.QueryFourResponse> METHOD_GET_TOP_GROWTH_STATES =
  MethodDescriptor.<com.example.Query.QueryFourRequest, com.example.Query.QueryFourResponse>newBuilder()
          .setType(MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName("com.example.QueryFourService", "GetTopGrowthStates"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(com.example.Query.QueryFourRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(com.example.Query.QueryFourResponse.getDefaultInstance()))
          .build();

  public static QueryFourServiceStub newStub(io.grpc.Channel channel) {
    return new QueryFourServiceStub(channel);
  }

  public static QueryFourServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new QueryFourServiceBlockingStub(channel);
  }

  public static QueryFourServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new QueryFourServiceFutureStub(channel);
  }

  public static interface QueryFourService {

    public void getTopGrowthStates(com.example.Query.QueryFourRequest request,
        io.grpc.stub.StreamObserver<com.example.Query.QueryFourResponse> responseObserver);
  }

  public static interface QueryFourServiceBlockingClient {

    public com.example.Query.QueryFourResponse getTopGrowthStates(com.example.Query.QueryFourRequest request);
  }

  public static interface QueryFourServiceFutureClient {

    public com.google.common.util.concurrent.ListenableFuture<com.example.Query.QueryFourResponse> getTopGrowthStates(
        com.example.Query.QueryFourRequest request);
  }

  public static class QueryFourServiceStub extends io.grpc.stub.AbstractStub<QueryFourServiceStub>
      implements QueryFourService {
    private QueryFourServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private QueryFourServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryFourServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new QueryFourServiceStub(channel, callOptions);
    }

    @java.lang.Override
    public void getTopGrowthStates(com.example.Query.QueryFourRequest request,
        io.grpc.stub.StreamObserver<com.example.Query.QueryFourResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_TOP_GROWTH_STATES, getCallOptions()), request, responseObserver);
    }
  }

  public static class QueryFourServiceBlockingStub extends io.grpc.stub.AbstractStub<QueryFourServiceBlockingStub>
      implements QueryFourServiceBlockingClient {
    private QueryFourServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private QueryFourServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryFourServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new QueryFourServiceBlockingStub(channel, callOptions);
    }

    @java.lang.Override
    public com.example.Query.QueryFourResponse getTopGrowthStates(com.example.Query.QueryFourRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_GET_TOP_GROWTH_STATES, getCallOptions()), request);
    }
  }

  public static class QueryFourServiceFutureStub extends io.grpc.stub.AbstractStub<QueryFourServiceFutureStub>
      implements QueryFourServiceFutureClient {
    private QueryFourServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private QueryFourServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QueryFourServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new QueryFourServiceFutureStub(channel, callOptions);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.example.Query.QueryFourResponse> getTopGrowthStates(
        com.example.Query.QueryFourRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_TOP_GROWTH_STATES, getCallOptions()), request);
    }
  }

  public static io.grpc.ServerServiceDefinition bindService(
      final QueryFourService serviceImpl) {
    return io.grpc.ServerServiceDefinition.builder(SERVICE_NAME)
      .addMethod(
        METHOD_GET_TOP_GROWTH_STATES,
        asyncUnaryCall(
          new io.grpc.stub.ServerCalls.UnaryMethod<
              com.example.Query.QueryFourRequest,
              com.example.Query.QueryFourResponse>() {
            @java.lang.Override
            public void invoke(
                com.example.Query.QueryFourRequest request,
                io.grpc.stub.StreamObserver<com.example.Query.QueryFourResponse> responseObserver) {
              serviceImpl.getTopGrowthStates(request, responseObserver);
            }
          })).build();
  }
}
