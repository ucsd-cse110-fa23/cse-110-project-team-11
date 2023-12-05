import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;

import pantryPal.client.API.APIFactory;
import pantryPal.client.API.IAPI;
import pantryPal.client.API.MockWhisper;
import pantryPal.client.Controller.Input;

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

    @Test
    public void testTypeParser() {
        Input in = new Input();
        assertEquals(in.typeParser("breakfast"),"Breakfast");
        assertEquals(in.typeParser("Breakfast"),"Breakfast");
        assertEquals(in.typeParser("dinner breakfast"),"Invalid");
        assertEquals(in.typeParser("dinner dinner"),"Dinner");
        assertEquals(in.typeParser("asdfjasoidfasdf dinner asdfioas"),"Dinner");
        assertEquals(in.typeParser("asdfjasoidfasdf asdfioas"),"Invalid");
    }
}
