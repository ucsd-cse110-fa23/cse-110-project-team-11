// package test.java;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.mongodb.client.result.UpdateResult;

import javafx.application.Application;
import javafx.application.Platform;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;  // Import the IOException class to handle errors
import java.util.ArrayList;

import pantryPal.client.RecipeManager;
import pantryPal.client.App;
import pantryPal.client.View.HomePageAppFrame;
import pantryPal.client.View.InputAppFrame;
import pantryPal.client.View.RecipeDisplay;
import pantryPal.client.View.RecipeDisplayAppFrame;
import pantryPal.client.View.RecipeTitle;

public class RecipeManagerTest {

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


    @Test
    public void testLoad() throws InterruptedException {

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {

                    setUpClass();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                                        InputAppFrame ip = new InputAppFrame();
                    HomePageAppFrame home = new HomePageAppFrame(ip);

                    ArrayList<String[]> recipes = RecipeManager.loadRecipes();
                    for(int i = 0; i < recipes.size(); i++){
                        String mealType = recipes.get(i)[0];
                        String stringID = recipes.get(i)[1];
                        String title = recipes.get(i)[2];
                        String ingredients = recipes.get(i)[3];
                        String steps = recipes.get(i)[4];
                        String imgURL = recipes.get(i)[5];
                        
                        RecipeDisplay recipeDisplay = new RecipeDisplay(stringID, title, ingredients, steps, imgURL, mealType);
                        RecipeDisplayAppFrame rec = new RecipeDisplayAppFrame(recipeDisplay);
                        RecipeTitle recipeTitle = new RecipeTitle(stringID, title, rec, mealType);
                        // recipes.get(i).setViewButtonAction(this::handleViewButton);
                        // RecipeTitle title = recipes.get(i);
                        // System.out.println(title.getID());
                        // RecipeDisplayAppFrame recDisp = title.getRecipeDetail();
                        rec.setID(recipeTitle.getID());
                        System.out.println(rec.getID());
                        home.getRecipeList().getChildren().add(recipeTitle);
                    }
                    assertEquals(home.getRecipeList().getChildren().size(), RecipeManager.loadRecipes().size());

                    }
                });
            }
        });
        thread.start();// Initialize the thread
        Thread.sleep(3000); // Time to use the app, with out this, the thread
    }

     @Test

    void testInsert() throws IOException {
        RecipeManager.insertRecipe("breakfast","test test test test test", "Test Ingredients", "Test Steps", "TESTURL");
        //Document doc = RecipeManager.searchRecipe("Apple Pie");
        long deletedCount = RecipeManager.deleteRecipe("test test test test test").getDeletedCount();
        assertEquals(deletedCount, 1);
    }

    //@Test
    void testUpdate() throws IOException {
        RecipeManager.insertRecipe("breakfast","please do not insert me", "no", "no", "TESTURL");
        UpdateResult res = RecipeManager.updateRecipe("please do not insert me", "updated", "updated", "0");
        RecipeManager.deleteRecipe("please do not insert me");
        assertEquals(res.getModifiedCount(),1);
    }

    //@Test 
    void testUpdateToRecipeNotInDB() throws IOException {
        UpdateResult res = RecipeManager.updateRecipe("please do not insert me", "updated", "updated", "0");
        RecipeManager.deleteRecipe("please do not insert me");
        assertEquals(res.getModifiedCount(),0);
    }

    @Test
    void testDelete() throws IOException {
        long deletedCount = RecipeManager.deleteRecipe("DO NOT INSERT ME").getDeletedCount();
        assertEquals(deletedCount, 0);
    }
}