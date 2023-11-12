/**
 * This the file that handles the front end of the code. Should deal with the
 * app UI and all of the button features. Has X different pages:
 * 
 * 1. Main (browse) page
 * 2. Create recipe page
 * 3. Detailed recipe page
 * 
 * Each of these pages have their respective headers, bodies, buttons, etc.
 */
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
import java.io.IOException;


class RecipeTitle extends HBox {
    private Label index;
    private String id;
    private TextField title;
    private Button viewButton;
    /**
     * Constructor for generating format and recipe page. Handles indexing of the recipes
     */
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

/**
 * holds the recipes in the main page
 */
class RecipeList extends VBox {
    RecipeList() {
        this.setSpacing(5);
        this.setPrefSize(500, 560);
        this.setStyle("-fx-background-color: #F0F8FF;");
        
    }
}

/**
 * head for homepage that should display create button for recipe generation.
 */
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

/**
* App frame for the home page, displays the header and recipe list
 */
class HomePageAppFrame extends BorderPane{
    private HomePageHeader homePageHeader;
    private static RecipeList recipeList;
    private Button createButton;


    HomePageAppFrame(InputAppFrame InputPage) {
        homePageHeader = new HomePageHeader();
        recipeList = new RecipeList();
        RecipeManager.loadRecipes(); // loads recipe

        createButton = homePageHeader.getCreateButton();    
        
        ScrollPane scrollPane = new ScrollPane(recipeList);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        this.setCenter(scrollPane);

        addListeners(InputPage);
    }

    public static RecipeList getRecipeList() {
        return recipeList;
    }

    public HomePageHeader getHomePageHeader() {
        return homePageHeader;
    }

    /**
     * Adds functionality for the create button, will go to the input audio page once cliicked
     */
    public void addListeners(InputAppFrame InputPage) {
        createButton.setOnAction( e -> {
            UI.getRoot().setCenter(InputPage);
            UI.getRoot().setTop(InputPage.getReturnHeader());
            // RecipeTitle recipeTitle = new RecipeTitle();
            // recipeList.getChildren().add(recipeTitle);
            // Button viewButton = recipeTitle.getViewButton();
            // viewButton.setOnAction( e1 -> {
            //     this.setCenter(DisplayPage);
            // });

        });
    }

    
}

/** RecipeDisplay: window that shows the detailed recipe. 
 * SHOULD get the information from the CSV S
 */
class RecipeDisplay extends VBox {
    private String id = null;
    private TextArea title, ingredients, steps;
    private Button editButton, saveButton, deleteButton;


