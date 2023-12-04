package pantryPal.client.View;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.control.TextArea;

public class RecButtons extends GridPane {
    private Button startButton, stopButton;
    private TextArea recipeText;
    private HBox buttonBox;

    // Set a default style for buttons and fields - background color, font size,
    // italics
    String defaultButtonStyle = "-fx-border-color: #000000; -fx-font: 25 arial; -fx-pref-width: 175px; -fx-pref-height: 50px;";
    String defaultTextStyle = " -fx-text-alignment: center; -fx-font: 20 arial; -fx-pref-width: 175px; -fx-pref-height: 100px; -fx-text-fill: black;";

    public RecButtons() {

        this.setPadding(new Insets(10, 10, 10, 20));

        buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER);
        startButton = new Button("Start");
        startButton.setStyle(defaultButtonStyle);

        stopButton = new Button("Stop");
        stopButton.setStyle(defaultButtonStyle);

        startButton.prefWidthProperty().bind(buttonBox.widthProperty().divide(2));
        stopButton.prefWidthProperty().bind(buttonBox.widthProperty().divide(2));

        recipeText = new TextArea("Select Meal Type: Breakfast, Lunch, or Dinner");
        recipeText.setStyle(defaultTextStyle);
        recipeText.setEditable(false);
        recipeText.setWrapText(true);
        

        buttonBox.getChildren().addAll(startButton);
        buttonBox.setSpacing(10);
        VBox header = new VBox(buttonBox);
        header.setSpacing(10);
        // header.setAlignment(Pos.CENTER_LEFT);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        this.getColumnConstraints().addAll(col1,col2);
        this.addRow(0, header, recipeText);
        setVgrow(recipeText, Priority.ALWAYS);
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

    public TextArea getRecipeText(){
        return recipeText;
    
    }

    public void setRecipeText(String label){
        this.recipeText.setText(label);
    }
}
