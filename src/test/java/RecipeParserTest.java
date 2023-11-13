import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import pantryPal.RecipeParser;

import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.io.FileNotFoundException;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

public class RecipeParserTest {

    @Test
    void testParseSpecificInputWithDashSpace() throws IOException, InterruptedException {
        String generatedText = "Title: Chipotle \n - rice \n #1 cook beans";
        try (FileWriter writer = new FileWriter("/src/main/resources/recipe.txt")) {
            System.out.println("HELLO");
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(generatedText);
            bw.close();

        } catch (FileNotFoundException e) {
            System.out.println("file not found :/");
            System.err.format("IOException: %s%n", e);
            return;
        } 
        RecipeParser rp = new RecipeParser();
        rp.parse();
        assertEquals("Chipotle ", rp.getTitle());
        assertNotEquals("Steps: \n #1 cook beans ", rp.getStringSteps());
        assertNotEquals("Ingredients: \n - rice ", rp.getStringIngredients());
    }

    @Test
    void testParseSpecificInputWithoutDashSpace() throws IOException, InterruptedException {
        String generatedText = "Title: Chipotle \n -rice \n #1 cook beans";
        try (FileWriter writer = new FileWriter("/src/main/resources/recipe.txt")) {
            System.out.println("HELLO");
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(generatedText);
            bw.close();

        } catch (FileNotFoundException e) {
            System.out.println("file not found :/");
            System.err.format("IOException: %s%n", e);
            return;
        } 
        RecipeParser rp = new RecipeParser();
        rp.parse();
        assertEquals("Chipotle ", rp.getTitle());
        assertNotEquals("Steps: \n #1 cook beans ", rp.getStringSteps());
        assertNotEquals("Ingredients: \n -rice ", rp.getStringIngredients());
    }
}