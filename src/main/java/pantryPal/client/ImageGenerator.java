package pantryPal.client;

import java.io.IOException;
import java.net.URISyntaxException;

public class ImageGenerator {
    DallE img = new DallE();

    public void generateImage(String title, String ingredients) throws IOException, InterruptedException, URISyntaxException{

        String prompt = "Display the dish: " + title + ", a dish with the ingredients: " + ingredients + ", like it is a dish in a Recipe Book";

        img.callAPI(prompt);
    }
}
