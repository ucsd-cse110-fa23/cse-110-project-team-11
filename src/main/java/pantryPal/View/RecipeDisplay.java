package pantryPal.View;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.control.TextArea;
import javafx.geometry.Insets;

/** RecipeDisplay: window that shows the detailed recipe. 
 * SHOULD get the information from the CSV S
 */
public class RecipeDisplay extends VBox {
    private String id = null;
    private TextArea title, ingredients, steps;
    private Button editButton, saveButton, deleteButton;


    public RecipeDisplay() {
        this.setPrefSize(500, 20); // sets size of task
        this.setStyle("-fx-background-color: #FFFFFF; -fx-border-width: 0; -fx-font-weight: bold;"); // sets background color of task
        
        title = new TextArea(); // create task name text 
        title.setEditable(false);
        title.setPrefSize(230, 20); // set size of text field
        title.setStyle("-fx-background-color: #FFFF00; -fx-border-width: 0;"); // set background color of textfield, yellow
        // title.setPadding(new Insets(10, 0, 10, 0)); // adds some padding to the text field
        // title.setPromptText("Name");
        this.getChildren().add(title); // add textlabel to task

        ingredients = new TextArea(); // create task name text field
        ingredients.setWrapText(true);
        ingredients.setEditable(false);
        ingredients.setPrefSize(200,800); // set size of text field
        ingredients.setStyle("-fx-background-color: #FFFF00; -fx-border-width: 0;"); // set background color of textfield, yellow
        // ingredients.setPadding(new Insets(0, 0, 0, 0)); // adds some padding to the text field
        // ingredients.setPromptText("1. ingredient");
        this.getChildren().add(ingredients); // add textlabel to task
        
        steps = new TextArea(); // create task name text field
        steps.setWrapText(true);
        steps.setEditable(false);
        steps.setPrefSize(400, 800); // set size of text field
        steps.setStyle("-fx-background-color: #FFFF00; -fx-border-width: 0;"); // set background color of textfield, yellow
        // steps.setPadding(new Insets(0, 0, 0, 0)); // adds some padding to the text field
        // steps.setPromptText("Step 1");
        this.getChildren().add(steps); // add textlabel to task

        editButton = new Button("Edit");
        editButton.setPrefSize(50,30);
        editButton.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); // light blue
        editButton.setAlignment(Pos.CENTER);
        this.getChildren().add(editButton);

        saveButton = new Button("Save");
        saveButton.setPrefSize(50,30);
        saveButton.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;");
        saveButton.setAlignment(Pos.CENTER);
        this.getChildren().add(saveButton);

        deleteButton = new Button("Delete");
        deleteButton.setPrefSize(50,30);
        deleteButton.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;");
        deleteButton.setAlignment(Pos.CENTER);
        this.getChildren().add(deleteButton);

        HBox headerBox = new HBox(title, editButton, saveButton, deleteButton); // orange
        headerBox.setSpacing(30);
        headerBox.setStyle("-fx-background-color: #008080; -fx-border-width: 0;"); // teal
        headerBox.setAlignment(Pos.TOP_CENTER);
        this.getChildren().add(headerBox);

        VBox leftBox = new VBox(ingredients); // dark greenish gray
        leftBox.setSpacing(10);
        leftBox.setAlignment(Pos.TOP_CENTER);
        this.getChildren().add(leftBox);
        leftBox.setStyle("-fx-background-color: #CCFFCC; -fx-border-width: 0;"); // light green
 
        VBox rightBox = new VBox(steps); // white
        rightBox.setSpacing(0);
        this.getChildren().add(rightBox);
        rightBox.setStyle("-fx-background-color: #CCCCFF; -fx-border-width: 0;"); // light purple
        rightBox.setAlignment(Pos.TOP_CENTER);

