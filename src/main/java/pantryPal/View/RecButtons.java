package pantryPal.View;
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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.io.IOException;

public class RecButtons extends VBox {
    private Button startButton, stopButton;
    private Label recordingLabel; 
    private TextArea recipeText;
    private HBox buttonBox;

    // Set a default style for buttons and fields - background color, font size,
    // italics
    String defaultButtonStyle = "-fx-border-color: #000000; -fx-font: 13 arial; -fx-pref-width: 175px; -fx-pref-height: 50px;";
    String defaultLabelStyle = "-fx-font: 13 arial; -fx-pref-width: 175px; -fx-pref-height: 50px; -fx-text-fill: red;";
    String defaultTextStyle = "-fx-font: 13 arial; -fx-pref-width: 175px; -fx-pref-height: 50px; -fx-text-fill: black;";

    public RecButtons() {

        buttonBox = new HBox();
        startButton = new Button("Start");
        startButton.setStyle(defaultButtonStyle);

        stopButton = new Button("Stop");
        stopButton.setStyle(defaultButtonStyle);

        buttonBox.getChildren().addAll(startButton);

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

    public HBox getButtonBox(){
        return buttonBox;
    }
    
    public Label getRecordingLabel() {
        return recordingLabel;
    }

    public TextArea getRecipeText(){
        return recipeText;
    
    }

    public void setRecordingLabel(String label){
        this.recordingLabel.setText(label);
    }

    public void setRecipeText(String label){
        this.recipeText.setText(label);
    }
}
