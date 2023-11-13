package pantryPal;
import java.io.File;

public interface TranscriptionService {
    String transcribe(File audioFile);
}