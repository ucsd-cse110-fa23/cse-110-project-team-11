/**
 * This file contains MockRecipeCreator and MockChatGPT
 */

package pantryPal.client.Backend;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import pantryPal.client.API.MockChatGPT;

/**
 * This class mocks RecipeCreator for unit testing
 */
public class MockRecipeCreator implements IRecipeCreator {

    // Reads info from "promptText.txt"
    public String readPrompt() throws IOException {
        FileReader fr
        = new FileReader("promptTest.txt"); // PLACEHOLDER NAME
        BufferedReader br = new BufferedReader(fr);
        String prompt = br.readLine();
        br.close();
        return prompt;
    }

    // Sends prompt to MockChatGPT and returns the response
    public String callAPI(String prompt) throws IOException, InterruptedException {
        MockChatGPT mc = new MockChatGPT();
        return mc.callAPI(prompt);
    }

    // Generates recipe from MockChatGPT
    public String generateRecipe() throws IOException, InterruptedException {
        String rawPrompt = readPrompt();
        return callAPI(rawPrompt);
    }
}