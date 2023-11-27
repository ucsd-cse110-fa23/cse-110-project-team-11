package pantryPal.client;
/**
 * Interface for RecipeCreator objects
 */

public interface IRecipeCreator
{
    static final String PROMPT_MEAL_SELECTION = "Give me a step-by-step recipe for ";
    static final String PROMPT_FORMATTING = ",using '#' to label each step, " +
                     "using the following ingredients, with a newline between step. ";
    static final String RECIPE_HEADER =  " Before printing the recipe, generate a title for the recipe." + 
            "Format the title as 'Title: (name of dish)', and put a dash and a space in front of every ingredient."
                    + " Before printing steps, print a list of all ingredients used, including those that are not in the original ingredient list, "
                    + "without specifying quantities.";
    
    
    /**
     * Add formatting to the user's prompt
     */
    public static String formatPrompt(String mealType, String rawPrompt) {
        return PROMPT_MEAL_SELECTION + mealType + PROMPT_FORMATTING+rawPrompt+RECIPE_HEADER;
    }
    
    // public abstract String generateRecipe() throws IOException, InterruptedException;
    
    // public abstract String callAPI(String prompt) throws IOException, InterruptedException;
}
