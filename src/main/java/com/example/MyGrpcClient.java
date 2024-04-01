package com.example;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

// import io.grpc.stub.StreamObserver;
import com.example.Query.QueryOneRequest;
import com.example.Query.QueryOneResponse;
import com.example.Query.QueryTwoRequest;
import com.example.Query.QueryTwoResponse;
import com.example.Query.QueryThreeRequest;
import com.example.Query.QueryThreeResponse;
import com.example.Query.QueryFourRequest;
import com.example.Query.QueryFourResponse;
import com.example.Query.QueryFiveRequest;
import com.example.Query.QueryFiveResponse;

public class MyGrpcClient {

    // Define the server address and port
    private static final String SERVER_ADDRESS = "151.145.35.98";
    private static final int SERVER_PORT = 8080;

    public static void main(String[] args) {
        // Create a channel to connect to the server
        ManagedChannel channel = ManagedChannelBuilder.forAddress(SERVER_ADDRESS, SERVER_PORT)
                .usePlaintext() // Disable SSL/TLS for simplicity
                .build();

        
        /******************  Create a stub for the QueryOneService ******************/
        QueryOneServiceGrpc.QueryOneServiceBlockingStub stubOne = QueryOneServiceGrpc.newBlockingStub(channel);

        // Prepare the request
        QueryOneRequest requestOne = QueryOneRequest.newBuilder()
                .setYear(2013)
                .setState("Alabama")
                .setType("Private")
                .setLength("4-year")
                .setExpense("Fees/Tuition")
                .build();
        
        try {
            // Send the request and receive the response
            QueryOneResponse response = stubOne.getCost(requestOne); // Use stub.getCost() here
            // Print the response
            System.out.println("Cost: " + response.getCost());
        } catch (StatusRuntimeException e) {
            // Handle the error
            System.out.println("Error: " + e.getStatus());
        }
        
        
        /******************  Create a stub for the QueryTwoService ******************/
        QueryTwoServiceGrpc.QueryTwoServiceBlockingStub stubTwo = QueryTwoServiceGrpc.newBlockingStub(channel);

        // Prepare the request
        QueryTwoRequest requestTwo = QueryTwoRequest.newBuilder()
                .setYear(2013)
                .setType("Private")
                .setLength("4-year")
                .build();
            
        try {
            // Send the request and receive the response
            QueryTwoResponse response = stubTwo.getTopStates(requestTwo); // Use stub.getTopStates() here
            // Print the response
            System.out.println("Expensive States: " + response.getTopStatesList());
        } catch (StatusRuntimeException e) {
            // Handle the error
            System.out.println("Error: " + e.getStatus());
        }
        
        
        /****************** Create a stub for the QueryThreeService ******************/
        QueryThreeServiceGrpc.QueryThreeServiceBlockingStub stubThree = QueryThreeServiceGrpc.newBlockingStub(channel);

        // Prepare the request
        QueryThreeRequest requestThree = QueryThreeRequest.newBuilder()
                .setYear(2013)
                .setType("Private")
                .setLength("4-year")
                .build();
            
        try {
            // Send the request and receive the response
            QueryThreeResponse response = stubThree.getTopEconomicStates(requestThree); // Use stub.getTopEconomicStates() here
            // Print the response
            System.out.println("Economic States: " + response.getTopStatesList());
        } catch (StatusRuntimeException e) {
            // Handle the error
            System.out.println("Error: " + e.getStatus());
        }

        
        /******************  Create a stub for the QueryFourService ******************/
        QueryFourServiceGrpc.QueryFourServiceBlockingStub stubFour = QueryFourServiceGrpc.newBlockingStub(channel);

        // Prepare the request
        QueryFourRequest requestFour = QueryFourRequest.newBuilder()
                .setYearRange(5)
                .setType("Private")
                .setLength("4-year")
                .build();
            
        try {
            // Send the request and receive the response
            QueryFourResponse response = stubFour.getTopGrowthStates(requestFour); // Use stub.getTopGrowthStates() here
            // Print the response
            System.out.println("Highiest Growth Rate States: " + response.getTopGrowthStatesList());
        } catch (StatusRuntimeException e) {
            // Handle the error
            System.out.println("Error: " + e.getStatus());
        }
        
        
        /******************  Create a stub for the QueryFiveService ******************/
        QueryFiveServiceGrpc.QueryFiveServiceBlockingStub stub = QueryFiveServiceGrpc.newBlockingStub(channel);

        // Prepare the request
        QueryFiveRequest request = QueryFiveRequest.newBuilder()
                .setYear(2013)
                .setType("Private")
                .setLength("4-year")
                .build();
            
        try {
            // Send the request and receive the response
            QueryFiveResponse response = stub.getRegionAverages(request); // Use stub.getRegionAverages() here
            // Print the response
            System.out.println("Region's Average Cost: " + response.getRegionAveragesMap());
        } catch (StatusRuntimeException e) {
            // Handle the error
            System.out.println("Error: " + e.getStatus());
        }
        

        // Shutdown the channel when done
        channel.shutdown();
    }
}

