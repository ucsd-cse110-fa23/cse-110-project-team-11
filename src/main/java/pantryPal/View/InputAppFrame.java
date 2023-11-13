package pantryPal.View;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class InputAppFrame extends BorderPane {
    // Input in = new Input();
    private ReturnHeader header;
    private Button startButton,stopButton,backButton;
    private Label recordingLabel;
    private RecButtons recButton;
    private TextArea recipeText;

    private RecipeList recipeList;

    public InputAppFrame() {
        // header = new Header();
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

        // Add the buttons and text fields
        this.setCenter(recButton);

        // Add the listeners to the buttons
        // addListeners();


    }
    
   public ReturnHeader getReturnHeader() {
        return header;
    }
    public void setStopButtonAction(EventHandler<ActionEvent> eventHandler) {
        stopButton.setOnAction(eventHandler);
    }

    public void setStartButtonAction(EventHandler<ActionEvent> eventHandler) {
        startButton.setOnAction(eventHandler);
    }

    public void setBackButtonAction(EventHandler<ActionEvent> eventHandler) {
        backButton.setOnAction(eventHandler);
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

    public RecButtons getRecButtons(){
        return recButton;
    }     
    
}
