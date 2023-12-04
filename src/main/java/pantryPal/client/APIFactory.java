package pantryPal.client;
public class APIFactory {
    public static IAPI createAPI(String apiName) {
        switch (apiName) {
            case "ChatGPT":
                return new ChatGPT();
            case "Whisper":
                return new Whisper();
            case "DallE":
                return new DallE();
            case "MockChatGPT": 
                return new MockChatGPT();
            case "MockWhisper":
                return new MockWhisper();
            case "MockDallE":
                return new MockDallE();
            default:
                return null;
        }
    }
}