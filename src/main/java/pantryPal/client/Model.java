package pantryPal.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URI;

public class Model {
    public String performRequest (String method, String id, String title, String ingredients, String steps) {
        try {
            String urlString = "http://localhost:8100/";
            if (id != null) {
                urlString += "?id=" + id;
            }
            URL url = new URI(urlString).toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            conn.setDoOutput(true);

            // insert/save
            if (method.equals("PUT")) {
                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                out.write(title + ";" + ingredients + ";" + steps);
                out.flush();
                out.close();
            }

            // Sending request to the server
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String response = in.readLine(); // reading response from the server
            in.close();
            
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}