package pantryPal.client.Model;

import java.net.ConnectException;
import java.net.URISyntaxException;
import java.util.Base64;

import com.sun.net.httpserver.*;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import pantryPal.server.Handler;
import pantryPal.server.MockServer;
import pantryPal.server.Server;
import pantryPal.server.MockHttpExchange;

public class MockModel implements IModel{

    @Override
    // login
    public String performRequest(String method, String username, String password) throws ConnectException, URISyntaxException {
        if (!MockServer.getStatus()) {
            throw new ConnectException("MockServer off");
        }
        Handler h = new Handler();
        MockHttpExchange httpExchange = new MockHttpExchange("?user="+username, method);

        String response = "";
        try {
            if (method.equals("GET")) {
                response = h.handleGet(httpExchange);
            }
            else if (method.equals("PUT")) {
                String encodedRequest = Base64.getEncoder().encodeToString("createAcc".getBytes());
                String encodedUsername = Base64.getEncoder().encodeToString(username.getBytes());
                String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes());
                String info = encodedRequest + ";" + encodedUsername + ";" + encodedPassword;
                httpExchange.setInfo(info);
                response = h.handlePut(httpExchange);
            }
            else if (method.equals("DELETE")) {
                response = h.handleDelete(httpExchange);
            }
        } catch (Exception e) {
            System.out.println("An erroneous request");
            response = e.toString();
            e.printStackTrace();
        }            
        return response;
    }

    // update/insert recipe
    @Override
    public String performRequest(String method, String mealType, String id, String title, String ingredients,
            String steps, String imgURL, String username) throws ConnectException, URISyntaxException {
        if (!MockServer.getStatus()) {
            throw new ConnectException("MockServer off");
        }
        Handler h = new Handler();
        MockHttpExchange httpExchange = new MockHttpExchange("?user=" + username + "&id=" + id, method);
        String info;
        
        String response = "";
        try {
            if (method.equals("GET")) {
                httpExchange.close();
                httpExchange = new MockHttpExchange("?share=" + username + "&id=" + id, method);
                String urlString = "?share=" + username + "&id=" + id;
                Alert a = new Alert(AlertType.INFORMATION, "URL copied to clipboard!", ButtonType.FINISH);
                a.showAndWait();
                httpExchange.setInfo(urlString);
                response = h.handleGet(httpExchange);
                Clipboard clipboard = Clipboard.getSystemClipboard();
                ClipboardContent content = new ClipboardContent();
                content.putString(Server.getDomain() + urlString);
                clipboard.setContent(content);

            }
            else if (method.equals("PUT")) {
                String encodedRequest = Base64.getEncoder().encodeToString("recipe".getBytes());
                String encodedTitle = Base64.getEncoder().encodeToString(title.getBytes());
                String encodedIngredients = Base64.getEncoder().encodeToString(ingredients.getBytes());
                String encodedSteps = Base64.getEncoder().encodeToString(steps.getBytes());
                String encodedMealType = Base64.getEncoder().encodeToString(mealType.getBytes());
                String encodedUrl = Base64.getEncoder().encodeToString(imgURL.getBytes());
                String encodedName = Base64.getEncoder().encodeToString(username.getBytes());
                String encodedID = Base64.getEncoder().encodeToString(id.getBytes());
                info = encodedRequest+";" + encodedMealType + ";" + encodedTitle + ";" + encodedIngredients + ";" 
                    + encodedSteps + ";" + encodedUrl + ";" + encodedName + ";" + encodedID;
                httpExchange.setInfo(info);
                response = h.handlePut(httpExchange);
            }
            else if (method.equals("DELETE")) {
                response = h.handleDelete(httpExchange);
            }
        } catch (Exception e) {
            System.out.println("An erroneous request");
            response = e.toString();
            e.printStackTrace();
        }            
        return response;
    }

    // api call
    @Override
    public String performRequest(String input, String API) throws ConnectException, URISyntaxException {
        if (!MockServer.getStatus()) {
            throw new ConnectException("MockServer off");
        }
        Handler h = new Handler();
        String encodedInput = Base64.getEncoder().encodeToString(input.getBytes());
        HttpExchange httpExchange = new MockHttpExchange("?api=Mock"+API+"&input="+encodedInput, null);

        String response = "";
        try {
            response = h.handleGet(httpExchange);
        } catch (Exception e) {
            
            System.out.println("An erroneous request");
            response = e.toString();
            e.printStackTrace();
        }            
        return response;
    }
    // load recipes
    @Override
    public String performRequest(String username) throws ConnectException, URISyntaxException {
        if (!MockServer.getStatus()) {
            throw new ConnectException("MockServer off");
        }
        Handler h = new Handler();
        HttpExchange httpExchange = new MockHttpExchange("?load="+username, null);

        String response = "";
        try {
            response = h.handleGet(httpExchange);
        } catch (Exception e) {
            
            System.out.println("An erroneous request");
            response = e.toString();
            e.printStackTrace();
        }            
        String decodedResponse = new String(Base64.getDecoder().decode(response));
        return decodedResponse;
    }
    
}
