package pantryPal.client.View;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class RecipeDisplayAppFrame extends BorderPane {

    private ReturnHeader header;
    private Button backButton,editButton,deleteButton, saveButton;
    private TextArea title, ingredients, steps;
    private String id;
    private Boolean editable = false;
    private RecipeDisplay recipe;

    public RecipeDisplayAppFrame(RecipeDisplay r) {

        header = new ReturnHeader();
        backButton = header.getBackButton();
        recipe = r;

        this.id = r.getId();
        editButton = recipe.getEditButton();
        deleteButton = recipe.getDeleteButton();
        saveButton = recipe.getSaveButton();
        title = recipe.getTitle();
        System.out.println(title.getText());
        ingredients = recipe.getIngredients();
        System.out.println(ingredients.getText());
        steps = recipe.getSteps();
        System.out.println(steps.getText());
        ScrollPane scrollPane = new ScrollPane(recipe);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        
        this.setCenter(scrollPane);
    }

    public RecipeDisplay getRecipe(){
        return recipe;
    }
    public void setRecipe(RecipeDisplay r){
        recipe = r;
    }
    public boolean getEditable() {
        return editable;
    }
    public void setEditable(boolean e) {
        editable = e;
    }

    public TextArea getTitle() {
        return title;
    }

    public TextArea getIngredients() {
        return ingredients;
    }

    public TextArea getSteps() {
        return steps;
    }

    public Button getEditButton() {
        return editButton;
    }

    public ReturnHeader getRecipeDisplayHeader() {
        return header;
    }
    public String getID(){
        return this.id;
    }

    public void setID(String id){
        this.id = id;
    }

    public void setBackButtonAction2(EventHandler<ActionEvent> eventHandler) {
        backButton.setOnAction(eventHandler);
    }

    public void setDeleteButtonAction(EventHandler<ActionEvent> eventHandler) {
        deleteButton.setOnAction(eventHandler);
    }

    public void setEditButtonAction(EventHandler<ActionEvent> eventHandler) {
        editButton.setOnAction(eventHandler);
    }
    public void setSaveButtonAction(EventHandler<ActionEvent> eventHandler) {
        saveButton.setOnAction(eventHandler);
    }
}
