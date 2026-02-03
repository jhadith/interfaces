/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author Administrador
 */
public final class MongoDB {
    private static final String URI = "mongodb://localhost:27017";
    private static final String DB_NAME = "citas_db";

    private static final MongoClient CLIENT = MongoClients.create(URI);

    private MongoDB() {}

    public static MongoDatabase database() {
        return CLIENT.getDatabase(DB_NAME);
    }
}