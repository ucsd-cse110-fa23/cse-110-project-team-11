package pantryPal.client.API;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;


public class DallE implements IAPI {


 // TODO: Set the URL of the API Endpoint
 private static final String API_ENDPOINT = "https://api.openai.com/v1/images/generations"; 
 private static final String API_KEY =
   "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";
 private static final String MODEL = "dall-e-2";


  public String callAPI(String prompt) throws IOException, InterruptedException, URISyntaxException {
                // Set request parameters
            int n = 1;

            // Create a request body which you will pass into request object
            JSONObject requestBody = new JSONObject();
            requestBody.put("model", MODEL);
            requestBody.put("prompt", prompt);
            requestBody.put("n", n);
            requestBody.put("size", "256x256");


            // Create the HTTP client
            HttpClient client = HttpClient.newHttpClient();


            // Create the request object
            HttpRequest request = HttpRequest
              .newBuilder()
              .uri(URI.create(API_ENDPOINT))
              .header("Content-Type", "application/json")
              .header("Authorization", String.format("Bearer %s", API_KEY))
              .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
              .build();


            // Send the request and receive the response
            HttpResponse<String> response = client.send(
              request,
              HttpResponse.BodyHandlers.ofString()
            );


            // Process the response
            String responseBody = response.body();
            JSONObject responseJson = new JSONObject(responseBody);
            
            JSONArray data = responseJson.getJSONArray("data");
            String generatedImageURL = data.getJSONObject(0).getString("url");
            
            System.out.println("DALL-E Response:" + generatedImageURL);

            // Download the Generated Image to Current Directory
            try(
                InputStream in = new URI(generatedImageURL).toURL().openStream()
            )
            {
              return generatedImageURL;
            }
          }
  


}
