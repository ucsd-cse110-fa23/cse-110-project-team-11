package pantryPal.client;

import java.util.Comparator;
import javafx.scene.Node;
import pantryPal.client.View.RecipeTitle;

public class SortAlphabetically implements Comparator<Node> {
    @Override
    public int compare(Node a, Node b) {
        // Check if both objects are instances of RecipeTitle
        
        if (a instanceof RecipeTitle && b instanceof RecipeTitle) {
            RecipeTitle recipeA = (RecipeTitle) a;
            RecipeTitle recipeB = (RecipeTitle) b;

            // Additional null checks can be added if necessary
            if (recipeA.getRecipeTitle() == null || recipeB.getRecipeTitle() == null) {
                
                return 0; // Or handle as appropriate
            }

            // Now safely compare the titles

            return recipeA.getRecipeTitle().compareToIgnoreCase(recipeB.getRecipeTitle());
        } else {
            // Handle the case where either a or b is not a RecipeTitle
            return 0; // Or handle as appropriate
        }
    }
}
