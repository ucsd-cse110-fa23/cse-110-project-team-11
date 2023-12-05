/**
 *  Methods/Unit Tests to test:
 * 
 */
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import pantryPal.client.App;
import pantryPal.client.MockApp;
import pantryPal.client.Backend.RecipeManager;
import pantryPal.client.View.HomePageAppFrame;
import pantryPal.client.View.HomePageHeader;
import pantryPal.client.View.InputAppFrame;
import pantryPal.client.View.RecipeDisplay;
import pantryPal.client.View.RecipeDisplayAppFrame;
import pantryPal.client.View.RecipeTitle;
import pantryPal.client.View.ReturnHeader;
//import org.testfx.framework.junit.ApplicationTest;
/**
 * UITest
 */

public class AppTest extends App {
   
    @BeforeEach
    void setup() throws Exception {
        ApplicationTest.launch(MockApp.class);
    }

    @AfterEach
    void cleanup() throws Exception {
        //FxToolkit.cleanupStages();
        Platform.setImplicitExit(false);
    }

    //@Test
    public void testAppButtons() throws InterruptedException{
        testCreateButton();
        testEditButton();
        testBackButton();
        testDeleteButton();
        testSaveButton();
        testViewButton();
        testHeader();
        testStartButton();
        testStopButton();
        testShare();
    }

    //@Test
    public void testCreateButton() throws InterruptedException {
        HomePageHeader hph = new HomePageHeader();
        assertNotNull(hph.getCreateButton(), "Should not be null");
    }
        
    

    //@Test
    public void testEditButton() throws InterruptedException {
        RecipeDisplay rd = new RecipeDisplay();
        assertNotNull(rd.getEditButton(), "Should not be null");
        //assertNull(rd.getEditButton(), "Should not be null");
    }
    
    
   // @Test
    public void testBackButton() throws InterruptedException {

        ReturnHeader rh = new ReturnHeader();
        assertNotNull(rh.getBackButton(), "Should not be null");
    }
    

    //Test
    public void testDeleteButton() throws InterruptedException {
        RecipeDisplay rd = new RecipeDisplay();
        assertNotNull(rd.getDeleteButton(), "Should not be null");
    }
    
    
    //@Test
    public void testSaveButton() throws InterruptedException {
        RecipeDisplay rd = new RecipeDisplay();
        assertNotNull(rd.getSaveButton(), "Should not be null");
    }
    
    
    //@Test
    public void testViewButton() throws InterruptedException {
        RecipeTitle rt = new RecipeTitle("id","title1, title2", new RecipeDisplayAppFrame(new RecipeDisplay()), "breakfast"); 
        assertNotNull(rt.getViewButton(), "Should not be null");
    }

    
    //@Test // tests the input buttons and appframe 
    public void testHeader () throws InterruptedException {

        InputAppFrame appFrame = new InputAppFrame(); // generate input appfram
        // 1) check if header of appframe exists
        assertNotNull(appFrame.getReturnHeader());
        // 2) check if buttons exist (non-null)
        assertNotNull(appFrame.getReturnHeader().getBackButton()); // should be in header
    }

    // 3) test functionality of buttons
    //@Test
    public void testStartButton() throws InterruptedException {
        InputAppFrame appFrame = new InputAppFrame(); // generate input appframe
        Button startButton = appFrame.getStartButton();
        
        assertNotNull(startButton,"should not be null");
    }


    //@Test
    public void testStopButton() throws InterruptedException {
                        InputAppFrame appFrame = new InputAppFrame(); // generate input appframe
                        Button stopButton = appFrame.getStartButton();
                        assertNotNull(stopButton,"should not be null");
    } 
    
    //@Test
    void testShare() {
        // Regenerated the recipe
        RecipeDisplay rdaf = new RecipeDisplay();

        assertNotNull(rdaf.getShareButton(), "Share button shouldn't be null.");
    }
}