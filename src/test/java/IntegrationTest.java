import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.scene.text.*;
import java.io.BufferedReader;
import java.io.FileReader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.io.IOException;
import org.junit.*;
import pantryPal.*;
import pantryPal.View.App;
import pantryPal.View.HomePageAppFrame;
import pantryPal.View.HomePageHeader;
import pantryPal.View.InputAppFrame;
import pantryPal.View.RecipeDisplay;
import pantryPal.View.RecipeDisplayAppFrame;
import pantryPal.View.RecipeTitle;
import pantryPal.View.ReturnHeader;
import pantryPal.View.UI;
import java.io.File;
 

public class IntegrationTest {

   

    /**
     * Scenario-based System #2
     * Click the create button, 
     * and you should see a screen 
     * with a voice record button prompting you 
     * for meal type.
     */

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
    }

    private static class MockTranscriptionService implements TranscriptionService {
        @Override
        public String transcribe(File audioFile) {
            return "lunch";
        }
    }
    
    @Test 
    public void Integration1() throws IOException, InterruptedException {

        
        // Check if the click button is created
        Model model = new Model();
        BorderPane root = new BorderPane();
        InputAppFrame ip = new InputAppFrame();
        HomePageAppFrame hp = new HomePageAppFrame(ip);
        RecipeDisplayAppFrame dp = new RecipeDisplayAppFrame(new RecipeDisplay());
        HomePageHeader hph = new HomePageHeader();
        UI ui = new UI(root, hp, ip, dp);
        Controller c = new Controller(ui, model);
        ActionEvent actionEvent = new ActionEvent();

        
        c.handleCreateButton(actionEvent);
        assertEquals(ui.getRoot().getCenter(), ip);

        c.handleStartButton(actionEvent);
        assertTrue(ip.getRecButtons().getButtonBox().getChildren().contains(ip.getRecButtons().getStopButton()));

        MockTranscriptionService mockService = new MockTranscriptionService();
        File audioFile = new File("input.wav");
        String output = mockService.transcribe(audioFile);
        assertEquals("lunch", output, "The transcribe method should return 'lunch'");

        c.handleStopButton(actionEvent);
        assertTrue(ip.getRecButtons().getButtonBox().getChildren().contains(ip.getRecButtons().getStartButton()));

        
        c.handleStartButton(actionEvent);
        assertTrue(ip.getRecButtons().getButtonBox().getChildren().contains(ip.getRecButtons().getStopButton()));
        
        c.handleStopButton(actionEvent);
        assertTrue(ip.getRecButtons().getButtonBox().getChildren().contains(ip.getRecButtons().getStartButton()));

        
        assertTrue(ui.getRoot().getCenter() instanceof RecipeDisplayAppFrame);

        c.handleSaveButton(actionEvent);

        assertEquals(ui.getRoot().getCenter(), hp);
    
    }
    
    

    
    
}
