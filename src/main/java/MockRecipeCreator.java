package main.java;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MockRecipeCreator extends RecipeCreator {
    private static final String PROMPT_FORMATTING = "Give me a step-by-step recipe using '#' to label each step." +
    "don't label ingredient list as a step."
    + "for these ingredients, with a newline between step. ";
    private static final String RECIPE_HEADER =  " Before printing the recipe, generate a title for the recipe." + 
"Format the title as 'Title: (name of dish)', and put a dash and a space in front of every ingredient."
    + "Then print a list of all ingredients used, including seasonings, condiments, oils etc., "
    + "without specifying quantities.";

    public static String readPrompt() throws IOException {
        FileReader fr
        = new FileReader("promptTest.txt"); // PLACEHOLDER NAME
        BufferedReader br = new BufferedReader(fr);
        String prompt = br.readLine();
        br.close();
        return prompt;
    }

    public static String formatPrompt(String formattedPrompt) {
        return PROMPT_FORMATTING+formattedPrompt + RECIPE_HEADER;
    }

    public static String callMockAPI(String prompt) throws IOException, InterruptedException {
        MockChatGPT.call(prompt);
        return "Valid input file detected. Input: " + readPrompt();
    }

    public static String generateRecipeMock() throws IOException, InterruptedException {
        String rawPrompt = readPrompt();
        String formattedPrompt = formatPrompt(rawPrompt);
        //System.out.println(formattedPrompt);
        return callMockAPI(formattedPrompt);
    }
    
    public static String getCorrectResponse()
    {
        return MockChatGPT.getCorrectResponse();
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(generateRecipeMock());
    }

    /*  To run code (VSCode)
     *  javac -cp ../lib/json-20230227.jar:. *.java
     *  java -cp ../lib/json-20230227.jar:. *
     */
}

class MockChatGPT {
    private static final String CORRECT_RESPONSE = "Give me a step-by-step recipe "
            + "using '#' to label each step.don't label ingredient list as a step."
            + "for these ingredients, with a newline between step. I have chicken,"
            + " balut, and carrots. Before printing the recipe, generate a title for "
            + "the recipe.Format the title as 'Title: (name of dish)', and put a dash "
            + "and a space in front of every ingredient.Then print a list of all "
            + "ingredients used, including seasonings, condiments, oils etc., "
            + "without specifying quantities.";

    public static boolean  call (String prompt) {
        //prompt = formatPrompt(prompt);
        if (prompt.equals(CORRECT_RESPONSE)) {
            System.out.println("Success");
            return true;
        }
        else {
            System.out.println("Failed");       
            return false;
        }

    }
    
    public static String getCorrectResponse() {
        return CORRECT_RESPONSE;
    }
}