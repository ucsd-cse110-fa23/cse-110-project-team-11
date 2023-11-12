/**
 *  Methods/Unit Tests to test:
 * 
 */
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.junit.*;
//import org.testfx.framework.junit.ApplicationTest;
/**
 * UITest
 */

public class UITest extends UI {
    // @Test
    // public void testReturnToHomePage() throws Exception {
    //     Stage stage = new Stage();
    //     UI.start(stage);
    //     int sizeBefore = HomePageAppFrame.getRecipeList().getChildren().size();
    //     UI.returnHomePage();
    //     assertEquals(UI.getRoot().getCenter(), UI.getHomePage());
    //     assertEquals(sizeBefore, HomePageAppFrame.getRecipeList().getChildren().size());
    // }
    
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
        assertEquals(label, "Recording"); // checks if button can be pressed/functions
    }

    @Test
    public void testStopButton() {
        InputAppFrame appFrame = new InputAppFrame(); // generate input appframe
        Button stopButton = appFrame.getStartButton();
        assertNotNull(stopButton,"should not be null");
        
        Label label = appFrame.getRecordingLabel();
        assertNotNull(label,"should not be null"); // check if button can be pressed/valid input
    }

    @Test
    public void testSetCenter() {
        BorderPane appFrame = new BorderPane();
        InputAppFrame inputPage = new InputAppFrame();
        HomePageAppFrame homePage = new HomePageAppFrame(inputPage);
        RecipeDisplayAppFrame recipePage = new RecipeDisplayAppFrame(null);
        appFrame.setCenter(inputPage);
        assertEquals(appFrame.getCenter(),inputPage);
        appFrame.setCenter(homePage);
        assertEquals(appFrame.getCenter(),homePage);
        appFrame.setCenter(recipePage);
        assertEquals(appFrame.getCenter(),recipePage);
    }
    
}