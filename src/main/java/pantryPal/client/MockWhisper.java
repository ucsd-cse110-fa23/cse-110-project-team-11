package pantryPal.client;

public class MockWhisper implements IAPI {
    private String responses[] = {"Dinner", "valid"};
    private int index = 0;
    public String callAPI(String prompt) {

        if (prompt.equals("stop")) {
            return responses[(index++)%2];
        }
        return null;

    }
}
