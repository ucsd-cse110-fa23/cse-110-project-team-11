/**
 * AccountManager.java should be take care of adding/changing
 * user account stuff around in the MongoDB.
 */
package pantryPal.client.UserAccount;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.TextSearchOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import java.util.*;
import java.io.*;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;

/**
 * MongoDB operations for users: search, delete, insert
 */
public class AccountManager {
    public static final String URI = "mongodb+srv://hek007:7GVnvvaUfbPZsgnq@recipemanager.ksn9u3g.mongodb.net/?retryWrites=true&w=majority";

    public static void insertAccount(String userID, String username, String password) {
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase recipeDB = mongoClient.getDatabase("recipes_db");
            MongoCollection<Document> recipeCollections = recipeDB.getCollection("users");

            Document user = new Document("_id", userID);
            user.append("username", username)
            .append("password", password);
            
            recipeCollections.insertOne(user); // inserts into MongoDB
            System.out.println("Inserted user account into MongoDB");
            mongoClient.close();
        }
    }

    /**
     * called from login button
     * if account does not exist
     */
    public static void searchAccount(String username) {
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase recipeDB = mongoClient.getDatabase("recipe_db");
            MongoCollection<Document> recipeCollections = recipeDB.getCollection("users");
            
            TextSearchOptions options = new TextSearchOptions().caseSensitive(true); // makes search case sensitive

            // Bson filter = eq("username", username);
            Bson filter = Filters.text(username);
            Document doc = recipeCollections.find(filter).first();
            if(doc != null) {

            }
        }
    }
}