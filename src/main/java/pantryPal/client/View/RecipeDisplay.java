package pantryPal.client.View;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;

/** RecipeDisplay: window that shows the detailed recipe. 
 * SHOULD get the information from the CSV S
 */
public class RecipeDisplay extends BorderPane {
    private String id = null;
    private TextArea title, ingredients, steps;
    private Button editButton, saveButton, deleteButton, regenerateButton, shareButton;
    private String imgURL = "https://blog.teamtreehouse.com/wp-content/uploads/2015/05/InternetSlowdown_Day.gif";
    private ImageView mealImage;
    private String mealType;
    
    public RecipeDisplay() {
        this.setPrefSize(500, 20); // sets size of task
        this.setStyle("-fx-background-color: #FFFFFF; -fx-border-width: 0; -fx-font-weight: bold;"); // sets background color of task

        title = new TextArea(); // create task name text 
        title.setEditable(false);
        title.setPrefSize(230, 20); // set size of text field
        title.setStyle("-fx-background-color: #008080; -fx-border-width: 0;"); // set background color of textfield, yellow

        
        ingredients = new TextArea(); // create task name text field
        ingredients.setWrapText(true);
        ingredients.setEditable(false);
        ingredients.setStyle("-fx-background-color: #008080; -fx-border-width: 0;"); // set background color of textfield, yellow
        
        steps = new TextArea(); // create task name text field
        steps.setWrapText(true);
        steps.setEditable(false);
        steps.setStyle("-fx-background-color: #008080; -fx-border-width: 0;"); // set background color of textfield, yellow

        mealImage = new ImageView(new Image(imgURL));
        mealImage.setPreserveRatio(true);
        
        editButton = new Button();
        editButton.setPrefSize(50,30);
        editButton.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); // light blue
        editButton.setAlignment(Pos.CENTER);
        ImageView editImage = new ImageView(new Image("file:graphics/e2.png"));
        editImage.setPreserveRatio(true);
        editImage.setFitHeight(25);
        editImage.setFitWidth(45);
        editButton.setGraphic(editImage);

        saveButton = new Button();
        saveButton.setPrefSize(50,30);
        saveButton.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;");
        saveButton.setAlignment(Pos.CENTER);
        ImageView saveImage = new ImageView(new Image("file:graphics/s2.png"));
        saveImage.setPreserveRatio(true);
        saveImage.setFitHeight(25);
        saveImage.setFitWidth(45);
        saveButton.setGraphic(saveImage);

        deleteButton = new Button();
        deleteButton.setPrefSize(50,30);
        deleteButton.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;");
        deleteButton.setAlignment(Pos.CENTER);
        ImageView delImage = new ImageView(new Image("file:graphics/d2.png"));
        delImage.setPreserveRatio(true);
        delImage.setFitHeight(25);
        delImage.setFitWidth(45);
        deleteButton.setGraphic(delImage);

        shareButton = new Button();
                /*

        shareButton.setPrefSize(50,30);
        shareButton.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;");
        shareButton.setAlignment(Pos.CENTER);
        ImageView shareImage = new ImageView(new Image("file:graphics/share.png"));
        shareImage.setPreserveRatio(true);
        shareImage.setFitHeight(25);
        shareImage.setFitWidth(45);
        shareButton.setGraphic(shareImage);
        */

        regenerateButton = new Button();
        regenerateButton.setPrefSize(50,30);
        regenerateButton.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;");
        regenerateButton.setAlignment(Pos.CENTER);
        ImageView reloadImage = new ImageView(new Image("file:graphics/r2.png"));
        reloadImage.setPreserveRatio(true);
        reloadImage.setFitHeight(25);
        reloadImage.setFitWidth(45);
        regenerateButton.setGraphic(reloadImage);

        HBox headerBox = new HBox(title, editButton, saveButton, deleteButton, regenerateButton); // orange
        headerBox.setSpacing(30);
        headerBox.setStyle("-fx-background-color: #008080; -fx-border-width: 0;"); // teal
        headerBox.setAlignment(Pos.TOP_CENTER);
        this.setTop(headerBox);

        VBox imageBox = new VBox(mealImage);
        imageBox.setStyle("-fx-background-color: #FFFFFF;");
        imageBox.setAlignment(Pos.CENTER);
        imageBox.setPadding(new Insets(5));
        
        VBox leftBox = new VBox(imageBox, ingredients); // dark greenish gray
        leftBox.setPadding(new Insets(5));
        leftBox.setAlignment(Pos.TOP_CENTER);
        leftBox.setStyle("-fx-background-color: #FFFFFF; -fx-border-width: 0;"); // light green
 
        VBox rightBox = new VBox(steps); // white
        rightBox.setPadding(new Insets(5));
        rightBox.setStyle("-fx-background-color: #FFFFFF; -fx-border-width: 0;"); // light purple
        rightBox.setAlignment(Pos.TOP_CENTER);

        mealImage.fitWidthProperty().bind(leftBox.widthProperty().divide(2));
        

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        GridPane center = new GridPane();
        center.getColumnConstraints().addAll(col1,col2);
        center.addRow(0, leftBox, rightBox);
        center.setVgrow(leftBox, Priority.ALWAYS);
        center.setVgrow(rightBox, Priority.ALWAYS);
        ingredients.prefHeightProperty().bind(center.heightProperty());
        steps.prefHeightProperty().bind(center.heightProperty());

