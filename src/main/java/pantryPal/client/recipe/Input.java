package pantryPal.client.recipe;
import java.io.*;
import java.net.*;
import org.json.*;
import javax.sound.sampled.*;


public class Input {
    private  final String API_ENDPOINT = "https://api.openai.com/v1/audio/transcriptions";
    private  final String TOKEN = "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";
    private  final String MODEL = "whisper-1";

    private  AudioFormat format = new AudioFormat(8000.0F,
                                16,
                                1,
                                true,
                                false);

    private  TargetDataLine mic; 
    private  Thread thread;
    private  File audioFile = new File("Input.wav");

    public  AudioFormat getAudioFormat() {
        return format;
    }

    private String promptType = "MealType";
    
    public  void captureAudio(){
        try {
            DataLine.Info line = new DataLine.Info(
                                TargetDataLine.class,
                                format);

            mic = (TargetDataLine) AudioSystem.getLine(line);

            if(!AudioSystem.isLineSupported(line)){
                System.err.println("Line not supported");
                return;
            }
            
            mic.open(format);
            mic.start();
            
            thread = new Thread (() -> {
                try (AudioInputStream a = new AudioInputStream(mic)){
                    AudioSystem.write(a, AudioFileFormat.Type.WAVE, audioFile);


                }
                catch (Exception e){
                    e.printStackTrace();
                }
            });
            thread.start();

            
    
            
        } catch(Exception e){
            e.printStackTrace();
            System.exit(0);
        }
        
    }

    public boolean stopCapture(String inputType){
        if (mic != null){
            mic.stop();
            mic.close();

            if(inputType.equals("MealType")){
                try {

                    thread.join();
                    String transcription = whisper();
                    String type = typeParser(transcription);
                    if(type.equals("Invalid")){
                        return false;
                    }
                    else{
                        try {
                            File file = new File("prompt.txt");
                            file.createNewFile();
                            BufferedWriter br = new BufferedWriter(new FileWriter(file));
                            br.write(type);
                            br.write("\n");
                            br.close();
                            
                        } catch(Exception e) {
                            System.out.println("File not found");
                        }
                        return true;
                    }


                } catch(Exception e) {
                    e.printStackTrace();
                }

            }
            else if (inputType.equals("Ingredients")){
                try {
                    thread.join();
                    String transcription = whisper();
                    try {
                        File file = new File("prompt.txt");
                        file.createNewFile();
                        BufferedWriter br = new BufferedWriter(new FileWriter(file, true));
                        br.write(transcription);
                        br.close();
                        
                    } catch(Exception e) {
                        System.out.println("File not found");
                    }
                    return true;
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
            
        
        }
        return false;
    }


    public TargetDataLine getMic(){
        return mic;
    }

    public void setPromptType(String promptType){
        this.promptType = promptType;
    }

    public String getPromptType(){
        return this.promptType;
    }

    private  String typeParser(String input) {

        input = input.toLowerCase();

        int count = 0;

        String[] meals = {"breakfast","lunch","dinner"};

        String meal = "";

        for(int i = 0; i  < meals.length; i++){

            if(input.contains(meals[i])){
                count++;
                meal = meals[i];
            }
        }

        if(count != 1){
            return "Invalid";
        }
        else{
            return meal;
        }
    }

    private  String whisper() throws IOException, URISyntaxException {
        // Create file object from file path
        File file = new File("input.wav");


        // Set up HTTP connection
        URL url = new URI(API_ENDPOINT).toURL();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);


        // Set up request headers
        String boundary = "Boundary-" + System.currentTimeMillis();
        connection.setRequestProperty(
            "Content-Type",
            "multipart/form-data; boundary=" + boundary
        );
        connection.setRequestProperty("Authorization", "Bearer " + TOKEN);


        // Set up output stream to write request body
        OutputStream outputStream = connection.getOutputStream();


        // Write model parameter to request body
        writeParameterToOutputStream(outputStream, "model", MODEL, boundary);


        // Write file parameter to request body
        writeFileToOutputStream(outputStream, file, boundary);


        // Write closing boundary to request body
        outputStream.write(("\r\n--" + boundary + "--\r\n").getBytes());


        // Flush and close output stream
        outputStream.flush();
        outputStream.close();


        // Get response code
        int responseCode = connection.getResponseCode();

        String generatedText = "";

        // Check response code and handle response accordingly
        if (responseCode == HttpURLConnection.HTTP_OK) {
            generatedText = handleSuccessResponse(connection);
        } else {
            handleErrorResponse(connection);
        }


        // Disconnect connection
        connection.disconnect();

        return generatedText;
    }

    // Helper method to write a parameter to the output stream in multipart form data format
    private  void writeParameterToOutputStream(
        OutputStream outputStream,
        String parameterName,
        String parameterValue,
        String boundary
        ) throws IOException {
        outputStream.write(("--" + boundary + "\r\n").getBytes());
        outputStream.write(
        (
            "Content-Disposition: form-data; name=\"" + parameterName + "\"\r\n\r\n"
        ).getBytes()
        );
        outputStream.write((parameterValue + "\r\n").getBytes());
    }
    
    
    // Helper method to write a file to the output stream in multipart form data format
    private  void writeFileToOutputStream(
        OutputStream outputStream,
        File file,
        String boundary
        ) throws IOException {

        outputStream.write(("--" + boundary + "\r\n").getBytes());
        outputStream.write(
        (
        "Content-Disposition: form-data; name=\"file\"; filename=\"" +
        file.getName() +
        "\"\r\n"
        ).getBytes()
            );
        outputStream.write(("Content-Type: audio/mpeg\r\n\r\n").getBytes());
        
        
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        fileInputStream.close();
    }
    
    // Helper method to handle a successful response
    private  String handleSuccessResponse(HttpURLConnection connection)
    throws IOException, JSONException {
        BufferedReader in = new BufferedReader(
            new InputStreamReader(connection.getInputStream())
        );
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();


        JSONObject responseJson = new JSONObject(response.toString());


        String generatedText = responseJson.getString("text");


        // Print the transcription result
        System.out.println("Transcription Result: " + generatedText);

        
        return generatedText;
    }

    // Helper method to handle an error response
    private  void handleErrorResponse(HttpURLConnection connection)
    throws IOException, JSONException {
        BufferedReader errorReader = new BufferedReader(
            new InputStreamReader(connection.getErrorStream())
        );
        String errorLine;
        StringBuilder errorResponse = new StringBuilder();
        while ((errorLine = errorReader.readLine()) != null) {
            errorResponse.append(errorLine);
        }
        errorReader.close();
        String errorResult = errorResponse.toString();
        System.out.println("Error Result: " + errorResult);
    }

    public void main(String[] args) throws InterruptedException {
        System.out.println("Recording");
        captureAudio();

        Thread.sleep(5000);

        System.out.println("Stopped");
        boolean t = stopCapture("MealType");

        if(t){
            System.out.println("Recording");
            captureAudio();
            Thread.sleep(10000);
            System.out.println("Stopped");
            stopCapture("Ingredients");
        }
        else{
            System.out.println("Failed");
        }
    }
}