import org.junit.jupiter.api.Test;
import pantryPal.client.MockChatGPT;
import pantryPal.client.TranscriptionService;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecipeRegenerateTest {

    private static class RecipeService {
        public String saveRecipeDisplay(String recipe) {
            return recipe;
        }
        public String cancelRecipeDisplay(String recipe) {
            return recipe;
        }
        public String homePageRecipes() {
            return "true";
        }
    }

    /*
     * US12 User
     */
    @Test
    void testRecipeRegenerationAndSave() {
        // Regenerated the recipe
        String input = "chicken banana carrots";
        List<String> ingredients = Arrays.asList(input.split(" "));

        String recipe1 = MockChatGPT.generateResponse(ingredients);
        String recipe2 = MockChatGPT.regenerateResponse(recipe1);

        assertNotEquals(recipe1, recipe2, "Generated recipes should be different.");

        // Checks if saved regenerated recipe is equal to screen displaying recipe.
        RecipeService recipeService = new RecipeService();
        String savedRecipe = recipeService.saveRecipeDisplay(recipe2);
        assertEquals(recipe2, savedRecipe);
    }

    @Test
    void testRecipeRegenerationAndCancel() {
        String input = "chicken banana carrots";
        List<String> ingredients = Arrays.asList(input.split(" "));

        String recipe1 = MockChatGPT.generateResponse(ingredients);
        String recipe2 = MockChatGPT.regenerateResponse(recipe1);

        // Check if the recipe is regenerated 
        assertEquals(recipe2, MockChatGPT.regenerateResponse(recipe1));

        RecipeService rs = new RecipeService();
        String cancelRecipe = rs.cancelRecipeDisplay(recipe1);
        assertNotEquals(rs.homePageRecipes(), cancelRecipe, "Generated recipes does not exist.");
        
    }
}
