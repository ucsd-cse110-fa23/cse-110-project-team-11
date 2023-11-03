package PantryPal;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.VLineTo;
import javafx.scene.text.TextAlignment;
import javafx.geometry.Insets;
import javafx.scene.text.*;
import javafx.scene.Node;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.imageio.ImageIO;
import java.io.File;

class Recipe extends VBox {

    // needs image

    private TextArea title, ingredients, steps;
    private Button editButton, saveButton, deleteButton;


    Recipe() {
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


class Header extends HBox {
    private Button backButton;

    Header() {
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

class AppFrame extends BorderPane {

    private Header header;
    private Recipe recipe;
    private Button editButton;
    private TextArea title;
    private TextArea ingredients;
    private TextArea steps;
    
    AppFrame()
    {

        // Initialise the header Object
        header = new Header();

        recipe = new Recipe();

        editButton = recipe.getEditButton();
        title = recipe.getTitle();
        ingredients = recipe.getIngredients();
        steps = recipe.getSteps();
        // Create a ScrollPane to hold the taskList
        ScrollPane scrollPane = new ScrollPane(recipe);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        

        // Add header to the top of the BorderPane
        this.setTop(header);
        // Add scroller to the centre of the BorderPane
        this.setCenter(scrollPane);
        // Add footer to the bottom of the BorderPane

        // Initialise Button Variables through the getters in Footer

        // Call Event Listeners for the Buttons
        addListeners();
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

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Setting the Layout of the Window- Should contain a Header, Footer and the TaskList
        AppFrame root = new AppFrame();

        // Set the title of the app
        primaryStage.setTitle("Recipe Details");
        // Create scene of mentioned size with the border pane
        primaryStage.setScene(new Scene(root, 500, 600));
        // Make window non-resizable
        primaryStage.setResizable(false);
        // Show the app
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}