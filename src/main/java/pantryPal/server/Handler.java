package pantryPal.server;

import com.sun.net.httpserver.*;

import pantryPal.client.RecipeManager;
import pantryPal.client.UserAccount.AccountManager;

import com.sun.net.httpserver.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class Handler implements HttpHandler {
    /**
     * Methods to handle:
     * GET recipes to search for a recipe
     * PUT a recipe (into MongoDB)
     * DELETE a recipe (from MongoDB)
     */
    public void handle(HttpExchange httpExchange) throws IOException {
        String method = httpExchange.getRequestMethod(); // gets method from Model.java
        String response = "Request Received";
        System.out.println(response);
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
    private String handleGet(HttpExchange httpExchange) throws IOException {
        String response = "Invalid GET request";

        URI uri = httpExchange.getRequestURI();
        String query = uri.getRawQuery();
        if (query != null) {
            String id = query.substring(query.indexOf("=") + 1);    // id of recipe?

            // TODO: search for the recipe, stuff like that
            response = "found";
        }
        return response;
    }

    /**
     * Handles the PUT operation on the server side.
     * @param httpExchange exchange that has the request
     * @return response to write back to th emodel
     * @throws IOException
     */
    private String handlePut (HttpExchange httpExchange) throws IOException {
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
            String [] info = postData.toString().split(";");

            // recipe collection
            if (info.length == 4) {
                // System.out.println("postdata: " + postData);
                String decodedTitle = new String(Base64.getDecoder().decode(info[0]));
                String decodedIngredients = new String(Base64.getDecoder().decode(info[1]));
                String decodedSteps = new String(Base64.getDecoder().decode(info[2]));

                // System.out.println("Decoded Title: " + decodedTitle);
                // System.out.println("Decoded Ingredients: " + decodedIngredients);
                // System.out.println("Decoded Steps: " + decodedSteps);

                String[] result = RecipeManager.insertRecipe(decodedTitle, decodedIngredients, decodedSteps);

                response = "INSERTED THE RECIPE" + result;
                // System.out.println(response);
            }

            else if (info.length == 2) {
                String decodedUserName = new String(Base64.getDecoder().decode(info[0]));
                String decodedPassword = new String(Base64.getDecoder().decode(info[1]));
                String[] result = AccountManager.insertAccount(decodedUserName, decodedPassword);
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
    private String handleDelete (HttpExchange httpExchange) throws IOException {
        URI uri = httpExchange.getRequestURI();
        String query = uri.getRawQuery();
        String response;
        if (query != null) {
            String id = query.substring(query.indexOf("=") + 1);

            RecipeManager.deleteRecipeByID(id);
            response = "Deleted " + id;
        } else {
            response = "Invalid DELETE request. No ID provided.";
            
        }
        System.out.println(response);
        return response;
    }
}
