import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
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
import pantryPal.client.Backend.RecipeManager;
import pantryPal.client.Backend.SortAlphabetically;
import pantryPal.client.Controller.Controller;
import org.bson.Document;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.assertions.api.Assertions.assertThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import static org.testfx.util.DebugUtils.informedErrorMessage;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
 

// @TestMethodOrder(MethodOrderer.DisplayName.class)
public class IntegrationTest2 extends FxRobot {
    public static final String URI = "mongodb+srv://hek007:7GVnvvaUfbPZsgnq@recipemanager.ksn9u3g.mongodb.net/?retryWrites=true&w=majority";

    @BeforeEach
    void setup() throws Exception {
        AccountManager.deleteAccount("test","test");

    //    MockServer.turnOn();
       try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase recipeDB = mongoClient.getDatabase("recipes_db");
            recipeDB.createCollection("test");
            MongoCollection<Document> coll = recipeDB.getCollection("test");
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
        AccountManager.insertAccount("test","test");
        ApplicationTest.launch(MockApp.class);
    }

    @AfterEach
    void cleanup() throws Exception {
        AccountManager.deleteAccount("test","test");
        FxToolkit.cleanupStages();
    }

    // @Test
    // public void autologinTest() {
        

    //     MockServer.turnOff();
    // }

    @Test
    public void integrationTest2() {
        // // test alert page
        // verifyThat("OK", NodeMatchers.isVisible());
        MockServer.turnOn();
        LoginPageAppFrame loginPage = MockApp.getUI().getLoginPage();
        loginPage.getAuto().setSelected(true);
        loginPage.setUsername("test");
        loginPage.setPassword("test");
        clickOn((Button) loginPage.getLoginButton());    

        HomePageAppFrame hpaf =  (HomePageAppFrame) MockApp.getUI().getRoot().getCenter();
        HomePageHeader hph = (HomePageHeader) MockApp.getUI().getRoot().getTop();
        assertTrue(hpaf instanceof HomePageAppFrame);
        assertTrue(hph instanceof HomePageHeader);
        clickOn((Button) hph.getLogoutButton());  
        
        String filePath = "/src/main/resources/autologin.txt";
        File f = new File(filePath);
        assertFalse(f.getAbsoluteFile().exists());
        

        loginPage = MockApp.getUI().getLoginPage();
        loginPage.getAuto().setSelected(false);
        loginPage.setUsername("test");
        loginPage.setPassword("test");
        clickOn((Button) loginPage.getLoginButton());   
        hpaf =  (HomePageAppFrame) MockApp.getUI().getRoot().getCenter();
        hph = (HomePageHeader) MockApp.getUI().getRoot().getTop();
        // assertTrue(true);
        // LoginPageAppFrame loginPage = MockApp.getUI().getLoginPage();
        // assertNotNull(loginPage.getLoginButton(), "Should not be null");
        // assertNotNull(loginPage.getCreateButton(), "Should not be null");
        // assertNotNull(loginPage.getAuto(), "Should not be null");
        // clickOn((Button) loginPage.getCreateButton());


        // check alphabetical sort
        /*
        ComboBox<String> filterButton = ((HomePageFooter) App.getUI().getRoot().getBottom()).getFilterButton();
        interact(() -> {
            filterButton.getSelectionModel().select(0);
        });
        */
        Controller.setFilterState("Breakfast");
        
        RecipeList rl = ((HomePageAppFrame) App.getUI().getRoot().getCenter()).getRecipeList();
        RecipeList rlNew = new RecipeList();
        for (int i = 0; i < rl.getChildren().size(); i++) {
            if (RecipeManager.searchRecipeByID("test", ((RecipeTitle) rl.getChildren().get(i)).getID()).get("Meal Type") == "Breakfast") {
                RecipeTitle rt = new RecipeTitle((String) RecipeManager.searchRecipeByID("test", ((RecipeTitle) rl.getChildren().get(i)).getID()).get("Title"), "Breakfast");
                rlNew.getChildren().add(rt);
            }
        }

        for (int i = 0; i < rlNew.getChildren().size();i++) {
            assertEquals(((RecipeTitle)rl.getChildren().get(i)).getRecipeTitle(), ((RecipeTitle) rlNew.getChildren().get(i)).getRecipeTitle());
        }
        
        // check sort
        SortAlphabetically sortAlphabetically = new SortAlphabetically();
        Controller.setSortState("A-Z");
        RecipeList rlSort = ((HomePageAppFrame) App.getUI().getRoot().getCenter()).getRecipeList();
        assertEquals(1, sortAlphabetically.compare((((RecipeTitle) rlSort.getChildren().get(0))), (((RecipeTitle) rlSort.getChildren().get(1)))), "title should come before title1");
        assertEquals(-1, sortAlphabetically.compare((((RecipeTitle) rlSort.getChildren().get(1))), (((RecipeTitle) rlSort.getChildren().get(0)))), "title1 should come before title");
        assertEquals(0, sortAlphabetically.compare(new RecipeTitle(null,null), (((RecipeTitle) rlSort.getChildren().get(1)))), "Null title should be handled properly");
        
        // testing mock server 
        MockServer.turnOff();
        assertFalse(MockServer.getStatus());
        MockServer.turnOn();
    }
}