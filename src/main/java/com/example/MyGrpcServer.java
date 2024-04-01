package com.example;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import io.grpc.Status;

import com.example.Query.*;
import com.example.QueryFiveServiceGrpc.QueryFiveService;
import com.example.QueryOneServiceGrpc.QueryOneService;
import com.example.QueryThreeServiceGrpc.QueryThreeService;
import com.example.QueryTwoServiceGrpc.QueryTwoService;
import com.example.QueryFourServiceGrpc.QueryFourService;
import java.util.*;


public class MyGrpcServer {

    // Define the server address and port
    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 8080;

    public static void main(String[] args) throws Exception {
        // Create a new server instance
        Server server = ServerBuilder.forPort(PORT)
                .addService(QueryOneServiceGrpc.bindService(new QueryOneServiceImpl())) // Bind the service implementation
                .addService(QueryTwoServiceGrpc.bindService(new QueryTwoServiceImpl()))
                .addService(QueryThreeServiceGrpc.bindService(new QueryThreeServiceImpl()))
                .addService(QueryFourServiceGrpc.bindService(new QueryFourServiceImpl()))
                .addService(QueryFiveServiceGrpc.bindService(new QueryFiveServiceImpl()))
                .build();

        // Start the server
        server.start();

        // Print a message indicating that the server has started
        System.out.println("Server started, listening on " + SERVER_ADDRESS + ":" + PORT);

        // Await termination (optional)
        server.awaitTermination();
    }

    // Implementation of the QueryOneService
    public static class QueryOneServiceImpl implements QueryOneService {
        
        // Implement the GetCost RPC method
        @Override
        public void getCost(QueryOneRequest request, StreamObserver<QueryOneResponse> responseObserver) {
            // Retrieve data from DAO and perform necessary processing
            EduCostStatQueryOneDAO dao = new EduCostStatQueryOneDAO();
            Integer cost = dao.query(request.getYear(), request.getState(), request.getType(), request.getLength(), request.getExpense());

            if (cost != null) {
                // Create a response
                QueryOneResponse response = QueryOneResponse.newBuilder().setCost(cost).build();

                // Send the response back to the client
                responseObserver.onNext(response);

                // Complete the response stream
                responseObserver.onCompleted();
            } else {
                // If cost is null, handle the situation accordingly
                // For example, you can return an error response to the client
                responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("Cost does not exist").asRuntimeException());
            }
        }


    }
    
    // Implementation of the QueryTwoService
    public static class QueryTwoServiceImpl implements QueryTwoService {
    
	    // Implement the GetTopStates RPC method
	    @Override
	    public void getTopStates(QueryTwoRequest request, StreamObserver<QueryTwoResponse> responseObserver) {
	        // Retrieve data from DAO and perform necessary processing
	        EduCostStatQueryTwoDAO dao = new EduCostStatQueryTwoDAO();
	        List<String> topStates = dao.query(request.getYear(), request.getType(), request.getLength());
	
	        if (topStates != null && !topStates.isEmpty()) {
	            // Create a response
	            QueryTwoResponse response = QueryTwoResponse.newBuilder().addAllTopStates(topStates).build();
	
	            // Send the response back to the client
	            responseObserver.onNext(response);
	
	            // Complete the response stream
	            responseObserver.onCompleted();
	        } else {
	            // If the list of top states is null or empty, handle the situation accordingly
	            // For example, you can return an error response to the client
	            responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("Top states not found").asRuntimeException());
	        }
	    }
	    
    }
    
    
    // Implementation of the QueryThreeService
    public static class QueryThreeServiceImpl implements QueryThreeService {
    
	    // Implement the GetTopStates RPC method
	    @Override
	    public void getTopEconomicStates(QueryThreeRequest request, StreamObserver<QueryThreeResponse> responseObserver) {
	        // Retrieve data from DAO and perform necessary processing
	    	EduCostStatQueryThreeDAO dao = new EduCostStatQueryThreeDAO();
	    	
	        List<String> topStates = dao.query(request.getYear(), request.getType(), request.getLength());
	
	        if (topStates != null && !topStates.isEmpty()) {
	            // Create a response
	            QueryThreeResponse response = QueryThreeResponse.newBuilder().addAllTopStates(topStates).build();
	
	            // Send the response back to the client
	            responseObserver.onNext(response);
	
	            // Complete the response stream
	            responseObserver.onCompleted();
	        } else {
	            // If the list of top states is null or empty, handle the situation accordingly
	            // For example, you can return an error response to the client
	            responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("Economic states not found").asRuntimeException());
	        }
	    }
	    
    }
    
    // Implementation of the QueryFourService
    public static class QueryFourServiceImpl implements QueryFourService {
    
	    // Implement the GetTopStates RPC method
	    @Override
	    public void getTopGrowthStates(QueryFourRequest request, StreamObserver<QueryFourResponse> responseObserver) {
	        // Retrieve data from DAO and perform necessary processing
	    	EduCostStatQueryFourDAO dao = new EduCostStatQueryFourDAO();
	    	
	        List<String> topGrowthStates = dao.query(request.getYearRange(), request.getType(), request.getLength());
	
	        if (topGrowthStates != null && !topGrowthStates.isEmpty()) {
	            // Create a response
	            QueryFourResponse response = QueryFourResponse.newBuilder().addAllTopGrowthStates(topGrowthStates).build();
	
	            // Send the response back to the client
	            responseObserver.onNext(response);
	
	            // Complete the response stream
	            responseObserver.onCompleted();
	        } else {
	            // If the list of top states is null or empty, handle the situation accordingly
	            // For example, you can return an error response to the client
	            responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("Highiest Growth states not found").asRuntimeException());
	        }
	    }
	    
    }
    
    // Implementation of the QueryFiveService
    public static class QueryFiveServiceImpl implements QueryFiveService {
    
	    // Implement the GetTopStates RPC method
    	@Override
    	public void getRegionAverages(QueryFiveRequest request, StreamObserver<QueryFiveResponse> responseObserver) {
    	    // Retrieve data from DAO and perform necessary processing
    	    EduCostStatQueryFiveDAO dao = new EduCostStatQueryFiveDAO();

    	    Map<String, Double> regionAverages = dao.query(request.getYear(), request.getType(), request.getLength());

    	    if (regionAverages != null && !regionAverages.isEmpty()) {
    	        // Create a response builder
    	        QueryFiveResponse.Builder responseBuilder = QueryFiveResponse.newBuilder();

    	        // Iterate over the map entries and add them to the response
    	        for (Map.Entry<String, Double> entry : regionAverages.entrySet()) {
    	            responseBuilder.putRegionAverages(entry.getKey(), entry.getValue());
    	        }

    	        // Build the response
    	        QueryFiveResponse response = responseBuilder.build();

    	        // Send the response back to the client
    	        responseObserver.onNext(response);

    	        // Complete the response stream
    	        responseObserver.onCompleted();
    	    } else {
    	        // If the map of region averages is null or empty, handle the situation accordingly
    	        // For example, you can return an error response to the client
    	        responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("Region averages not found").asRuntimeException());
    	    }
    	}

	    
    }

}
