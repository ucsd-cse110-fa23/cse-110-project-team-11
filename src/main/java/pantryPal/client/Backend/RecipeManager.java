package pantryPal.client.Backend;
/**
 * RecipeManager.java should handle the functions that change our database in MongoDB.
 * Should handle delete recipe, insert recipe, save recipe (after editing).
 */

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import java.io.*;
import static com.mongodb.client.model.Filters.eq;

import org.json.JSONArray;  

/**
 * Class that handles the MongoDB. essentially creates functionality to buttons
 * in terms of backend.
 */

public class RecipeManager {
    public static final String URI = "mongodb+srv://hek007:7GVnvvaUfbPZsgnq@recipemanager.ksn9u3g.mongodb.net/?retryWrites=true&w=majority";
    public static ObjectId id = new ObjectId();
    public static String stringID;

    MongoClient mongoClient = MongoClients.create(URI);
    MongoDatabase recipeDB;
    MongoCollection<Document> recipeCollections;


    public static String getStringID() {
        return stringID;
    }

    /**
     * Retrieves data from MongoDB and returns it into the HomePage to display.
     * @return recipe list
     */
    public static JSONArray loadRecipes(String username){
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase recipeDB = mongoClient.getDatabase("recipes_db");
            MongoCollection<Document> recipeCollections = recipeDB.getCollection(username);

            JSONArray recipes = new JSONArray();
            try (MongoCursor<Document> cursor = recipeCollections.find().iterator()) {
                while (cursor.hasNext()) {
                    System.out.println("loading");
                    Document document = cursor.next();
                    recipes.put(document.toJson());
                }
            }
            return recipes;
        }
        
        
    }
    
    /**
     * Updates the entries, given the recipe name
     * -> Get id for the recipe being edited, 
     * -> edit (title, ingredient, steps) 3 entries.
     * -> Take all entries, and update 
     *
     * save_button() {
     *      string = title;
     *      string = ingredidentes;
     *       ...
     *      updateRecipe(id, title, ing, steps)
     * }
     * @param recipe
     * @throws IOException
     * 
     */
    public static UpdateResult updateRecipe(String username, String mealType, 
    String title, String ingredients, String steps, String imgURL, String id) throws IOException {
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase recipeDB = mongoClient.getDatabase("recipes_db");
            MongoCollection<Document> recipeCollections = recipeDB.getCollection(username);
    
            ObjectId objId = new ObjectId(id);

            // Define the filter to find the document
            Document filter = new Document("_id", objId);

            // Combine all updates into one operation
            Document updates = new Document("$set",
                    new Document("title", title)
                            .append("ingredients", ingredients)
                            .append("steps", steps)
                            .append("imageURL", imgURL)
                            .append("mealType", mealType)
            );

            UpdateOptions options = new UpdateOptions().upsert(true);

            // Perform the update
            UpdateResult result = recipeCollections.updateOne(filter, updates, options);
    
            mongoClient.close();
            return result;
        }
    }

    /**
     * Deletes one recipe, given id
     * @param id recipe to delete
     */
    public static DeleteResult deleteRecipeByID(String username, String id) 
    throws IOException {
        ObjectId objID = new ObjectId(id);
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase recipeDB = mongoClient.getDatabase("recipes_db");
            MongoCollection<Document> recipeCollections = recipeDB.getCollection(username);
            System.out.println("opened mongoDB (delete recipe)");
            Bson filter = eq("_id", objID);
            DeleteResult result = recipeCollections.deleteOne(filter);

            // check to see if something got deleted
            if (result.getDeletedCount() > 0) {
                System.out.println("document deleted: " + result);
            } else {
                System.out.println("no document found to delete");
            }
            return result;
        }
    }

    /**
     * Deletes one recipe, given a name
     * @param title recipe to delete
     */
    public static DeleteResult deleteRecipe(String username, String title) throws IOException {
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase recipeDB = mongoClient.getDatabase("recipes_db");
            MongoCollection<Document> recipeCollections = recipeDB.getCollection(username);
            System.out.println("opened mongoDB (delete recipe)");
            Bson filter = eq("title", title);
            DeleteResult result = recipeCollections.deleteOne(filter);
            System.out.println("delete: " + result);
            return result;
        }
    }
    public static Document searchRecipeByID(String username, String id) {
        ObjectId objID = new ObjectId(id);
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase recipeDB = mongoClient.getDatabase("recipes_db");
            MongoCollection<Document> recipeCollections = recipeDB.getCollection(username);
            
            // filter based on id
            Bson filter = eq("_id", objID);
            Document doc = recipeCollections.find(filter).first();
            if(doc != null) {
                System.out.println("found: " + doc.toJson());
                return doc;

            }
            else {
                System.out.println("doc not found");
                return null;
            }
        }
    }

    /**
     * Search for the id object and returns the Object of the id. should be a
     * helper method
     * @param title
     * @return document of id 
     */
    public static Document searchRecipe(String username, String title) {
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase recipeDB = mongoClient.getDatabase("recipes_db");
            MongoCollection<Document> recipeCollections = recipeDB.getCollection(username);
            
            // filter based on id
            Bson filter = eq("title", title);
            Document doc = recipeCollections.find(filter).first();
            if(doc != null) {
                System.out.println("found: " + doc.toJson());
                return doc;

            }
            else {
                System.out.println("doc not found");
                return null;
            }
        }
    }

    /**
     * helper method to delete all recipes? currently not used.
     */
    public static void deleteAll() {
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase recipeDB = mongoClient.getDatabase("recipe_db");
            MongoCollection<Document> recipeCollections = recipeDB.getCollection("recipes");

            recipeCollections.deleteMany(new Document());
        }
    }   
}