        center.setPadding(new Insets(10, 10, 10, 10));
        this.setCenter(center);
    }

    public RecipeDisplay(String idString, String titleString, String ingredientString, String stepsString, String imageURL, String mType) {
        this.id = idString;

        this.mealType = mType;

        this.setPrefSize(500, 20); // sets size of task
        this.setStyle("-fx-background-color: #FFFFFF; -fx-border-width: 0; -fx-font-weight: bold;"); // sets background color of task
        
        title = new TextArea(titleString); // create task name text field
        title.setEditable(false);
        title.setPrefSize(230, 20); // set size of text field
        title.setStyle("-fx-background-color: #008080; -fx-border-width: 0;"); // set background color of textfield, yellow

        ingredients = new TextArea(ingredientString); // create task name text field
        ingredients.setWrapText(true);
        ingredients.setEditable(false);
        ingredients.setStyle("-fx-background-color: #008080; -fx-border-width: 0;"); // set background color of textfield, yellow
        ScrollPane scrollPane = new ScrollPane(ingredients);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        
        steps = new TextArea(stepsString); // create task name text field
        steps.setWrapText(true);
        steps.setEditable(false);
        steps.setStyle("-fx-background-color: #008080; -fx-border-width: 0;"); // set background color of textfield, yellow

        imgURL = imageURL;
        mealImage = new ImageView(new Image(imgURL));
        mealImage.setPreserveRatio(true);

        editButton = new Button();
        editButton.setPrefSize(50,30);
        editButton.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); // light blue
        editButton.setAlignment(Pos.CENTER);
        ImageView editImage = new ImageView(new Image("file:graphics/e2.png"));
        editImage.setPreserveRatio(true);
        editImage.setFitHeight(25);
        editImage.setFitWidth(45);
        editButton.setGraphic(editImage);

        saveButton = new Button();
        saveButton.setPrefSize(50,30);
        saveButton.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;");
        saveButton.setAlignment(Pos.CENTER);
        ImageView saveImage = new ImageView(new Image("file:graphics/s2.png"));
        saveImage.setPreserveRatio(true);
        saveImage.setFitHeight(25);
        saveImage.setFitWidth(45);
        saveButton.setGraphic(saveImage);

        deleteButton = new Button();
        deleteButton.setPrefSize(50,30);
        deleteButton.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;");
        deleteButton.setAlignment(Pos.CENTER);
        ImageView delImage = new ImageView(new Image("file:graphics/d2.png"));
        delImage.setPreserveRatio(true);
        delImage.setFitHeight(25);
        delImage.setFitWidth(45);
        deleteButton.setGraphic(delImage);

        shareButton = new Button();
        shareButton.setPrefSize(50,30);
        shareButton.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;");
        shareButton.setAlignment(Pos.CENTER);
        ImageView shareImage = new ImageView(new Image("file:graphics/share.png"));
        shareImage.setPreserveRatio(true);
        shareImage.setFitHeight(25);
        shareImage.setFitWidth(45);
        shareButton.setGraphic(shareImage);

        HBox headerBox = new HBox(title, editButton, saveButton, deleteButton, shareButton); // orange
        headerBox.setSpacing(30);
        headerBox.setStyle("-fx-background-color: #008080; -fx-border-width: 0;"); // teal
        headerBox.setAlignment(Pos.TOP_CENTER);
        this.setTop(headerBox);

        VBox imageBox = new VBox(mealImage);
        imageBox.setStyle("-fx-background-color: #FFFFFF;");
        imageBox.setAlignment(Pos.CENTER);
        imageBox.setPadding(new Insets(5));

        VBox leftBox = new VBox(imageBox, ingredients); // dark greenish gray
        leftBox.setPadding(new Insets(5));
        leftBox.setAlignment(Pos.TOP_CENTER);
        leftBox.setStyle("-fx-background-color: #008080; -fx-border-width: 0;"); // light green
 
        VBox rightBox = new VBox(steps); // white
        rightBox.setPadding(new Insets(5));
        rightBox.setStyle("-fx-background-color: #008080; -fx-border-width: 0;"); // light purple
        rightBox.setAlignment(Pos.TOP_CENTER);

        mealImage.fitWidthProperty().bind(leftBox.widthProperty().divide(2));

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        GridPane center = new GridPane();
        center.getColumnConstraints().addAll(col1,col2);
        center.addRow(0, leftBox, rightBox);
        center.setVgrow(leftBox, Priority.ALWAYS);
        center.setVgrow(rightBox, Priority.ALWAYS);
        ingredients.prefHeightProperty().bind(center.heightProperty());
        steps.prefHeightProperty().bind(center.heightProperty());

        center.setPadding(new Insets(10, 10, 10, 10));

        this.setCenter(center);
    }

    public String getID() {
        return this.id;
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

    public Button getRegenerateButton() {
        return this.regenerateButton;
    }

    public Button getShareButton() {
        return this.shareButton;
    }

    public TextArea getTitle() {
        return this.title;
    }

    public TextArea getIngredients() {
        return this.ingredients;
    }

    public String getImage(){
        return this.imgURL;
    }

    public TextArea getSteps() {
        return this.steps;
    }

    public String getMealType(){
        return this.mealType;
    }

    public String getStringTitle() {
        return this.title.getText();
    }

    public String getStringIngredients() {
        return this.ingredients.getText();
    }

    public void setMealType(String type){
        this.mealType = type;
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

    public void setImage(String imgURL){
        this.imgURL = imgURL;
        this.mealImage.setImage(new Image(imgURL));
    }

   
}

