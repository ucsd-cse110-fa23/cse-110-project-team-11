import javax.print.attribute.HashDocAttributeSet;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.scene.text.*;

import java.io.BufferedReader;
import java.io.FileReader;
class RecipeTitle extends HBox {
    private Label index;
    private TextField title;
    private Button viewButton;

    RecipeTitle () {
        this.setPrefSize(500, 20); // sets size of task
        this.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0; -fx-font-weight: bold;");

        index = new Label();
        index.setText(""); 
        index.setPrefSize(40, 20); 
        index.setTextAlignment(TextAlignment.CENTER); 
        index.setPadding(new Insets(10, 0, 10, 0)); 
        this.getChildren().add(index); 

        title = new TextField(); 
        title.setPrefSize(380, 20); 
        title.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); 
        index.setTextAlignment(TextAlignment.LEFT); 
        title.setPadding(new Insets(10, 0, 10, 0)); 
        this.getChildren().add(title); 

        viewButton = new Button("Done"); 
        viewButton.setPrefSize(100, 20);
        viewButton.setPrefHeight(Double.MAX_VALUE);
        viewButton.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); 
        this.getChildren().add(viewButton);
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
}

class RecipeList extends VBox {
    RecipeList() {
        this.setSpacing(5);
        this.setPrefSize(500, 560);
        this.setStyle("-fx-background-color: #F0F8FF;");
    }
}

class HomePageHeader extends HBox {
    private Button createButton;

    HomePageHeader() {
        this.setPrefSize(500, 60); // Size of the header
        this.setStyle("-fx-background-color: #F2F2F2;");
        this.setAlignment(Pos.TOP_CENTER);
        
        Text titleText = new Text("PantryPal"); // Text of the Header
        titleText.setStyle("-fx-font-weight: bold; -fx-font-size: 20;");
        
        this.getChildren().add(titleText);

        VBox pantryPal = new VBox(titleText);
        pantryPal.setAlignment(Pos.TOP_LEFT);
        pantryPal.setSpacing(15);
        pantryPal.setPrefWidth(300);// prefWidth
        this.getChildren().add(pantryPal);

        createButton = new Button("create");
        createButton.setPrefSize(100,50);
        this.getChildren().add(createButton);

        VBox buttonBox = new VBox(createButton);
        buttonBox.setAlignment(Pos.TOP_RIGHT);
        buttonBox.setSpacing(15);
        buttonBox.setPrefWidth(300);// prefWidth
        this.getChildren().add(buttonBox);
    }

    public Button getCreateButton() {
        return createButton;
    }
}

class HomePageAppFrame extends BorderPane{
    private HomePageHeader homePageHeader;
    private RecipeList recipeList;
    private Button viewButton, createButton;


    HomePageAppFrame(BorderPane InputPage, BorderPane DisplayPage) {
        homePageHeader = new HomePageHeader();
        recipeList = new RecipeList();
        createButton = homePageHeader.getCreateButton();    
        
        ScrollPane scrollPane = new ScrollPane(recipeList);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        this.setTop(homePageHeader);
        this.setCenter(scrollPane);

        addListeners(InputPage,DisplayPage);

    }

    public void addListeners(BorderPane InputPage, BorderPane DisplayPage) {
        createButton.setOnAction( e -> {
            this.setCenter(InputPage);

            RecipeTitle recipeTitle = new RecipeTitle();
            recipeList.getChildren().add(recipeTitle);
            Button viewButton = recipeTitle.getViewButton();
            viewButton.setOnAction( e1 -> {
                this.setCenter(DisplayPage);
            });

        });
    }

    
}

// recipe display view

class RecipeDisplay1 extends VBox {

    private TextArea title, ingredients, steps;
    private Button editButton, saveButton, deleteButton;


