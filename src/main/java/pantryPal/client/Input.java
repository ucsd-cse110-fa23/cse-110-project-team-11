package pantryPal.client;
import java.io.*;
import java.net.*;
import org.json.*;
import javax.sound.sampled.*;
import pantryPal.client.Whisper;

public class Input {
    private  AudioFormat format = new AudioFormat(8000.0F,
                                16,
                                1,
                                true,
                                false);

    private  TargetDataLine mic; 
    private  Thread thread;
    private  File audioFile = new File("Input.wav");

    private String type = "";
    private String transcription = "";

    public  AudioFormat getAudioFormat() {
        return format;
    }

    private String promptType = "MealType";
    
    public void captureAudio(){
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
                    transcription = Whisper.callAPI();
                    type = typeParser(transcription);
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
                    transcription = Whisper.callAPI();
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

    public String getTranscription(){
        return transcription;
    }

    public String getMealType(){
        return type;
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
            return meal.substring(0, 1).toUpperCase() + meal.substring(1);
        }
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