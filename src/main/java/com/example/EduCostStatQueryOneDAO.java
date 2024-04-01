package com.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Optional;

public class EduCostStatQueryOneDAO {

    private final String databaseName = "assignment-2";
    private final String mainCollectionName = "nces-collection";
    private final String queryCollectionName = "EduCostStatQueryOne";
    private final String mongoURL = "mongodb+srv://ernestinyama-2:2aRBRLPi9AnzLZ8E@assignment-2-practice.jwf37e8.mongodb.net/?retryWrites=true&w=majority&appName=Assignment-2-practice";

    public Integer query(int year, String state, String type, String length, String expense) {
        // Check if the query result exists in the query collection
        Optional<Document> existingQuery = findQuery(year, state, type, length, expense);
        if (existingQuery.isPresent()) {
        	return existingQuery.get().getInteger("Value");
        }

        // Query the main collection
        Document queryResult = queryMainCollection(year, state, type, length, expense);
        if (queryResult != null) {
            // Save the query result in the query collection
            saveQuery(queryResult);
            // Return the cost from the query result
            return queryResult.getInteger("Value");
        }

        return null;
    }

    private Optional<Document> findQuery(int year, String state, String type, String length, String expense) {
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
                    .append("Year", year)
                    .append("State", state)
                    .append("Type", type)
                    .append("Length", length)
                    .append("Expense", expense);

            return Optional.ofNullable(collection.find(query).first());
        }
    }

    private Document queryMainCollection(int year, String state, String type, String length, String expense) {
        // Create a MongoDB client
        try (MongoClient mongoClient = MongoClients.create(mongoURL)) {
            // Access the database
            MongoDatabase database = mongoClient.getDatabase(databaseName);

            // Access the main collection
            MongoCollection<Document> collection = database.getCollection(mainCollectionName);

            // Query for the document with the given parameters
            Document query = new Document()
                    .append("Year", year)
                    .append("State", state)
                    .append("Type", type)
                    .append("Length", length)
                    .append("Expense", expense);

            return collection.find(query).first();
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

    private void saveQuery(Document queryResult) {
        // Create a MongoDB client
        try (MongoClient mongoClient = MongoClients.create(mongoURL)) {
            // Access the database
            MongoDatabase database = mongoClient.getDatabase(databaseName);

            // Create the query collection if it doesn't exist
            database.createCollection(queryCollectionName);

            // Access the query collection
            MongoCollection<Document> collection = database.getCollection(queryCollectionName);

            // Insert the query result document into the query collection
            collection.insertOne(queryResult);
        }
    }
}
