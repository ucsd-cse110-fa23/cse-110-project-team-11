import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.layout.*;
import pantryPal.client.RecipeManager;
import pantryPal.client.App;
import pantryPal.client.View.HomePageAppFrame;
import pantryPal.client.View.HomePageHeader;
import pantryPal.client.View.InputAppFrame;
import pantryPal.client.View.LoginPageAppFrame;
import pantryPal.client.View.RecipeDisplay;
import pantryPal.client.View.RecipeDisplayAppFrame;
import pantryPal.client.View.RecipeList;
import pantryPal.client.View.RecipeTitle;
import pantryPal.client.View.ReturnHeader;
import pantryPal.client.View.UI;
import pantryPal.server.Server;
import pantryPal.server.IServer;
import pantryPal.server.MockServer;

import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.assertions.api.Assertions.assertThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import static org.testfx.util.DebugUtils.informedErrorMessage;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
 

public class IntegrationTest2 {

   
    @BeforeEach
    void setup() throws Exception {
        App.setTest(true);
        ApplicationTest.launch(App.class);

        // Assume the user has created an account beforehand
        
    }

    @Test
    public void integrationTest2() {
        MockServer.turnOff();
        
    }





    // Scenario-based System #2
    // Assume the user has created an account beforehand
    // The user’s phone is not connected to the internet, and therefore gives an error with connecting to the server
    // Next, the user switches to their computer, which has the same user account.
    // In the user’s account, they checked the “automatically log in” button beforehand (when creating the account before). The app is opened on their computer, and then automatically takes the user to the main recipe list.
    // The recipes that were on the user’s phone are now displayed on the user’s computer, due to multi-device syncing associated with the user account.
    // The user was looking for a delicious dinner they had a few days ago, so to help search for it, the user selects the “dinner” filter on the recipe list, now showing the dinner meals that were previously created.
    // Now, the user wants to share this delicious dinner with their friend. So, they click share and are generated a link. They copy and paste the link, for them to share to their friends

    // @BeforeAll
    // public static void setUpClass() throws InterruptedException {
    //     Thread thread = new Thread(new Runnable() {
    //         @Override
    //         public void run() {
    //                 Application.launch(App.class,new String[0]); 
    //             }
    //         });
    //         thread.setDaemon(true);
    //         thread.start();// Initialize the thread
    // }

   
    
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