    RecipeDisplay1() {
        this.setPrefSize(500, 20); // sets size of task
        this.setStyle("-fx-background-color: #FFFFFF; -fx-border-width: 0; -fx-font-weight: bold;"); // sets background color of task
        
        title = new TextArea(); // create task name text field
        title.setEditable(false);
        title.setPrefSize(230, 20); // set size of text field
        title.setStyle("-fx-background-color: #FFFF00; -fx-border-width: 0;"); // set background color of textfield, yellow
        // title.setPadding(new Insets(10, 0, 10, 0)); // adds some padding to the text field
        title.setPromptText("Name");
        this.getChildren().add(title); // add textlabel to task

        ingredients = new TextArea(); // create task name text field
        ingredients.setEditable(false);
        ingredients.setPrefSize(300,800); // set size of text field
        ingredients.setStyle("-fx-background-color: #FFFF00; -fx-border-width: 0;"); // set background color of textfield, yellow
        // ingredients.setPadding(new Insets(0, 0, 0, 0)); // adds some padding to the text field
        ingredients.setPromptText("1. ingredient");
        this.getChildren().add(ingredients); // add textlabel to task
        
        steps = new TextArea(); // create task name text field
        steps.setEditable(false);
        steps.setPrefSize(300, 800); // set size of text field
        steps.setStyle("-fx-background-color: #FFFF00; -fx-border-width: 0;"); // set background color of textfield, yellow
        // steps.setPadding(new Insets(0, 0, 0, 0)); // adds some padding to the text field
        steps.setPromptText("Step 1");
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
}


class RecipeDisplayHeader extends HBox {
    private Button backButton;

    RecipeDisplayHeader() {
        this.setPrefSize(500, 60); // Size of the header
        this.setStyle("-fx-background-color: #F2F2F2;");
        this.setAlignment(Pos.TOP_CENTER);
        
        Text titleText = new Text("PantryPal"); // Text of the Header
        titleText.setStyle("-fx-font-weight: bold; -fx-font-size: 20;");
        
        this.getChildren().add(titleText);

        VBox pantryPal = new VBox(titleText);
        pantryPal.setAlignment(Pos.TOP_LEFT);
        pantryPal.setSpacing(15);
        pantryPal.setPrefWidth(300);// prefWidth
        this.getChildren().add(pantryPal);

        backButton = new Button("Back");
        backButton.setPrefSize(100,50);
        this.getChildren().add(backButton);

        VBox buttonBox = new VBox(backButton);
        buttonBox.setAlignment(Pos.TOP_RIGHT);
        buttonBox.setSpacing(15);
        buttonBox.setPrefWidth(300);// prefWidth
        this.getChildren().add(buttonBox);
    }
}

class RecipeDisplayAppFrame extends BorderPane {

    private RecipeDisplayHeader header;
    private RecipeDisplay1 recipe;
    private Button editButton;
    private TextArea title;
    private TextArea ingredients;
    private TextArea steps;

        RecipeDisplayAppFrame() {

        // header = new RecipeDisplayHeader();

        // recipe = new RecipeDisplay();

        // editButton = recipe.getEditButton();
        // title = recipe.getTitle();
        // ingredients = recipe.getIngredients();
        // steps = recipe.getSteps();

        // ScrollPane scrollPane = new ScrollPane(recipe);
        // scrollPane.setFitToWidth(true);
        // scrollPane.setFitToHeight(true);
        

    
        // this.setTop(header);
        // this.setCenter(scrollPane);

        // addListeners();
    }

    public void addListeners()
    {
        // Save tasks to file
        editButton.setOnAction(e -> {
            title.setEditable(true);
            ingredients.setEditable(true);
            steps.setEditable(true);
        });

        // saveButton.setOnAction(e -> {
        //     ingredients.setEditable(false);
        //     steps.setEditable(false);
        // });
        
    // }
}
}

// start/stop buttons
class InputHeader extends HBox {
    private Button backButton;

    InputHeader() {
        this.setPrefSize(500, 60); // Size of the header
        this.setStyle("-fx-background-color: #F2F2F2;");
        this.setAlignment(Pos.TOP_CENTER);
        
        Text titleText = new Text("PantryPal"); // Text of the Header
        titleText.setStyle("-fx-font-weight: bold; -fx-font-size: 20;");
        
        this.getChildren().add(titleText);

        VBox pantryPal = new VBox(titleText);
        pantryPal.setAlignment(Pos.TOP_LEFT);
        pantryPal.setSpacing(15);
        pantryPal.setPrefWidth(300);// prefWidth
        this.getChildren().add(pantryPal);

        backButton = new Button("Back");
        backButton.setPrefSize(100,50);
        this.getChildren().add(backButton);

        VBox buttonBox = new VBox(backButton);
        buttonBox.setAlignment(Pos.TOP_RIGHT);
        buttonBox.setSpacing(15);
        buttonBox.setPrefWidth(300);// prefWidth
        this.getChildren().add(buttonBox);
    }
}

class RecButtons extends VBox {
    private Button startButton, stopButton;
    private Label recordingLabel; 
    private TextArea recipeText;

