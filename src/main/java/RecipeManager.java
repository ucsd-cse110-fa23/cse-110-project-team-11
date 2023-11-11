import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
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

public class RecipeManager {
    public static final String URI = "mongodb+srv://hek007:7GVnvvaUfbPZsgnq@recipemanager.ksn9u3g.mongodb.net/?retryWrites=true&w=majority";

    public static void insertRecipe(String title, String ingredients, String steps) throws IOException{
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase recipeDB = mongoClient.getDatabase("recipes_db");
            MongoCollection<Document> recipeCollections = recipeDB.getCollection("recipes");

            // FileReader fr = new FileReader(CSV_FILE); 
            // // Convert fileReader to bufferedReader 
            // BufferedReader br = new BufferedReader(fr); 
            // br.readLine(); // skips first line
            // line = br.readLine();  
            Document recipe = new Document("_id", new ObjectId());
            recipe.append("title", title)
            .append("ingredients", ingredients)
            .append("steps", steps);
            System.out.println(recipe);
            recipeCollections.insertOne(recipe);
            System.out.println("Insert successful");
            // while ((line = br.readLine()) != null) { 
            //     StringTokenizer st = new StringTokenizer(line, ",");
                
            //     Document recipe = new Document("_id", new ObjectId());
            //     recipe.append("title", st.nextToken())
            //     .append("ingredients", st.nextToken())
            //     .append("steps", st.nextToken());
            //     System.out.println(recipe);
            //     recipeCollections.insertOne(recipe);
            // }
            //br.close();  //closes the BR
        }
    }
    /**
     * Search for the id object and returns the Object of the id
     * @param id
     * @return document of id 
     */
    public static Document searchRecipe(ObjectId id) {
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase recipeDB = mongoClient.getDatabase("recipe_db");
            MongoCollection<Document> recipeCollections = recipeDB.getCollection("recipes");
            
            // filter based on id
            Bson filter = eq("_id", id);
            return recipeCollections.find(filter).first();
        }
    }
    /**
     * Updates the entries, given the recipe name
     * -> Get id for the recipe being edited, 
     * -> edit (title, ingredient, steps ) 3 entries.
     * -> Take all entries, and update 
     */
    /**
     * done_button() {
     *      string = title;
     *      string = ingredidentes;
     *       ...
     *      updateRecipe(id, title, ing, steps)
     * }
     * @param recipe
     */
    public static void updateRecipe(ObjectId id, String title, String ingredients, String steps) {
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase recipeDB = mongoClient.getDatabase("recipe_db");
            MongoCollection<Document> recipeCollections = recipeDB.getCollection("recipes");

            // search for old recipe in database using the ID and get the titel, ing, steps
            Document recipe = searchRecipe(id);
            
            // compare if they are different to properly update
            String oldTitle = recipe.getString("title");
            String oldIngredients = recipe.getString("ingredients");
            String oldSteps = recipe.getString("steps");
            
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
        }
    }

    /**
     * Deletes one recipe, given a name
     * @param recipeName recipe to delete
     */
    public static void deleteRecipe(ObjectId id) {
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase recipeDB = mongoClient.getDatabase("recipe_db");
            MongoCollection<Document> recipeCollections = recipeDB.getCollection("recipes");
            Bson filter = eq("_id", id);
            DeleteResult result = recipeCollections.deleteOne(filter);
            // Bson filter = eq("recipe", "Savory Spinach Delight");
            // Bson updateOperation = set("hours", 4.5);
            // UpdateResult updateResult = recipeCollections.updateOne(filter, updateOperation);

            // System.out.println("updating the recipe hours");
            // System.out.println(recipeCollections.find(filter).first().toJson());

            // delete one document
            System.out.println(result);
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
