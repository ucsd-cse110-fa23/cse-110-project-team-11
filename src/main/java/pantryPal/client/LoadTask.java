package pantryPal.client;


import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

import java.net.ConnectException;

import org.json.JSONArray;
import org.json.JSONObject;

public class LoadTask extends Task {
    
    private String name;
    private Model model;
    
    public LoadTask(String n, Model m){
        this.name = n;
        this.model = m;
    }
    @Override
    public String call() throws ConnectException {
        try {
            String response = model.performRequest(this.name);
            return response;

        } catch (ConnectException err) {
            Alert a = new Alert(AlertType.ERROR, "Server is Offline", ButtonType.OK);
            a.showAndWait();
            return "Error";
        }
    }

    
}