    // Set a default style for buttons and fields - background color, font size,
    // italics
    String defaultButtonStyle = "-fx-border-color: #000000; -fx-font: 13 arial; -fx-pref-width: 175px; -fx-pref-height: 50px;";
    String defaultLabelStyle = "-fx-font: 13 arial; -fx-pref-width: 175px; -fx-pref-height: 50px; -fx-text-fill: red;";
    String defaultTextStyle = "-fx-font: 13 arial; -fx-pref-width: 175px; -fx-pref-height: 50px; -fx-text-fill: black;";

    RecButtons() {

        HBox buttonBox = new HBox();
        startButton = new Button("Start");
        startButton.setStyle(defaultButtonStyle);

        stopButton = new Button("Stop");
        stopButton.setStyle(defaultButtonStyle);

        buttonBox.getChildren().addAll(startButton, stopButton);

        recordingLabel = new Label("Select Meal Type: Breakfast, Lunch, or Dinner");
        recordingLabel.setStyle(defaultLabelStyle);
        recordingLabel.setWrapText(true);

        recipeText = new TextArea("");
        recipeText.setStyle(defaultTextStyle);
        recipeText.setEditable(false);
        recipeText.setMinHeight(400);

        this.getChildren().addAll(buttonBox, recordingLabel, recipeText);
    }
    
    public Button getStartButton() {
        return startButton;
    }

    public Button getStopButton() {
        return stopButton;
    }

    public Label getRecordingLabel() {
        return recordingLabel;
    }

    public TextArea getRecipeText(){
        return recipeText;
    }

   

}
class InputAppFrame extends BorderPane {
    // Input in = new Input();
    private Header header;
    private Button startButton;
    private Button stopButton;
    private Label recordingLabel;
    private RecButtons recButton;
    private String promptType;
    private TextArea recipeText;

    InputAppFrame() {
        // header = new Header();
        promptType = "MealType";
        recButton = new RecButtons();
        this.setTop(header);
        startButton = recButton.getStartButton();
        stopButton = recButton.getStopButton();
        recordingLabel = recButton.getRecordingLabel();
        recipeText = recButton.getRecipeText();

        // Set properties for the flowpane
        this.setPrefSize(500, 600);
        this.setPadding(new Insets(5, 0, 5, 5));
        /*
        this.setVgap(10);
        this.setHgap(10);
        this.setPrefWrapLength(170);
        */

        // Add the buttons and text fields
        this.setCenter(recButton);

        // Add the listeners to the buttons
        addListeners();

        moveToInput();
    }

    public void addListeners() {
        // Start Button
        startButton.setOnAction(e -> {
            recordingLabel.setText("Recording");
            Input.captureAudio();
        });

        // Stop Button
        stopButton.setOnAction(e -> {
            if(Input.stopCapture(promptType)){
                if(promptType.equals("MealType")){
                    promptType = "Ingredients";
                    recipeText.setText("");
                    recordingLabel.setText("Please input Ingredients");
                }
                else{
                    recordingLabel.setText("Recipe Displayed");
                    promptType = "MealType";
                    try {
                        FileReader fr = new FileReader("recipe.txt"); // PLACEHOLDER NAME
                        BufferedReader br = new BufferedReader(fr);
                        String text = "";
                        String str = "";
                        while((str=br.readLine())!=null){
                            text += str + "\n";
                        }
                        recipeText.setText(text);
                        br.close();
                        br.close();
                    } catch(Exception err){
                        err.printStackTrace();
                    }
                }
            }
            else{
                recordingLabel.setText("Invalid Input. Please say a proper meal type");
            }
        });
    }

    public void moveToInput() {
        
    }
}

public class UI extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage stage) throws Exception {
        InputAppFrame inputPage = new InputAppFrame();
        RecipeDisplayAppFrame displayPage = new RecipeDisplayAppFrame();
        HomePageAppFrame root = new HomePageAppFrame(inputPage,displayPage);

        // Set the title of the app
        stage.setTitle("PantryPal");
        // Create scene of mentioned size with the border pane
        stage.setScene(new Scene(root, 500, 600));
        // Make window non-resizable
        stage.setResizable(false);
        // Show the app
        stage.show();
    }
}
