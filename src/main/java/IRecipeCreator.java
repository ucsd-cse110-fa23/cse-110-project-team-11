package main.java;

import java.io.IOException;

public interface IRecipeCreator
{
    static final String PROMPT_FORMATTING = "Give me a step-by-step recipe using '#' to label each step." +
            "don't label ingredient list as a step."
            + "for these ingredients, with a newline between step. ";
    static final String RECIPE_HEADER =  " Before printing the recipe, generate a title for the recipe." + 
            "Format the title as 'Title: (name of dish)', and put a dash and a space in front of every ingredient."
                    + "Before printing steps, print a list of all ingredients used, including those that are not in the original ingredient list, "
                    + "without specifying quantities.";
    
    
    public static String formatPrompt(String formattedPrompt) {
        return PROMPT_FORMATTING+formattedPrompt+RECIPE_HEADER;
    }
    
    public abstract String generateRecipe() throws IOException, InterruptedException;
    
    public abstract String callAPI(String prompt) throws IOException, InterruptedException;
}
