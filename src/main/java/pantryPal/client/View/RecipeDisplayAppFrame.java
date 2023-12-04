package pantryPal.client.View;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RecipeDisplayAppFrame extends BorderPane {

    private ReturnHeader header;
    private Button backButton,editButton,deleteButton, saveButton, regenerateButton, logoutButton, shareButton;
    private TextArea title, ingredients, steps;
    private String id;
    private Boolean editable = false;
    private RecipeDisplay recipe;
    private String img;
    private String mealType;

    public RecipeDisplayAppFrame(RecipeDisplay r) {

        header = new ReturnHeader();
        backButton = header.getBackButton();
        logoutButton = header.getLogoutButton();
        recipe = r;

        this.id = r.getId();
        editButton = recipe.getEditButton();
        deleteButton = recipe.getDeleteButton();
        saveButton = recipe.getSaveButton();
        regenerateButton = recipe.getRegenerateButton();
        shareButton = recipe.getShareButton();
        title = recipe.getTitle();
        mealType = recipe.getMealType();
        ingredients = recipe.getIngredients();
        steps = recipe.getSteps();
        img = recipe.getImage();
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

    public String getStringSteps() {
        return this.steps.getText();
    }

    public String getStringTitle() {
        return this.title.getText();
    }

    public String getStringIngredients() {
        return this.ingredients.getText();
    }

    public String getMealType(){
        return mealType;
    }

    public String getImage(){
        return img;
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

    public void setRegenerateButtonAction(EventHandler<ActionEvent> eventHandler) {
        regenerateButton.setOnAction(eventHandler);
    }

    public void setLogoutButtonAction(EventHandler<ActionEvent> eventHandler) {
        logoutButton.setOnAction(eventHandler);
    }

    public void setShareButtonAction(EventHandler<ActionEvent> eventHandler) {
        shareButton.setOnAction(eventHandler);
    }

    
}
