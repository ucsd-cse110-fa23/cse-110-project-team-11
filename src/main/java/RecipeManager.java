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
    public static final String CSV_FILE = "recipes.csv"; // recipe file to read from
    public static void main(String[] args) throws FileNotFoundException, IOException {
        deleteAll();
        insertRecipes();
    }

    public static void insertRecipes() throws IOException{
        String line;
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase recipeDB = mongoClient.getDatabase("recipes_db");
            MongoCollection<Document> recipeCollections = recipeDB.getCollection("recipes");

            FileReader fr = new FileReader(CSV_FILE); 
            // Convert fileReader to bufferedReader 
            BufferedReader br = new BufferedReader(fr); 
            br.readLine(); // skips first line
            line = br.readLine();
            StringTokenizer st = new StringTokenizer(line, ",");    
            Document recipe = new Document("_id", new ObjectId());
            recipe.append("title", st.nextToken())
            .append("ingredients", st.nextToken())
            .append("steps", st.nextToken());
            System.out.println(recipe);
            recipeCollections.insertOne(recipe);
            // while ((line = br.readLine()) != null) { 
            //     StringTokenizer st = new StringTokenizer(line, ",");
                
            //     Document recipe = new Document("_id", new ObjectId());
            //     recipe.append("title", st.nextToken())
            //     .append("ingredients", st.nextToken())
            //     .append("steps", st.nextToken());
            //     System.out.println(recipe);
            //     recipeCollections.insertOne(recipe);
            // }
            br.close();  //closes the BR
        }
    }

    /**
     * Updates the entries, given the recipe name
     */
    public static void updateHours(Document recipe) {
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase recipeDB = mongoClient.getDatabase("recipe_db");
            MongoCollection<Document> recipeCollections = recipeDB.getCollection("recipes");

            Bson filter = eq("recipe", "Savory Spinach Delight");
            Bson updateOperation = set("hours", 4.5);
            UpdateResult updateResult = recipeCollections.updateOne(filter, updateOperation);

            System.out.println("updating the recipe hours");
            System.out.println(recipeCollections.find(filter).first().toJson());
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
