import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

import pantryPal.client.App;
import pantryPal.client.MockApp;
import pantryPal.client.API.MockImageGenerator;
import pantryPal.client.View.RecipeDisplay;
import static org.junit.jupiter.api.Assertions.*;


import java.io.IOException;
import java.net.URISyntaxException;

import javafx.application.Application;
import javafx.application.Platform;

class MockImageGeneratorTest {
    MockImageGenerator mockImage = new MockImageGenerator();

    @BeforeEach
    void setup() throws Exception {
        ApplicationTest.launch(MockApp.class);
    }

    @AfterEach
    void cleanup() throws Exception {
        //FxToolkit.cleanupStages();
        Platform.setImplicitExit(false);

    }

    @Test 
    void testImageGenerationWhileCreating() throws IOException, InterruptedException, URISyntaxException {
        String url = mockImage.generateImage("banana bread","banana");
        RecipeDisplay rd =  new RecipeDisplay();
        rd.setImage(url);
        assertEquals(url, rd.getImage());
    }
}