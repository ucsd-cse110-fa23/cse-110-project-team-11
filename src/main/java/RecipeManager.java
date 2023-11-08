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
        insertRecipes();
    }

    public static void insertRecipes() throws IOException{
        String line;
        // try (MongoClient mongoClient = MongoClients.create(URI)) {
        //     MongoDatabase recipeDB = mongoClient.getDatabase("recipes_db");
        //     MongoCollection<Document> recipeCollections = recipeDB.getCollection("recipes");

        //     FileReader fr = new FileReader(CSV_FILE); 
        //     BufferedReader br = new BufferedReader(fr);
        //     boolean isFirstLine = true;
        //     while ((line = br.readLine()) != null) {
        //         if (isFirstLine) {
        //             isFirstLine = false;
        //             continue; 
        //         }
        
        //         int lastSemiColonIndex = line.lastIndexOf(',');
        //         if (lastSemiColonIndex == -1) {
        //             throw new IOException("Invalid line format: " + line);
        //         }
        
        //         String name = line.substring(0, line.indexOf(','));
        //         String description = line.substring(line.indexOf(',') + 1, lastSemiColonIndex);
        //         String hoursString = line.substring(lastSemiColonIndex + 1);
        //         //System.out.println(name);
        //         //System.out.println(description);
        //         //System.out.println(hoursString);
        
        //         double hours = Double.parseDouble(hoursString);
        
        //         Document doc = new Document("name", name)
        //                 .append("description", description)
        //                 .append("hours", hours);
        //         //recipeDocuments.add(doc);
        //         System.out.println(doc);
        //         recipeCollections.insertOne(doc);
        //     }
        //     br.close();
        //     mongoClient.close();
        // }
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase recipeDB = mongoClient.getDatabase("recipes_db");
            MongoCollection<Document> recipeCollections = recipeDB.getCollection("recipes");

            FileReader fr = new FileReader(CSV_FILE); 
            // Convert fileReader to bufferedReader 
            BufferedReader br = new BufferedReader(fr); 
            br.readLine(); // skips first line
            while ((line = br.readLine()) != null) { 
                StringTokenizer st = new StringTokenizer(line, ",");
                
                Document recipe = new Document("_id", new ObjectId());
                recipe.append("title", st.nextToken())
                .append("ingredients", st.nextToken())
                .append("steps", st.nextToken());
                System.out.println(recipe);
            //     recipeCollections.insertOne(recipe);
            }
            br.close();  //closes the BR
        }
    }
}
