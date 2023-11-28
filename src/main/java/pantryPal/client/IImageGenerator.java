package pantryPal.client;
import java.io.IOException;
import java.net.URISyntaxException;

public interface IImageGenerator {
    public String generateImage(String title, String ingredients) throws IOException, InterruptedException, URISyntaxException;
}