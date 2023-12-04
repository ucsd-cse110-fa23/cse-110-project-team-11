package pantryPal.client;

import pantryPal.server.MockServer;

public class MockApp extends App {
    public static void main(String[] args) {   
        MockApp.setTest(true);     
        MockServer.turnOn();
        launch(args);
    }
}
