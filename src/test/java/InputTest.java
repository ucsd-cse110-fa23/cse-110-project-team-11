import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;

import pantryPal.client.APIFactory;
import pantryPal.client.IAPI;
import pantryPal.client.MockWhisper;

public class InputTest extends APIFactory {
    /**
     * Basic idea of "hard coding the response"
     * @throws URISyntaxException
     * @throws InterruptedException
     * @throws IOException
     */
    @Test 
    public void FakeResponse() throws IOException, InterruptedException, URISyntaxException {        
        IAPI output = createAPI("Whisper");
        assertEquals("Dinner", output.callAPI("input.wav"), "The transcribe method should return 'Dinner'");
    }
}
