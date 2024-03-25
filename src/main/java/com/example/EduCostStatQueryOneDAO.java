package com.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Optional;

public class EduCostStatQueryOneDAO {

    private final String databaseName = "your_database_name";
    private final String mainCollectionName = "nces-collection";
    private final String queryCollectionName = "EduCostStatQueryOne";
    private final String mongoURL = "mongodb://localhost:27017";

    public Double query(int year, String state, String type, String length, String expense) {
        // Check if the query result exists in the query collection
        Optional<Document> existingQuery = findQuery(year, state, type, length, expense);
        if (existingQuery.isPresent()) {
            return existingQuery.get().getDouble("cost");
        }

        // Query the main collection
        Document queryResult = queryMainCollection(year, state, type, length, expense);
        if (queryResult != null) {
            // Save the query result in the query collection
            saveQuery(queryResult);
            // Return the cost from the query result
            return queryResult.getDouble("Value");
        }

        return null;
    }

    private Optional<Document> findQuery(int year, String state, String type, String length, String expense) {
        // Create a MongoDB client
        try (MongoClient mongoClient = MongoClients.create(mongoURL)) {
            // Access the database
            MongoDatabase database = mongoClient.getDatabase(databaseName);

            // Access the query collection
            MongoCollection<Document> collection = database.getCollection(queryCollectionName);

            // Query for the document with the given parameters
            Document query = new Document()
                    .append("year", year)
                    .append("state", state)
                    .append("type", type)
                    .append("length", length)
                    .append("expense", expense);

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
