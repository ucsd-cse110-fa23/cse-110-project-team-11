/**
 * This file should handle the functions that change our database in MongoDB.
 * Should handle delete recipe, insert recipe, save recipe (after editing).
 * TODO: add functionality to those buttons to call these methods.
 */
import com.mongodb.client.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonWriterSettings;
import org.bson.types.ObjectId;
import java.util.*;
import java.io.*;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;

/**
 * Class that handles the MongoDB. essentially creates functionality to buttons
 * in terms of backend.
 */
public class RecipeManager {
    public static final String URI = "mongodb+srv://hek007:7GVnvvaUfbPZsgnq@recipemanager.ksn9u3g.mongodb.net/?retryWrites=true&w=majority";
    public static ObjectId id = new ObjectId();
    public static String stringID;

    public static String getStringID() {
        return stringID;
    }

    public static void loadRecipes() throws IOException {
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase recipeDB = mongoClient.getDatabase("recipes_db");
            MongoCollection<Document> recipeCollections = recipeDB.getCollection("recipes");

            try (MongoCursor<Document> cursor = recipeCollections.find().iterator()) {
                while (cursor.hasNext()) {
                    System.out.println("loading");
                    Document document = cursor.next();
                    String stringID = document.get("_id").toString();
                    String title = document.get("title").toString();
                    String ingredients = document.get("ingredients").toString();
                    String steps = document.get("steps").toString();
                    RecipeDisplayAppFrame displayRec = new RecipeDisplayAppFrame(new RecipeDisplay(stringID, title, ingredients, steps));
                    RecipeTitle recipeTitle = new RecipeTitle(stringID, title, displayRec);
                    HomePageAppFrame.getRecipeList().getChildren().add(recipeTitle);
                    // RecipeDisplay recipeDisplay = new RecipeDisplay(stringID, title, ingredients, steps);
                    // HomePageAppFrame.getRecipeList().getChildren().add(recipeDisplay);
                }
                mongoClient.close();
            }
        }
    }
    /**
     * inserts recipe, gets instance variables from parser
     */
    public static void insertRecipe(String title, String ingredients, String steps) throws IOException{
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase recipeDB = mongoClient.getDatabase("recipes_db");
            MongoCollection<Document> recipeCollections = recipeDB.getCollection("recipes");

            // recipe details: id, title, ingredients, steps
            ObjectId objectID = new ObjectId();
            stringID = objectID.toString();
            System.out.println("Steps:" + steps);
            Document recipe = new Document("_id", objectID);
            recipe.append("title", title)
            .append("ingredients", ingredients)
            .append("steps", steps);
            //System.out.println(recipe);
            recipeCollections.insertOne(recipe); // inserts into MongoDB
            RecipeDisplayAppFrame displayRec = new RecipeDisplayAppFrame(new RecipeDisplay(stringID, title, ingredients, steps));
            RecipeTitle recipeTitle = new RecipeTitle(stringID, title, displayRec);
            HomePageAppFrame.getRecipeList().getChildren().add(recipeTitle);
            System.out.println("Insert successful");
            mongoClient.close();
        }
    }
    
    /**
     * Updates the entries, given the recipe name
     * -> Get id for the recipe being edited, 
     * -> edit (title, ingredient, steps ) 3 entries.
     * -> Take all entries, and update 
     */
    /**
     * save_button() {
     *      string = title;
     *      string = ingredidentes;
     *       ...
     *      updateRecipe(id, title, ing, steps)
     * }
     * @param recipe
     * @throws IOException
     */
    public static void updateRecipe(String title, String ingredients, String steps) throws IOException {
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase recipeDB = mongoClient.getDatabase("recipes_db");
            MongoCollection<Document> recipeCollections = recipeDB.getCollection("recipes");
            System.out.println("opened mongoDB?");
            // back up; does not correct the indices
            // deleteRecipe(title);
            // insertRecipe(title, ingredients, steps);

            // update ingredients
            Bson filter = eq("title", title);
            Bson update = set("ingredients", ingredients);
            UpdateResult result = recipeCollections.updateOne(filter, update);
            System.out.println("update ingredients: " + result);
            // update steps
            update = set("steps", steps);
            result = recipeCollections.updateOne(filter, update);
            System.out.println("update steps: " + result);

            mongoClient.close();
        }
    }

    /**
     * Deletes one recipe, given a name
     * @param title recipe to delete
     */
    public static void deleteRecipe(String title) throws IOException {
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase recipeDB = mongoClient.getDatabase("recipes_db");
            MongoCollection<Document> recipeCollections = recipeDB.getCollection("recipes");
            System.out.println("opened mongoDB?");
            Bson filter = eq("title", title);
            DeleteResult result = recipeCollections.deleteOne(filter);
            System.out.println("delete: " + result);
        }
    }

    /**
     * Search for the id object and returns the Object of the id. should be a
     * helper method
     * @param title
     * @return document of id 
     */
    public static Document searchRecipe(String title) {
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase recipeDB = mongoClient.getDatabase("recipe_db");
            MongoCollection<Document> recipeCollections = recipeDB.getCollection("recipes");

            
            // filter based on id
            Bson filter = eq("title", title);
            Document doc = recipeCollections.find(filter).first();
            System.out.println("found: " + doc.toJson());
            return doc;
        }
    }

    /**
     * Helper to delete all recipe entries
     */
    public static void deleteAll() {
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase recipeDB = mongoClient.getDatabase("recipe_db");
            MongoCollection<Document> recipeCollections = recipeDB.getCollection("recipes");

            recipeCollections.deleteMany(new Document());
        }
    }
}
