package pantryPal.server;

import com.sun.net.httpserver.*;

import pantryPal.client.DallE;
import pantryPal.client.IAPI;
import pantryPal.client.APIFactory; 
import pantryPal.client.ChatGPT;
import pantryPal.client.Whisper;
import pantryPal.client.RecipeManager;
import pantryPal.client.UserAccount.AccountManager;
import pantryPal.client.Input;
import pantryPal.client.MockWhisper;

import java.io.*;
import java.net.*;
import java.util.*;

import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

public class Handler implements HttpHandler {
    // String userID = "";
    Input input = new Input();
    /**
     * Methods to handle:
     * GET recipes to search for a recipe
     * PUT a recipe (into MongoDB)
     * DELETE a recipe (from MongoDB)
     */
    public void handle(HttpExchange httpExchange) throws IOException {
        System.out.println(httpExchange.getRequestMethod());
        String method = httpExchange.getRequestMethod(); // gets method from Model.java
        String response = "Request Received";
        System.out.println(response);
        System.out.println(method);
        try {
            if (method.equals("GET")) {
                response = handleGet(httpExchange);
            }
            else if (method.equals("PUT")) {
                response = handlePut(httpExchange);
            }
            else if (method.equals("DELETE")) {
                response = handleDelete(httpExchange);
            }
            else {
                throw new Exception("Not Valid Request Method");
            }
        } catch (Exception e) {
            
            System.out.println("An erroneous request");
            response = e.toString();
            e.printStackTrace();
        }

        // Sending back response to the client
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream outStream = httpExchange.getResponseBody();
        outStream.write(response.getBytes());
        outStream.close();
    }

    /**
     * THIS IS a SEARCHING THING. SHOULD BE FOR MS2 
     * @param httpExchange
     * @return
     * @throws IOException
     */
    public String handleGet(HttpExchange httpExchange) throws IOException, URISyntaxException, InterruptedException  {
        URI uri = httpExchange.getRequestURI();
        String query = uri.getRawQuery();
        System.out.println(query);
        String response = "";
        if (query != null) {
            String tag = query.substring((query.indexOf("?")+1), query.indexOf("="));
            // login button/ loading recipes upon login
            if(tag.equals("user")) {
                String account = query.substring(query.indexOf("=") + 1);

                Document result = AccountManager.searchAccount(account);
                if(result != null) {
                    response = result.getString("password");
                    //response = "Found " + account;
                }
                else
                    response = "Account not found";
            }
            else if (tag.equals("api")) {                
                String apiCall = query.substring(query.indexOf("=")+1, query.indexOf("&"));
                String temp = query.substring(query.indexOf("&")+1);
                String encodedInput = temp.substring(temp.indexOf("=")+1);
                String decodedInput = new String(Base64.getDecoder().decode(encodedInput));

                IAPI api = APIFactory.createAPI(apiCall);

                if (api !=null) {
                    response = api.callAPI(decodedInput);
                }
                else {
                    response = "Invalid API";
                }
                return response;
            }
                
            else if(tag.equals("load")){
                String username = query.substring(query.indexOf("=") + 1);
                JSONArray recipes = RecipeManager.loadRecipes(username);
                response = recipes.toString();
                String encodedResponse = Base64.getEncoder().encodeToString(response.getBytes());
                return encodedResponse;
            }
            else if(tag.equals("share")){
                String username = query.substring(query.indexOf("=") + 1, query.indexOf("&"));
                String id = query.substring(query.indexOf("&") + 4);
                Document recipe = RecipeManager.searchRecipeByID(username, id);

                // get info from document
                StringBuilder htmlBuilder = new StringBuilder();
                htmlBuilder
                    .append("<html>")
                    .append("<body>")
                    .append("<h1>")
                    .append(recipe.get("title"))
                    .append("</h1>")
                    .append("<img src=" + recipe.get("imageURL") + ">")
                    .append("<h3>Ingredients</h3>")
                    .append(recipe.get("ingredients"))
                    .append("<h3>Steps</h3>")
                    .append(recipe.get("steps"))
                    .append("</body>")
                    .append("</html>");

                // encode HTML content
                response = htmlBuilder.toString();
                System.out.println("response" + response);
                // String encodedHTML = Base64.getEncoder().encodeToString(response.getBytes());
                return response;
            }
            else {
                response = "invalid GET request";
            }
            System.out.println(response);
            return response;
        }
        return null;
    }

    /**
     * Handles the PUT operation on the server side.
     * @param httpExchange exchange that has the request
     * @return response to write back to th emodel
     * @throws IOException
     */
    public String handlePut (HttpExchange httpExchange) throws IOException {
        try {
            String response = "";
            // Should read the stuff from the httpRequest from Model
            InputStream inStream = httpExchange.getRequestBody();
            BufferedReader br = new BufferedReader(new InputStreamReader(inStream));
            StringBuilder postData = new StringBuilder();

            // adds all the data into one line
            String line;
            while ((line = br.readLine()) != null) {
                postData.append(line);
            }
            System.out.println("SIDUHGFIOSUDHFOISDHOFISDFH: "+postData.toString());
            String [] info = postData.toString().split(";");
            String decodedRequest = new String(Base64.getDecoder().decode(info[0]));
            // recipe collection
            if (decodedRequest.equals("recipe")) {
                String decodedMealType = new String(Base64.getDecoder().decode(info[1]));
                String decodedTitle = new String(Base64.getDecoder().decode(info[2]));
                String decodedIngredients = new String(Base64.getDecoder().decode(info[3]));
                String decodedSteps = new String(Base64.getDecoder().decode(info[4]));
                String decodedImage = new String(Base64.getDecoder().decode(info[5]));
                String decodedUsername = new String(Base64.getDecoder().decode(info[6]));

                RecipeManager.updateRecipe(decodedUsername, decodedMealType, decodedTitle, decodedIngredients, decodedSteps, decodedImage);

                response = "INSERTED/UPDATED THE RECIPE";
            }

            else if (decodedRequest.equals("createAcc")) {
                String decodedUserName = new String(Base64.getDecoder().decode(info[1]));
                String decodedPassword = new String(Base64.getDecoder().decode(info[2]));
                String[] result = AccountManager.insertAccount(decodedUserName, decodedPassword);
                if (result == null) {
                    return "NULL/INVALID ACCOUNT CREATION";
                }
                response = "INSERTED THE ACCOUNT: " + result[0] + " " + result[1];
            }
            
            return response;

        }
        catch (Exception e) {
            e.printStackTrace(); // Log the exception
            return "Error handling PUT request";
        }
    }

    /**
     * Handles the DELETE operation on the server side.
     * @param httpExchange
     * @return
     * @throws IOException
     */
    public String handleDelete (HttpExchange httpExchange) throws IOException {
        URI uri = httpExchange.getRequestURI();
        String query = uri.getRawQuery();
        String response;
        if (query != null) {
            String username = query.substring(query.indexOf("=") + 1, query.indexOf("&"));
            String temp = query.substring(query.indexOf("&")+1);
            String id = temp.substring(temp.indexOf('=')+1);

            RecipeManager.deleteRecipeByID(username, id);
            response = "Deleted " + id;
        } else {
            response = "Invalid DELETE request. No ID provided.";
        }
        System.out.println(response);
        return response;
    }

 
}
