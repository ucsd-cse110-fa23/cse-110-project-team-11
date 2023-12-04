package pantryPal.client.View;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class InputAppFrame extends BorderPane {
    private ReturnHeader header;
    private Button startButton,stopButton,backButton, logoutButton;
    private RecButtons recButton;
    private String mealType;

    public InputAppFrame() {
        recButton = new RecButtons();
        header = new ReturnHeader();
        backButton = header.getBackButton();
        startButton = recButton.getStartButton();
        stopButton = recButton.getStopButton();
        logoutButton = header.getLogoutButton();

        // Set properties for the flowpane
        this.setPrefSize(500, 600);
        this.setPadding(new Insets(5, 0, 5, 5));

        // Add the buttons and text fields
        this.setCenter(recButton);

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
    public void setLogoutButtonAction(EventHandler<ActionEvent> eventHandler) {
        logoutButton.setOnAction(eventHandler);
    }

    public Button getStartButton() {
        return startButton;
    }

    public Button getStopButton() {
        return stopButton;
    }

    public RecButtons getRecButtons(){
        return recButton;
    }     
    public String getMealType() {
        return this.mealType;
    }

    public void setMealType(String s){
        this.mealType = s;
    }
}