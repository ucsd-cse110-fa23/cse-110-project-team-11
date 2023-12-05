import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javafx.application.Application;
import pantryPal.client.App;
import pantryPal.client.API.MockChatGPT;
import pantryPal.client.View.RecipeDisplay;
import pantryPal.client.View.RecipeDisplayAppFrame;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShareTest {

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

    private static class RecipeService {
        public String saveRecipeDisplay(String recipe) {
            return recipe;
        }
    }

    /*
     * BDD Scenario 1 
     */
    @Test
    void testShare() {
        // Regenerated the recipe
        RecipeDisplay rdaf = new RecipeDisplay();

        assertNotNull(rdaf.getShareButton(), "Share button shouldn't be null.");
    }
}
