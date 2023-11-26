import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import pantryPal.client.App;
import pantryPal.client.Controller;
import pantryPal.client.Model;
import pantryPal.client.TranscriptionService;
import pantryPal.client.View.HomePageAppFrame;
import pantryPal.client.View.HomePageHeader;
import pantryPal.client.View.InputAppFrame;
import pantryPal.client.View.LoginPageAppFrame;
import pantryPal.client.View.RecipeDisplay;
import pantryPal.client.View.RecipeDisplayAppFrame;
import pantryPal.client.View.UI;
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
    
    // @Test 
    // public void Integration1() throws InterruptedException, IOException {

    //     Thread thread = new Thread(new Runnable() {

    //         @Override
    //         public void run() {
    //             try {
    //                 setUpClass();
    //             } catch (InterruptedException e) {
    //                 e.printStackTrace();
    //             }
    //             Platform.runLater(new Runnable() {

    //                 @Override
    //                 public void run() {
    //                         // Check if the click button is created
    //                 Model model = new Model();
    //                 BorderPane root = new BorderPane();
    //                 InputAppFrame ip = new InputAppFrame();
    //                 HomePageAppFrame hp = new HomePageAppFrame(ip);
    //                 RecipeDisplayAppFrame dp = new RecipeDisplayAppFrame(new RecipeDisplay());
    //                 LoginPageAppFrame lp = new LoginPageAppFrame();
    //                 HomePageHeader hph = new HomePageHeader();
    //                 UI ui = new UI(root, hp, ip, dp, lp);
    //                 Controller c = new Controller(ui, model);
    //                 ActionEvent actionEvent = new ActionEvent();

                    
    //                 c.handleCreateButton(actionEvent);
    //                 assertEquals(ui.getRoot().getCenter(), ip);

    //                 c.handleStartButton(actionEvent);
    //                 assertTrue(ip.getRecButtons().getButtonBox().getChildren().contains(ip.getRecButtons().getStopButton()));

    //                 MockTranscriptionService mockService = new MockTranscriptionService();
    //                 File audioFile = new File("input.wav");
    //                 String output = mockService.transcribe(audioFile);
    //                 assertEquals("lunch", output, "The transcribe method should return 'lunch'");

    //                 try {
    //                     c.handleStopButton(actionEvent);
    //                 } catch (InterruptedException e) {
    //                     // TODO Auto-generated catch block
    //                     e.printStackTrace();
    //                 } catch (IOException e) {
    //                     // TODO Auto-generated catch block
    //                     e.printStackTrace();
    //                 }
    //                 assertTrue(ip.getRecButtons().getButtonBox().getChildren().contains(ip.getRecButtons().getStartButton()));

                    
    //                 c.handleStartButton(actionEvent);
    //                 assertTrue(ip.getRecButtons().getButtonBox().getChildren().contains(ip.getRecButtons().getStopButton()));
                    
    //                 try {
    //                     c.handleStopButton(actionEvent);
    //                 } catch (InterruptedException e) {
    //                     // TODO Auto-generated catch block
    //                     e.printStackTrace();
    //                 } catch (IOException e) {
    //                     // TODO Auto-generated catch block
    //                     e.printStackTrace();
    //                 }
    //                 assertTrue(ip.getRecButtons().getButtonBox().getChildren().contains(ip.getRecButtons().getStartButton()));

                    
    //                 assertTrue(ui.getRoot().getCenter() instanceof RecipeDisplayAppFrame);

    //                 c.handleSaveButton(actionEvent);

    //                 assertEquals(ui.getRoot().getCenter(), hp);

    //                 }
    //             });
    //         }
    //     });
    //     thread.start();// Initialize the thread
    //     Thread.sleep(2000); // Time to use the app, with out this, the thread
    // }
    
    
}