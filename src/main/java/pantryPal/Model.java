package pantryPal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import pantryPal.View.RecipeTitle;

import java.net.URI;


public class Model {
    public String[] performRequest(String method, String title, String ingredients, String steps) {
        // Implement your HTTP request logic here and return the response

        try {
            String urlString = "http://localhost:8100/";
            if (title != null) {
                urlString += "?=" + title;
            }
            URL url = new URI(urlString).toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            conn.setDoOutput(true);

            RecipeManager rm = new RecipeManager();
            if (method.equals("PUT")) {
                String[] strings = RecipeManager.insertRecipe(title, ingredients, steps);
                return strings;
            }
            else if (method.equals("GET")) {
                RecipeManager.loadRecipes();
            }
            else if (method.equals("DELETE")) {
                System.out.println("HI THIS IS IN MODEL.JAVA");
                RecipeManager.deleteRecipe(title);
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}