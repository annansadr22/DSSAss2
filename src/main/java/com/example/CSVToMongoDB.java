package com.example;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CSVToMongoDB {

    public static void main(String[] args) {
        String connectionString = "mongodb+srv://ernestinyama-2:2aRBRLPi9AnzLZ8E@assignment-2-practice.jwf37e8.mongodb.net/?retryWrites=true&w=majority&appName=Assignment-2-practice";
        String csvFile = "nces330_20.csv";

        try {
            MongoClient mongoClient = MongoClients.create(connectionString);
            MongoDatabase database = mongoClient.getDatabase("assignment-2");

            // Set the collection name
            String userDefinedCollectionName = "nces-collection";

            // Check if the collection exists, if not, create it
            if (!database.listCollectionNames().into(new ArrayList<>()).contains(userDefinedCollectionName)) {
                database.createCollection(userDefinedCollectionName);
                System.out.println("Collection '" + userDefinedCollectionName + "' created successfully.");
            }

            MongoCollection<Document> collection = database.getCollection(userDefinedCollectionName);

            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            // Read the first line (headers)
            String[] headers = br.readLine().split(",");
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                Document doc = new Document();
                // Use column headers as keys for document fields
                for (int i = 0; i < fields.length; i++) {
                    doc.append(headers[i], fields[i]);
                }
                collection.insertOne(doc);
            }

            br.close();
            System.out.println("CSV data imported into MongoDB collection '" + userDefinedCollectionName + "' successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
