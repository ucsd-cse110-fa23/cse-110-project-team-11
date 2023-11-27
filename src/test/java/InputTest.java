import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import pantryPal.client.TranscriptionService;

import java.io.File;

public class InputTest {

    // Mock implementation of TranscriptionService
    private static class MockTranscriptionService implements TranscriptionService {
        @Override
        public String transcribe(File audioFile) {
            return "Dinner";
        }
    }

    /**
     * Basic idea of "hard coding the response"
     */
    @Test 
    public void FakeResponse() {
        MockTranscriptionService mockService = new MockTranscriptionService();
        
        File audioFile = new File("input.wav");
        String output = mockService.transcribe(audioFile);
        assertEquals("Dinner", output, "The transcribe method should return 'Dinner'");
    }
}
