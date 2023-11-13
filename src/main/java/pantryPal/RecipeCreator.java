package pantryPal;
/**
 * This file contains RecipeCreator
 */

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

/**
 * This file sends a request to ChatGPT and asks it to generate a recipe given a certain meal type and list of ingredients,
 * which are received from the file "prompt.txt"
 */
public class RecipeCreator implements IRecipeCreator {
    private final String API_ENDPOINT = "https://api.openai.com/v1/completions";
    private final String API_KEY = "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";
    private final String MODEL = "text-davinci-003";
    private final int MAX_TOKENS = 600;

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
     * Sends prompt to ChatGPT and returns the response
     */
    public String callAPI(String prompt) throws IOException, InterruptedException {
     // Create a request body which you will pass into request object
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", MODEL);
        requestBody.put("prompt", prompt);
        requestBody.put("max_tokens", MAX_TOKENS);
        requestBody.put("temperature", 0.4);
        
     // Create the HTTP Client


        HttpClient client = HttpClient.newHttpClient();


        // Create the request object
        HttpRequest request = HttpRequest
        .newBuilder()
        .uri(URI.create(API_ENDPOINT))    
        .header("Content-Type", "application/json")
        .header("Authorization", String.format("Bearer %s", API_KEY))
        .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
        .build();
     // Send the request and receive the response
        HttpResponse<String> response = client.send(
            request,
            HttpResponse.BodyHandlers.ofString()
        );
        String responseBody = response.body();
        
        JSONObject responseJson = new JSONObject(responseBody);
        JSONArray choices = responseJson.getJSONArray("choices");
        String generatedText = choices.getJSONObject(0).getString("text");

        // write recipe to "recipe.txt"
        try (FileWriter writer = new FileWriter("src/main/resources/recipe.txt");
                BufferedWriter bw = new BufferedWriter(writer)) {

               bw.write(generatedText);

           } catch (IOException e) {
               System.err.format("IOException: %s%n", e);
           } 
        return generatedText;
    }
    
    /**
     * Generate recipe given info from "prompt.txt"
     */
    public String generateRecipe() throws IOException, InterruptedException {
        String[] info = readPrompt();
        String rawPrompt = info[0];
        String mealType = info[1];
        String formattedPrompt = IRecipeCreator.formatPrompt(mealType, rawPrompt);
        //System.out.println(formattedPrompt);

        return callAPI(formattedPrompt);
    }
    
    public void main(String[] args) throws IOException, InterruptedException, URISyntaxException
    {
        // for(int i = 0; i < 1;i++)
        //     System.out.println(generateRecipe());
        System.out.println(IRecipeCreator.formatPrompt("breakfast", "I have chicken, balut, and carrots."));
    }
}
