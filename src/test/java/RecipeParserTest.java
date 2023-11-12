import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.io.FileNotFoundException;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

public class RecipeParserTest {

    @Test
    void testParseSpecificInput() throws IOException, InterruptedException {
        String generatedText = "Title: Chipotle \n - rice \n #1 cook beans";
        try (FileWriter writer = new FileWriter("src/main/resources/recipe.txt")) {
            System.out.println("HELLO");
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(generatedText);
            bw.close();

        } catch (FileNotFoundException e) {
            System.out.println("file not found :/");
            System.err.format("IOException: %s%n", e);
            return;
        } 
        RecipeParser.parse();
        assertEquals("Chipotle ", RecipeParser.getTitle());
        assertNotEquals("Steps: \n #1 cook beans ", RecipeParser.getStringSteps());
        assertNotEquals("Ingredients: \n - rice ", RecipeParser.getStringIngredients());
    }
}
