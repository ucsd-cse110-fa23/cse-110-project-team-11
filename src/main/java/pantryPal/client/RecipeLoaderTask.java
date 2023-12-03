package pantryPal.client;

import javafx.concurrent.Task;
import pantryPal.client.View.RecipeDisplay;
import pantryPal.client.View.RecipeDisplayAppFrame;
import pantryPal.client.View.RecipeTitle;

import java.io.IOException;

import org.json.JSONObject;

import javafx.application.Platform;

public class RecipeLoaderTask extends Task<RecipeTitle> {

    private final JSONObject recipe;
    private final boolean first;
    private final Model model;

    public RecipeLoaderTask(JSONObject recipe, boolean first, Model model) {
        this.recipe = recipe;
        this.first = first;
        this.model = model;
    }

    @Override
    protected RecipeTitle call() throws Exception {
        System.out.println("TETSTETS");
        String stringID = recipe.getJSONObject("_id").getString("$oid");
        String title = recipe.getString("title");
        String ingredients = recipe.getString("ingredients");
        String steps = recipe.getString("steps");
        String mealType = recipe.getString("mealType");
        String imgURL;

        if (first) {
            imgURL = recipe.getString("imageURL");
        } else {
            String imagePrompt = "Display the dish: " + title +
                    " like you are displaying it in a recipe book. Ingredients: " +
                    ingredients + ". Don't put too much emphasis on the ingredients and base it off the title mostly";

            imgURL = model.performRequest(imagePrompt, "DallE");
        }

        RecipeDisplay recipeDisplay = new RecipeDisplay(stringID, title, ingredients, steps, imgURL, mealType);
        RecipeDisplayAppFrame rec = new RecipeDisplayAppFrame(recipeDisplay);
        RecipeTitle recipeTitle = new RecipeTitle(stringID, title, rec, mealType);
        rec.setID(stringID);

        return recipeTitle;
        // recipeTitle.getViewButton().setOnAction(e1 -> {
        //     Platform.runLater(() -> {
        //         ui.getRoot().setCenter(recipeTitle.getRecipeDetail());
        //         ui.getRoot().setTop(recipeTitle.getRecipeDetail().getRecipeDisplayHeader());
        //         ui.getRoot().setBottom(null);

        //         rd = rec;

        //         rec.setEditButtonAction(() -> callback.handleEditButton());
        //         rec.setSaveButtonAction(() -> callback.handleSaveButton());
        //         rec.setDeleteButtonAction(this::handleDeleteButton);
        //         rec.setShareButtonAction(event -> {
        //             try {
        //                 handleShareButton(event);
        //             } catch (IOException e) {
        //                 e.printStackTrace();
        //             }
        //         });
        //     });
        // });

        // Platform.runLater(() -> {
        //     hp.getRecipeList().getChildren().add(recipeTitle);
        //     recipeTitle.getRecipeDetail().setBackButtonAction2(this::handleBackButton2);
        //     recipeTitle.getRecipeDetail().setLogoutButtonAction(this::handleLogoutButton);
        // });
    }
}