package com.example;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EduCostStatQueryFiveDAO {

    private final String databaseName = "assignment-2";
    private final String mainCollectionName = "nces-collection";
    private final String queryCollectionName = "EduCostStatQueryFive";
    private final String mongoURL = "mongodb+srv://ernestinyama-2:2aRBRLPi9AnzLZ8E@assignment-2-practice.jwf37e8.mongodb.net/?retryWrites=true&w=majority&appName=Assignment-2-practice";

    @SuppressWarnings("unchecked")
	public Map<String, Double> query(int year, String type, String length) {
        // Check if the query result exists in the query collection
        Document existingQuery = findQuery(year, type, length);
        if (existingQuery != null) {
            return existingQuery.get("regionAverages", Map.class);
        }

        // Query the main collection and aggregate region averages
        Map<String, Double> regionAverages = queryMainCollection(year, type, length);

        // Save the query result in the query collection
        saveQuery(year, type, length, regionAverages);

        return regionAverages;
    }

    private Document findQuery(int year, String type, String length) {
        // Create a MongoDB client
        try (MongoClient mongoClient = MongoClients.create(mongoURL)) {
            // Access the database
            MongoDatabase database = mongoClient.getDatabase(databaseName);

            // Access the query collection
            MongoCollection<Document> collection = database.getCollection(queryCollectionName);

            // Query for the document with the given parameters
            return collection.find(new Document("year", year)
                    .append("type", type)
                    .append("length", length)).first();
        }
    }

    private Map<String, Double> queryMainCollection(int year, String type, String length) {
        // Create a MongoDB client
        try (MongoClient mongoClient = MongoClients.create(mongoURL)) {
            // Access the database
            MongoDatabase database = mongoClient.getDatabase(databaseName);

            // Access the main collection
            MongoCollection<Document> collection = database.getCollection(mainCollectionName);

            // Create the pipeline for aggregation
            List<Document> pipeline = new ArrayList<>();

            // Match documents based on the provided parameters
            Document matchStage = new Document("$match",
                    new Document("Year", year)
                            .append("Type", type)
                            .append("Length", length));
            pipeline.add(matchStage);

            // Add region field to each document based on state-to-region mapping
            Document addFieldsStage = new Document("$addFields",
                    new Document("Region",
                            new Document("$switch", new Document("branches",
                                    createStateToRegionBranches()).append("default", "Others"))));
            pipeline.add(addFieldsStage);

            // Group documents by region and calculate average expense
            Document groupStage = new Document("$group",
                    new Document("_id", "$Region")
                            .append("avgExpense", new Document("$avg", "$Value")));
            pipeline.add(groupStage);

            // Execute the aggregation pipeline
            AggregateIterable<Document> aggregateResult = collection.aggregate(pipeline);

            // Convert aggregate results to a map of region averages
            Map<String, Double> regionAverages = new HashMap<>();
            for (Document doc : aggregateResult) {
                regionAverages.put(doc.getString("_id"), doc.getDouble("avgExpense"));
            }
            return regionAverages;
        }
    }

    private void saveQuery(int year, String type, String length, Map<String, Double> regionAverages) {
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
                    .append("regionAverages", regionAverages);

            // Insert the query result document into the query collection
            collection.insertOne(queryResult);
        }
    }
    
    private List<Document> createStateToRegionBranches() {
        List<Document> branches = new ArrayList<>();
        // Add branches for each state and its corresponding region
        branches.add(new Document("case", new Document("$eq", List.of("$State", "Alabama")))
                .append("then", "Southeast"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "Alaska")))
                .append("then", "West"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "Arizona")))
                .append("then", "West"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "Arkansas")))
                .append("then", "South"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "California")))
                .append("then", "West"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "Colorado")))
                .append("then", "West"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "Connecticut")))
                .append("then", "Northeast"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "Delaware")))
                .append("then", "Northeast"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "Florida")))
                .append("then", "Southeast"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "Georgia")))
                .append("then", "Southeast"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "Hawaii")))
                .append("then", "West"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "Idaho")))
                .append("then", "West"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "Illinois")))
                .append("then", "Midwest"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "Indiana")))
                .append("then", "Midwest"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "Iowa")))
                .append("then", "Midwest"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "Kansas")))
                .append("then", "Midwest"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "Kentucky")))
                .append("then", "South"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "Louisiana")))
                .append("then", "South"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "Maine")))
                .append("then", "Northeast"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "Maryland")))
                .append("then", "Northeast"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "Massachusetts")))
                .append("then", "Northeast"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "Michigan")))
                .append("then", "Midwest"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "Minnesota")))
                .append("then", "Midwest"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "Mississippi")))
                .append("then", "South"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "Missouri")))
                .append("then", "Midwest"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "Montana")))
                .append("then", "West"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "Nebraska")))
                .append("then", "Midwest"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "Nevada")))
                .append("then", "West"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "New Hampshire")))
                .append("then", "Northeast"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "New Jersey")))
                .append("then", "Northeast"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "New Mexico")))
                .append("then", "West"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "New York")))
                .append("then", "Northeast"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "North Carolina")))
                .append("then", "Southeast"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "North Dakota")))
                .append("then", "Midwest"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "Ohio")))
                .append("then", "Midwest"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "Oklahoma")))
                .append("then", "South"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "Oregon")))
                .append("then", "West"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "Pennsylvania")))
                .append("then", "Northeast"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "Rhode Island")))
                .append("then", "Northeast"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "South Carolina")))
                .append("then", "Southeast"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "South Dakota")))
                .append("then", "Midwest"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "Tennessee")))
                .append("then", "South"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "Texas")))
                .append("then", "Southwest"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "Utah")))
                .append("then", "West"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "Vermont")))
                .append("then", "Northeast"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "Virginia")))
                .append("then", "Southeast"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "Washington")))
                .append("then", "West"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "West Virginia")))
                .append("then", "South"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "Wisconsin")))
                .append("then", "Midwest"));
        branches.add(new Document("case", new Document("$eq", List.of("$State", "Wyoming")))
                .append("then", "West"));
        // Add branches for other states...
        return branches;
    }

}
