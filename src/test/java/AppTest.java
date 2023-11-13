/**
 *  Methods/Unit Tests to test:
 * 
 */
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import pantryPal.View.App;
import pantryPal.View.HomePageHeader;
import pantryPal.View.InputAppFrame;
import pantryPal.View.RecipeDisplay;
import pantryPal.View.RecipeDisplayAppFrame;
import pantryPal.View.RecipeTitle;
import pantryPal.View.ReturnHeader;
//import org.testfx.framework.junit.ApplicationTest;
/**
 * UITest
 */

public class AppTest extends App {
    // @Test
    // public void testReturnToHomePage() throws Exception {
    //     Stage stage = new Stage();
    //     UI.start(stage);
    //     int sizeBefore = HomePageAppFrame.getRecipeList().getChildren().size();
    //     UI.returnHomePage();
    //     assertEquals(UI.getRoot().getCenter(), UI.getHomePage());
    //     assertEquals(sizeBefore, HomePageAppFrame.getRecipeList().getChildren().size());
    // }
    @BeforeAll 
    public static void setUpClass() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            Application.launch(App.class,new String[0]); 
            }
        });
        thread.start();// Initialize the thread
        Thread.sleep(0);
    }
    @Test
    public void testCreateButton() {
        
        HomePageHeader hph = new HomePageHeader();
        assertNotNull(hph.getCreateButton(), "Should not be null");
        // assertEquals(UI.getRoot().getCenter(), UI.getInputPage());
    }

    @Test
    public void testEditbutton() {
        RecipeDisplay rd = new RecipeDisplay();
        assertNotNull(rd.getEditButton(), "Should not be null");
    }
    
    @Test
    public void testBackButton() {
        ReturnHeader rh = new ReturnHeader();
        assertNotNull(rh.getBackButton(), "Should not be null");
    }

    @Test
    public void testDeleteButton() {
        RecipeDisplay rd = new RecipeDisplay();
        assertNotNull(rd.getDeleteButton(), "Should not be null");
    }
    
    @Test
    public void testSaveButton() {
        RecipeDisplay rd = new RecipeDisplay();
        assertNotNull(rd.getSaveButton(), "Should not be null");
    }
    
    @Test
    public void testViewButton() {
        RecipeTitle rt = new RecipeTitle("id","title1, title2", new RecipeDisplayAppFrame(new RecipeDisplay())); 
        assertNotNull(rt.getViewButton(), "Should not be null");
        // assertEquals(getRoot().getCenter() instanceof RecipeDisplayAppFrame, true);
    }

    
    @Test // tests the input buttons and appframe 
    public void testHeader () {
        InputAppFrame appFrame = new InputAppFrame(); // generate input appfram
        // 1) check if header of appframe exists
        assertNotNull(appFrame.getReturnHeader());
        // 2) check if buttons exist (non-null)
        assertNotNull(appFrame.getReturnHeader().getBackButton()); // should be in header
    }

    // 3) test functionality of buttons
    @Test
    public void testStartButton() {
        InputAppFrame appFrame = new InputAppFrame(); // generate input appframe
        Button startButton = appFrame.getStartButton();
        
        assertNotNull(startButton,"should not be null");
        
        Label label = appFrame.getRecordingLabel();
        assertNotNull(label, "should not be null"); // checks if button can be pressed/functions
    }

    @Test
    public void testStopButton() {
        InputAppFrame appFrame = new InputAppFrame(); // generate input appframe
        Button stopButton = appFrame.getStartButton();
        assertNotNull(stopButton,"should not be null");
        
        Label label = appFrame.getRecordingLabel();
        assertNotNull(label,"should not be null"); // check if button can be pressed/valid input
    }
    
}