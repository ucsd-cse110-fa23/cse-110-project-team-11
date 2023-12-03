package pantryPal.client;

import javafx.concurrent.Task;
import java.net.ConnectException;

public class imageTask extends Task<String>{
    private String imagePrompt;
    private Model model;
    
    public imageTask(String prompt, Model model){
        this.model = model;
        this.imagePrompt = prompt;
    }

    @Override
    public String call() throws ConnectException{
        String imgURL = model.performRequest(imagePrompt, "DallE");
        return imgURL;

    }
    
}
