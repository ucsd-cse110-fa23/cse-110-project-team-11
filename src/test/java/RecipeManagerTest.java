// package test.java;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import pantryPal.RecipeManager;
import pantryPal.View.HomePageAppFrame;
import pantryPal.View.InputAppFrame;

public class RecipeManagerTest {

    @Test
    void testLoad() throws IOException {        
        InputAppFrame ip = new InputAppFrame();
        HomePageAppFrame home = new HomePageAppFrame(ip);
        assertEquals(home.getRecipeList().getChildren().size(), RecipeManager.loadRecipes().size());
    }

     @Test
    void testSearch() throws IOException {
        RecipeManager.insertRecipe("Apple Pie", "Test Ingredients", "Test Steps");
        assertEquals(RecipeManager.searchRecipe("Apple Pie"), null);
    }

    @Test
    void testInsert() throws IOException {
        RecipeManager.insertRecipe("Cookies", "Test Ingredients", "Test Steps");
        assertNotEquals(RecipeManager.searchRecipe("Cookies"), null);
    }

    @Test
    void testUpdate() throws IOException {
        RecipeManager.updateRecipe("Cookies", "- Butter \n - Sugar \n - Flour", "#1 Mix Ingredients \n #2 Bake");
        assertEquals(RecipeManager.searchRecipe("Cookies").get("steps"), "#1 Mix Ingredients \n #2 Bake");
    }

    @Test
    void testDelete() throws IOException {
        RecipeManager.deleteRecipe("Cookies");
        assertEquals(RecipeManager.searchRecipe("Cookies"), null);
    }
}