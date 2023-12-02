package pantryPal.client;
/**
 * RecipeManager.java should handle the functions that change our database in MongoDB.
 * Should handle delete recipe, insert recipe, save recipe (after editing).
 */

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import java.util.*;
import java.io.*;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;

import org.json.JSONArray;  
import org.json.JSONObject;


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
        
        // ArrayList<String[]> recipes = new ArrayList<String[]>();
        //     try (MongoCursor<Document> cursor = rm.getCollection().find().iterator()) {
        //         while (cursor.hasNext()) {
        //             System.out.println("loading");
        //             Document document = cursor.next();
        //             String stringID = document.get("_id").toString();
        //             String title = document.get("title").toString();
        //             String ingredients = document.get("ingredients").toString();
        //             String steps = document.get("steps").toString();
        //             String mealType = document.getString("mealType"); // Retrieve mealType
        //             String imgURL = document.get("imageURL").toString();
        //             String[] rec = {stringID, title, ingredients, steps, mealType, imgURL}; // Include mealType in the array
        //             recipes.add(0, rec);
        //         }
        //     }
        // return recipes;
    }
    
    /**
     * Inserts recipe into MongoDB. WE are assuming that recipe is does not exist.
     * @param title
     * @param ingredients
     * @param steps
     * @return recipe to be inserted
     * @throws IOException
     */
    public static String[] insertRecipe(String username, String mealType, String title, String ingredients, String steps, String imgURL) throws IOException{
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase recipeDB = mongoClient.getDatabase("recipes_db");
            MongoCollection<Document> recipeCollections = recipeDB.getCollection(username);

            // recipe details: id, title, ingredients, steps
            ObjectId objectID = new ObjectId();
            stringID = objectID.toString();
            
            Document recipe = new Document("_id", objectID);
            recipe.append("title", title)
            .append("ingredients", ingredients)
            .append("steps", steps)
            .append("mealType", mealType)
            .append("imageURL", imgURL);
            
            recipeCollections.insertOne(recipe); // inserts into MongoDB
            String[] rec = {stringID, title, ingredients, steps};
            
            System.out.println("Insert successful");
            mongoClient.close();
            return rec;
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
     * TODO: update adds an entry if it doesnt exist? might need to remove the insert recipe
     */
    public static UpdateResult updateRecipe(String username, String title, String ingredients, String steps, String id) throws IOException {
        ObjectId objID = new ObjectId(id);
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase recipeDB = mongoClient.getDatabase("recipes_db");
            MongoCollection<Document> recipeCollections = recipeDB.getCollection(username);
            System.out.println("Opened mongoDB? (update recipe)");
    
            Bson filter = eq("_id", objID);
            
            // Combine all updates into one operation
            Bson updates = combine(
                set("title", title), // Assuming you also want to update the title
                set("ingredients", ingredients),
                set("steps", steps)
            );
    
            UpdateResult result = recipeCollections.updateOne(filter, updates);
    
            mongoClient.close();
            return result;
        }
    }

    /**
     * Deletes one recipe, given id
     * @param id recipe to delete
     */
    public static DeleteResult deleteRecipeByID(String username, String id) throws IOException {
        ObjectId objID = new ObjectId(id);
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase recipeDB = mongoClient.getDatabase("recipes_db");
            MongoCollection<Document> recipeCollections = recipeDB.getCollection(username);
            System.out.println("opened mongoDB?");
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
     * TODO: try to remove this and just delete by ID from now on? idk
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

    // public static ArrayList<String[]> filterRecipes (String selectedMealType){
    //     ArrayList<String[]> recipes = new ArrayList<String[]>();

    //     try (MongoClient mongoClient = MongoClients.create(URI)) {
    //         MongoDatabase recipeDB = mongoClient.getDatabase("recipes_db");
    //         MongoCollection<Document> recipeCollections = recipeDB.getCollection("recipes");

    //         try (MongoCursor<Document> cursor = recipeCollections.find().iterator()) {
    //             while (cursor.hasNext()) {
    //                 System.out.println("loading");
    //                 Document document = cursor.next();
    //                 String stringID = document.get("_id").toString();
    //                 String title = document.get("title").toString();
    //                 String ingredients = document.get("ingredients").toString();
    //                 String steps = document.get("steps").toString();
    //                 String mealType = document.getString("mealType"); // Retrieve mealType
    //                 String imageURL = document.getString("imageURL");
    //                 if (selectedMealType.equals(mealType)) {
    //                     System.out.println("found a " + mealType);
    //                     String[] rec = {stringID, title, ingredients, steps, mealType, imageURL}; // Include mealType in the array
    //                     recipes.add(0, rec);
    //                 }
    //             }
    //             mongoClient.close();
    //         }
    //     }
    //     return recipes;
    // }

    // public static ArrayList<String[]> sortRecipes(String sort, String filterState){
    //     ArrayList<String[]> recipes = filterRecipes(filterState);
    //     /*
    //     if (sort.equals("A-Z")) {
    //         Collections.sort(recipes, new SortAlphabetically());
    //     }
    //     else if (sort.equals("Z-A")) {
    //         Collections.sort(recipes, new SortReverseAlphabetically());
    //     }
    //     */ 
    //     if (sort.equals("Reverse")) {
    //         Collections.reverse(recipes);
    //     }
    //     return recipes;
    // }
}
