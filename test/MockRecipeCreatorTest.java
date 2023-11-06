import static org.junit.jupiter.api.Assertions.*;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

import org.junit.jupiter.api.Test;

class MockRecipeCreatorTest
{

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
        assertEquals(testPrompt, MockRecipeCreator.readPrompt());
    }
    @Test
    void testFormatPrompt() {
        String correct = MockChatGPT.getCorrectResponse();
        String prompt = "I have chicken, balut, and carrots.";
        String formattedPrompt = MockRecipeCreator.formatPrompt(prompt);
        assertEquals(correct, formattedPrompt);
    }
    @Test
    void testGenerateRecipe() throws IOException, InterruptedException {
        String testPrompt = "I have chicken, balut, and carrots.";

        try {
            FileWriter myWriter = new FileWriter("promptTest.txt");
            myWriter.write(testPrompt);
            myWriter.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
        assertEquals(MockRecipeCreator.generateRecipeMock(), 
                "Valid input file detected. Input: I have chicken, balut, and carrots.");
    }

}