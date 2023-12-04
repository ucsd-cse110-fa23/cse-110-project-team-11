package pantryPal.client.API;

import java.io.IOException;
import java.net.URISyntaxException;


public interface IAPI {
    public String callAPI(String prompt) throws IOException, InterruptedException, URISyntaxException; 
}