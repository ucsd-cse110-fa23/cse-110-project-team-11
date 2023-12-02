// package test.java;

import org.json.JSONArray;
import org.json.JSONObject;
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

                    JSONArray recipes = RecipeManager.loadRecipes("recipes");
                    for(int i = 0; i < recipes.length(); i++){
                        JSONObject recipe = recipes.getJSONObject(i);
                        String mealType = recipe.getString("mealType");
                        String stringID = recipe.getString("id");
                        String title = recipe.getString("title");
                        String ingredients = recipe.getString("ingredients");
                        String steps = recipe.getString("steps");
                        String imgURL = recipe.getString("imgURL");
                        
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
                    assertEquals(home.getRecipeList().getChildren().size(), RecipeManager.loadRecipes("UserID"));

                    }
                });
            }
        });
        thread.start();// Initialize the thread
        Thread.sleep(3000); // Time to use the app, with out this, the thread
    }

    @Test
    void testInsert() throws IOException {
        //public static String[] insertRecipe(String username, String mealType, String title, String ingredients, String steps, String imgURL) throws IOException{

        RecipeManager.insertRecipe("recipes","dinner","Mango Rice", "Test Ingredients", "Test Steps", "TESTURL");
        long deletedCount = RecipeManager.deleteRecipe("recipes", "Mango Rice").getDeletedCount();
        assertEquals(deletedCount, 1);
    }

    @Test
    void testUpdate() throws IOException {
        RecipeManager.insertRecipe("recipes","dinner","Mango Rice", "Test Ingredients", "Test Steps", "TESTURL");
        UpdateResult res = RecipeManager.updateRecipe("recipes", "Mango Rice", "test,test,test","1,2,3", "0");
        //RecipeManager.deleteRecipe("recipes", "Mango Rice");
        assertEquals(res.getModifiedCount(),1);
    }

    @Test 
    void testUpdateToRecipeNotInDB() throws IOException {
        UpdateResult res = RecipeManager.updateRecipe("please do not insert me", "Mango Rice", "updated","123", "0");
        RecipeManager.deleteRecipe("recipes", "Mango Rice");
        assertEquals(res.getModifiedCount(),0);
    }

    @Test
    void testDelete() throws IOException {
        long deletedCount = RecipeManager.deleteRecipe("recipes", "Mango Rice").getDeletedCount();
        assertEquals(deletedCount, 0);
    }
}