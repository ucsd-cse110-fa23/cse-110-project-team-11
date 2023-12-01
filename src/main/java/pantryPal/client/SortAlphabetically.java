package pantryPal.client;

import java.util.Comparator;
import javafx.scene.Node;
import pantryPal.client.View.RecipeTitle;

public class SortAlphabetically implements Comparator<Node> {
    @Override
    public int compare(Node a, Node b) {
        // Check if both objects are instances of RecipeTitle
        System.out.println("hi");
        if (a instanceof RecipeTitle && b instanceof RecipeTitle) {
            RecipeTitle recipeA = (RecipeTitle) a;
            RecipeTitle recipeB = (RecipeTitle) b;

            // Additional null checks can be added if necessary
            if (recipeA.getRecipeTitle() == null || recipeB.getRecipeTitle() == null) {
                System.out.println("Null title found");
                return 0; // Or handle as appropriate
            }

            // Now safely compare the titles
            System.out.println(recipeA.getRecipeTitle() + ", " + recipeB.getRecipeTitle() + " is " + recipeA.getRecipeTitle().compareToIgnoreCase(recipeB.getRecipeTitle()));
            return recipeA.getRecipeTitle().compareToIgnoreCase(recipeB.getRecipeTitle());
        } else {
            // Handle the case where either a or b is not a RecipeTitle
            System.out.println("Type mismatch: " + a.getClass() + ", " + b.getClass());
            return 0; // Or handle as appropriate
        }
    }
}
