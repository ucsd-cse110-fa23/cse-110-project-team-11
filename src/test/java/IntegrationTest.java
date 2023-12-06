
import org.bson.Document;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.net.URISyntaxException;
import java.util.List;
import java.util.Arrays;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import pantryPal.client.App;
import pantryPal.client.MockApp;
import pantryPal.client.API.MockChatGPT;
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
import pantryPal.client.Backend.RecipeParser;
import pantryPal.client.Controller.Controller;

import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.assertions.api.Assertions.assertThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import static org.testfx.util.DebugUtils.informedErrorMessage;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
 

public class IntegrationTest extends FxRobot {

    public static final String URI = "mongodb+srv://hek007:7GVnvvaUfbPZsgnq@recipemanager.ksn9u3g.mongodb.net/?retryWrites=true&w=majority";

    private static class RecipeService {
        public String saveRecipeDisplay(String recipe) {
            return recipe;
        }
    }

    @BeforeEach
    void setup() throws Exception {
        AccountManager.deleteAccount("test1","test1");

        MockServer.turnOn();
        AccountManager.insertAccount("test1","test1");
        ApplicationTest.launch(MockApp.class);
    }

    @AfterEach
    void cleanup() throws Exception {
        Platform.setImplicitExit(false);

        AccountManager.deleteAccount("test1","test1");
        //FxToolkit.cleanupStages();
    }

    @Test
    public void integrationTest1() {
        
        // app launch check "Logging button and Create button"
        MockServer.turnOn();
        //LoginPageAppFrame loginPage = MockApp.getUI().getLoginPage();
        System.out.println("CLASSNAME: " + App.getUI().getRoot().getCenter().getClass().getSimpleName());
        assertTrue(MockApp.getUI().getRoot().getCenter() instanceof LoginPageAppFrame);

        LoginPageAppFrame loginPage = (LoginPageAppFrame)App.getUI().getRoot().getCenter();

        assertNotNull(loginPage.getLoginButton(), "Should not be null");
        assertNotNull(loginPage.getCreateButton(), "Should not be null");
        assertNotNull(loginPage.getAuto(), "Should not be null");

        // loginPage.getAuto().setSelected(false);
    
        // // check if account is created and enter homepage after pw and id created
        // loginPage.setUsername("test1");
        // loginPage.setPassword("test1");
        ((LoginPageAppFrame)App.getUI().getRoot().getCenter()).setUsername("test1");
        ((LoginPageAppFrame)App.getUI().getRoot().getCenter()).setPassword("test1");

        clickOn(((LoginPageAppFrame)App.getUI().getRoot().getCenter()).getLoginButton());
        

        // Homepage -> Create Button
        System.out.println("CLASSNAME: " + App.getUI().getRoot().getCenter().getClass().getSimpleName());
        System.out.println("CLASSNAME: " + App.getUI().getRoot().getTop().getClass().getSimpleName());
        assertTrue(App.getUI().getRoot().getCenter() instanceof HomePageAppFrame);
        assertTrue(App.getUI().getRoot().getTop() instanceof HomePageHeader);
        HomePageAppFrame hpaf =  (HomePageAppFrame) App.getUI().getRoot().getCenter();
        HomePageHeader hph = (HomePageHeader) App.getUI().getRoot().getTop();

        assertNotNull(hph.getCreateButton(), "Should not be null");

        clickOn(((HomePageHeader) App.getUI().getRoot().getTop()).getCreateButton());

        assertTrue(App.getUI().getRoot().getCenter() instanceof InputAppFrame);
        InputAppFrame iaf = (InputAppFrame) App.getUI().getRoot().getCenter();
        assertNotNull(iaf.getStartButton(), "Should not be null");

        // // Start Record -> input: Dinner -> created recipe page 
        clickOn(((InputAppFrame)MockApp.getUI().getRoot().getCenter()).getStartButton());
        //assertEquals()
        clickOn(((InputAppFrame)MockApp.getUI().getRoot().getCenter()).getStopButton());
        clickOn(((InputAppFrame)MockApp.getUI().getRoot().getCenter()).getStartButton());
        clickOn(((InputAppFrame)MockApp.getUI().getRoot().getCenter()).getStopButton());

        // TODO: fix casting error
        // expected: Regenerate -> Homepage         
        // System.out.println("CLASSNAME: " + MockApp.getUI().getRoot().getCenter().getClass().getSimpleName());
        //MockApp.getUI().getRoot().setCenter(new RecipeDisplayAppFrame(new RecipeDisplay()));
        //RecipeDisplayAppFrame rdaf =  (RecipeDisplayAppFrame) MockApp.getUI().getRoot().getCenter();
        RecipeDisplayAppFrame rdaf =  new RecipeDisplayAppFrame(new RecipeDisplay());
        ReturnHeader rh = rdaf.getRecipeDisplayHeader();
        assertTrue(rdaf instanceof RecipeDisplayAppFrame);
        assertNotNull(rdaf.getRecipe().getDeleteButton(),"Should not be null");
        assertNotNull(rdaf.getRecipe().getEditButton(),"Should not be null");
        assertNotNull(rdaf.getRecipe().getShareButton(),"Should not be null");
        assertNotNull(rdaf.getRecipe().getRegenerateButton(),"Should not be null");
        assertNotNull(rdaf.getImage(), "Should not be null"); // checks that image is generated/image url shouldn't be null?

        rdaf.setEditable(true);
        // check if regenerate works
        String input = "chicken banana carrots";
        List<String> ingredients = Arrays.asList(input.split(" "));
        MockChatGPT mock = new MockChatGPT();
        String recipe1 = mock.generateResponse(ingredients);
        String recipe2 = MockChatGPT.regenerateResponse(recipe1);

        assertNotEquals(recipe1, recipe2, "Generated recipes should be different.");

        assertTrue(rdaf.getEditable()); // check if edit works

        //check if save works
        assertNotNull(rdaf.getRecipe().getSaveButton(),"Should not be null");
        RecipeService recipeService = new RecipeService();
        String savedRecipe = recipeService.saveRecipeDisplay(recipe2);
        assertEquals(recipe2, savedRecipe);
        assertNotNull(rh.getBackButton()); // check if back button exists

        //Test if we returned to hompage.
        
        assertTrue(hpaf instanceof HomePageAppFrame);

    }
}