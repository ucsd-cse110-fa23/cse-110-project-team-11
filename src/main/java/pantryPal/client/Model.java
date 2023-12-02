package pantryPal.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URI;
import java.util.Base64;

public class Model {
    public String performRequest(String method,String username, String password) throws ConnectException {
        try {
            String urlString = "http://localhost:8100/";
            if (username != null) {
                urlString += "?user=" + username;
            }
            URL url = new URI(urlString).toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            conn.setDoOutput(true);

            // insert/save
            if (method.equals("PUT")) {
                conn.setDoOutput(true);
                conn.setRequestMethod("PUT");

                String encodedRequest = Base64.getEncoder().encodeToString("createAcc".getBytes());
                String encodedUsername = Base64.getEncoder().encodeToString(username.getBytes());
                String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes());
                
                // Send data in the request body
                try (OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream())) {
                    out.write(encodedRequest + ";" + encodedUsername + ";" + encodedPassword);
                    out.flush();
                }
                
            }            
            
            // Sending request to the server
            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String response = in.readLine(); // reading response from the server
                System.out.println("RESPONSE: "+ response);
                return response;
            }
            

        }   
        catch (ConnectException err) {
            throw new ConnectException("Server Error");
        

        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error handling PUT request";
        }
    }
    public String performRequest (String method, String mealType, String id, String title, String ingredients, String steps, String imgURL) throws ConnectException{
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
                // System.out.println("PUT REQUEST (MODEL)");
                // encode recipe details to allow to write \n characters
                String encodedRequest = Base64.getEncoder().encodeToString("recipe".getBytes());
                String encodedTitle = Base64.getEncoder().encodeToString(title.getBytes());
                String encodedIngredients = Base64.getEncoder().encodeToString(ingredients.getBytes());
                String encodedSteps = Base64.getEncoder().encodeToString(steps.getBytes());
                String encodedMealType = Base64.getEncoder().encodeToString(mealType.getBytes());
                String encodedUrl = Base64.getEncoder().encodeToString(imgURL.getBytes());
                

                
                // Send data in the request body
                try (OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream())) {
                    out.write(encodedRequest + ";" + encodedMealType + ";" + encodedTitle + ";" + encodedIngredients + ";" + encodedSteps + ";" + encodedUrl);
                    out.flush();
                }
            }

            // Sending request to the server
            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String response = in.readLine(); // reading response from the server
                System.out.println(response);
                return response;
            }

        } 
        catch (ConnectException err) {
            throw new ConnectException("Server Error");
        

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    // GET for API
    public String performRequest(String input, String API) throws ConnectException {
        
        try {
            
            String urlString = "http://localhost:8100/";
            String encodedInput = Base64.getEncoder().encodeToString(input.getBytes());
            if (API != null) {
                urlString += "?api=" + API + "&input=" + encodedInput;
            }    
            URL url = new URI(urlString).toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {

                String response = in.readLine(); // reading response from the server
                System.out.println("RESPONSE: "+ response);
                return response;
            }
            
        } 
        catch (ConnectException err) {
            throw new ConnectException("Server Error");
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    // public String performRequest(String method, boolean auto, String username) throws ConnectException{
    //     try {
    //         String urlString = "http://localhost:8100/";
    //         // if (id != null) {
    //         //     urlString += "?id=" + id;
    //         // }
    //         // URL url = new URI(urlString).toURL();
    //         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    //         conn.setRequestMethod(method);
    //         conn.setDoOutput(true);

    //         // insert/save
    //         if (method.equals("PUT")) {
    //             // System.out.println("PUT REQUEST (MODEL)");
    //             // encode recipe details to allow to write \n characters
    //             String encodedRequest = Base64.getEncoder().encodeToString("auto".getBytes());
    //             String encodedUsername = Base64.getEncoder().encodeToString(username.getBytes());
    //             String encodedAuto = Base64.getEncoder().encodeToString(auto.toString().getBytes());

                
    //             // Send data in the request body
    //             try (OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream())) {
    //                 out.write(encodedRequest + ";" +encodedUsername + ";" + encodedAuto);
    //                 out.flush();
    //             }
    //         }

    //         // Sending request to the server
    //         try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
    //             String response = in.readLine(); // reading response from the server
    //             System.out.println(response);
    //             return response;
    //         }

    //     } 
    //     catch (ConnectException err) {
    //         throw new ConnectException("Server Error");
        

    //     } catch (Exception ex) {
    //         ex.printStackTrace();
    //         return null;
    //     }
    // }
    
}