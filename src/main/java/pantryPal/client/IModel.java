package pantryPal.client;

import java.net.ConnectException;
import java.net.URISyntaxException;

public interface IModel {
    // login
    public String performRequest(String method,String username, String password) throws ConnectException, URISyntaxException;
    //recipe update/insert
    public String performRequest (String method, String mealType, String id, String title, String ingredients, 
        String steps, String imgURL, String username) throws ConnectException, URISyntaxException;
    // api call
    public String performRequest(String input, String API) throws ConnectException, URISyntaxException;
    // load recipes
    public String performRequest(String username) throws ConnectException, URISyntaxException;


}