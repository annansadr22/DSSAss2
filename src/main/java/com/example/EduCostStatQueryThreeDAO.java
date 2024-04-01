package com.example;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class EduCostStatQueryThreeDAO {

    private final String databaseName = "assignment-2";
    private final String mainCollectionName = "nces-collection";
    private final String queryCollectionName = "EduCostStatQueryThree";
    private final String mongoURL = "mongodb+srv://ernestinyama-2:2aRBRLPi9AnzLZ8E@assignment-2-practice.jwf37e8.mongodb.net/?retryWrites=true&w=majority&appName=Assignment-2-practice";

    public List<String> query(int year, String type, String length) {
        // Check if the query result exists in the query collection
        List<String> existingQuery = findQuery(year, type, length);
        if (!existingQuery.isEmpty()) {
            return existingQuery;
        }

        // Query the main collection and aggregate to get the top 5 most economic states
        List<String> queryResult = queryMainCollection(year, type, length);
        if (!queryResult.isEmpty()) {
            // Save the query result in the query collection
            saveQuery(year, type, length, queryResult);
        }

        return queryResult;
    }

    @SuppressWarnings("unchecked")
	private List<String> findQuery(int year, String type, String length) {
        // Create a MongoDB client
        try (MongoClient mongoClient = MongoClients.create(mongoURL)) {
        	
            // Access the database
            MongoDatabase database = mongoClient.getDatabase(databaseName);
            
            // Check if the collection exists, create it if it doesn't
            if (!collectionExists(database, queryCollectionName)) {
                // Create the collection
                database.createCollection(queryCollectionName);
            }

            // Access the query collection
            MongoCollection<Document> collection = database.getCollection(queryCollectionName);

            // Query for the document with the given parameters
            Document query = new Document()
                    .append("year", year)
                    .append("type", type)
                    .append("length", length);

            Document resultDoc = collection.find(query).first();
            if (resultDoc != null) {
                return resultDoc.get("states", List.class);
            }
        }
        return new ArrayList<>();
    }

    private List<String> queryMainCollection(int year, String type, String length) {
        // Create a MongoDB client
        try (MongoClient mongoClient = MongoClients.create(mongoURL)) {
            // Access the database
            MongoDatabase database = mongoClient.getDatabase(databaseName);

            // Access the main collection
            MongoCollection<Document> collection = database.getCollection(mainCollectionName);

            // Aggregate to group by states and sum up the values for the given year, type, and length
            Document matchStage = new Document("$match",
                    new Document("Year", year)
                            .append("Type", type)
                            .append("Length", length));
            Document groupStage = new Document("$group",
                    new Document("_id", "$State")
                            .append("totalExpense", new Document("$sum", "$Value")));
            Document sortStage = new Document("$sort",
                    new Document("totalExpense", 1));
            Document limitStage = new Document("$limit", 5);

            AggregateIterable<Document> aggregateResult = collection.aggregate(List.of(matchStage, groupStage, sortStage, limitStage));

            List<String> states = new ArrayList<>();
            for (Document doc : aggregateResult) {
                states.add(doc.getString("_id"));
            }
            return states;
        }
    }
    
    
    private boolean collectionExists(MongoDatabase database, String collectionName) {
        for (String name : database.listCollectionNames()) {
            if (name.equalsIgnoreCase(collectionName)) {
                return true;
            }
        }
        return false;
    }
    

    private void saveQuery(int year, String type, String length, List<String> states) {
        // Create a MongoDB client
        try (MongoClient mongoClient = MongoClients.create(mongoURL)) {
            // Access the database
            MongoDatabase database = mongoClient.getDatabase(databaseName);

            // Create the query collection if it doesn't exist
            database.createCollection(queryCollectionName);

            // Access the query collection
            MongoCollection<Document> collection = database.getCollection(queryCollectionName);

            // Create the query result document
            Document queryResult = new Document()
                    .append("year", year)
                    .append("type", type)
                    .append("length", length)
                    .append("states", states);

            // Insert the query result document into the query collection
            collection.insertOne(queryResult);
        }
    }
}

