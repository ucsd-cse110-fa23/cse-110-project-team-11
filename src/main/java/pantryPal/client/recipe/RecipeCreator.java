/**
 * RecipeCreator.java sends a request to ChatGPT and asks it to generate a recipe given a certain meal type and list of ingredients,
 * which are received from the file "prompt.txt"
 */

package pantryPal.client.recipe;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;

public class RecipeCreator implements IRecipeCreator {
    ChatGPT api = new ChatGPT();
    /**
     * Gets info from "prompt.txt" 
     */
    public String[] readPrompt() throws IOException {
        FileReader fr
        = new FileReader("prompt.txt"); // PLACEHOLDER NAME
        BufferedReader br = new BufferedReader(fr);
        String mealType = br.readLine();
        String prompt = br.readLine();
        String [] info = {prompt,mealType};
        br.close();
        return info;
    }
    
    /**
     * Generate recipe given info from "prompt.txt"
     */
    public String generateRecipe() throws IOException, InterruptedException {
        String[] info = readPrompt();
        String rawPrompt = info[0];
        String mealType = info[1];
        String formattedPrompt = IRecipeCreator.formatPrompt(mealType, rawPrompt);
        // System.out.println(formattedPrompt);

        return api.callAPI(formattedPrompt);
    }
    
    public void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
        System.out.println(IRecipeCreator.formatPrompt("breakfast", "I have chicken, balut, and carrots."));
    }
}
