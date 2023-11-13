package pantryPal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import pantryPal.View.RecipeTitle;

import java.net.URI;


public class Model {
    public String[] performRequest(String method, String title, String ingredients, String steps, String id) {
        // Implement your HTTP request logic here and return the response

        try {
            String urlString = "http://localhost:8100/";
            if (id != null) {
                urlString += "?=" + id;
            }
            URL url = new URI(urlString).toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            conn.setDoOutput(true);

            RecipeManager rm = new RecipeManager();
            if (method.equals("PUT")) {
                String[] strings = RecipeManager.insertRecipe(title, ingredients, steps);
                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                out.write(title + ", " + ingredients + ", " + steps + ", " + id);
                out.flush();
                out.close();
                return strings;
            }
            else if (method.equals("GET")) {
                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                out.write("LOADING RECIPES");
                out.flush();
                out.close();
                RecipeManager.loadRecipes();
            }
            else if (method.equals("DELETE")) {
                RecipeManager.loadRecipes();
                RecipeManager.deleteRecipeByID(id);
                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                out.write("DELETING RECIPES");
                out.flush();
                out.close();
                
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}