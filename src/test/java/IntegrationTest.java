import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import pantryPal.client.App;
import pantryPal.client.View.HomePageAppFrame;
import pantryPal.client.View.HomePageHeader;
import pantryPal.client.View.InputAppFrame;
import pantryPal.client.View.LoginPageAppFrame;
import pantryPal.client.View.RecipeDisplay;
import pantryPal.client.View.RecipeDisplayAppFrame;
import pantryPal.client.View.UI;
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
import javafx.scene.control.Button;
import javafx.stage.Stage;
 

public class IntegrationTest extends FxRobot {

   
    @BeforeEach
    void setup() throws Exception {
        App.setTest(true);
       // MockServer.turnOn();
        ApplicationTest.launch(App.class);
    }

    @AfterEach
    void cleanup() throws Exception {
        FxToolkit.cleanupStages();
    }
   
    /*
    US: 10,11,12,15,16 
    */
    @Test
    public void integrationTest1() {
    // app launch check "Loging button and Create button"
    MockServer.turnOn();
    LoginPageAppFrame loginPage = App.getUI().getLoginPage();
    assertNotNull(loginPage.getLoginButton(), "Should not be null");
    assertNotNull(loginPage.getCreateButton(), "Should not be null");
    
    // check if account is created and enter homepage after pw and id created
    loginPage.setUsername("test");
    loginPage.setPassword("test");
    clickOn((Button) loginPage.getCreateButton());
    
    HomePageAppFrame hpaf =  (HomePageAppFrame) App.getUI().getRoot().getCenter();
    HomePageHeader hph = (HomePageHeader) App.getUI().getRoot().getTop();
    assertTrue(hpaf instanceof HomePageAppFrame);
    assertTrue(hph instanceof HomePageHeader);
    assertNotNull(hph.getCreateButton(), "Should not be null");

    clickOn((Button) hph.getCreateButton());
    InputAppFrame iaf = (InputAppFrame) App.getUI().getRoot().getCenter();
    assertTrue(iaf instanceof InputAppFrame);
    assertNotNull(iaf.getStartButton(), "Should not be null");

    clickOn((Button) iaf.getStartButton());
    clickOn((Button) iaf.getStopButton());
    clickOn((Button) iaf.getStartButton());
    clickOn((Button) iaf.getStopButton());





   
    
    }


}


    // /**
    //  * Scenario-based System #2
    //  * Click the create button, 
    //  * and you should see a screen 
    //  * with a voice record button prompting you 
    //  * for meal type.
    //  */

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
    
    