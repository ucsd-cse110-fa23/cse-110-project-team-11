import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import pantryPal.client.App;
import pantryPal.client.API.MockImageGenerator;
import pantryPal.client.View.RecipeDisplay;
import static org.junit.jupiter.api.Assertions.*;


import java.io.IOException;
import java.net.URISyntaxException;

import javafx.application.Application;

class MockImageGeneratorTest {
    MockImageGenerator mockImage = new MockImageGenerator();

@BeforeAll
    public static void setUpClass() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                    Application.launch(App.class,new String[0]); 
                }
            });
            thread.setDaemon(true);
            thread.start();// Initialize the thread
            Thread.sleep(500);
    }

    @Test 
    void testImageGenerationWhileCreating() throws IOException, InterruptedException, URISyntaxException {
        String url = mockImage.generateImage("banana bread","banana");
        RecipeDisplay rd =  new RecipeDisplay();
        rd.setImage(url);
        assertEquals(url, rd.getImage());
    }
}