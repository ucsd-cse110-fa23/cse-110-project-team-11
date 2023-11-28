package pantryPal.client.View;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.text.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class RecipeTitle extends BorderPane {
	private String id = null;
    private Label index;
    private TextField title, mealType;
    private Button viewButton;
    private RecipeDisplayAppFrame recipeDetail;
    
    /**
     * Constructor for generating format and recipe page. Handles indexing of the recipes
     */
    public RecipeTitle (String recipeTitle, String meal) {
        this.setPrefSize(500, 20); 
        
        this.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0; -fx-font-weight: bold;");

        index = new Label();
        index.setText(""); 
        index.setPrefSize(40, 20); 
        index.setTextAlignment(TextAlignment.LEFT); 
        index.setPadding(new Insets(10, 0, 10, 0)); 
        this.setLeft(index);

        title = new TextField(recipeTitle); 
        title.setPrefSize(380, 20); 
        title.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); 
        title.setPadding(new Insets(10, 0, 10, 0)); 
        this.setCenter(title);

        mealType = new TextField(meal); 
        mealType.setPrefSize(380, 20); 
        // mealType.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); 
        mealType.setPadding(new Insets(10, 0, 10, 0)); 
       // this.setRight(mealType);

        viewButton = new Button("View"); 
        viewButton.setPrefSize(100, 20);
        viewButton.setPrefHeight(Double.MAX_VALUE);
        // this.setRight(viewButton);

        VBox tags = new VBox(mealType, viewButton);
        tags.setAlignment(Pos.CENTER_RIGHT);
        tags.setSpacing(15);
        tags.setPrefWidth(300);
        tags.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); 

    }

    public RecipeTitle (String idString, String recipeTitle, RecipeDisplayAppFrame recDet, String meal) {
        this.id = idString;
        this.setPrefSize(500, 20); // sets size of task
        this.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0; -fx-font-weight: bold;");

        recipeDetail = recDet;
        
        index = new Label();
        index.setText(""); 
        index.setPrefSize(40, 20); 
        index.setTextAlignment(TextAlignment.CENTER); 
        index.setPadding(new Insets(10, 0, 10, 0)); 
        this.setLeft(index); 

        mealType = new TextField(meal); 
        mealType.setPrefSize(380, 20); 
        mealType.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); 
        mealType.setPadding(new Insets(10, 0, 10, 0)); 

        title = new TextField(recipeTitle); 
        title.setEditable(false);
        title.setPrefSize(380, 20); 
        title.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); 
        index.setTextAlignment(TextAlignment.LEFT); 
        title.setPadding(new Insets(10, 0, 10, 0)); 
        this.setCenter(title); 

        viewButton = new Button("View"); 
        viewButton.setPrefSize(100, 20);
        viewButton.setPrefHeight(Double.MAX_VALUE);
        viewButton.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); 
        this.setRight(viewButton);
  
    }

    public void setViewButtonAction(EventHandler<ActionEvent> eventHandler) {
        viewButton.setOnAction(eventHandler);
    }

    public void setRecipeIndex(int num) {
        this.index.setText(num + ""); 
        this.title.setPromptText("Recipe " + num);
    }

    public TextField getTitle () {
        return title;
    }

    public Button getViewButton () {
        return viewButton;
    } 

    public RecipeDisplayAppFrame getRecipeDetail() {
        return recipeDetail;
    }  

    public String getRecipeTitle() {
        return title.toString();
    }

    public String getID() {
        return this.id;
    }
    // Getters for recipe metadata
    public void setID(String id) {
        this.id = id;
    }

}