    RecipeDisplay() {
        this.setPrefSize(500, 20); // sets size of task
        this.setStyle("-fx-background-color: #FFFFFF; -fx-border-width: 0; -fx-font-weight: bold;"); // sets background color of task
        
        title = new TextArea(); // create task name text field
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

    RecipeDisplay(String idString, String titleString, String ingredientString, String stepsString) {
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
    // Setters for recipe metadata
    public String getID() {
        return this.id;
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

/**
 * Header for the detailed recipe display
 */

class RecipeDisplayAppFrame extends BorderPane {

    private ReturnHeader header;
    private Button backButton,editButton,deleteButton, saveButton;
    private TextArea title, ingredients, steps;
    private String id;
    private Boolean editable = false;

    RecipeDisplayAppFrame(RecipeDisplay recipe) {

        header = new ReturnHeader();
        backButton = header.getBackButton();

        editButton = recipe.getEditButton();
        deleteButton = recipe.getDeleteButton();
        saveButton = recipe.getSaveButton();
        title = recipe.getTitle();
        // System.out.println(title.getText());
        ingredients = recipe.getIngredients();
        // System.out.println(ingredients.getText());
        steps = recipe.getSteps();
        // System.out.println(steps.getText());
        id = recipe.getID();
        System.out.println(id);

        ScrollPane scrollPane = new ScrollPane(recipe);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        
        this.setCenter(scrollPane);

        addListeners();
    }

    public ReturnHeader getRecipeDisplayHeader() {
        return header;
    }

    public void addListeners()
    {
        // Save tasks to file
        editButton.setOnAction(e -> {
            if (!editable) {
                ingredients.setEditable(true);
                steps.setEditable(true);
                editButton.setText("Cancel");
                editable = true;
            }
            else {
                ingredients.setEditable(false);
                steps.setEditable(false);
                editButton.setText("Edit");
                editable = false;
            }
        });

        backButton.setOnAction( e -> {
            UI.returnHomePage();
        });

        deleteButton.setOnAction( e -> {
            RecipeManager.deleteRecipe(id);
            for (int i = 0; i < HomePageAppFrame.getRecipeList().getChildren().size(); i++) {
                
            }
            UI.returnHomePage();
            
        });

        // save after edit?
        saveButton.setOnAction(e -> {
            ingredients.setEditable(false);
            steps.setEditable(false);
            if (id == null) {
                try {
                    RecipeManager.insertRecipe(title.getText(), ingredients.getText(), steps.getText());
                    id = RecipeManager.getStringID();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
            else {
                try {
                    RecipeManager.updateRecipe(id, title.getText(), steps.getText(), ingredients.getText());
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }

        });
        
    // }
}
}

/**
 * Contains start & stop buttons
 * Responsible for getting audio using methods from Input.java
 */
class ReturnHeader extends HBox {
    private Button backButton;

    ReturnHeader() {
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

    public Button getBackButton() {
        return backButton;
    }
}

/**
 * Handles buttons for recording in the "create recipe" page
 */
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

/**
 * Initializes buttons, header, and labels to display the input page
 */
class InputAppFrame extends BorderPane {
    // Input in = new Input();
    private ReturnHeader header;
    private Button startButton,stopButton,backButton;
    private Label recordingLabel;
    private RecButtons recButton;
    private String promptType;
    private TextArea recipeText;

    InputAppFrame() {
        // header = new Header();
        promptType = "MealType";
        recButton = new RecButtons();
        header = new ReturnHeader();
        backButton = header.getBackButton();
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


    }
    
   public ReturnHeader getReturnHeader() {
        return header;
    }

    public void addListeners() {
        // Back Button
        backButton.setOnAction( e -> {
            UI.returnHomePage();
            recordingLabel.setText("");
        });
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
                    RecipeDisplay rec = new RecipeDisplay();
                    promptType = "MealType";
                    try {
                        RecipeParser.parse();
                        // rec.setID(RecipeParser.getID());
                        rec.setID(null);
                        rec.setTitle(RecipeParser.getTitle());
                        rec.setIngreds(RecipeParser.getStringIngredients());
                        rec.setSteps(RecipeParser.getStringSteps());
                        // System.out.println(rec.getIngredients().getText());
                        // System.out.println(rec.getSteps().getText());
                        RecipeDisplayAppFrame displayRec = new RecipeDisplayAppFrame(rec);
                        UI.getRoot().setCenter(displayRec);
                        UI.getRoot().setTop(displayRec.getRecipeDisplayHeader());
                        // recipeText.setText(text);
                        // br.close();
                    } catch(Exception err){
                        err.printStackTrace();
                    }
                    // HomePageAppFrame.getRecipeList().getChildren().add(rec);
                }
            }
            else{
                recordingLabel.setText("Invalid Input. Please say a proper meal type");
            }
        });
    }
}

public class UI extends Application {

    private static BorderPane root;
    private static HomePageAppFrame HomePage;

    public static HomePageAppFrame getHomePage() {
        return HomePage;
    }
    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage stage) throws Exception {
        InputAppFrame inputPage = new InputAppFrame();
        //RecipeDisplay recipe = new RecipeDisplay();
        //RecipeDisplayAppFrame displayPage = new RecipeDisplayAppFrame(recipe);
        root = new BorderPane();
        HomePage = new HomePageAppFrame(inputPage);
        root.setCenter(HomePage);
        root.setTop(HomePage.getHomePageHeader());
        
        // Set the title of the app
        stage.setTitle("Recipe Details");
        // Create scene of mentioned size with the border pane
        stage.setScene(new Scene(root, 500, 600));
        // Make window non-resizable
        stage.setResizable(false);
        // Show the app
        stage.show();
    }

    public static BorderPane getRoot() {
        return root;
    }
    
    public static void returnHomePage() {
        root.setCenter(HomePage);
        root.setTop(HomePage.getHomePageHeader());
    }
    
}
