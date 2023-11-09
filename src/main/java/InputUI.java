import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.*;
import javax.sound.sampled.*;


// TODO: Add reference or change code to make it our own
// https://www.developer.com/java/java-sound-capturing-microphone-data-into-an-audio-file/#Run%20the%20program 

class Header1 extends HBox {
    private Button backButton;

    Header1() {
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

class RecButtons extends HBox {
    private Button startButton, stopButton;
    private Label recordingLabel; 

    // Set a default style for buttons and fields - background color, font size,
    // italics
    String defaultButtonStyle = "-fx-border-color: #000000; -fx-font: 13 arial; -fx-pref-width: 175px; -fx-pref-height: 50px;";
    String defaultLabelStyle = "-fx-font: 13 arial; -fx-pref-width: 175px; -fx-pref-height: 50px; -fx-text-fill: red; visibility: hidden";

    RecButtons() {
        startButton = new Button("Start");
        startButton.setStyle(defaultButtonStyle);

        stopButton = new Button("Stop");
        stopButton.setStyle(defaultButtonStyle);

        recordingLabel = new Label("Recording...");
        recordingLabel.setStyle(defaultLabelStyle);
        this.getChildren().addAll(startButton, stopButton, recordingLabel);
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

}

class AppFrame1 extends BorderPane {
    // Input in = new Input();
    private Header header;
    private Button startButton;
    private Button stopButton;
    private AudioFormat audioFormat;
    private TargetDataLine targetDataLine;
    private Label recordingLabel;
    private RecButtons recButton;

    AppFrame1() {
        // header = new Header();
        recButton = new RecButtons();
        this.setTop(header);
        startButton = recButton.getStartButton();
        stopButton = recButton.getStopButton();
        recordingLabel = recButton.getRecordingLabel();
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

        // Get the audio format
        audioFormat = Input.getAudioFormat();

        // Add the listeners to the buttons
        addListeners();

        moveToInput();
    }

    public void addListeners() {
        // Start Button
        startButton.setOnAction(e -> {
            Input.captureAudio();
        });

        // Stop Button
        stopButton.setOnAction(e -> {
            Input.stopCapture();
        });
    }

    public void moveToInput() {
        
    }
}


public class InputUI extends Application {

        @Override
    public void start(Stage primaryStage) throws Exception {

        // Setting the Layout of the Window (Flow Pane)
        AppFrame1 root = new AppFrame1();

        // Set the title of the app
        primaryStage.setTitle("Audio Recorder");
        // Create scene of mentioned size with the border pane
        primaryStage.setScene(new Scene(root, 370, 120));
        // Make window non-resizable
        primaryStage.setResizable(false);
        // Show the app
        primaryStage.show();
    }

    public static void main(String[] args) throws InterruptedException {
        /*
        System.out.println("Recording");
        i.captureAudio();

        Thread.sleep(10000);

        System.out.println("Stopped");
        i.stopCapture();
        */
        launch(args);
    }
}
