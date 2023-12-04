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
import pantryPal.client.MockApp;
import pantryPal.client.View.HomePageAppFrame;
import pantryPal.client.View.HomePageFooter;
import pantryPal.client.View.HomePageHeader;
import pantryPal.client.View.InputAppFrame;
import pantryPal.client.View.LoginPageAppFrame;
import pantryPal.client.View.RecipeDisplay;
import pantryPal.client.View.RecipeDisplayAppFrame;
import pantryPal.client.View.RecipeList;
import pantryPal.client.View.RecipeTitle;
import pantryPal.client.View.ReturnHeader;
import pantryPal.client.View.UI;
import pantryPal.server.MockServer;
import pantryPal.client.Backend.AccountManager;

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
       // MockServer.turnOn();
        ApplicationTest.launch(MockApp.class);
    }

    @AfterEach
    void cleanup() throws Exception {
        AccountManager.deleteAccount("test","test");
        FxToolkit.cleanupStages();
    }
   
    /*
    US: 10,11,12,15,16 
    */
    @Test
    public void integrationTest1() {
    // app launch check "Loging button and Create button"
    MockServer.turnOn();
    LoginPageAppFrame loginPage = MockApp.getUI().getLoginPage();
    assertNotNull(loginPage.getLoginButton(), "Should not be null");
    assertNotNull(loginPage.getCreateButton(), "Should not be null");
    assertNotNull(loginPage.getAuto(), "Should not be null");

    // loginPage.getAuto().setSelected(true);
    
    // check if account is created and enter homepage after pw and id created
    loginPage.setUsername("test");
    loginPage.setPassword("test");
    clickOn((Button) loginPage.getCreateButton());
        // Homepage -> Create Button
    HomePageAppFrame hpaf =  (HomePageAppFrame) MockApp.getUI().getRoot().getCenter();
    HomePageHeader hph = (HomePageHeader) MockApp.getUI().getRoot().getTop();
    assertTrue(hpaf instanceof HomePageAppFrame);
    assertTrue(hph instanceof HomePageHeader);
    assertNotNull(hph.getCreateButton(), "Should not be null");

    clickOn((Button) hph.getCreateButton());
    InputAppFrame iaf = (InputAppFrame) MockApp.getUI().getRoot().getCenter();
    assertNotNull(iaf.getStartButton(), "Should not be null");

    // Start Record -> input: Dinner -> created recipe page 
    clickOn((Button) iaf.getStartButton());
    //assertEquals()
    clickOn((Button) iaf.getStopButton());
    clickOn((Button) iaf.getStartButton());
    clickOn((Button) iaf.getStopButton());

    // expected: Regenerate -> Homepage 
    RecipeDisplayAppFrame rdaf = (RecipeDisplayAppFrame) MockApp.getUI().getRoot().getCenter();
    assertTrue(rdaf instanceof RecipeDisplayAppFrame);
    assertNotNull(rdaf.getRecipe().getDeleteButton(),"Should not be null");
    assertNotNull(rdaf.getRecipe().getSaveButton(),"Should not be null");
    assertNotNull(rdaf.getRecipe().getEditButton(),"Should not be null");
    assertNotNull(rdaf.getRecipe().getShareButton(),"Should not be null");
    assertNotNull(rdaf.getRecipe().getRegenerateButton(),"Should not be null");
    assertNotNull(rdaf.getImage(), "Should not be null"); // checks that image is generated/image url shouldn't be null?
    clickOn((Button) rdaf.getRecipe().getRegenerateButton());
    RecipeDisplayAppFrame rdaf2 = (RecipeDisplayAppFrame) MockApp.getUI().getRoot().getCenter();
    
    boolean rdEquals = (rdaf.getStringTitle().equals(rdaf2.getStringTitle())) 
                            && (rdaf.getStringSteps().equals(rdaf2.getStringSteps())) 
                            && (rdaf.getStringIngredients().equals(rdaf2.getStringIngredients()));
    assertFalse(rdEquals); 

    ReturnHeader rdh = rdaf2.getRecipeDisplayHeader();
    assertNotNull(rdh.getBackButton());
    clickOn((Button) rdaf2.getRecipe().getSaveButton());

   // return to home page
    clickOn((Button) rdh.getBackButton());
    hpaf =  (HomePageAppFrame) MockApp.getUI().getRoot().getCenter();
    hph = (HomePageHeader) MockApp.getUI().getRoot().getTop();
    HomePageFooter hpf = (HomePageFooter) MockApp.getUI().getRoot().getBottom();
    assertTrue(hpaf instanceof HomePageAppFrame);
    assertTrue(hph instanceof HomePageHeader);
    assertTrue(hpf instanceof HomePageFooter);
    assertNotNull(hpf.getFilterButton());
    assertNotNull(hpf.getSortButton());

   // check save
    RecipeList rl = hpaf.getRecipeList();
    assertEquals(((RecipeTitle) rl.getChildren().get(0)).getTitle().getText(), rdaf2.getStringTitle()); // need to add set getter also

    // check alphabetical sort
    // check filter
    // clickOn(“Breakfast”); //idk about this part
    // RecipeList newRL =  (HomePageAppFrame) App.getUI().getRoot().getCenter();



   
    
    }

}

   
    
 
    
    
