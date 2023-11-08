package main;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class MockRecipeCreator implements IRecipeCreator {
    public String readPrompt() throws IOException {
        FileReader fr
        = new FileReader("promptTest.txt"); // PLACEHOLDER NAME
        BufferedReader br = new BufferedReader(fr);
        String prompt = br.readLine();
        br.close();
        return prompt;
    }

    public String callAPI(String prompt) throws IOException, InterruptedException {
        return MockChatGPT.call(prompt);
    }

    public String generateRecipe() throws IOException, InterruptedException {
        String rawPrompt = readPrompt();
        return callAPI(rawPrompt);
    }
    
    public static void main(String[] args) throws IOException, InterruptedException {
        MockRecipeCreator rc = new MockRecipeCreator();
        System.out.println(rc.generateRecipe());
        System.out.println(IRecipeCreator.formatPrompt("I have chicken, balut, and carrots."));
    }

    /*  To run code (VSCode)
     *  javac -cp ../lib/json-20230227.jar:. *.java
     *  java -cp ../lib/json-20230227.jar:. *
     */
}

class MockChatGPT {

    public static List<String> parseList(String prompt) {
        StringTokenizer st = new StringTokenizer(prompt, " ");
        List<String> ingredientList = new ArrayList<String>();
        while(st.hasMoreTokens()) {
            ingredientList.add(st.nextToken());
        }
        return ingredientList;
    }
    
    public static String generateResponse(List<String> list) {
        String response = "";
        String first = list.get(0);
        String last = list.get(list.size()-1);
        String title = "Title: " + first + " and " + last + " dish";
        
        response+=title +"\n\n";
        
        String ingredients = "Ingredients: ";
        for(String ingred:list) {
            ingredients += ingred + ", ";
        }
        ingredients = ingredients.substring(0, ingredients.length()-2);
        
        response += ingredients + "\n\n";
        
        String steps = "Steps: [steps]";
        response += steps;
        return response;
        
        
    }
    
    public static String call (String prompt) {
        List<String> ingreds = parseList(prompt);
        String response = generateResponse(ingreds);
        System.out.println(response);
        return response;

    }
}