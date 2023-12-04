package pantryPal.client.Backend;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import pantryPal.client.API.ChatGPT;

/**
 * This file sends a request to ChatGPT and asks it to generate a recipe given a certain meal type and list of ingredients,
 * which are received from the file "prompt.txt"
 */
public class RecipeCreator implements IRecipeCreator {
    ChatGPT api = new ChatGPT();
    /**
     * Gets info from "prompt.txt" 
     */
    public String[] readPrompt() throws IOException {
        FileReader fr
        = new FileReader("prompt.txt");
        BufferedReader br = new BufferedReader(fr);
        String mealType = br.readLine();
        String prompt = br.readLine();
        String [] info = {prompt,mealType};
        br.close();
        return info;
    }
    
    // Generate recipe given info from "prompt.txt"
    public String generateRecipe() throws IOException, InterruptedException {
        String[] info = readPrompt();
        String rawPrompt = info[0];
        String mealType = info[1];
        String formattedPrompt = IRecipeCreator.formatPrompt(mealType, rawPrompt);
        
        return api.callAPI(formattedPrompt);
    }
}
