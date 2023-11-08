package main.java;
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

class createRecipePage extends VBox {
    private Button createButton;

    createRecipePage() {
        createButton = new Button("create");
        createButton.setPrefSize(50,30);
        createButton.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;");
        createButton.setAlignment(Pos.CENTER);
        this.getChildren().add(createButton);
    }

    public Button getCreateButton() {
        return createButton;
    }
}

class AppFrame extends BorderPane {

    private createRecipePage recipe;
    private Button createButton;
    
    AppFrame()
    {
        // Initialise the header Object
        recipe = new createRecipePage();

        createButton = recipe.getCreateButton();
        // Create a ScrollPane to hold the taskList
        ScrollPane scrollPane = new ScrollPane(recipe);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        

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
        createButton.setOnAction(e -> {
            
            Scene newScene = new Scene(); // ... commands which define the new scene.
            Stage stage = ((Node) e.getTarget()).getScene().getStage();
            // or alternatively, just:
            // Stage stage = button.getScene().getStage();
            stage.setScene(newScene);
            // moveToInput();
        });

        //  createButton.setOnAction(e -> {
        //     ingredients.setEditable(false);
        //     steps.setEditable(false);
        // });
        
    // }
    }

    // moves to recipe inptu screen
    public void moveToInput() {
        
    }
}

public class CreateRecipe extends Application{
    
    @Override
    public void start(Stage primaryStage) throws Exception {

        // Setting the Layout of the Window- Should contain a Header, Footer and the TaskList
        AppFrame root = new AppFrame();

        // Set the title of the app
        primaryStage.setTitle("Home Page");
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