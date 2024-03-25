package com.example;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class EduCostStatQueryFourDAO {

    private final String databaseName = "your_database_name";
    private final String mainCollectionName = "nces-collection";
    private final String queryCollectionName = "EduCostStatQueryFour";
    private final String mongoURL = "mongodb://localhost:27017";

    public List<String> query(int yearsRange, String type, String length) {
        // Get the latest year from the dataset
        int latestYear = getLatestYear();

        // Calculate the start year based on the range
        int startYear = latestYear - yearsRange + 1;

        // Check if the query result exists in the query collection
        List<String> existingQuery = findQuery(yearsRange, type, length);
        if (!existingQuery.isEmpty()) {
            return existingQuery;
        }

        // Query the main collection and compute growth rates
        List<String> queryResult = queryMainCollection(startYear, latestYear, type, length);
        if (!queryResult.isEmpty()) {
            // Save the query result in the query collection
            saveQuery(yearsRange, type, length, queryResult);
        }

        return queryResult;
    }

    @SuppressWarnings("unchecked")
	private List<String> findQuery(int yearsRange, String type, String length) {
        // Create a MongoDB client
        try (MongoClient mongoClient = MongoClients.create(mongoURL)) {
            // Access the database
            MongoDatabase database = mongoClient.getDatabase(databaseName);

            // Access the query collection
            MongoCollection<Document> collection = database.getCollection(queryCollectionName);

            // Query for the document with the given parameters
            Document query = new Document()
                    .append("yearsRange", yearsRange)
                    .append("type", type)
                    .append("length", length);

            Document resultDoc = collection.find(query).first();
            if (resultDoc != null) {
                return resultDoc.get("states", List.class);
            }
        }
        return new ArrayList<>();
    }

    private List<String> queryMainCollection(int startYear, int endYear, String type, String length) {
        // Create a MongoDB client
        try (MongoClient mongoClient = MongoClients.create(mongoURL)) {
            // Access the database
            MongoDatabase database = mongoClient.getDatabase(databaseName);

            // Access the main collection
            MongoCollection<Document> collection = database.getCollection(mainCollectionName);

            // Aggregate to calculate growth rates
            Document matchStage = new Document("$match",
                    new Document("Year", new Document("$gte", startYear).append("$lte", endYear))
                            .append("Type", type)
                            .append("Length", length));
            Document groupStage = new Document("$group",
                    new Document("_id", "$State")
                            .append("totalExpense", new Document("$sum", "$Value")));
            Document sortStage = new Document("$sort",
                    new Document("totalExpense", -1));
            Document limitStage = new Document("$limit", 5);

            AggregateIterable<Document> aggregateResult = collection.aggregate(List.of(matchStage, groupStage, sortStage, limitStage));

            List<String> states = new ArrayList<>();
            for (Document doc : aggregateResult) {
                states.add(doc.getString("_id"));
            }
            return states;
        }
    }

    private void saveQuery(int yearsRange, String type, String length, List<String> states) {
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
                    .append("yearsRange", yearsRange)
                    .append("type", type)
                    .append("length", length)
                    .append("states", states);

            // Insert the query result document into the query collection
            collection.insertOne(queryResult);
        }
    }

    private int getLatestYear() {
        // Create a MongoDB client
        try (MongoClient mongoClient = MongoClients.create(mongoURL)) {
            // Access the database
            MongoDatabase database = mongoClient.getDatabase(databaseName);

            // Access the main collection
            MongoCollection<Document> collection = database.getCollection(mainCollectionName);

            // Find the latest year in the dataset
            Document sortStage = new Document("$sort", new Document("Year", -1));
            Document limitStage = new Document("$limit", 1);

            AggregateIterable<Document> aggregateResult = collection.aggregate(List.of(sortStage, limitStage));

            // Extract the latest year from the result
            for (Document doc : aggregateResult) {
                return doc.getInteger("Year");
            }
        }
        return -1;
    }
}

