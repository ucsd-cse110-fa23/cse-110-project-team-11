import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;

import pantryPal.client.API.APIFactory;
import pantryPal.client.API.IAPI;
import pantryPal.client.API.MockWhisper;

public class InputTest extends APIFactory {
    /**
     * Basic idea of "hard coding the response"
     * @throws URISyntaxException
     * @throws InterruptedException
     * @throws IOException
     */
    @Test 
    public void FakeResponse() throws IOException, InterruptedException, URISyntaxException {        
        IAPI output = APIFactory.createAPI("MockWhisper");
        assertEquals("Dinner", output.callAPI("stop"), "The transcribe method should return 'Dinner'");
    }
}
