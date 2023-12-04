package pantryPal.client;

import javafx.stage.Stage;
import pantryPal.server.MockServer;

public class MockApp extends App {
    public static void main(String[] args) {   
        MockApp.setTest(true);     
        MockServer.turnOn();
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        MockApp.setTest(true);     
        MockServer.turnOn();
        super.start(stage);
    }
}

