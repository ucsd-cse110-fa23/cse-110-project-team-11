// package test.java;

import org.bson.Document;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javafx.application.Application;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import pantryPal.RecipeManager;
import pantryPal.View.App;
import pantryPal.View.HomePageAppFrame;
import pantryPal.View.InputAppFrame;

public class RecipeManagerTest {

    @BeforeAll 
    public static void setUpClass() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            Application.launch(App.class,new String[0]); 
            }
        });
        thread.start();// Initialize the thread
        Thread.sleep(1);
    }

    @Test
    void testLoad() throws IOException {        
        InputAppFrame ip = new InputAppFrame();
        HomePageAppFrame home = new HomePageAppFrame(ip);
        assertEquals(home.getRecipeList().getChildren().size(), RecipeManager.loadRecipes().size());
    }

     @Test
    void testInsert() throws IOException {
        RecipeManager.insertRecipe("Apple Pie", "Test Ingredients", "Test Steps");
        Document doc = RecipeManager.searchRecipe("Apple Pie");
        assertNotEquals(doc, null);
    }

    @Test
    void testSearch() throws IOException {
        Document doc = RecipeManager.searchRecipe("Apple Pie");
        assertNotEquals(doc, null);
    }

    @Test
    void testUpdate() throws IOException {
        RecipeManager.updateRecipe("Cookies", "- Butter \n - Sugar \n - Flour", "#1 Mix Ingredients \n #2 Bake");
        assertEquals(RecipeManager.searchRecipe("Cookies").get("steps"), "#1 Mix Ingredients \n #2 Bake");
    }

    @Test
    void testDelete() throws IOException {
        RecipeManager.deleteRecipe("Cookies");
        Document d= RecipeManager.searchRecipe("Cookies");
        assertEquals(d, null);
    }
}