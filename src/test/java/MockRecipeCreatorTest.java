
import org.junit.jupiter.api.Test;

import pantryPal.client.recipe.IRecipeCreator;
import pantryPal.client.recipe.MockRecipeCreator;

import static org.junit.jupiter.api.Assertions.*;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors



class MockRecipeCreatorTest
{
    MockRecipeCreator rc = new MockRecipeCreator();
    
    @Test
    void testReadFile() throws IOException
    {
        
        String testPrompt = "I have chicken, balut, and carrots.";
        try {
            FileWriter myWriter = new FileWriter("promptTest.txt");
            myWriter.write(testPrompt); 
            myWriter.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
        assertEquals(testPrompt, rc.readPrompt());
    }
    @Test
    void testFormatPrompt() {
        String testPrompt = "I have chicken, balut, and carrots.";
        String formattedPrompt = IRecipeCreator.formatPrompt("breakfast",testPrompt);
        String correct = "Give me a step-by-step recipe for breakfast,using '#' to label each step, using " + 
        "the following ingredients, with a newline between step. I have chicken, balut, and carrots. Before " +
         "printing the recipe, generate a title for the recipe.Format the title as 'Title: (name of dish)', " +
         "and put a dash and a space in front of every ingredient. Before printing steps, print a list of all " + 
         "ingredients used, including those that are not in the original ingredient list, without specifying " +
         "quantities.";
        assertEquals(correct, formattedPrompt);
    }
    @Test
    void testGenerateRecipe() throws IOException, InterruptedException {
        try {
            String testPrompt = "chicken balut carrots";
            FileWriter myWriter = new FileWriter("promptTest.txt");
            myWriter.write(testPrompt);
            myWriter.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
        String correct = "Title: chicken and carrots dish\n"
                + "\n"
                + "Ingredients: chicken, balut, carrots\n"
                + "\n"
                + "Steps: [steps]";
        System.out.println(rc.generateRecipe());
        assertEquals(rc.generateRecipe(), 
                correct);
    }
    

}