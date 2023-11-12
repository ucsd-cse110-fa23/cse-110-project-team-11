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

    public static ArrayList loadRecipes(){

        ArrayList<RecipeTitle> recipes = new ArrayList<RecipeTitle>();
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase recipeDB = mongoClient.getDatabase("recipes_db");
            MongoCollection<Document> recipeCollections = recipeDB.getCollection("recipes");

            try (MongoCursor<Document> cursor = recipeCollections.find().iterator()) {
                while (cursor.hasNext()) {
                    Document document = cursor.next();
                    String stringID = document.get("_id").toString();
                    String title = document.get("title").toString();
                    String ingredients = document.get("ingredients").toString();
                    String steps = document.get("steps").toString();
                    RecipeDisplay recipeDisplay = new RecipeDisplay(stringID, title, ingredients, steps);
                    RecipeDisplayAppFrame rec = new RecipeDisplayAppFrame(recipeDisplay);
                    RecipeTitle recipeTitle = new RecipeTitle(stringID, title, rec);
                    recipes.add(recipeTitle);
                }
                mongoClient.close();
            }
        }
        return recipes;
    }
    /**
     * inserts recipe, gets instance variables from parser
     */
    public static RecipeTitle insertRecipe(String title, String ingredients, String steps) throws IOException{
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase recipeDB = mongoClient.getDatabase("recipes_db");
            MongoCollection<Document> recipeCollections = recipeDB.getCollection("recipes");

            // recipe details: id, title, ingredients, steps
            ObjectId objectID = new ObjectId();
            stringID = objectID.toString();
            Document recipe = new Document("_id", objectID);
            recipe.append("title", title)
            .append("ingredients", ingredients)
            .append("steps", steps);
            
            //System.out.println(recipe);
            recipeCollections.insertOne(recipe); // inserts into MongoDB
            RecipeDisplay recipeDisplay = new RecipeDisplay(stringID, title, ingredients, steps);
            RecipeDisplayAppFrame rec = new RecipeDisplayAppFrame(recipeDisplay);
            RecipeTitle recipeTitle = new RecipeTitle(stringID, title, rec);
            System.out.println("Insert successful");
            mongoClient.close();
            return recipeTitle;
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
    public static void updateRecipe(String id, String title, String ingredients, String steps) throws IOException {
            ObjectId objID = new ObjectId(id); // wrap in ObjectID
            try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase recipeDB = mongoClient.getDatabase("recipe_db");
            MongoCollection<Document> recipeCollections = recipeDB.getCollection("recipes");

            // search for old recipe in database using the ID and get the titel, ing, steps
            Document recipe = searchRecipe(title);
            
            // Document recipe = recipeCollections.find(new Document("title", title)).first();
            if (recipe == null) {
                System.out.println("hello2");
                return;
            }
            // compare if they are different to properly update
            String oldTitle = recipe.getString("title");
            String oldIngredients = recipe.getString("ingredients");
            String oldSteps = recipe.getString("steps");
            System.out.println(steps);
            if (!oldTitle.equals(title)) { // if title is updated
                Bson filter = eq("_id", id);
                Bson updateOperation = set("title", title);
                UpdateResult updateResult = recipeCollections.updateOne(filter, updateOperation);
                System.out.println("updated title from " + oldTitle + "to " + title);
            }
            if (!oldIngredients.equals(ingredients)) { // if ingredients is updated
                Bson filter = eq("_id", id);
                Bson updateOperation = set("ingredients", ingredients);
                UpdateResult updateResult = recipeCollections.updateOne(filter, updateOperation);
                System.out.println("updated title from " + oldIngredients + "to " + ingredients);
            }
            if (!oldSteps.equals(steps)) {
                Bson filter = eq("_id", id);
                Bson updateOperation = set("steps", steps);
                UpdateResult updateResult = recipeCollections.updateOne(filter, updateOperation);
                System.out.println("updated title from " + oldSteps + "to " + steps);
            }
            mongoClient.close();
        }
        
    }

    /**
     * Deletes one recipe, given a name
     * @param recipeName recipe to delete
     */
    public static void deleteRecipe(String title) {
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase recipeDB = mongoClient.getDatabase("recipe_db");
            MongoCollection<Document> recipeCollections = recipeDB.getCollection("recipes");
            Bson filter = eq("title", title);
            DeleteResult result = recipeCollections.deleteOne(filter);

            // delete one document
            System.out.println(result);
            // ObjectId objectID = new ObjectId(id);
            // Document document = searchRecipe(title);
            //delete from MongoDB
            recipeCollections.deleteOne(filter);
            // //delete from App
            // String title = document.get("title").toString();
            // String ingredients = document.get("ingredients").toString();
            // String steps = document.get("steps").toString();
            // RecipeDisplay recipe = new RecipeDisplay(id, title, ingredients, steps);

            // HomePageAppFrame.getRecipeList().getChildren().remove(recipe);
            mongoClient.close();
        }
    }

    /**
     * Search for the id object and returns the Object of the id. should be a
     * helper method
     * @param id
     * @return document of id 
     */
    public static Document searchRecipe(String title) {
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase recipeDB = mongoClient.getDatabase("recipe_db");
            MongoCollection<Document> recipeCollections = recipeDB.getCollection("recipes");

            
            // filter based on id
            Bson filter = eq("title", title);
            Document query = new Document("title", title);
            Document document = recipeCollections.find(filter).first();
            if (document != null) {
                System.out.println("Recipe found: " + document.toJson());
                return document;
            } else {
                System.out.println("Recipe not found: " + title);
                return null;
            }
            /*

            try (MongoCursor<Document> cursor = recipeCollections.find().iterator()) {
                while(cursor.hasNext()) {
                    Document doc = cursor.next();
                    if(doc.get("title") == title) {
                        System.out.println("HOLY FUCK");
                        return doc;
                    }
                }
            }
            System.out.println("NOOOOOOO");
            */
            // mongoClient.close();
            //return recipeCollections.find(filter).first();
            // //System.out.println("recipe found: " + title);
            // System.out.println("HI PLS PRINT");
            // System.out.println(recipeCollections.find(eq("title", title)).first());
            // //System.out.println(recipeCollections.find(filter).first());
            // return recipeCollections.find(filter).first();
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
