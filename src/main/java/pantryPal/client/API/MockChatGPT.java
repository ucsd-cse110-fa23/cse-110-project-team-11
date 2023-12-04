package pantryPal.client.API;

import java.util.StringTokenizer;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MockChatGPT implements IAPI {
    public static List<String> parseList(String prompt) {
        StringTokenizer st = new StringTokenizer(prompt, " ");
        List<String> ingredientList = new ArrayList<String>();
        while(st.hasMoreTokens()) {
            ingredientList.add(st.nextToken());
        }
        return ingredientList;
    }
    
    public String generateResponse(List<String> list) {
        if (list.isEmpty()) {
            return "No ingredients provided.";
        }

        String response = "";
        String first = list.get(0);
        String last = list.get(list.size() - 1);
        String title = "Title: " + first + " and " + last + " dish";
        
        response += title + "\n\n";
        
        String ingredients = "Ingredients: ";
        for(String ingred : list) {
            ingredients += ingred + ", ";
        }
        ingredients = ingredients.substring(0, ingredients.length() - 2);
        
        response += ingredients + "\n\n";
        
        String steps = "Steps: [steps]";
        response += steps;
        return response;
    }

    /*
     * Regenerate the reponse
     */
    public static String regenerateResponse(String response) {
        response = "regenerated recipe different from originially generated recipe";
        return response;
    }
    
    public String callAPI(String prompt) {
        List<String> ingreds = parseList(prompt);
        String response = generateResponse(ingreds);
        System.out.println(response);
        // write recipe to "recipe.txt"
           try (FileWriter writer = new FileWriter("src/main/resources/recipe.txt",true);
                   BufferedWriter bw = new BufferedWriter(writer)) {
   
                  bw.append(".");
   
              } catch (IOException e) {
                  System.err.format("IOException: %s%n", e);
              }
        return response;
    }
}
