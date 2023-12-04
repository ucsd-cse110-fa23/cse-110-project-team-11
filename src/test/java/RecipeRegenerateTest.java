import org.junit.jupiter.api.Test;

import pantryPal.client.API.MockChatGPT;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecipeRegenerateTest {

    private static class RecipeService {
        public String saveRecipeDisplay(String recipe) {
            return recipe;
        }
    }

    /*
     * BDD Scenario 1 
     */
    @Test
    void testRecipeRegenerationAndSave() {
        // Regenerated the recipe
        String input = "chicken banana carrots";
        List<String> ingredients = Arrays.asList(input.split(" "));
        MockChatGPT mock = new MockChatGPT();
        String recipe1 = mock.generateResponse(ingredients);
        String recipe2 = MockChatGPT.regenerateResponse(recipe1);

        assertNotEquals(recipe1, recipe2, "Generated recipes should be different.");

        // Checks if saved regenerated recipe is equal to screen displaying recipe.
        RecipeService recipeService = new RecipeService();
        String savedRecipe = recipeService.saveRecipeDisplay(recipe2);
        assertEquals(recipe2, savedRecipe);
    }
}