        HBox contentBox = new HBox(leftBox, rightBox);
        contentBox.setSpacing(0);
        contentBox.setAlignment(Pos.TOP_CENTER);
        contentBox.setPadding(new Insets(0, 0, 0, 0)); // adds some padding to the text field
        this.getChildren().add(contentBox);
        contentBox.setStyle("-fx-background-color: #FFCCE5; -fx-border-width: 0;"); // light pin
    }

    public RecipeDisplay(String idString, String titleString, String ingredientString, String stepsString) {
        this.id = idString;

        this.setPrefSize(500, 20); // sets size of task
        this.setStyle("-fx-background-color: #FFFFFF; -fx-border-width: 0; -fx-font-weight: bold;"); // sets background color of task
        
        title = new TextArea(titleString); // create task name text field
        title.setEditable(false);
        title.setPrefSize(230, 20); // set size of text field
        title.setStyle("-fx-background-color: #FFFF00; -fx-border-width: 0;"); // set background color of textfield, yellow
        // title.setPadding(new Insets(10, 0, 10, 0)); // adds some padding to the text field
        // title.setPromptText("Name");
        this.getChildren().add(title); // add textlabel to task

        ingredients = new TextArea(ingredientString); // create task name text field
        ingredients.setWrapText(true);
        ingredients.setEditable(false);
        ingredients.setPrefSize(200,800); // set size of text field
        ingredients.setStyle("-fx-background-color: #FFFF00; -fx-border-width: 0;"); // set background color of textfield, yellow
        // ingredients.setPadding(new Insets(0, 0, 0, 0)); // adds some padding to the text field
        // ingredients.setPromptText("1. ingredient");
        this.getChildren().add(ingredients); // add textlabel to task
        
        steps = new TextArea(stepsString); // create task name text field
        steps.setWrapText(true);
        steps.setEditable(false);
        steps.setPrefSize(400, 800); // set size of text field
        steps.setStyle("-fx-background-color: #FFFF00; -fx-border-width: 0;"); // set background color of textfield, yellow
        // steps.setPadding(new Insets(0, 0, 0, 0)); // adds some padding to the text field
        // steps.setPromptText("Step 1");
        this.getChildren().add(steps); // add textlabel to task

        editButton = new Button("Edit");
        editButton.setPrefSize(50,30);
        editButton.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); // light blue
        editButton.setAlignment(Pos.CENTER);
        this.getChildren().add(editButton);

        saveButton = new Button("Save");
        saveButton.setPrefSize(50,30);
        saveButton.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;");
        saveButton.setAlignment(Pos.CENTER);
        this.getChildren().add(saveButton);

        deleteButton = new Button("Delete");
        deleteButton.setPrefSize(50,30);
        deleteButton.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;");
        deleteButton.setAlignment(Pos.CENTER);
        this.getChildren().add(deleteButton);

        HBox headerBox = new HBox(title, editButton, saveButton, deleteButton); // orange
        headerBox.setSpacing(30);
        headerBox.setStyle("-fx-background-color: #008080; -fx-border-width: 0;"); // teal
        headerBox.setAlignment(Pos.TOP_CENTER);
        this.getChildren().add(headerBox);

        VBox leftBox = new VBox(ingredients); // dark greenish gray
        leftBox.setSpacing(10);
        leftBox.setAlignment(Pos.TOP_CENTER);
        this.getChildren().add(leftBox);
        leftBox.setStyle("-fx-background-color: #CCFFCC; -fx-border-width: 0;"); // light green
 
        VBox rightBox = new VBox(steps); // white
        rightBox.setSpacing(0);
        this.getChildren().add(rightBox);
        rightBox.setStyle("-fx-background-color: #CCCCFF; -fx-border-width: 0;"); // light purple
        rightBox.setAlignment(Pos.TOP_CENTER);

        HBox contentBox = new HBox(leftBox, rightBox);
        contentBox.setSpacing(0);
        contentBox.setAlignment(Pos.TOP_CENTER);
        contentBox.setPadding(new Insets(0, 0, 0, 0)); // adds some padding to the text field
        this.getChildren().add(contentBox);
        contentBox.setStyle("-fx-background-color: #FFCCE5; -fx-border-width: 0;"); // light pin
    }


    public Button getDeleteButton() {
        return this.deleteButton;
    }

    public Button getEditButton() {
        return this.editButton;
    }

    public Button getSaveButton() {
        return this.saveButton;
    }

    public TextArea getTitle() {
        return this.title;
    }

    public TextArea getIngredients() {
        return this.ingredients;
    }

    public TextArea getSteps() {
        return this.steps;
    }

    // Getters for recipe metadata
    public void setID(String id) {
        this.id = id;
    }
    
    public void setTitle(String title) {
        this.title.setText(title);

    }

    public void setIngreds(String ingreds) {
        this.ingredients.setText(ingreds);
    }

    public void setSteps(String steps) {
        this.steps.setText(steps);
    } 
}