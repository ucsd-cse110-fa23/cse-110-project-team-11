package pantryPal.client;

import java.io.IOException;
import java.net.URISyntaxException;

public class MockImageGenerator implements IImageGenerator{
    //DallE img = new DallE();
    MockDallE img = new MockDallE();

    public String generateImage(String title, String ingredients) throws IOException, InterruptedException, URISyntaxException{

        String prompt = "Display the dish: " + title + ", a dish with the ingredients: " + ingredients + ", like it is a dish in a Recipe Book";

        return img.callAPI(prompt);
    }
}


