import org.bson.Document;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.net.URISyntaxException;

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

    @BeforeEach
    void setup() throws Exception {
        AccountManager.deleteAccount("test1","test1");

        MockServer.turnOn();
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase recipeDB = mongoClient.getDatabase("recipes_db");
            // recipeDB.createCollection("test1");
            MongoCollection<Document> coll = recipeDB.getCollection("test1");
            Document doc1 = new Document("title", "A")
                            .append("ingredients", "")
                            .append("steps", "")
                            .append("imageURL","https://demo.sirv.com/looks.jpg?h=150&w=150")
                            .append("mealType", "Dinner");
            coll.insertOne(doc1);
            Document doc2 = new Document("title", "B")
                            .append("ingredients", "")
                            .append("steps", "")
                            .append("imageURL","https://demo.sirv.com/looks.jpg?h=150&w=150")
                            .append("mealType", "Breakfast");
            coll.insertOne(doc2);
        }
        MockServer.turnOn();
        AccountManager.insertAccount("test1","test1");
        ApplicationTest.launch(MockApp.class);
        LoginPageAppFrame loginPage = MockApp.getUI().getLoginPage();
        // assertNotNull(loginPage.getLoginButton(), "Should not be null");
        // assertNotNull(loginPage.getCreateButton(), "Should not be null");
        // assertNotNull(loginPage.getAuto(), "Should not be null");

        loginPage.getAuto().setSelected(false);
    
        // check if account is created and enter homepage after pw and id created
        loginPage.setUsername("test1");
        loginPage.setPassword("test1");
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
        LoginPageAppFrame loginPage = MockApp.getUI().getLoginPage();
        assertNotNull(loginPage.getLoginButton(), "Should not be null");
        assertNotNull(loginPage.getCreateButton(), "Should not be null");
        assertNotNull(loginPage.getAuto(), "Should not be null");

        // loginPage.getAuto().setSelected(false);
    
        // // check if account is created and enter homepage after pw and id created
        // loginPage.setUsername("test1");
        // loginPage.setPassword("test1");
        clickOn((Button) loginPage.getLoginButton());
        // Homepage -> Create Button
        HomePageAppFrame hpaf =  (HomePageAppFrame) MockApp.getUI().getRoot().getCenter();
        HomePageHeader hph = (HomePageHeader) MockApp.getUI().getRoot().getTop();
        assertTrue(hpaf instanceof HomePageAppFrame);
        assertTrue(hph instanceof HomePageHeader);
        assertNotNull(hph.getCreateButton(), "Should not be null");

        clickOn((Button) hph.getCreateButton());
        InputAppFrame iaf = (InputAppFrame) MockApp.getUI().getRoot().getCenter();
        assertNotNull(iaf.getStartButton(), "Should not be null");

        // // Start Record -> input: Dinner -> created recipe page 
        clickOn(((InputAppFrame)MockApp.getUI().getRoot().getCenter()).getStartButton());
        //assertEquals()
        clickOn(((InputAppFrame)MockApp.getUI().getRoot().getCenter()).getStopButton());
        clickOn(((InputAppFrame)MockApp.getUI().getRoot().getCenter()).getStartButton());
        clickOn(((InputAppFrame)MockApp.getUI().getRoot().getCenter()).getStopButton());

        // TODO: fix casting error
        // expected: Regenerate -> Homepage         
        System.out.println("CLASSNAME: " + MockApp.getUI().getRoot().getCenter().getClass().getSimpleName());
        //MockApp.getUI().getRoot().setCenter(new RecipeDisplayAppFrame(new RecipeDisplay()));
        //RecipeDisplayAppFrame rdaf =  (RecipeDisplayAppFrame) MockApp.getUI().getRoot().getCenter();
        RecipeDisplayAppFrame rdaf =  (RecipeDisplayAppFrame) MockApp.getUI().getDisplayPage();
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
        //clickOn((Button) rdh.getBackButton());
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

        // check if we can view newly inserted recipe
        RecipeTitle rt = (RecipeTitle)rl.getChildren().get(0);
        clickOn(rt.getViewButton());
        assertEquals(MockApp.getUI().getRoot().getCenter(),rt.getRecipeDetail());
        
        RecipeDisplayAppFrame rd = ((RecipeDisplayAppFrame) MockApp.getUI().getRoot().getCenter());
        clickOn(rt.getRecipeDetail().getEditButton());
        assertTrue(rd.getEditable());
        clickOn(rd.getStepsArea());
        push(javafx.scene.input.KeyCode.L);
        push(javafx.scene.input.KeyCode.O);
        push(javafx.scene.input.KeyCode.L);
        clickOn(rd.getEditButton());
        clickOn(rd.getSaveButton());
        assertFalse(rd.getEditable());

        clickOn(rd.getRecipeDisplayHeader().getBackButton());

        hpaf =  (HomePageAppFrame) MockApp.getUI().getRoot().getCenter();
        clickOn(rt.getViewButton());
        String text = rd.getSteps().getText();
        assertTrue(text.contains("lol"));
        AccountManager.deleteAccount("test1","test1");
    }
}