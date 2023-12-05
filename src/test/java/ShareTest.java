import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.application.Application;
import javafx.application.Platform;
import pantryPal.client.App;
import pantryPal.client.MockApp;
import pantryPal.client.API.MockChatGPT;
import pantryPal.client.View.RecipeDisplay;
import pantryPal.client.View.RecipeDisplayAppFrame;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.*;

class ShareTest {

    @BeforeEach
    void setup() throws Exception {
        ApplicationTest.launch(MockApp.class);
    }

    @AfterEach
    void cleanup() throws Exception {
        //FxToolkit.cleanupStages();
        Platform.setImplicitExit(false);
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
