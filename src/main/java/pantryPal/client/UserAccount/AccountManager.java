/**
 * AccountManager.java should be take care of adding/changing
 * user account stuff around in the MongoDB.
 */

package pantryPal.client.UserAccount;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import static com.mongodb.client.model.Filters.eq;

/**
 * MongoDB operations for users: search, delete, insert
 */
public class AccountManager {
    public static final String URI = "mongodb+srv://hek007:7GVnvvaUfbPZsgnq@recipemanager.ksn9u3g.mongodb.net/?retryWrites=true&w=majority";

    public static String[] insertAccount(String username, String password) {
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase recipeDB = mongoClient.getDatabase("recipes_db");
            MongoCollection<Document> userCollections = recipeDB.getCollection("users");

            // find account
            if (searchAccount(username) != null) { // if account exists {
                return null;
                // TODO: display account already exists
            }
            Document user = new Document();
            user.append("username", username)
            .append("password", password);
            
            userCollections.insertOne(user); // inserts into MongoDB
            System.out.println("Inserted user account into MongoDB");
            mongoClient.close();
            return new String[] {username, password};
        }
    }

    /**
     * called from login button
     * if account does not exist
     */
    public static Document searchAccount(String username) {
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase recipeDB = mongoClient.getDatabase("recipes_db");
            MongoCollection<Document> userCollections = recipeDB.getCollection("users");

            // Bson filter = eq("username", username);
            Bson filter = eq("username", username);
            //Bson filter = Filter.text();
            // Bson update = set("username", username);
            Document doc = userCollections.find(filter).first();
            if (doc != null) {
                System.out.println("found");
            }
            else
                System.out.println("not found");
            mongoClient.close();
            return doc;
        }
    }

    public static int deleteAccount (String username, String password) {
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase recipeDB = mongoClient.getDatabase("recipes_db");
            MongoCollection<Document> userCollections = recipeDB.getCollection("users");

            // find account
            if (searchAccount(username) != null) { // if account exists {
                Document user = new Document();
                user.append("username", username)
                    .append("password", password);
                userCollections.deleteOne(user);
                recipeDB.getCollection("xd").drop();
                System.out.println("Deleted user account from MongoDB");
                mongoClient.close();
                return 1;
            }
            System.out.println("Account not found");
            return 0;
        }
    }
}