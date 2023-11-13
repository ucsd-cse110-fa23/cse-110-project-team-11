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

            if (method.equals("PUT")) {
                String[] strings = RecipeManager.insertRecipe(title, ingredients, steps);
                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                out.write("insert: " + title + ", " + ingredients + ", " + steps + ", " + id);
                out.flush();
                out.close();
                return strings;
            }
            else if (method.equals("GET")) {
                RecipeManager.loadRecipes();
            }
            else if (method.equals("DELETE")) {
                System.out.println("HI THIS IS IN MODEL.JAVA");
                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                out.write("to delete: " + title + ", " + ingredients + ", " + steps + ", " + id);
                out.flush();
                out.close();
                RecipeManager.deleteRecipeByID(id);
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}